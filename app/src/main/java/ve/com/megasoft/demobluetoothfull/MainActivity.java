package ve.com.megasoft.demobluetoothfull;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    final String TAG = "BLUETOOTH_DEMO";

    private static final UUID BT_MODULE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // "random" unique identifier

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_PRIVILEGED
    };
    private static String[] PERMISSIONS_LOCATION = {
            ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_PRIVILEGED
    };

    private final static int CONNECTING_STATUS = 3; // used in bluetooth handler to identify message status
    private int REQUEST_FINE_LOCATION_PERMISSION = 100;
    private int REQUEST_BLUETOOTH_SCAN_PERMISSION = 101;
    private int REQUEST_BACKGROUND_LOCATION_PERMISSION = 102;
    private int REQUEST_BLUETOOTH_CONNECT_PERMISSION = 103;

    static BluetoothAdapter mBlueAdapter;
    static BluetoothManager bluetoothManager = null;
    ListView listViewPaired;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;

    ListView listViewDetected;
    ArrayList<String> arrayListpaired;
    Button buttonSearch, buttonOn, buttonDesc, buttonOff;
    ArrayAdapter<String> adapter, detectedAdapter;

    ArrayList<BluetoothDevice> arrayListPairedBluetoothDevices;
    ListItemClickedonPaired listItemClickedonPaired;
    BluetoothAdapter bluetoothAdapter = null;
    ArrayList<BluetoothDevice> arrayListBluetoothDevices = null;
    ListItemClicked listItemClicked;

    private ArrayList dispostivosBT;
    private ArrayAdapter adaptador1;
    BluetoothDevice bdDevice;
    public final static int MESSAGE_READ = 2; // used in bluetooth handler to identify message update
    static String name;
    static Context context = null;
    static Activity activity = null;
    private ConnectedThread mConnectedThread; // bluetooth background worker thread to send and receive data
    Button Lista_dispositivos;
    Button Permitir_Encontrar;
    Button Dispositivos_disponibles;
    Button Activar_Bluetooth;
    Button Limpiar_Lista;
    static String address;
    Button DesActivar_Bluetooth;
    private Handler mHandler; // Our main handler that will receive callback notifications
    private BluetoothSocket mBTSocket = null; // bi-directional client-to-client data path
    private ListView lvDispositivos;
    private ArrayList<BluetoothDevice> arrayDevices;


    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Add same code that you want to add in onActivityResult method
                }
            });

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
    private static final String[] BLE_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    @RequiresApi(api = Build.VERSION_CODES.S)
    private static final String[] ANDROID_12_BLE_PERMISSIONS = new String[]{
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InstanciarElementos();
        PermisosBluetooth();
        checkPermissions();


    }

    @Override
    protected void onStart() {
        super.onStart();
        Lista_dispositivos.setOnClickListener(v -> {
            Log.d(TAG, "Dispositivos_disponibles presionado");
            if (mBlueAdapter.isEnabled()) {
                dispositivosBluetooth(context);
            } else {
                ActivarBluetooth();
            }
        });

        Dispositivos_disponibles.setOnClickListener(view -> {
            //BusquedaDispositivos();
            // BuscarDispositivo();

            BTPermissions(context);
            bluetoothScanning();


        });

        Activar_Bluetooth.setOnClickListener(v -> {

            ActivarBluetooth();

        });

        Permitir_Encontrar.setOnClickListener(v -> {

            int requestCode = 1;
            Intent discoverableIntent =
                    new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);

            startActivityIntent.launch(discoverableIntent);
            //startActivityForResult(discoverableIntent, requestCode);
        });

        DesActivar_Bluetooth.setOnClickListener(v -> {

            DesActivarBluetooth();

        });


        Limpiar_Lista.setOnClickListener(v -> {

            mPairedDevicesArrayAdapter.clear();

        });

        RespuestaEmparejamiento();
    }

    @Override
    protected void onResume() {
        super.onResume();


        registrarEventosBluetooth();
        ListView pairedListView = (ListView) findViewById(R.id.devices_list_view);
        pairedListView.setAdapter(mPairedDevicesArrayAdapter);

        pairedListView.setOnItemClickListener(mDeviceClickListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Don't forget to unregister the ACTION_FOUND receiver.
        // unregisterReceiver(mReceiverRRRRR);
    }


    private void InstanciarElementos() {
        context = this;
        activity = MainActivity.this;
        Lista_dispositivos = findViewById(R.id.button4);
        Permitir_Encontrar = findViewById(R.id.button3);
        Dispositivos_disponibles = findViewById(R.id.button5);
        Activar_Bluetooth = findViewById(R.id.button1);
        DesActivar_Bluetooth = findViewById(R.id.button2);
        Limpiar_Lista = findViewById(R.id.button6);

        lvDispositivos = findViewById(R.id.devices_list_view);
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);


        /*****************************************************************************************/


        /*****************************************************************************************/


    }

    private final void PermisosBluetooth() {

        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (Build.VERSION.SDK_INT >= 31) {

                    //Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                }
            }
            bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);

        }

        if (Build.VERSION.SDK_INT >= 31) {
            //Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

            bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            mBlueAdapter = bluetoothManager.getAdapter();
        } else {
            // Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            mBlueAdapter = BluetoothAdapter.getDefaultAdapter();
        }

    }

    public Boolean dispositivosBluetooth(Context context) {
        //  manipularJSON lista = new manipularJSON();
        //  AppPreferences guardarLista = new AppPreferences(context, "buscarBluetooth");
        //  JsonObject listaCompleta = new JsonObject();
        //   JsonArray listaDispositivo = new JsonArray();
        Activity activity = (Activity) context;

        Log.d(TAG, "dispositivosBluetooth: " + "ENTRO AQUI PARA EL MANEJO DEL BLUETOOTH");
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= 31) {
                Log.d(TAG, "dispositivosBluetooth: " + "necesita permiso API mayor a 31");

                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 2);
                Set<BluetoothDevice> pairedDevicesList = mBlueAdapter.getBondedDevices();
                Log.d(TAG, "dispositivosBluetooth: " + " OBTENGO LA LISTA DE DISPOSITIVOS YA EMPAREJADOS");
                for (BluetoothDevice pairedDevice : pairedDevicesList) {

                    //  JsonObject dispositivo = new JsonObject();
                    //   dispositivo.addProperty("name", pairedDevice.getName());
                    //   dispositivo.addProperty("Address", pairedDevice.getAddress());
                    //  listaDispositivo.add(dispositivo);
                    mPairedDevicesArrayAdapter.add(pairedDevice.getName() + "\n" + pairedDevice.getAddress());

                    Log.d(TAG, "pairedDevice.getName(): " + pairedDevice.getName());
                    Log.d(TAG, "pairedDevice.getAddress(): " + pairedDevice.getAddress());


                }

            } else {
                Log.d(TAG, "dispositivosBluetooth: " + " NO necesita permiso API mayor a 31");


                Set<BluetoothDevice> pairedDevicesList = mBlueAdapter.getBondedDevices();
                Log.d(TAG, "dispositivosBluetooth: " + " OBTENGO LA LISTA DE DISPOSITIVOS YA EMPAREJADOS");
                for (BluetoothDevice pairedDevice : pairedDevicesList) {
                    mPairedDevicesArrayAdapter.add(pairedDevice.getName() + "\n" + pairedDevice.getAddress());

                    // Log.i(TAG, "---" + mPairedDevicesArrayAdapter.getItem(1));
                    //  JsonObject dispositivo = new JsonObject();
                    //   dispositivo.addProperty("name", pairedDevice.getName());
                    //   dispositivo.addProperty("Address", pairedDevice.getAddress());
                    //  listaDispositivo.add(dispositivo);
                    //   lvDispositivos.set
                    Log.d(TAG, "pairedDevice.getName(): " + pairedDevice.getName());
                    Log.d(TAG, "pairedDevice.getAddress(): " + pairedDevice.getAddress());


                }

            }


        } else {

            Set<BluetoothDevice> pairedDevicesList = mBlueAdapter.getBondedDevices();
            Log.d(TAG, "dispositivosBluetooth: " + " OBTENGO LA LISTA DE DISPOSITIVOS YA EMPAREJADOS");
            for (BluetoothDevice pairedDevice : pairedDevicesList) {

                //   JsonObject dispositivo = new JsonObject();
                //    dispositivo.addProperty("name", pairedDevice.getName());
                //    dispositivo.addProperty("Address", pairedDevice.getAddress());
                //   listaDispositivo.add(dispositivo);
                mPairedDevicesArrayAdapter.add(pairedDevice.getName() + "\n" + pairedDevice.getAddress());

                Log.d(TAG, "pairedDevice.getName(): " + pairedDevice.getName());
                Log.d(TAG, "pairedDevice.getAddress(): " + pairedDevice.getAddress());


            }


        }

        return true;
    }


    private void ActivarBluetooth() {


        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (Build.VERSION.SDK_INT >= 31) {

                    // Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                }
            }
            bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);

        }

        if (Build.VERSION.SDK_INT >= 31) {
            //  Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);
            bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            mBlueAdapter = bluetoothManager.getAdapter();
            if (!mBlueAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityIntent.launch(enableBtIntent);
                // startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        } else {
            //Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            mBlueAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!mBlueAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityIntent.launch(enableBtIntent);
                //startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }


    }

    private void DesActivarBluetooth() {


        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (Build.VERSION.SDK_INT >= 31) {

                    //    //  Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                }
            }
            bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);

        }

        if (Build.VERSION.SDK_INT >= 31) {
            //  Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

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
            //  Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
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


    void bluetoothScanning() {

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        context.registerReceiver(mReceiverRRRRR, filter);
        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= 31) {
                Log.d(TAG, "dispositivosBluetooth: " + "necesita permiso API mayor a 31");

                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);
                Set<BluetoothDevice> pairedDevicesList = mBlueAdapter.getBondedDevices();
                Log.d(TAG, "dispositivosBluetooth: " + " OBTENGO DISPOSITIVOS DISPONIBLES");
                if (mBlueAdapter.isEnabled()) {
                    Log.e(TAG, "Inicia escaneo de dispostivos cercanos");
                    mBlueAdapter.startDiscovery();
                } else {
                    ActivarBluetooth();
                    // Comprobamos si existe un descubrimiento en curso. En caso afirmativo, se cancela.
                    if (mBlueAdapter.isDiscovering()) {
                        mBlueAdapter.cancelDiscovery();
                    } else {
                        mBlueAdapter.startDiscovery();
                    }


                }

            } else {
                Log.d(TAG, "dispositivosBluetooth: " + " NO necesita permiso API mayor a 31");
                if (mBlueAdapter.isEnabled()) {
                    Log.e(TAG, "Inicia escaneo de dispostivos cercanos");
                    mBlueAdapter.startDiscovery();
                } else {
                    ActivarBluetooth();
                    // Comprobamos si existe un descubrimiento en curso. En caso afirmativo, se cancela.
                    if (mBlueAdapter.isDiscovering()) {
                        mBlueAdapter.cancelDiscovery();
                    } else {
                        mBlueAdapter.startDiscovery();
                    }


                }


            }


        } else {

            Set<BluetoothDevice> pairedDevicesList = mBlueAdapter.getBondedDevices();
            if (mBlueAdapter.isEnabled()) {
                Log.e(TAG, "Inicia escaneo de dispostivos cercanos");
                mBlueAdapter.startDiscovery();
            } else {
                ActivarBluetooth();
                // Comprobamos si existe un descubrimiento en curso. En caso afirmativo, se cancela.
                if (mBlueAdapter.isDiscovering()) {
                    mBlueAdapter.cancelDiscovery();
                } else {
                    mBlueAdapter.startDiscovery();
                }


            }


        }



