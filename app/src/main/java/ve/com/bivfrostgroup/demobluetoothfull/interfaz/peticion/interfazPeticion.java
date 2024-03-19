package ve.com.bivfrostgroup.demobluetoothfull.interfaz.peticion;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import ve.com.bivfrostgroup.demobluetoothfull.interfaz.config.datosUniversales;
import ve.com.bivfrostgroup.demobluetoothfull.interfaz.model.DefinicionRespuestaRetrofit;

public interface interfazPeticion {

    @Headers({"Content-Type: application/json", "Accept: application/json"})

    @POST( datosUniversales.URLPeticion)
    Call<DefinicionRespuestaRetrofit> RespuestaServidor(@Header("Authorization") String authHeader, @Body JsonObject body);

}


