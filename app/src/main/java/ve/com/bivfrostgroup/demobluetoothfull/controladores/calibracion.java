package ve.com.bivfrostgroup.demobluetoothfull.controladores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.PowerManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class calibracion {

 private static String TAG = calibracion.class.getSimpleName();
    public void PantallaEncendida(Context context) {
        PowerManager.WakeLock mWakeLock;
        Activity activity = (Activity) context;
        final PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    public boolean isOnline(Context context) {
        Log.d("tag", "isOnline() called");
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnectedOrConnecting()) {
            Log.d("", "isOnline() returned: " + true);
            return true;

        } else {


            return false;
        }
    }


    public String AnchoDispositivo(Context context) {
        DisplayMetrics metricsP = new DisplayMetrics();
        String resultado = "";
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(metricsP);
        int width = metricsP.widthPixels; // ancho absoluto en pixels
        int height = metricsP.heightPixels; // alto absoluto en pixels
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dpWidth = (metrics.widthPixels / metrics.density);
        float dpHeight = metrics.heightPixels / metrics.density;
        int dpWidthInt = (int) dpWidth;

        String DPWidth = String.valueOf(dpWidthInt);
        String DPHeight = String.valueOf(dpHeight);

        Log.i("TAG", "Dimensaciones-DPWidth: " + DPWidth);
        Log.i("TAG", "Dimensaciones-DPHeight: " + DPHeight);

        resultado = DPWidth;
        return resultado;
    }

    public String AltoDispositivo(Context context) {
        DisplayMetrics metricsP = new DisplayMetrics();
        String resultado = "";
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(metricsP);
        int width = metricsP.widthPixels; // ancho absoluto en pixels
        int height = metricsP.heightPixels; // alto absoluto en pixels
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dpWidth = (metrics.widthPixels / metrics.density);
        float dpHeight = metrics.heightPixels / metrics.density;
        int dpWidthInt = (int) dpWidth;

        String DPWidth = String.valueOf(dpWidthInt);
        String DPHeight = String.valueOf(dpHeight);

        Log.i("TAG", "Dimensaciones-DPWidth: " + DPWidth);
        Log.i("TAG", "Dimensaciones-DPHeight: " + DPHeight);

        resultado = DPHeight;
        return resultado;
    }

    public void Vibrar(Context context) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(1000);
        }

    }

    public int MedirBateria(Context context) {

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float battery = (level / (float) scale) * 100;

        Log.i(TAG, "niveles Bateria: " + battery);
        return (int) battery;
    }

    public void DensidadPixeles(Context context) {
        Activity activity = (Activity) context;
        DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        double density = activity.getResources().getDisplayMetrics().density;

        Log.i(TAG, "DensidadPixeles: " + density);

        if (0.75 <= density && density < 1.0) {
            Log.i(TAG, "DensidadPixeles: ldpi");

        }
        if (1.0 <= density && density < 1.5) {
            Log.i(TAG, "DensidadPixeles: mdpi");

        }
        if (1.5 <= density && density < 2.0) {
            Log.i(TAG, "DensidadPixeles: hdpi");

        }
        if (2.0 <= density && density < 3.0) {
            Log.i(TAG, "DensidadPixeles: xhdpi");

        }
        if (3.0 <= density && density <= 4.0) {
            Log.i(TAG, "DensidadPixeles: xxhdpi y xxxhdpi");

        }
    }

    public String GuardarUUID(Context context) {
        Log.d(TAG, "GuardarUUID() called");

        String UUID = Settings.Secure.getString(((Activity) context).getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return UUID;
    }

}

