package ve.com.bivfrostgroup.demobluetoothfull.view.ui;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;

import ve.com.bivfrostgroup.demobluetoothfull.R;
import ve.com.bivfrostgroup.demobluetoothfull.controladores.BluetoothControladores;
import ve.com.bivfrostgroup.demobluetoothfull.controladores.ConnectedThread;
import ve.com.bivfrostgroup.demobluetoothfull.controladores.peticion.OnResponse;
import ve.com.bivfrostgroup.demobluetoothfull.controladores.toastCustomer;
import ve.com.bivfrostgroup.demobluetoothfull.interfaz.config.config;
import ve.com.bivfrostgroup.demobluetoothfull.interfaz.config.peticion;
import ve.com.bivfrostgroup.demobluetoothfull.interfaz.model.DefinicionRespuestaRetrofit;
import ve.com.bivfrostgroup.demobluetoothfull.interfaz.model.ErrorResponse;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Emparejamiento BLuetooth";
    toastCustomer toast = new toastCustomer();
    private UUID BT_MODULE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // "random" unique identifier
    private static final UUID MY_UUID_SECURE =
            UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
    private static final UUID MY_UUID_INSECURE =
            UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");
    // #defines for identifying shared types between calling functions
    private final static int REQUEST_ENABLE_BT = 1; // used to identify adding bluetooth names
    public final static int MESSAGE_READ = 2; // used in bluetooth handler to identify message update
    private final static int CONNECTING_STATUS = 3; // used in bluetooth handler to identify message status
    private Button SerDescubierto;
    private Button mOffBtn;
    private Button PermisosBLuetooth;
    private Button LimpiarListas;
    private Button mDiscoverBtn;
    private Button mListBtn;
    private ListView mDevicesListView;
    private ListView mDevicesListView2;

    private BluetoothAdapter mBTAdapter;
    private Set<BluetoothDevice> mPairedDevices;
    private ArrayAdapter<String> mBTArrayAdapter;
    private ArrayAdapter<String> mBTArrayAdapter2;

    private FloatingActionButton floatingActionButton;
    private FloatingActionButton floatingActionButtonUUIDbasic;
    private FloatingActionButton floatingActionButtonUUID;

    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Add same code that you want to add in onActivityResult method
                }
            });

    static String address;
    static String name;

    private Handler mHandler; // Our main handler that will receive callback notifications
    private ConnectedThread mConnectedThread; // bluetooth background worker thread to send and receive data
    private BluetoothSocket mBTSocket = null; // bi-directional client-to-client data path


    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        config.BTControlador.BTPermissions(this);
        config.BTControlador.PermisoUbicacion(this);
        config.BTControlador.checkPermissions(this);
        config.timeOut.startTimer(3000, 1, "Timeout", this);
        config.calibrar.PantallaEncendida(this);
        InstanciarObjetos();
        PermisoUbicacion();


    }

    @Override
    protected void onStart() {
        super.onStart();
        //  startTimer();
        listPairedDevices();
        Botones();
        RespuestaEmparejamiento();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LimpiarLista();
        mBTArrayAdapter2.clear(); // clear items
        listPairedDevices();
        discover();


    }


    /*********************************************************************************************/
    private void bluetoothOn() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (Build.VERSION.SDK_INT >= 31) {

                    Toast.makeText(this, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                }
            }

        } else {

        }

        if (Build.VERSION.SDK_INT >= 31) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);
        }


        if (mBTAdapter.isEnabled() == false) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityIntent.launch(enableBtIntent);
        } else {
            Log.i(TAG, "YA ESTABA HABILITADO EL BLUETOOTH");
        }
    }

    private void bluetoothOff() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (Build.VERSION.SDK_INT >= 31) {

                    Toast.makeText(this, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                }
            }

        } else {

        }

        if (Build.VERSION.SDK_INT >= 31) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);
        }
        mBTAdapter.disable(); // turn off
        Log.i(TAG, "DESHABILITADO EL BLUETOOTH");
    }

    private void discover() {
        // Check if the device is already discovering
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (Build.VERSION.SDK_INT >= 31) {

                    Toast.makeText(this, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                }
            }

        } else {

        }

        if (Build.VERSION.SDK_INT >= 31) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);
        }

        if (mBTAdapter.isDiscovering()) {
            Log.i(TAG, "ISDISCOVERING EL BLUETOOTH");
            mBTAdapter.cancelDiscovery();
        } else {
            if (mBTAdapter.isEnabled() == true) {
                Log.i(TAG, "INICIA ESCANEO DEL BLUETOOTH");
                mBTArrayAdapter.clear(); // clear items
                mBTAdapter.startDiscovery();
                registerReceiver(blReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            } else {
                bluetoothOn();
            }
        }
    }

    private void GetUUID() {
        try {
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            Method getUuidsMethod = BluetoothAdapter.class.getDeclaredMethod("getUuids", null);
            ParcelUuid[] uuids = (ParcelUuid[]) getUuidsMethod.invoke(adapter, null);

            if (uuids != null) {
                for (ParcelUuid uuid : uuids) {
                    Log.d(TAG, "UUID: " + uuid.getUuid().toString());
                    BT_MODULE_UUID = uuid.getUuid();
                }
            } else {
                Log.d(TAG, "Uuids no encontrados, asegura habilitar Bluetooth!");
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private void listPairedDevices() {
        mBTArrayAdapter.clear();


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (Build.VERSION.SDK_INT >= 31) {

                    Toast.makeText(this, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                }
            }

        } else {

        }

        if (Build.VERSION.SDK_INT >= 31) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);
        }
        mPairedDevices = mBTAdapter.getBondedDevices();
        if (mBTAdapter.isEnabled() == true) {
            // put it's one to the adapter
            for (BluetoothDevice device : mPairedDevices) {
                if (device.getName().equalsIgnoreCase("null") == false) {
                    String Prefijo1 = device.getName().charAt(0) + "";
                    String Prefijo2 = device.getName().charAt(1) + "";
                    //  String Prefijo3 = device.getName().charAt(2) +"";
                    String Prefijo = Prefijo1 + Prefijo2;
                    Log.d(TAG, "Prefijo: " + Prefijo1 + Prefijo2);
                    // if (Prefijo.equalsIgnoreCase("MP") == true) {
                    mBTArrayAdapter2.add(device.getName() + "\n" + device.getAddress());
                    // }
                }

            }

        } else {
            bluetoothOn();


        }

    }

    private void SerDescubireto() {
        Intent discoverableIntent =
                new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);

        startActivityIntent.launch(discoverableIntent);

    }


    /*********************************************************************************************/
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        config.timeOut.cancelTimer();
        config.timeOut.initTimer();
    }

    private void Botones() {

        if (mBTArrayAdapter == null) {
            // Device does not support Bluetooth
            Log.i(TAG, "Fallo en creacion de adapter");
        } else {

            floatingActionButton.setOnClickListener(v -> {
                //  cTimer.cancel();
                toast.toastGrande(this, "Recargar listas", 50);

                LimpiarLista();
                mBTArrayAdapter2.clear(); // clear items
                listPairedDevices();
                discover();


            });

            floatingActionButtonUUIDbasic.setOnClickListener(v -> {

                toast.toastGrande(this, "UUID seguridad generico", 50);
                BT_MODULE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // "random" unique identifier
            });

            floatingActionButtonUUID.setOnClickListener(v -> {

                toast.toastGrande(this, "UUID seguridad del dispositivo ", 50);
                GetUUID();
            });

            mDiscoverBtn.setOnClickListener(v -> {
                toast.toastGrande(this, "Radeando dispositivos", 50);
                discover();
            });
            mOffBtn.setOnClickListener(v -> {
                config.Toas.toastGrande(mContext, "Apagando BLuetooth", 25);
                bluetoothOff();
            });

            SerDescubierto.setOnClickListener(v -> {
                SerDescubireto();
            });


            LimpiarListas.setOnClickListener(v -> {
                mBTArrayAdapter2.clear(); // clear items
                LimpiarLista();
            });


            PermisosBLuetooth.setOnClickListener(v -> {
                config.Toas.toastGrande(this, "verificando permisos", 25);
                config.BTControlador.BTPermissions(this);
                config.BTControlador.PermisoUbicacion(this);
                config.BTControlador.checkPermissions(this);
                PermisoUbicacion();
            });

            mListBtn.setOnClickListener(v -> {
                mBTArrayAdapter2.clear(); // clear items
                LimpiarLista();

                listPairedDevices();

            });
        }

    }

    private void PermisoUbicacion() {
        // Ask for location permission if not already allowed
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);


    }

    private void RespuestaEmparejamiento() {

        mHandler = new Handler(Looper.getMainLooper()) {

            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_READ) {
                    String readMessage = null;
                    readMessage = new String((byte[]) msg.obj, StandardCharsets.UTF_8);
                    // config.Dialog.AlertDialog("BLuetooth", readMessage, mContext);
                }

                if (msg.what == CONNECTING_STATUS) {
                    char[] sConnected;
                    Log.d(TAG, "msg.arg1: " + msg.arg1);
                    if (msg.arg1 == 1) {
                        config.Dialog.AlertDialog("Emparejo BLuetooth", "Conexion Exitosa", mContext);
                    } else {
                        config.Toas.toastGrande(mContext, "Fallo de conexion", 50);
                    }

                }
            }
        };

    }

    private void InstanciarObjetos() {
        //  mBluetoothStatus = (TextView) findViewById(R.id.bluetooth_status);
        //  mReadBuffer = (TextView) findViewById(R.id.read_buffer);
        //mScanBtn = (Button) findViewById(R.id.scan);
        // mOffBtn = (Button) findViewById(R.id.off);
        mDiscoverBtn = (Button) findViewById(R.id.discover);

        mOffBtn = (Button) findViewById(R.id.ApagarBLuetooth);

        SerDescubierto = (Button) findViewById(R.id.SerDescubierto);
        LimpiarListas = (Button) findViewById(R.id.comunicacionBLE);
        PermisosBLuetooth = (Button) findViewById(R.id.discover3);
        // mListPairedDevicesBtn = (Button) findViewById(R.id.paired_btn);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.Recargar);

        floatingActionButtonUUIDbasic = (FloatingActionButton) findViewById(R.id.UUIDbasic);
        floatingActionButtonUUID = (FloatingActionButton) findViewById(R.id.UUID);

        mBTArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mBTArrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mBTAdapter = BluetoothAdapter.getDefaultAdapter(); // get a handle on the bluetooth radio
        mDevicesListView = (ListView) findViewById(R.id.devices_list_view);
        mDevicesListView.setAdapter(mBTArrayAdapter); // assign model to view
        mDevicesListView.setOnItemClickListener(mDeviceClickListener);
        mDevicesListView2 = (ListView) findViewById(R.id.devices_list_view2);
        mDevicesListView2.setAdapter(mBTArrayAdapter2); // assign model to view
        mDevicesListView2.setOnItemClickListener(mDeviceClickListenerList);
        mListBtn = (Button) findViewById(R.id.discover2);

    }

    /*********************************************************************************************/

    final BroadcastReceiver blReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // add the name to the list

                if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
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
                Log.d(TAG, device.getName() + "\n" + device.getAddress());
                if (device.getName() != null) {
                    String Prefijo1 = device.getName().charAt(0) + "";
                    String Prefijo2 = device.getName().charAt(1) + "";
                    String Prefijo3 = device.getName().charAt(2) + "";
                    String Prefijo = Prefijo1 + Prefijo2 + Prefijo3;
                    Log.d(TAG, "Prefijo: " + Prefijo1 + Prefijo2 + Prefijo3);
                    mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());


                }


                mBTArrayAdapter.notifyDataSetChanged();
            }
        }
    };

    /*********************************************************************************************/

    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // config.Dialog.AlertDialog_alerta_Informar("Espere un momento", "Procesando petición",  MainActivity.this);
            //  cTimer.cancel();
            if (mBTAdapter.isEnabled() == false) {
                bluetoothOn();
            } else {
                String info = ((TextView) view).getText().toString();
                address = info.substring(info.length() - 17);
                name = info.substring(0, address.length());
                Log.i(TAG, "Seleccinado address: " + address);
                Log.i(TAG, "Seleccinado name: " + name);
                TextView estatus = findViewById(R.id.estado);
                estatus.setVisibility(View.VISIBLE);
                // cTimer.cancel();
                new Thread() {
                    @Override
                    public void run() {
                        boolean fail = false;

                        BluetoothDevice device = mBTAdapter.getRemoteDevice(address);

                        try {

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
                            //
                            mHandler.obtainMessage(CONNECTING_STATUS, 1, -1, name)
                                    .sendToTarget();

                        }
                    }
                }.start();

            }

        }
    };


    private AdapterView.OnItemClickListener mDeviceClickListenerList = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // config.Dialog.AlertDialog_alerta_Informar("Espere un momento", "Procesando petición",  MainActivity.this);
            //  cTimer.cancel();
            if (mBTAdapter.isEnabled() == false) {
                bluetoothOn();
            } else {
                String info = ((TextView) view).getText().toString();
                address = info.substring(info.length() - 17);
                name = info.substring(0, info.length() - address.length() - 1);
                Log.i(TAG, "Seleccinado address: " + address);
                Log.i(TAG, "Seleccinado name: " + name);
                TextView estatus = findViewById(R.id.estado);
                estatus.setVisibility(View.VISIBLE);
                // cTimer.cancel();     BluetoothDevice device = mBTAdapter.getRemoteDevice(address);

                if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        if (Build.VERSION.SDK_INT >= 31) {

                            Toast.makeText(MainActivity.this, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);

                        }
                    }

                } else {

                }

                if (Build.VERSION.SDK_INT >= 31) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 3);
                }

                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();


                for (BluetoothDevice device : pairedDevices) {
                    Log.i(TAG, "device->: " + device); //log para conocer dispositivo
                    Log.i(TAG, "address->: " + address); //log para conocer dispositivo
                    Log.e(TAG, "name->: " + name); //log para conocer dispositivo
                    Log.e(TAG, "name->: " + name.length()); //log para conocer dispositivo
                    Log.i(TAG, "--------------------------------"); //log para conocer dispositivo
                    Log.e(TAG, "device.getName()->: " + device.getName()); //log para conocer dispositivo
                    Log.e(TAG, "device.getName()->: " + device.getName().length()); //log para conocer dispositivo
                    Log.e(TAG, "device.getName().equals(name)->: " + device.getName().equals(name)); //log para conocer dispositivo
                    if (device.getName().equals(name)) {
                        Log.i(TAG, "device: " + device); //log para conocer dispositivo
                        Log.i(TAG, "device: " + device); //log para conocer dispositivo
                        Log.e(TAG, "device.getName()->: " + device.getName()); //log para conocer dispositivo
                        try {
                            Toast toast = Toast.makeText(MainActivity.this, "Desvinculando", Toast.LENGTH_LONG);
                            toast.show();
                            config.Dialog.AlertDialog("Desvinculando", "Desvinculando " + device.getName(), MainActivity.this);
                            Method m = device.getClass().getMethod("removeBond", (Class[]) null);
                            m.invoke(device, (Object[]) null);

                        } catch (Exception e) {
                            Log.e(TAG, "fallo: " + e.getCause().toString());
                            e.printStackTrace();
                        }
                    }

                }

            }
            mBTArrayAdapter2.clear(); // clear items
            LimpiarLista();

        }


    };


    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        try {
            final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", UUID.class);
            return (BluetoothSocket) m.invoke(device, BT_MODULE_UUID);
        } catch (Exception e) {
            Log.e(TAG, "Could not create Insecure RFComm Connection", e);
            config.Dialog.AlertDialog("FALLO", "Could not create Insecure RFComm Connection", this);
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (Build.VERSION.SDK_INT >= 31) {

                    Toast.makeText(this, "VERSION ES: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
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

    /*********************************************************************************************/

    public void LimpiarLista() {
        mBTArrayAdapter.clear(); // clear items
        mBTArrayAdapter2.clear(); // clear items
    }


    private void PeticionDemoEstructura() {

        peticion.peticionRetrofit.PeticionServidor("", new JsonObject(), this,

                new OnResponse() {
                    @Override
                    public void Response(DefinicionRespuestaRetrofit definicionRespuestaRetrofit) {
                    }

                    @Override
                    public void ErrorResponse(ErrorResponse errorResponse) {

                    }
                }
        );

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //replaces the default 'Back' button action
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            config.Toas.toastGrande(this, "Back", 25);
        }

        if (keyCode == KeyEvent.KEYCODE_0) {

        }
        return true;
    }

    @Override
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.i(TAG, "onLeaveHint");
        config.Toas.toastGrande(this, "onLeaveHint", 25);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            config.Toas.toastGrande(this, "LANDSCAPE", 25);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

        }
        config.Toas.toastGrande(this, "PORTRAIT", 25);


    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
        // cTimer.cancel();
    }


}









