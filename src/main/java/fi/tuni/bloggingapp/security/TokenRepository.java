package fi.tuni.bloggingapp.security;

import java.util.List;




public interface TokenRepository {

    public List<Token> findAll();

    public boolean contains(String token);

    public String addNewToken(UserType type);

}
