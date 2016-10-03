package pe.com.chfernandezrios.pokemontradingcards.beans;

/**
 * Created by chfernandezrios on 2/10/2016.
 */
public class Usuario {
    private int id;
    private String user;
    private String username;
    private String password;

    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
