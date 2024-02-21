package org.example.panacheauth.repos;

import org.example.panacheauth.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token save(Token token);

    Optional<Token> findByTokenValueAndDeletedEquals(String value, boolean isDeleted);

    Optional<Token> findByTokenValueAndDeletedEqualsAndExpiresAtGreaterThan(String value, boolean isDeleted, Date expiryGreaterThan);
}