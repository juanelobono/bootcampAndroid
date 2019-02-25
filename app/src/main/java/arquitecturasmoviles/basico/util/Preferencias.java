package arquitecturasmoviles.basico.util;


import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    public static final int LOGIN_OK = 1;
    public static final int LOGIN_ERROR = 2;
    public static final int REGISTRO_OK = 3;
    public static final int REGISTRO_ERROR = 4;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    public static final String EMAIL_USUARIO = "email_usuario";
    public static final String CONTRASENIA_USUARIO = "contrasenia_usuario";
    public static final String TOKEN = "token";

    public Preferencias(Context context) {
        this.context = context;
        pref = context.getSharedPreferences("configuraciones", PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setToken(String token){
        editor.putString(TOKEN, token).apply();
    }
    public String getToken(){
        return pref.getString(TOKEN,"");
    }

    public void setEmailUsuario(String emailUsuario){
        editor.putString(EMAIL_USUARIO, emailUsuario);
    }

    public String getEmailUsuario(){
        return pref.getString(EMAIL_USUARIO,"");
    }

    public void setContraseniaUsuario(String contraseniaUsuario){
        editor.putString(CONTRASENIA_USUARIO, contraseniaUsuario);
    }

    public String getContraseniaUsuario(){
        return pref.getString(CONTRASENIA_USUARIO,"");
    }
}
