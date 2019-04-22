package fi.tuni.bloggingapp.security;

public class Token {
    private String token;
    private UserType type;
    private long refreshed;

    public Token(){

    }

    public String getToken(){
        return token;
    }

    public void setToken(String token, UserType type){
        this.token = token;
        this.setType(type);
        refresh();
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

    public void refresh(){
        refreshed = System.currentTimeMillis();
    }

    public long getLatestRefresh(){
        return refreshed;
    }


}
