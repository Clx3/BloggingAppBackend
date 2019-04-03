package fi.tuni.bloggingapp.controller;

import com.google.gson.JsonObject;
import fi.tuni.bloggingapp.entity.User;
import fi.tuni.bloggingapp.repository.UserRepository;
import fi.tuni.bloggingapp.security.TokenRepository;
import fi.tuni.bloggingapp.security.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
public class AuthController {

    @Autowired
    private TokenRepository tokens;

    @Autowired
    private UserRepository users;


    @RequestMapping("/auth")
    public String auth(@RequestBody User user) throws UserAuthenticationException{
        if(user != null) {
            User userFromDatabase = users.findByUsername(user.getUsername());

            if(userFromDatabase == null)
                throw new UserAuthenticationException("User " + user.getUsername() + " not found.");


            /* EBIN :-D */
            if(user.getPassword().contentEquals(userFromDatabase.getPassword())){
                JsonObject userWithToken = new JsonObject();
                userWithToken.addProperty("token", tokens.addNewToken(userFromDatabase.getIsAdmin()));
                userWithToken.addProperty("user", userFromDatabase.getUsername());
                if(userFromDatabase.getIsAdmin() == UserType.admin){
                    userWithToken.addProperty("admin", true);
                } else {
                    userWithToken.addProperty("admin", false);
                }

                return userWithToken.toString();
            }
            else{
                throw new UserAuthenticationException("Väärä salis vitun runkku");
            }

        }
        return null;
    }

    @RequestMapping("/tokenRefresh")
    public String test(@RequestHeader(value="Token") String token) throws InvalidTokenException{
        if(tokens.contains(token))
            return "authorized";
        else
            throw new InvalidTokenException("Väärä token vitun homo");
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    class UsernameAlreadyTakenException extends AuthenticationException {

        public UsernameAlreadyTakenException(String msg) {
            super(msg);
        }

    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    class UserAuthenticationException extends AuthenticationException {

        public UserAuthenticationException(String msg) {
            super(msg);
        }

    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    class InvalidTokenException extends AuthenticationException {

        public InvalidTokenException(String msg) {
            super(msg);
        }

    }

}