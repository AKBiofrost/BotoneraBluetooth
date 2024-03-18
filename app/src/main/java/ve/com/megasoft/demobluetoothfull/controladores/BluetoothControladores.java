package ve.com.megasoft.demobluetoothfull.controladores;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothControladores extends AppCompatActivity {
    /********************************************************************************/

    static String TAG = "BluetoothControlador";
    static BluetoothAdapter mBlueAdapter;
    static BluetoothManager bluetoothManager = null;
    ListView listViewPaired;
    private static ArrayAdapter<String> mPairedDevicesArrayAdapter;


    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            android.Manifest.permission.BLUETOOTH_SCAN,
            android.Manifest.permission.BLUETOOTH_CONNECT,
            android.Manifest.permission.BLUETOOTH_PRIVILEGED
    };
    private static String[] PERMISSIONS_LOCATION = {
            ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            android.Manifest.permission.BLUETOOTH_SCAN,
            android.Manifest.permission.BLUETOOTH_CONNECT,
            android.Manifest.permission.BLUETOOTH_PRIVILEGED
    };


    ListView listViewDetected;
    ArrayList<String> arrayListpaired;
    Button buttonSearch, buttonOn, buttonDesc, buttonOff;
    ArrayAdapter<String> adapter, detectedAdapter;

    ArrayList<BluetoothDevice> arrayListPairedBluetoothDevices;
    //ListItemClickedonPaired listItemClickedonPaired;
    BluetoothAdapter bluetoothAdapter = null;
    ArrayList<BluetoothDevice> arrayListBluetoothDevices = null;
    // ListItemClicked listItemClicked;

    private ArrayList dispostivosBT;
    private ArrayAdapter adaptador1;
    BluetoothDevice bdDevice;

    static Context context = null;
    //static Activity activity = null;

    Button Lista_dispositivos;
    Button Permitir_Encontrar;
    Button Dispositivos_disponibles;
    Button Activar_Bluetooth;
    Button Limpiar_Lista;

    Button DesActivar_Bluetooth;

    private ListView lvDispositivos;
    private ArrayList<BluetoothDevice> arrayDevices;


    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            });

    private  ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Add same code that you want to add in onActivityResult method
                }
            });
    /********************************************************************************/


    public boolean checkPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            int permission = ContextCompat.checkSelfPermission(context.getApplicationContext(), android.Manifest.permission.BLUETOOTH_SCAN);
            return permission == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    public void ActivarBluetooth(Context context) {

        Activity activity1 = (Activity) context;

        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (Build.VERSION.SDK_INT >= 31) {

                    Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(activity1, new String[]{android.Manifest.permission.BLUETOOTH_CONNECT}, 3);

                }
            }
            bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);

        }

        if (Build.VERSION.SDK_INT >= 31) {
            Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(activity1, new String[]{android.Manifest.permission.BLUETOOTH_CONNECT}, 3);
            bluetoothManager = (BluetoothManager) activity1.getSystemService(Context.BLUETOOTH_SERVICE);
            mBlueAdapter = bluetoothManager.getAdapter();
            if (mBlueAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityIntent.launch(enableBtIntent);
                // startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        } else {
            Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            mBlueAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBlueAdapter.isEnabled() == false) {
                mBlueAdapter.enable();
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                // startActivityIntent.launch(enableBtIntent);
                //startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }


    }

    public void DesActivarBluetooth(Context context) {

        Activity activity1 = (Activity) context;

        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (Build.VERSION.SDK_INT >= 31) {

                    Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(activity1, new String[]{android.Manifest.permission.BLUETOOTH_CONNECT}, 3);

                }
            }
            bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);

        }

        if (Build.VERSION.SDK_INT >= 31) {
            Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(activity1, new String[]{android.Manifest.permission.BLUETOOTH_CONNECT}, 3);

            bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            mBlueAdapter = bluetoothManager.getAdapter();

            if (mBlueAdapter.isEnabled()) {
                mBlueAdapter.disable();
                Toast.makeText(context, "BLUETOOTH DESACTIVADO", Toast.LENGTH_LONG).show();
                // Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                //  startActivityIntent.launch(enableBtIntent);
                // startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        } else {
            Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            mBlueAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBlueAdapter.isEnabled()) {
                mBlueAdapter.disable();
                Toast.makeText(context, "BLUETOOTH DESACTIVADO", Toast.LENGTH_LONG).show();
                // Intent enableBtIntent = new Intent(BluetoothAdapter.);
                // startActivityIntent.launch(enableBtIntent);
                //startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }


    }

    public void bluetoothScanning(Context context) {
        ActivarBluetooth(context);
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Activity activity1 = (Activity) context;

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= 31) {
                Log.d(TAG, "dispositivosBluetooth: " + "necesita permiso API mayor a 31");

                ActivityCompat.requestPermissions(activity1, new String[]{android.Manifest.permission.BLUETOOTH_CONNECT}, 3);
                Set<BluetoothDevice> pairedDevicesList = mBlueAdapter.getBondedDevices();
                Log.d(TAG, "dispositivosBluetooth: " + " OBTENGO DISPOSITIVOS DISPONIBLES");
                if (mBlueAdapter.isEnabled()) {
                    Log.e(TAG, "Inicia escaneo de dispostivos cercanos");
                    mBlueAdapter.startDiscovery();
                } else {
                    ActivarBluetooth(context);
                    // Comprobamos si existe un descubrimiento en curso. En caso afirmativo, se cancela.
                    if (mBlueAdapter.isDiscovering()) {
                        mBlueAdapter.cancelDiscovery();
                    } else {
                        mBlueAdapter.startDiscovery();
                        context.registerReceiver(mReceiverRRRRR, filter);
                    }


                }

            } else {
                Log.d(TAG, "dispositivosBluetooth: " + " NO necesita permiso API mayor a 31");
                if (mBlueAdapter.isEnabled()) {
                    Log.e(TAG, "Inicia escaneo de dispostivos cercanos");
                    mBlueAdapter.startDiscovery();
                    context.registerReceiver(mReceiverRRRRR, filter);
                } else {
                    ActivarBluetooth(context);
                    // Comprobamos si existe un descubrimiento en curso. En caso afirmativo, se cancela.
                    if (mBlueAdapter.isDiscovering()) {
                        mBlueAdapter.cancelDiscovery();
                    } else {
                        mBlueAdapter.startDiscovery();
                        context.registerReceiver(mReceiverRRRRR, filter);
                    }


                }


            }


        } else {

            //  Set<BluetoothDevice> pairedDevicesList = mBlueAdapter.getBondedDevices();
            if (mBlueAdapter.isEnabled()) {
                Log.e(TAG, "Inicia escaneo de dispostivos cercanos");
                mBlueAdapter.startDiscovery();
            } else {
                ActivarBluetooth(context);
                // Comprobamos si existe un descubrimiento en curso. En caso afirmativo, se cancela.
                if (mBlueAdapter.isDiscovering()) {
                    mBlueAdapter.cancelDiscovery();
                } else {
                    mBlueAdapter.startDiscovery();
                    context.registerReceiver(mReceiverRRRRR, filter);
                }


            }


        }



