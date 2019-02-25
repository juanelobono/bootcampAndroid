package arquitecturasmoviles.basico.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import arquitecturasmoviles.basico.R;
import arquitecturasmoviles.basico.util.Preferencias;
import arquitecturasmoviles.basico.webservice.ApiService;
import arquitecturasmoviles.basico.webservice.dto.ResponseDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtApellido;
    private EditText edtEmail;
    private EditText edtContrasenia;
    private EditText edtConfirmacionContrasenia;
    private Button btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtNombre = (EditText) findViewById(R.id.edt_registro_nombre);
        edtApellido = (EditText) findViewById(R.id.edt_registro_apellido);
        edtEmail = (EditText) findViewById(R.id.edt_registro_correo_electronico);
        edtContrasenia = (EditText) findViewById(R.id.edt_registro_contrasenia);
        edtConfirmacionContrasenia = (EditText) findViewById(R.id.edt_registro_repita_contrasenia);
        btnRegistrarse = (Button) findViewById(R.id.btn_registro_registrarse);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = edtNombre.getText().toString();
                String apellido = edtApellido.getText().toString();
                String email = edtEmail.getText().toString();
                String contrasenia = edtContrasenia.getText().toString();
                String confirmacionContrasenia = edtConfirmacionContrasenia.getText().toString();

                if(inputsValidos(nombre, apellido, email, contrasenia, confirmacionContrasenia)){
                    btnRegistrarse.setEnabled(false);
                    registrarse(nombre,apellido,email,contrasenia);
                }
            }
        });

    }

    private void registrarse(String nombre, String apellido, final String email, final String contrasenia) {
        ApiService.Factory.getInstance(this).registro(nombre,apellido,email,contrasenia).enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                boolean error = response.body().isError();
                String mensaje = response.body().getMensaje();
                if(error){
                    btnRegistrarse.setEnabled(true);
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                }else{
                    login(email,contrasenia);
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                btnRegistrarse.setEnabled(true);
            }
        });
    }

    private void login(final String email, final String contrasenia) {
        ApiService.Factory.getInstance(this).login(email,contrasenia).enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if(response.body().isError()){
                    Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                    intentLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intentLogin);
                }else{
                    Preferencias preferencias = new Preferencias(RegistroActivity.this);
                    preferencias.setEmailUsuario(email);
                    preferencias.setContraseniaUsuario(contrasenia);
                    preferencias.setToken(response.body().getToken());

                    Intent intent = new Intent(getApplicationContext(), EventosActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                btnRegistrarse.setEnabled(true);
            }
        });
    }

    private boolean inputsValidos(String nombre, String apellido, String email, String contrasenia, String confirmacionContrasenia){

        if(TextUtils.isEmpty(nombre)){
            edtNombre.requestFocus();
            edtNombre.setError("Nombre requerido");
            return false;
        }

        if(TextUtils.isEmpty(apellido)){
            edtApellido.requestFocus();
            edtApellido.setError("Apellido requerido");
            return false;
        }

        if(TextUtils.isEmpty(email)){
            edtEmail.requestFocus();
            edtEmail.setError("Correo electrónico requerido");
            return false;
        }

        if(TextUtils.isEmpty(contrasenia)){
            edtContrasenia.requestFocus();
            edtContrasenia.setError("Contraseña requerido");
            return false;
        }

        if(TextUtils.isEmpty(confirmacionContrasenia) || !confirmacionContrasenia.equals(contrasenia)){
            edtConfirmacionContrasenia.requestFocus();
            edtConfirmacionContrasenia.setError("Las contraseñas no coinciden");
            return false;
        }

        return true;
    }
}
