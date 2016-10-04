package pe.com.chfernandezrios.pokemontradingcards.beans.responses;

import pe.com.chfernandezrios.pokemontradingcards.beans.Usuario;

/**
 * Created by chfernandezrios on 3/10/2016.
 */
public class RegistroRequest {
    private Usuario usuario;

    public RegistroRequest(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
