package fi.tuni.bloggingapp.security;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TokenService implements TokenRepository {
    List<Token> tokens;

    public TokenService(){
        tokens = new ArrayList<Token>();
    }

    @Override
    public List<Token> findAll() {
        return null;
    }

    @Override
    public boolean contains(String a) {
        for(Token b : tokens){
            if(b.equals(a))
                b.refresh();
                return true;
        }
        return false;
    }

    @Override
    public boolean containsAdmin(String a) {
        for(Token b : tokens){
            if(b.equals(a) && b.getType() == UserType.admin)
                b.refresh();
            return true;
        }
        return false;
    }

    @Override
    public String addNewToken(UserType type) {
        Token newToken = new Token();
        newToken.setToken(getSaltString(), type);
        tokens.add(newToken);
        return newToken.getToken();

    }

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
