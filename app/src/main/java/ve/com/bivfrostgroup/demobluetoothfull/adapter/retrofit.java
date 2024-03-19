package ve.com.bivfrostgroup.demobluetoothfull.adapter;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ve.com.bivfrostgroup.demobluetoothfull.interfaz.config.datosUniversales;
import ve.com.bivfrostgroup.demobluetoothfull.interfaz.model.DefinicionRespuestaRetrofit;
import ve.com.bivfrostgroup.demobluetoothfull.interfaz.peticion.interfazPeticion;

public class retrofit {


    public interfazPeticion PeticionRetrofit() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        final String LOGS="";  //= BuildConfig.BUILD_TYPE;
        if (LOGS.equalsIgnoreCase("release")==false) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        //******************************************************************
       // OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        OkHttpClient okHttpClient = new OkHttpClient();
        //es lo nuevo, sin seguridad
        OkHttpClient.Builder httpClient = okHttpClient
                .newBuilder()
                .addInterceptor(interceptor);

        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.connectTimeout(60, TimeUnit.SECONDS );

        //***************************************************************************
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(datosUniversales.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()) // <-- set log leve
                .build();


        interfazPeticion userService = retrofit.create(interfazPeticion.class);

        return userService;


    }


}

