package ve.com.bivfrostgroup.demobluetoothfull.controladores.peticion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ve.com.bivfrostgroup.demobluetoothfull.adapter.retrofit;
import ve.com.bivfrostgroup.demobluetoothfull.interfaz.model.DefinicionRespuestaRetrofit;

/**
 * Diciembre 2023
 *
 * @author Delgado Carlos
 */
public class PeticionRetrofit {


    final static String TAG = "UltimoVoucher ";
    String _jwt = "";
    retrofit PeticionRetrofit = new retrofit();
    int code = 0;
    boolean status = false;

    public void PeticionServidor(String JWT, JsonObject body, Context context,OnResponse callback) {

        Activity activity = (Activity) context;

        /*----------------------------------------------------------------------------------------*/
        Call callUser = PeticionRetrofit.PeticionRetrofit().RespuestaServidor(JWT, body);
        callUser.enqueue(new Callback<DefinicionRespuestaRetrofit>() {
            @Override
            public void onResponse(Call<DefinicionRespuestaRetrofit> call, Response<DefinicionRespuestaRetrofit> response) {

                code = response.code();

                if (response.isSuccessful()) {

                    if (response.body() != null) {


                        callback.Response(response.body());

                    }


                }
                else {}
            }

            @Override
            public void onFailure(Call<DefinicionRespuestaRetrofit> call, Throwable t) {



            }

        });


    }


}
