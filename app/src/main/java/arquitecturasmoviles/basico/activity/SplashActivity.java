package arquitecturasmoviles.basico.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import arquitecturasmoviles.basico.util.Preferencias;


public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Preferencias preferencias = new Preferencias(SplashActivity.this);
        String token = preferencias.getToken();
        if(token.equals("")){
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }else{
            startActivity(new Intent(SplashActivity.this, EventosActivity.class));
        }
        finish();
    }
}
