package fi.tuni.bloggingapp.security;

import java.util.List;




public interface TokenRepository {

    public List<Token> findAll();

    public boolean contains(String token);

    public boolean containsAdmin(String a);

    public String addNewToken(UserType type);

}
