package ve.com.megasoft.demobluetoothfull;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class BluetoothDeviceArrayAdapter extends ArrayAdapter
{

    private List<BluetoothDevice> deviceList; // Contendra el listado de dispositivos
    private Context context;                    // Contexto activo

    public BluetoothDeviceArrayAdapter(Context context, int textViewResourceId,
                                       List<BluetoothDevice> objects) {
        // Invocamos el constructor base
        super(context, textViewResourceId, objects);

        // Asignamos los parametros a los atributos
        this.deviceList = objects;
        this.context = context;
    }
    @Override
    public int getCount()
    {
        if(deviceList != null)
            return deviceList.size();
        else
            return 0;
    }
    @Override
    public Object getItem(int position)
    {
        return (deviceList == null ? null : deviceList.get(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return null;
    }
}
