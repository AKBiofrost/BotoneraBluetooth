package ve.com.bivfrostgroup.demobluetoothfull.controladores;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ve.com.bivfrostgroup.demobluetoothfull.R;

public class toastCustomer {

    private static final String TAG = toastCustomer.class.getSimpleName();
    private static Activity activity;
    public void toastGrande(Context context, String texto, int Size){

        activity=(Activity)context;
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) activity.findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(texto);
        text.setTextSize(Size);
        layout.animate().start();
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();

    }


}
