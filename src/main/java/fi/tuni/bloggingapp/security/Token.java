package fi.tuni.bloggingapp.security;

public class Token {
    private String token;
    private UserType type;

    public Token(){

    }

    public String getToken(){
        return token;
    }

    public void setToken(String token, UserType type){
        this.token = token;
    }

    public boolean equals(String requested) {
        if(token.equals(requested))
            return true;

        return false;
    }

    public UserType getType(){
        return type;
    }

    public void setType(UserType type){
        this.type = type;
    }

}