/*
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (Build.VERSION.SDK_INT >= 31) {

                    Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                }
            }
            bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);

        }
        */

/*
        if (Build.VERSION.SDK_INT >= 31) {
            Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

            bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            mBlueAdapter = bluetoothManager.getAdapter();
        } else {
            Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            mBlueAdapter = BluetoothAdapter.getDefaultAdapter();
        }
*/

        if (mBlueAdapter.isEnabled()) {
            Log.e(TAG, "Inicia escaneo de dispostivos cercanos");
            mBlueAdapter.startDiscovery();
        } else {
            ActivarBluetooth(context);
            // Comprobamos si existe un descubrimiento en curso. En caso afirmativo, se cancela.
            if (mBlueAdapter.isDiscovering()) {
                mBlueAdapter.cancelDiscovery();
            } else {
                mBlueAdapter.startDiscovery();
            }


        }



/*
        TimerTask task = new TimerTask() {
            //   @SuppressLint("MissingPermission")
            public void run() {
                //execute the task
                // mBlueAdapter.clear();
                Log.d(TAG, "TimerTASK");
                // mBlueAdapter.startDiscovery();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1000);
*/
    }


    public void checkPermissions(Context context) {
        Activity activity1 = (Activity) context;
        int permission1 = ActivityCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission2 = ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_SCAN);
        int permission3 = ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT);
        int permission4 = ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION);

        if (permission1 != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity1,
                    PERMISSIONS_STORAGE,
                    1
            );
        } else if (permission2 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity1,
                    PERMISSIONS_LOCATION,
                    1
            );
        }


        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ActivityCompat.requestPermissions(activity1, new String[]{android.Manifest.permission.BLUETOOTH_CONNECT}, 2);
                Log.d(TAG, "--------------------------------------");

                return;
            } else {
                Log.d(TAG, "++++++++++++++++++++++++++++++++++");

            }
        } else {
            Log.d(TAG, "++++++++++++++++++++++------------------");


        }

    }


    public int BTPermissions(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            int REQUEST_CODE = 1;
            Activity activity1 = (Activity) context;
            Activity currentActivity = activity1;

            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(currentActivity, new String[]{android.Manifest.permission.BLUETOOTH_CONNECT, android.Manifest.permission.BLUETOOTH_SCAN, android.Manifest.permission.BLUETOOTH_ADVERTISE}, REQUEST_CODE);
                return 0;
            }
        }
        return 1;

    }


    public void registrarEventosBluetooth() {

        IntentFilter filtro = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        this.registerReceiver(bReceiver, filtro);
    }

    private final BroadcastReceiver bReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {

            }
            final int estado = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                    BluetoothAdapter.ERROR);

            switch (estado) {
                // Apagado
                case BluetoothAdapter.STATE_OFF: {
                    //((Button)findViewById(R.id.btnBluetooth)).setText(R.string.ActivarBluetooth);
                    Log.e(TAG, "BLUETOOTH DESACTIVADO");
                    break;
                }

                // Encendido
                case BluetoothAdapter.STATE_ON: {
                    //  ((Button)findViewById(R.id.btnBluetooth)).setText(R.string.DesactivarBluetooth);
                    Log.e(TAG, "BLUETOOTH ACTIVADO");
                    break;
                }
                default:
                    break;
            }

        }
    };



    private final BroadcastReceiver mReceiverRRRRR = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Activity activity1 = (Activity) context;
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        if (Build.VERSION.SDK_INT >= 31) {

                            Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                            ActivityCompat.requestPermissions(activity1, new String[]{android.Manifest.permission.BLUETOOTH_CONNECT}, 3);

                        }
                    }
                    bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);

                }

                if (Build.VERSION.SDK_INT >= 31) {
                    Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(activity1, new String[]{android.Manifest.permission.BLUETOOTH_CONNECT}, 3);

                    bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
                    //mBlueAdapter = bluetoothManager.getAdapter();
                } else {
                    Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    mBlueAdapter = BluetoothAdapter.getDefaultAdapter();
                }


                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address

                // arrayListpaired.add(device.getName() + "\n" + device.getAddress());
                // arrayListPairedBluetoothDevices.add(device);

                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());

                // Log.i(TAG, "---" + mPairedDevicesArrayAdapter.getItem(1));
                Log.e(TAG, "-----------------------------------------------------");
                Log.d(TAG, "Nombre dispositivo: " + deviceName);
                Log.d(TAG, "Direccion fisica: " + deviceHardwareAddress);
                Log.e(TAG, "-----------------------------------------------------");
            }
        }
    };

    public void AsignarList(ArrayAdapter<String> mPairedDevicesArrayAdapterX) {

        Log.d(TAG, "mPairedDevicesArrayAdapterX: " + mPairedDevicesArrayAdapterX);
        this.mPairedDevicesArrayAdapter = mPairedDevicesArrayAdapterX;
        Log.d(TAG, "mPairedDevicesArrayAdapterX: " + mPairedDevicesArrayAdapter);
    }

    public void PermisoUbicacion() {
        // Ask for location permission if not already allowed
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);


    }


}
