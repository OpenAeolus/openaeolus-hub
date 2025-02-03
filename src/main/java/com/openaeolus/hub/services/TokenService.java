package com.openaeolus.hub.services;

import com.openaeolus.hub.models.Token;
import com.openaeolus.hub.repositories.TokenRepository;
import com.openaeolus.hub.utils.AuthenticationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token generateToken(String subject) {
        LOGGER.debug("Generating token for subject {}", subject);
        Token token = new Token();
        token.setToken(AuthenticationUtils.generateToken(subject));
        return tokenRepository.save(token);
    }

    public boolean validateToken(String token) {
        LOGGER.debug("Validating token {}", token);
        Token t = tokenRepository.findByToken(token);
        return t != null && AuthenticationUtils.validateToken(t.getToken());
    }

    public void deleteToken(String token) {
        LOGGER.debug("Deleting token {}", token);
        Token findToken  = tokenRepository.findByToken(token);
        if(token != null){
            tokenRepository.delete(findToken);
        }
    }

}
