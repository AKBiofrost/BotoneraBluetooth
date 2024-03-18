package ve.com.megasoft.demobluetoothfull.interfaz.utiles;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


public class SharesPreferend {

    final static  String TAG="-SharedPreferences";
     private SharedPreferences _sharedPrefs;
    private SharedPreferences.Editor _prefsEditor;

    public SharesPreferend(Context context, String nombrePreferens) {
        this._sharedPrefs = context.getSharedPreferences(nombrePreferens, Activity.MODE_PRIVATE);
        this._prefsEditor = _sharedPrefs.edit();
    }

    public String getSmsBody(String nombrePreferens) {
        return _sharedPrefs.getString(nombrePreferens, "");
    }

    public int getSmsBody_int(String nombrePreferens) {
        return _sharedPrefs.getInt(nombrePreferens,0);
    }

    public void saveSmsBody(String text,String nombrePreferens) {
        Log.d(TAG, "saveSmsBody() called with: text = [" + text + "], nombrePreferens = [" + nombrePreferens + "]");
        _prefsEditor.putString(nombrePreferens, text);
        _prefsEditor.commit();
    }

    public void saveSmsBody_int(int text,String nombrePreferens) {
        _prefsEditor.putInt(nombrePreferens, text);
        _prefsEditor.commit();
    }

    public int getSmsBody_boolean(String nombrePreferens) {

        return _sharedPrefs.getInt(nombrePreferens,0);

    }

    public void saveSmsBody_boolean(int bool,String nombrePreferens) {
        _prefsEditor.putInt(nombrePreferens,bool);
        _prefsEditor.commit();
    }

    public void deleteAll(String nombrePreferens) {
        _prefsEditor.clear();
        _prefsEditor.apply();
    }
}