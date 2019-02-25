package arquitecturasmoviles.basico.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import arquitecturasmoviles.basico.R;
import arquitecturasmoviles.basico.util.Preferencias;
import arquitecturasmoviles.basico.webservice.ApiService;
import arquitecturasmoviles.basico.webservice.dto.ResponseDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView txtViewRegistro;
    private Button btnIngresar;
    private EditText edtEmail;
    private EditText edtContrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtViewRegistro = (TextView) findViewById(R.id.txt_login_registro);
        btnIngresar = (Button) findViewById(R.id.btn_login_ingresar);
        edtEmail = (EditText) findViewById(R.id.edt_login_correo_electronico);
        edtContrasenia = (EditText) findViewById(R.id.edt_login_contrasenia);

        txtViewRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String contrasenia = edtContrasenia.getText().toString();

                View focusView = null;

                if(TextUtils.isEmpty(email)){
                    edtEmail.setError("Correo electrónico requerido");
                    edtEmail.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(contrasenia)){
                    edtContrasenia.setError("Contraseña requerida");
                    edtContrasenia.requestFocus();
                    return;
                }

                btnIngresar.setEnabled(false);

                login(email,contrasenia);
            }
        });

    }

    private void login(final String email, final String contrasenia) {
        ApiService.Factory.getInstance(this).login(email,contrasenia).enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if(response.body().isError()){
                    String mensaje = response.body().getMensaje();
                    Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_LONG).show();
                    btnIngresar.setEnabled(true);
                }else{
                    Preferencias preferencias = new Preferencias(LoginActivity.this);
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
                btnIngresar.setEnabled(true);
            }
        });
    }
}
