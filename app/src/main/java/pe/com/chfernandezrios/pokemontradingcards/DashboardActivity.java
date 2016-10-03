package pe.com.chfernandezrios.pokemontradingcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import pe.com.chfernandezrios.pokemontradingcards.beans.Pokemon;
import retrofit2.Call;

public class DashboardActivity extends AppCompatActivity {
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        /******** Views ********/
        Button butMisPokemones = (Button) findViewById(R.id.butMisPokemonesDashboard);
        Button butPokemonesDisponibles = (Button) findViewById(R.id.butPokemonesDisponiblesDashboard);

        // Cliente REST
        final IPokemonClient client = ServiceGenerator.createService(IPokemonClient.class);

        // Obtener el intent que condujo aquí
        Intent loginIntent = getIntent();

        // Obtener el id del usuario mandado en el intent
        id = loginIntent.getIntExtra("ID", 0);

        // Cuando se haga click en el botón Mis Pokemones
        butMisPokemones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Pokemon>> misPokemonesCall = client.obtenerMisPokemones(id);

                try {
                    List<Pokemon> misPokemones = misPokemonesCall.execute().body();

                    // Si la lista no es nula ni está vacía
                    if (misPokemones != null && misPokemones.size() == 0) {
                        Intent intent = new Intent();
                        intent.putExtra("ID", id);
                        intent.setClass(DashboardActivity.this, MispokemonesActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getBaseContext(), "Usted no tiene pokemones", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Cuando se haga click en el botón Pokemones Disponibles
        butPokemonesDisponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // UI en nuevo hilo
                new Thread() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Funcionalidad aún no implementada", Toast.LENGTH_SHORT).show();
                    }
                }.start();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("ID", id);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        id = savedInstanceState.getInt("ID");
    }
}