/*
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (Build.VERSION.SDK_INT >= 31) {

                      //  Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                }
            }
            bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);

        }
        */

/*
        if (Build.VERSION.SDK_INT >= 31) {
              //  Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

            bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            mBlueAdapter = bluetoothManager.getAdapter();
        } else {
              //  Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            mBlueAdapter = BluetoothAdapter.getDefaultAdapter();
        }
*/

        if (mBlueAdapter.isEnabled()) {
            Log.e(TAG, "Inicia escaneo de dispostivos cercanos");
            mBlueAdapter.startDiscovery();
        } else {
            ActivarBluetooth();
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

    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver mReceiverRRRRR = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        if (Build.VERSION.SDK_INT >= 31) {

                            //  Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                        }
                    }
                    bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);

                }

                if (Build.VERSION.SDK_INT >= 31) {
                    //  Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                    bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
                    //mBlueAdapter = bluetoothManager.getAdapter();
                } else {
                    //  Toast.makeText(context, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
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


    private void checkPermissions() {
        int permission1 = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN);
        int permission3 = ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT);
        int permission4 = ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION);

        if (permission1 != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    1
            );
        } else if (permission2 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_LOCATION,
                    1
            );
        }


        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 2);
                Log.d(TAG, "--------------------------------------");

                return;
            } else {
                Log.d(TAG, "++++++++++++++++++++++++++++++++++");

            }
        } else {
            Log.d(TAG, "++++++++++++++++++++++------------------");


        }

    }


    public static int BTPermissions(Context context) {

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

    // Instanciamos un BroadcastReceiver que se encargara de detectar si el estado
// del Bluetooth del dispositivo ha cambiado mediante su handler onReceive
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


    private void registrarEventosBluetooth() {

        IntentFilter filtro = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        this.registerReceiver(bReceiver, filtro);
    }


    class ListItemClickedonPaired implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (mBlueAdapter.isEnabled() == false) {
                ActivarBluetooth();
            } else {
                String info = ((TextView) view).getText().toString();
                address = info.substring(info.length() - 17);
                name = info.substring(0, address.length());
                Log.i(TAG, "Seleccinado address: " + address);
                Log.i(TAG, "Seleccinado name: " + name);

                new Thread() {
                    @Override
                    public void run() {
                        boolean fail = false;

                        BluetoothDevice device = mBlueAdapter.getRemoteDevice(address);
                        Log.d("bluetooth", "Bluetooth device"+device);
                        try {
                            Log.d("bluetooth", "Bluetooth device");
                            mBTSocket = createBluetoothSocket(device);
                        } catch (IOException e) {
                            fail = true;
                            Toast.makeText(getBaseContext(), "conexion", Toast.LENGTH_SHORT).show();
                        }
                        // Establish the Bluetooth socket connection.
                        try {
                            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                                    if (Build.VERSION.SDK_INT >= 31) {

                                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                                    }
                                }

                            } else {

                            }

                            if (Build.VERSION.SDK_INT >= 31) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);
                            }
                            mBTSocket.connect();
                        } catch (IOException e) {
                            try {
                                fail = true;
                                mBTSocket.close();
                                mHandler.obtainMessage(CONNECTING_STATUS, -1, -1)
                                        .sendToTarget();
                                /************************************************************************************************/


                                /***********************************************************************************************/
                            } catch (IOException e2) {
                                //insert code to deal with this
                                Toast.makeText(getBaseContext(), "conexion", Toast.LENGTH_SHORT).show();
                            }
                        }

                        if (!fail) {
                            mConnectedThread = new ConnectedThread(mBTSocket, mHandler);
                            mConnectedThread.start();

                            mHandler.obtainMessage(CONNECTING_STATUS, 1, -1, name)
                                    .sendToTarget();
                        } else {
                            mHandler.obtainMessage(CONNECTING_STATUS, 1, -1, name)
                                    .sendToTarget();

                        }
                    }
                }.start();

            }

        }
    }


    class ListItemClicked implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // TODO Auto-generated method stub
            bdDevice = arrayListBluetoothDevices.get(position);
            //bdClass = arrayListBluetoothDevices.get(position);
            Log.i("Log", "The dvice : " + bdDevice.toString());
            /*
             * here below we can do pairing without calling the callthread(), we can directly call the
             * connect(). but for the safer side we must usethe threading object.
             */
            //callThread();
            //connect(bdDevice);
            Boolean isBonded = false;
            try {
                isBonded = createBond(bdDevice);
                if (isBonded) {
                    //arrayListpaired.add(bdDevice.getName()+"\n"+bdDevice.getAddress());
                    //adapter.notifyDataSetChanged();
                    //     getPairedDevices();
                    adapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }//connect(bdDevice);
            Log.i("Log", "The bond is created: " + isBonded);
        }
    }

    /*
        private void getPairedDevices() {
            Set<BluetoothDevice> pairedDevice = bluetoothAdapter.getBondedDevices();
            if (pairedDevice.size() > 0) {
                for (BluetoothDevice device : pairedDevice) {
                    arrayListpaired.add(device.getName() + "\n" + device.getAddress());
                    arrayListPairedBluetoothDevices.add(device);
                }
            }
            adapter.notifyDataSetChanged();
        }
    */
    public boolean createBond(BluetoothDevice btDevice)
            throws Exception {
        Class class1 = Class.forName("android.bluetooth.BluetoothDevice");
        Method createBondMethod = class1.getMethod("createBond");
        Boolean returnValue = (Boolean) createBondMethod.invoke(btDevice);
        return returnValue.booleanValue();
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        Log.d(TAG, "createBluetoothSocket"+ device);
        try {
            final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", UUID.class);
            Log.d(TAG, "createBluetoothSocket "+ m);
            Log.d(TAG, "createBluetoothSocket "+ m.invoke(device, BT_MODULE_UUID));
            return (BluetoothSocket) m.invoke(device, BT_MODULE_UUID);
        } catch (Exception e) {
            Log.e(TAG, "Could not create Insecure RFComm Connection", e);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (Build.VERSION.SDK_INT >= 31) {

                    // Toast.makeText(this, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                }
            }

        } else {

        }

        if (Build.VERSION.SDK_INT >= 31) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);
        }

        Log.i(TAG, "createBluetoothSocket: " + device);
        return device.createRfcommSocketToServiceRecord(BT_MODULE_UUID);
    }


    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            String info = ((TextView) v).getText().toString();
            address = info.substring(info.length() - 17);

            Toast.makeText(MainActivity.this, " Selecionado: " + info, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "---------------------------------------------");
            Log.d(TAG, "onItemClick: " + info);
            Log.d(TAG, "onItemClick: " + address);
            Log.e(TAG, "---------------------------------------------");
            if (mBlueAdapter.isEnabled() == false) {
                ActivarBluetooth();
            } else {
                address = info.substring(info.length() - 17);
                name = info.substring(0, address.length());
                Log.i(TAG, "Seleccinado address: " + address);
                Log.i(TAG, "Seleccinado name: " + name);

                new Thread() {
                    @Override
                    public void run() {
                        boolean fail = false;

                        BluetoothDevice device = mBlueAdapter.getRemoteDevice(address);
                        Log.d("bluetooth", "Bluetooth device "+device);
                        try {

                            mBTSocket = createBluetoothSocket(device);
                        } catch (IOException e) {
                            fail = true;
                            Toast.makeText(getBaseContext(), "Fallo conexion", Toast.LENGTH_SHORT).show();
                        }
                        // Establish the Bluetooth socket connection.
                        try {
                            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                                    if (Build.VERSION.SDK_INT >= 31) {

                                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                                    }
                                }

                            } else {

                            }

                            if (Build.VERSION.SDK_INT >= 31) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);
                            }
                            mBTSocket.connect();
                        } catch (IOException e) {
                            try {
                                fail = true;
                                mBTSocket.close();
                                mHandler.obtainMessage(CONNECTING_STATUS, -1, -1)
                                        .sendToTarget();
                                /************************************************************************************************/


                                /***********************************************************************************************/
                            } catch (IOException e2) {
                                //insert code to deal with this
                                Toast.makeText(getBaseContext(), "Fallo conexion", Toast.LENGTH_SHORT).show();
                            }
                        }

                        if (!fail) {
                            mConnectedThread = new ConnectedThread(mBTSocket, mHandler);
                            mConnectedThread.start();

                            mHandler.obtainMessage(CONNECTING_STATUS, 1, -1, name)
                                    .sendToTarget();
                        } else {
                            mHandler.obtainMessage(CONNECTING_STATUS, 1, -1, name)
                                    .sendToTarget();

                        }
                    }
                }.start();

            }


        }
    };

    private void RespuestaEmparejamiento() {

        mHandler = new Handler(Looper.getMainLooper()) {

            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_READ) {
                    String readMessage = null;
                    readMessage = new String((byte[]) msg.obj, StandardCharsets.UTF_8);
                    // mReadBuffer.setText(readMessage);
                }

                if (msg.what == CONNECTING_STATUS) {
                    char[] sConnected;
                    if (msg.arg1 == 1) {
                        Log.i("-----", "SE CONECTO Y EMPAREJO");
                        // config.calibrarPinpadBT.dispositivosBluetoothPinpad(BotoneraBLuetooth.this, address);
                        Toast.makeText(getBaseContext(), "Fallo conexion", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.i("-----", "msg.arg1: " + msg.arg1);
                        // config.Dialog.AlertDialog_alerta("Fallo al Emparejar", "No se pudo emparejar su dispositivo", BotoneraBLuetooth.this);
                    }

                }
            }
        };

    }


}
