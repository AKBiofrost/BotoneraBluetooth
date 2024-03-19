package ve.com.bivfrostgroup.demobluetoothfull.controladores.peticion;

import ve.com.bivfrostgroup.demobluetoothfull.interfaz.model.DefinicionRespuestaRetrofit;
import ve.com.bivfrostgroup.demobluetoothfull.interfaz.model.ErrorResponse;
public interface OnResponse {
    void Response (DefinicionRespuestaRetrofit definicionRespuestaRetrofit);

    void ErrorResponse (ErrorResponse errorResponse);
}
