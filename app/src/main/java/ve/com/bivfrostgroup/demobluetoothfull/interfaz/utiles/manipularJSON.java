package ve.com.bivfrostgroup.demobluetoothfull.interfaz.utiles;

import android.content.Context;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class manipularJSON {


    private static String TAG = "MANEJAR_JSON";
/*
    public void SaveJsonBasico(String params, JsonObject mJsonResponse, String PackageName, Context context) throws IOException {

        // Convert JsonObject to String Format
        String userString = mJsonResponse.toString();
        // Define the File Path and its Name
        File file = new File(context.getFilesDir(), params);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();

    }

    public Object ReadingJsonBasico(String params, String PackageName, Context context) {

        String ubicacionJSON = "/data/data/" + PackageName + "/files/" + params;

        Gson gson = new Gson();
        Object objetoPersonal = null;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(ubicacionJSON));
            if( buffer!=null    ) {
                objetoPersonal = gson.fromJson(buffer, body_json.class);
            }
            //  System.out.println(objetoPersonal);

        } catch (IOException e) {
            e.printStackTrace();

        }
        return objetoPersonal;

    }

    public void Save_body_cell(String params, JsonObject mJsonResponse, String PackageName, Context context) throws JSONException, IOException {

        //Log.d(TAG, "Save_body_cell() called with: params = [" + params + "], mJsonResponse = [" + mJsonResponse + "], PackageName = [" + PackageName + "], context = [" + context + "]");
        // Convert JsonObject to String Format
        String userString = mJsonResponse.toString();
        // Define the File Path and its Name
        File file = new File(context.getFilesDir(), params);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();


    }

    public void Save_body(String params, JsonObject mJsonResponse, String PackageName, Context context) throws JSONException, IOException {
        //Log.d(TAG, "Save_body() called with: params = [" + params + "], mJsonResponse = [" + mJsonResponse + "], PackageName = [" + PackageName + "], context = [" + context + "]");
        // Convert JsonObject to String Format
        String userString = mJsonResponse.toString();
        // Define the File Path and its Name
        File file = new File(context.getFilesDir(), params);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();


    }
   */
}
