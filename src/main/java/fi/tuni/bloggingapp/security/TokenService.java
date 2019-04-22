package fi.tuni.bloggingapp.security;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Service for handling tokens. Keeps a list of valid tokens and removes unused token periodically.
 * Tokens are only saved to memory, and not in a permanent store.
 *
 * @author Joonas Salojarvi
 * @version 2019.04.22
 * @since 0.1
 */
@Service
public class TokenService implements TokenRepository {

    /**
     * All valid tokens.
     */
    List<Token> tokens;

    public TokenService(){
        tokens = new ArrayList<Token>();
    }

    @Override
    public List<Token> findAll() {
        return null;
    }

    /**
     * Checks if requested token is found in tokens list. If found, return boolean and refreshes the found token.
     * @param a Token to search for
     * @return boolean
     */
    @Override
    public boolean contains(String a) {
        for(Token b : tokens){
            if(b.equals(a))
                b.refresh();
                return true;
        }
        return false;
    }

    /**
     * Checks if the token is valid and has admin rights.
     * @param a Token
     * @return boolean
     */
    @Override
    public boolean containsAdmin(String a) {
        for(Token b : tokens){
            if(b.equals(a) && b.getType() == UserType.admin)
                b.refresh();
            return true;
        }
        return false;
    }

    /**
     * Add new token to token store and return is as a string.
     * @param type UserType
     * @return New token
     */
    @Override
    public String addNewToken(UserType type) {
        Token newToken = new Token();
        newToken.setToken(getSaltString(), type);
        tokens.add(newToken);
        return newToken.getToken();

    }

    /**
     * Generate new token
     * @return Token
     */
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    /**
     * Delete old tokens. Called very 5 minutes, deletes token that are older than 5 minutes.
     */
    @Scheduled(fixedRate = (1000 * 60 * 5))
    public void deleteOldTokens() {
        tokens.removeIf((token -> {
            if(token.getLatestRefresh() <= System.currentTimeMillis() - (1000 * 60 * 5)){
                System.out.println("Removed token: " + token.getToken());
                return true;
            } else
                return false;
        }));
    }


}
