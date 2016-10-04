package pe.com.chfernandezrios.pokemontradingcards.beans.responses;

/**
 * Created by chfernandezrios on 3/10/2016.
 */
public class LoginRequest {
    private String user;
    private String password;

    public LoginRequest(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
