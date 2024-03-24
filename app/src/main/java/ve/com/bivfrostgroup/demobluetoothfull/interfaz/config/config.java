package ve.com.bivfrostgroup.demobluetoothfull.interfaz.config;

import ve.com.bivfrostgroup.demobluetoothfull.controladores.BluetoothControladores;
import ve.com.bivfrostgroup.demobluetoothfull.controladores.TimeOut;
import ve.com.bivfrostgroup.demobluetoothfull.controladores.calibracion;
import ve.com.bivfrostgroup.demobluetoothfull.controladores.peticion.PeticionRetrofit;
import ve.com.bivfrostgroup.demobluetoothfull.controladores.toastCustomer;
import ve.com.bivfrostgroup.demobluetoothfull.interfaz.utiles.VentanaEmergente;

public class config {


    public static final calibracion calibrar = new calibracion();
    public static final BluetoothControladores BTControlador = new BluetoothControladores();
    public static final TimeOut timeOut = new TimeOut();
    public static final toastCustomer Toas = new toastCustomer();
    public static final VentanaEmergente Dialog = new VentanaEmergente();

}
