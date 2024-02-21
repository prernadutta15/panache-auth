package org.example.panacheauth.service;

import org.example.panacheauth.models.Token;
import org.example.panacheauth.models.User;
import org.example.panacheauth.repos.TokenRepository;
import org.example.panacheauth.repos.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    public User signUp(String fullName,
                       String email,
                       String password) {
        /**
         * Here we will store the details of user in the db. However, for password
         * we will hash it using BCrypt as discussed.
         */

        User u = new User();
        u.setEmail(email);
        u.setName(fullName);
        u.setHashedPassword(bCryptPasswordEncoder.encode(password));
        //better to add check for user by email before storing
        User user = userRepository.save(u);
        return user;
    }

    public Token login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            // throw user not exists exception
            return null;
        }

        User user = userOptional.get();
        /**
         * The following commented code will never work because bcrypt generates new hash everytime,
         * the only way is to match it.
         */
//        String hashedPassword = bCryptPasswordEncoder.encode(password);
//        if(!hashedPassword.equals(user.getHashedPassword()))
//            return null;
        if (!bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {
            // throw password not matching exception
            return null;
        }

        Token token = getToken(user);

        // TODO 1: Change the above token to a JWT Token

        Token savedToken = tokenRepository.save(token);

        return savedToken;
    }

    private static Token getToken(User user) {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plus(30, ChronoUnit.DAYS);

        // Convert LocalDate to Date
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Token token = new Token();
        token.setUser(user);
        token.setExpiresAt(expiryDate);
        token.setTokenValue(RandomStringUtils.randomAlphanumeric(128));
        return token;
    }

    public void logout(String token) {
        /**
         * Here we soft delete it by modifying isDeleted
         */
        Optional<Token> token1 = tokenRepository.findByTokenValueAndDeletedEquals(token, false);

        if (token1.isEmpty()) {
            // throw TokenNotExistsOrAlreadyExpiredException();
            return;
        }
        Token tkn = token1.get();
        tkn.setDeleted(true);
        tokenRepository.save(tkn);
        return;

    }

    public User validateToken(String token) {
        Optional<Token> tkn = tokenRepository.
                findByTokenValueAndDeletedEqualsAndExpiresAtGreaterThan(token, false, new Date());

        if (tkn.isEmpty()) {
            return null;
        }

        // TODO 2: Instead of validating via the DB, as the token is now a JWT
        // token, validate using JWT

        return tkn.get().getUser();
    }
}
