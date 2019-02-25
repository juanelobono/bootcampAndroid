package arquitecturasmoviles.basico.webservice;

import android.content.Context;

import arquitecturasmoviles.basico.webservice.dto.ResponseDTO;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {


    @FormUrlEncoded
    @POST("usuarios/login")
    public Call<ResponseDTO> login(@Field("email") String email, @Field("contrasenia") String contrasenia);

    @FormUrlEncoded
    @POST("usuarios/nuevo")
    public Call<ResponseDTO> registro(@Field("nombre") String nombre,
                               @Field("apellido") String apellido,
                               @Field("email") String email,
                               @Field("contrasenia") String contrasenia);


    @GET("eventos/todos")
    public Call<ResponseDTO> getEventos();

    @GET("cursos/todos")
    public Call<ResponseDTO>  getCursos();


    public class Factory {
        private static ApiService apiService;

        public static ApiService getInstance(Context context) {
            if (apiService == null) {

                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


                final String BASE_URL = "http://testing.nexoserver.com.ar/bootcampmobile/";

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                return apiService = retrofit.create(ApiService.class);
            } else {
                return apiService;
            }
        }
    }
}

