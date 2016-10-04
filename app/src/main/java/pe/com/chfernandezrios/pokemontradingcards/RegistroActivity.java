package pe.com.chfernandezrios.pokemontradingcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import pe.com.chfernandezrios.pokemontradingcards.beans.Usuario;
import pe.com.chfernandezrios.pokemontradingcards.beans.responses.LoginResponse;
import pe.com.chfernandezrios.pokemontradingcards.beans.responses.RegistroRequest;
import pe.com.chfernandezrios.pokemontradingcards.beans.responses.StatusResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {
    EditText eteUsuario;
    EditText etePassword;
    EditText eteRPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        /******** Views ********/
        eteUsuario = (EditText) findViewById(R.id.eteUsuarioRegistro);
        etePassword = (EditText) findViewById(R.id.etePasswordRegistro);
        eteRPassword = (EditText) findViewById(R.id.eteRpasswordRegistro);
        Button butGuardar = (Button) findViewById(R.id.butGuardarRegistro);

        // Cliente REST
        final IPokemonClient client = ServiceGenerator.createService(IPokemonClient.class);

        // Obtener el intent que condujo aquí
        Intent loginIntent = getIntent();

        // Obtener las credenciales ya ingresadas en el login
        String username = loginIntent.getStringExtra("USERNAME");
        String password = loginIntent.getStringExtra("PASSWORD");

        // Llenar los EditText con los campos llenados en el Login
        eteUsuario.setText(username);
        etePassword.setText(password);

        // Cuando se haga click en el botón Guardar
        butGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // UI en nuevo hilo
                new Thread() {
                    @Override
                    public void run() {
                        // Si las contraseñas coinciden
                        if (etePassword.getText().toString().equals(eteRPassword.getText().toString())) {
                            // Realizar registro
                            RegistroRequest registroRequest = new RegistroRequest(
                                    new Usuario(
                                            eteUsuario.getText().toString(),
                                            etePassword.getText().toString()
                                    )
                            );

                            client.registro(registroRequest).enqueue(new Callback<StatusResponse>() {
                                @Override
                                public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                                    StatusResponse statusResponse = response.body();
                                    // Si el StatusResponse no muestra errores
                                    if (statusResponse.getCode() == 0 && statusResponse.getMsg() == null) {
                                        // Realizar pase al Login
                                        Intent intent = new Intent();
                                        intent.setClass(RegistroActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(getBaseContext(), "Registro correcto", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getBaseContext(), "Error en el registro", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<StatusResponse> call, Throwable t) {
                                    Toast.makeText(getBaseContext(), "Error en el registro", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else /* Si las contraseñas no coinciden */ {
                            Toast.makeText(getBaseContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.start();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("USERNAME", eteUsuario.getText().toString());
        outState.putString("PASSWORD", etePassword.getText().toString());
        outState.putString("RPASSWORD", eteRPassword.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        eteUsuario.setText(savedInstanceState.getString("USERNAME"));
        etePassword.setText(savedInstanceState.getString("PASSWORD"));
        eteRPassword.setText(savedInstanceState.getString("RPASSWORD"));
    }
}
