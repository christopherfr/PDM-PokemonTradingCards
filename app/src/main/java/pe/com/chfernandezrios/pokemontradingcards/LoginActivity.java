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
import pe.com.chfernandezrios.pokemontradingcards.beans.responses.LoginRequest;
import pe.com.chfernandezrios.pokemontradingcards.beans.responses.LoginResponse;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {
    private EditText eteUsuario;
    private EditText etePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /******** Views ********/
        eteUsuario = (EditText) findViewById(R.id.eteUsuarioLogin);
        etePassword = (EditText) findViewById(R.id.etePasswordLogin);
        Button butLogin = (Button) findViewById(R.id.butLoginLogin);
        Button butRegistro = (Button) findViewById(R.id.butRegistroLogin);

        // Cliente REST
        final IPokemonClient client = ServiceGenerator.createService(IPokemonClient.class);

        // Cuando se haga click en el botón Login
        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // UI en nuevo hilo
                new Thread() {
                    @Override
                    public void run() {
                        LoginRequest loginRequest = new LoginRequest(
                                eteUsuario.getText().toString(),
                                etePassword.getText().toString()
                        );
                        // Realizar la validación de credenciales ingresadas (Log In)
                        Call<LoginResponse> loginResponseCall = client.logIn(loginRequest);

                        try {
                            LoginResponse loginResponse = loginResponseCall.execute().body();

                            // Si el LoginResponse no tiene mensaje (msg) o el usuario no es nulo
                            if (loginResponse.getMsg().equals("") && loginResponse.getUsuario() != null) {
                                // Realizar pase al Dashboard mandando id obtenido
                                Intent intent = new Intent();
                                intent.putExtra("ID", loginResponse.getUsuario().getId());
                                intent.setClass(LoginActivity.this, DashboardActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getBaseContext(), "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        // Cuando se haga click en el botón Registro
        butRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Realizar pase a la pantalla de Registro
                Intent intent = new Intent();
                // Si el usuario ya había ingresado username, se manda en el intent
                if (!eteUsuario.getText().toString().equals("")) {
                    intent.putExtra("USERNAME", eteUsuario.getText().toString());
                    // Si el usuario ya había ingresado password junto al username, también se manda en el intent
                    if (!etePassword.getText().toString().equals("")) {
                        intent.putExtra("PASSWORD", etePassword.getText().toString());
                    }
                }
                intent.setClass(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("USERNAME", eteUsuario.getText().toString());
        outState.putString("PASSWORD", etePassword.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        eteUsuario.setText(savedInstanceState.getString("USERNAME"));
        etePassword.setText(savedInstanceState.getString("PASSWORD"));
    }
}
