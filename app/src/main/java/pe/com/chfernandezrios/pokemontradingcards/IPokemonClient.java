package pe.com.chfernandezrios.pokemontradingcards;

import java.util.List;

import pe.com.chfernandezrios.pokemontradingcards.beans.Pokemon;
import pe.com.chfernandezrios.pokemontradingcards.beans.responses.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by chfernandezrios on 2/10/2016.
 */
public interface IPokemonClient {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @POST("usuarios/login")
    Call<LoginResponse> logIn(@Body LoginRequest loginRequest);

    @POST("usuarios")
    Call<StatusResponse> registro(@Body RegistroRequest registroRequest);

    @GET("usuarios/{id_usuario}/pokemones")
    Call<List<Pokemon>> obtenerMisPokemones(@Path("id_usuario") int id);

}
