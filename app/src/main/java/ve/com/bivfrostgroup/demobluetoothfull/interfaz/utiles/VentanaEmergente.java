package ve.com.bivfrostgroup.demobluetoothfull.interfaz.utiles;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import org.json.JSONException;
import java.io.IOException;

import ve.com.bivfrostgroup.demobluetoothfull.R;


public class VentanaEmergente {

    public static final String TAG = "VentanaEmergente";
    private static final int REQUEST_CODE_BLUETOOTH_SCAN = 1;

    public int MultiEmpresaItempos = 0;
    public String MultiEmpresaItem = "";

    public AlertDialog.Builder alertDialogBuilder;
    AlertDialog dialog;
    private Context contextA;



    public void AlertDialog(String titulo, String mensaje, Context context){
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                    dialog.cancel();
                });
        CrearDialog();

    }

/*
    public void AlertDialog_error(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_error() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        ValidacionMpos MposValidacionInterna = new ValidacionMpos();
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)


                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();

                            if (mensaje != null) {
                                if (mensaje.equalsIgnoreCase("Operación no permitida.") == true) {
                                    alertDialogBuilder.show().dismiss();
                                    ((Activity) (context)).finish();
                                } else {
                                    //Log.e(TAG, "AlertDialog_error: " + context.getPackageName());
                                    //Log.w(TAG, "-----------------------------------------------");
                                    //Log.e(TAG, "mensaje de alerta");
                                    //Log.w(TAG, "-----------------------------------------------");

                                    if (MposValidacionInterna.GetModoIntegrado(context) == true) {
                                        alertDialogBuilder.show().dismiss();
                                        ((Activity) (context)).finish();
                                    } else {
                                        if (config.calibrar.ExistePreRegistro(context) == true) {
                                            alertDialogBuilder.show().dismiss();
                                            Intent i = new Intent(context, Cargando.class);
                                            (context).startActivity(i);
                                            ((Activity) (context)).finish();

                                        } else {
                                            if (config.calibrar.GetActionRelogin(context) == false) {
                                                alertDialogBuilder.show().dismiss();
                                                Intent i = new Intent(context, dashboard.class);
                                                (context).startActivity(i);
                                                ((Activity) (context)).finish();
                                            } else {
                                                alertDialogBuilder.setMessage("Esperando respuesta del Vpos Server").setCancelable(false);


                                            }

                                        }

                                    }

                                }

                            }


                        }

                );
        CrearDialog();
    }

    public void AlertDialog_errorNumeroControl( String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_error() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        ValidacionMpos MposValidacionInterna = new ValidacionMpos();
        alertDialogBuilder = new AlertDialog.Builder(context);
        AppPreferences reverso = new AppPreferences(context, "reverso");
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            reverso.saveSmsBody("", "reversoActual");
                            reverso.saveSmsBody("", "Secuencia");
                            reverso.saveSmsBody("", "vtidReverso");

                            if (mensaje != null) {
                                if (mensaje.equalsIgnoreCase("Operación no permitida.") == true) {
                                    ((Activity) (context)).finish();
                                } else {
                                    if (MposValidacionInterna.GetModoIntegrado(context) == true) {
                                        ((Activity) (context)).finish();
                                    } else {
                                        if (config.calibrar.ExistePreRegistro(context) == true) {
                                            Intent i = new Intent(context, Cargando.class);
                                            (context).startActivity(i);
                                            ((Activity) (context)).finish();

                                        } else {
                                            Intent i = new Intent(context, dashboard.class);
                                            (context).startActivity(i);
                                            ((Activity) (context)).finish();
                                        }

                                    }

                                }

                            }


                        }

                );
        CrearDialog();
    }

    public void AlertDialog_errorTarjeta(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_error() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        ValidacionMpos MposValidacionInterna = new ValidacionMpos();
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            discriminante.saveSmsBody(" ", "linkit");
                            if (mensaje != null) {
                                if (mensaje.equalsIgnoreCase("Operación no permitida.") == true) {
                                    ((Activity) (context)).finish();
                                } else {
                                    if (MposValidacionInterna.GetModoIntegrado(context) == true) {
                                        config.calibrar.LimpiarDatosTransaccion(context);
                                        config.calibrar.SetBotonFallBackmorefunVacio(context);
                                        ((Activity) (context)).finish();
                                    } else {
                                        if (config.calibrar.ExistePreRegistro(context) == true) {
                                            config.calibrar.LimpiarDatosTransaccion(context);
                                            config.calibrar.SetBotonFallBackmorefunVacio(context);
                                            Intent i = new Intent(context, Cargando.class);
                                            (context).startActivity(i);
                                        } else {
                                            config.calibrar.LimpiarDatosTransaccion(context);
                                            config.calibrar.SetBotonFallBackmorefunVacio(context);
                                            Intent i = new Intent(context, dashboard.class);
                                            (context).startActivity(i);

                                        }
                                    }

                                }

                            }


                        }

                );
        CrearDialog();
    }

    public void AlertDialog_Advertencia(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_Advertencia() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            //Log.e(TAG, "AlertDialog_error: " + context.getPackageName());
                            ((Activity) (context)).finish();
                        }

                );
        CrearDialog();
    }

    public void AlertDialog_DUKPT(Context context, int https, String mensaje) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        alertDialogBuilder = new AlertDialog.Builder(context);
        Activity activity = (Activity) context;
        View customLayout = inflater.inflate(R.layout.animaciondukpt, null);
        //alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setIcon(R.drawable.alert).setCancelable(false);
        TextView textView = customLayout.findViewById(R.id.textView9);
        TextView textView1 = customLayout.findViewById(R.id.text1);
        LottieAnimationView animacion = customLayout.findViewById(R.id.animationView);
        ImageView logo = customLayout.findViewById(R.id.logoDUKPT);
        Log.i(TAG, "AlertDialog_DUKPT:  entro con context: " + context);
        logo.setVisibility(View.GONE);
        if (https >= 199 && https <= 205) {
            textView.setText("Validando.....");
            textView1.setText("Cargando llave de seguridad");
            animacion.setAnimation(R.raw.paymentsuccessful);

        } else {
            textView.setText(mensaje);
            textView1.setText("Validacion fallida");
            animacion.setAnimation(R.raw.paymentfailed);
            alertDialogBuilder.setPositiveButton("Aceptar", (dialog, id) -> {
                dialog.cancel();
                //Log.e(TAG, "AlertDialog_error: " + context.getPackageName());
                Intent intent = new Intent(context, dashboard.class);
                activity.startActivity(intent);
                activity.finish();
            });
        }


        CrearDialog();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (https >= 199 && https <= 205) {
                    Log.i(TAG, "PREPARANDO CONFIRMACION DUKPT");
                    Log.i(TAG, " CONFIRMACION DUKPT");
                    config.llave.confirmar_dukpt(config.calibrar.Peticion_Get_JWT(context), context, config.preRegistros.PreregistroDUKPT(context));
                }
            }
        }, 2 * 1000);


    }

    public void AlertDialog_DUKPTEmparejar(Context context, int https, String mensaje) throws InterruptedException {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        alertDialogBuilder = new AlertDialog.Builder(context);
        Activity activity = (Activity) context;
        View customLayout = inflater.inflate(R.layout.castbot, null);
        //alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setIcon(R.drawable.alert).setCancelable(false);
        TextView textView = customLayout.findViewById(R.id.textView9);
        TextView textView1 = customLayout.findViewById(R.id.text1);
        LottieAnimationView animacion = customLayout.findViewById(R.id.animationView);
        Log.i(TAG, "AlertDialog_DUKPT:  entro con context: " + context);
        if (https >= 199 && https <= 205) {
            textView.setText("Validando.....");
            textView1.setText(mensaje);
            animacion.setAnimation(R.raw.btanimation);

        }
        ProgressBar mProgressBar;
        CountDownTimer mCountDownTimer;
        final int[] i = {0};
        mProgressBar = customLayout.findViewById(R.id.progressbar);
        mProgressBar.setProgress(i[0]);
        mProgressBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(56, 128, 255)));
        mCountDownTimer = new CountDownTimer(13000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress" + i[0] + millisUntilFinished);
                i[0]++;
                mProgressBar.setProgress((int) i[0] * 100 / (13000 / 1000));

            }

            @Override
            public void onFinish() {
                //Do what you want
                i[0]++;
                mProgressBar.setProgress(100);
            }
        };
        mCountDownTimer.start();

        CrearDialog();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (https >= 199 && https <= 205) {
                    Log.i(TAG, "PREPARANDO CONFIRMACION DUKPT");
                    Log.i(TAG, " CONFIRMACION DUKPT");

                }
            }
        }, 500);


    }

    public void AlertDialog_DUKPTS(Context context, int https, String mensaje) {
        // @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        alertDialogBuilder = new AlertDialog.Builder(context);
        Activity activity = (Activity) context;
        View customLayout = inflater.inflate(R.layout.animaciondukpt, null);
        // alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setIcon(R.drawable.alert).setCancelable(false);
        TextView textView = customLayout.findViewById(R.id.textView9);
        TextView textView1 = customLayout.findViewById(R.id.text1);
        LottieAnimationView animacion = customLayout.findViewById(R.id.animationView);
        ImageView logo = customLayout.findViewById(R.id.logoDUKPT);

        if (https >= 199 && https <= 205) {
            textView.setText("Validando.....");
            textView1.setText("Cargando llaves");
            animacion.setAnimation(R.raw.paymentsuccessful);
            logo.setVisibility(View.GONE);

        } else {
            textView.setText(mensaje);
            if (mensaje.equalsIgnoreCase("Se detectó que a este dispositivo ya se le cargaron llaves de seguridad")) {
                textView1.setText("");
                animacion.setVisibility(View.GONE);
                logo.setVisibility(View.VISIBLE);
            } else {
                textView1.setText("Validacion fallida");
                animacion.setAnimation(R.raw.paymentfailed);
                logo.setVisibility(View.GONE);
            }


            alertDialogBuilder.setPositiveButton("Aceptar", (dialog, id) -> {
                dialog.cancel();
                //Log.e(TAG, "AlertDialog_error: " + context.getPackageName());

                if (https == 409) {
                    Intent intent = new Intent(context, CargaAIDS_CAPKS.class);
                    context.startActivity(intent);
                    activity.finish();
                } else {
                    Intent intent = new Intent(context, dashboard.class);
                    activity.startActivity(intent);
                    activity.finish();
                }

            });
        }


        CrearDialog();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // config.llave.confirmar_dukpt(JWT, context,  body);

                //  Intent intent = new Intent(context, PeticionDUKPT.class);
                // context.startActivity(intent);
                //  activity.finish();

            }
        }, 3 * 1000);


    }

    public void AlertDialog_DUKPTRegistrado(Context context, int https, String mensaje) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        Activity activity = (Activity) context;
        View customLayout = inflater.inflate(R.layout.animaciondukpt, null);
        // alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setIcon(R.drawable.alert);
        TextView textView = customLayout.findViewById(R.id.textView9);
        TextView textView1 = customLayout.findViewById(R.id.text1);
        LottieAnimationView animacion = customLayout.findViewById(R.id.animationView);

        if (https > 199 && https <= 205) {
            textView.setText("Validando.....");
            textView1.setText("Cargando llaves");
            animacion.setAnimation(R.raw.paymentsuccessful);

        } else {
            textView.setText(mensaje);
            textView1.setText("Validacion fallida");
            animacion.setAnimation(R.raw.paymentfailed);
            alertDialogBuilder.setPositiveButton("Aceptar", (dialog, id) -> {
                dialog.cancel();
                //Log.e(TAG, "AlertDialog_error: " + context.getPackageName());
                Intent intent = new Intent(context, dashboard.class);
                activity.startActivity(intent);
                activity.finish();
            });
        }


        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(context, compra_monto.class);
                context.startActivity(intent);
                activity.finish();

            }
        }, 3 * 1000);


    }


    public void AlertDialog_AdvertenciaTarjeta_code500(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_AdvertenciaTarjeta_code500() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            //Log.e(TAG, "AlertDialog_error: " + context.getPackageName());
                            ((Activity) (context)).finish();
                        }

                );
        CrearDialog();
    }


    public void AlertDialog_AdvertenciaLogin(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_AdvertenciaLogin() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            //Log.e(TAG, "AlertDialog_error: " + context.getPackageName());
                            Intent i = new Intent(context, splashScreenConfiguracion.class);
                            (context).startActivity(i);
                        }

                );

    }


    public void AlertDialog_errorLogin(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_errorLogin() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            //TODO: toco javier
                            Intent i = new Intent(context, login.class);
                            (context).startActivity(i);

                        }

                );
        CrearDialog();
    }

    public void AlertDialog_cargaAIDS(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_cargaAIDS() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert)
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    Intent i = new Intent(context, splashScreenConfiguracion.class);
                    context.startActivity(i);
                    ((Activity) (context)).finish();
                    dialog.cancel();
                });
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            calibracion calibrar = new calibracion();

                            Log.w(TAG, "----------------------------------------------------------");
                            Log.d(TAG, "AlertDialog_cargaAIDS: REINTENTO DE ACTUALIZAR CAPKS Y AIDS");
                            Log.w(TAG, "----------------------------------------------------------");
                            calibrar.ActualizarAids_CAPKS(context);
                            dialog.cancel();
                            //TODO: toco javier

                        }

                );
        CrearDialog();
    }

    public void AlertDialog_cargaAIDSPOS(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_cargaAIDS() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert)
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    dialog.cancel();
                });
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            Intent i = new Intent(context, splashScreenConfiguracion.class);
                            context.startActivity(i);
                            ((Activity) (context)).finish();
                            dialog.cancel();
                            //TODO: toco javier

                        }

                );

    }

    public void AlertDialog_errorLoginAction(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_errorLoginAction() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            //TODO: toco javier
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());
                            Intent i = new Intent(context, splashScreenConfiguracion.class);
                            (context).startActivity(i);
                        }

                );
        CrearDialog();
    }

    public void AlertDialog_errorCredenciales(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_errorCredenciales() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            //TODO: toco javier
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());
                            Intent i = new Intent(context, login.class);
                            (context).startActivity(i);
                        }

                );
        CrearDialog();
    }


    public void AlertDialog_reintento_bluetooth(String titulo, String mensaje, Context context, Activity activity) {
        Log.d(TAG, "AlertDialog_reintento_bluetooth() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "], activity = [" + activity + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje + "\n\n ¿Desea reintentar?")
                .setNegativeButton("NO", (dialog, which) -> {
                    dialog.cancel();
                    Intent intent = new Intent(context, splashScreenConfiguracion.class);
                    context.startActivity(intent);
                    ((Activity) (context)).finish();

                })
                .setPositiveButton("SI", (dialog, id) -> {
                            dialog.cancel();
                            //TODO: toco
                            AlertDialog_SeleccionarBLuetooth(context, activity);
                        }

                );
        CrearDialog();
    }

    public void AlertDialog_reverso(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_reverso() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());
                            calibracion calibrar = new calibracion();
                            if (calibrar.ExistePreRegistro(context) == true) {
                                Intent i = new Intent(context, Cargando.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();

                            } else {
                                Intent i = new Intent(context, dashboard.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();
                            }
                        }

                );

    }

    public void AlertDialog_onbording(String titulo, String mensaje, Context context) {
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.logo_icono);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            Log.d(TAG, "AlertDialog_onbording: " + context.getPackageName());
                            AlertDialog_onbording2("Tenga el pinpad a la mano", "Su pinpad debe estar encendio para el siguiente proceso, " +
                                    "¿desea continuar?", context);

                        }

                );


        // dlgAlert.create();
        AlertDialog dialog_card = alertDialogBuilder.create();
        // dlgAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // WindowManager.LayoutParams WMLP =
        dialog_card.getWindow().setGravity(Gravity.CENTER);
        dialog_card.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        //  dialog_card.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_card.show();

    }

    public void AlertDialog_onbordingFIN(String titulo, String mensaje, Context context) {
        alertDialogBuilder = new AlertDialog.Builder(context);
        Activity activity = (Activity) context;
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.logo_icono);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            Log.d(TAG, "AlertDialog_onbording: " + context.getPackageName());
                            Intent intent = new Intent(context, dashboard.class);
                            context.startActivity(intent);
                            activity.finish();
                        }

                );


        // dlgAlert.create();
        AlertDialog dialog_card = alertDialogBuilder.create();
        // dlgAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // WindowManager.LayoutParams WMLP =
        dialog_card.getWindow().setGravity(Gravity.CENTER);
        dialog_card.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        //  dialog_card.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_card.show();

    }


    public void AlertDialog_onbording2(String titulo, String mensaje, Context context) {
        Activity activity = (Activity) context;
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.logo_icono);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            Log.d(TAG, "AlertDialog_onbording: " + context.getPackageName());
                            Intent intent = new Intent(context, BotoneraBLuetooth.class);
                            //Intent intent = new Intent(context, DeviceListActivity.class);
                            context.startActivity(intent);
                            activity.finish();

                        }

                ).setNegativeButton("Cancelar", (dialog, id) -> {
                    Intent intent = new Intent(context, dashboard.class);
                    //Intent intent = new Intent(context, DeviceListActivity.class);
                    context.startActivity(intent);
                    activity.finish();

                });

        // dlgAlert.create();
        AlertDialog dialog_card = alertDialogBuilder.create();
        // dlgAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // WindowManager.LayoutParams WMLP =
        dialog_card.getWindow().setGravity(Gravity.CENTER);
        //      dialog_card.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_card.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        dialog_card.show();

    }

    public void AlertDialog_Reverso(Context context, String cedula, String estado, String monto,
                                    String voucher, String secuencia) {

        Log.d(TAG, "AlertDialog_Reverso() called with: context = [" + context + "], alertDialogBuilder = [" + alertDialogBuilder + "], cedula = [" + cedula + "], estado = [" + estado + "], monto = [" + monto + "], voucher = [" + voucher + "], secuencia = [" + secuencia + "]");
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        autentificacion login = new autentificacion(true, 200);
        View customLayout = inflater.inflate(R.layout.vistareverso, null);
        TextView estadoR = customLayout.findViewById(R.id.estado);
        TextView seq = customLayout.findViewById(R.id.seq);
        TextView cedulaR = customLayout.findViewById(R.id.cedula);
        TextView montoR = customLayout.findViewById(R.id.monto);
        TextView voucherC = customLayout.findViewById(R.id.voucherC);

        estadoR.setText(estado);
        seq.setText(secuencia);
        cedulaR.setText(cedula);
        montoR.setText(monto);
        voucherC.setText(voucher);

        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setIcon(R.drawable.alert)
                .setPositiveButton(
                        "Aceptar",
                        (dialog, which) -> {

                            dialog.cancel();
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());
                            calibracion calibrar = new calibracion();
                            if (calibrar.ExistePreRegistro(context) == true) {
                                Intent i = new Intent(context, Cargando.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();

                            } else {
                                Intent i = new Intent(context, dashboard.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();
                            }

                        });
        CrearDialog();

    }

    public void AlertDialog_info(Context context, String serial, String kernel, String marca,
                                 String pinpadOS, String pinblock) {
        Log.d(TAG, "AlertDialog_info() called with: context = [" + context + "], alertDialogBuilder = [" + alertDialogBuilder + "], serial = [" + serial + "], kernel = [" + kernel + "], marca = [" + marca + "], pinpadOS = [" + pinpadOS + "], pinblock = [" + pinblock + "]");
        manipularJSON info = new manipularJSON();
        Log.i("VentanaEmergente", "entro en ventana info");
        alertDialogBuilder = new AlertDialog.Builder(context);
        Body_DatosDispositivos infoDispositivo = new Body_DatosDispositivos();
        infoDispositivo = info.ReadingDatosDispositivos(URL_API.JSON_datosDispositivos, context.getApplicationContext().getPackageName());
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customLayout = inflater.inflate(R.layout.dialog_info, null);

        TextView estadoR = customLayout.findViewById(R.id.estado);
        //TextView seq = customLayout.findViewById(R.id.seq);
        TextView cedulaR = customLayout.findViewById(R.id.cedula);
        TextView montoR = customLayout.findViewById(R.id.monto);
        TextView voucherC = customLayout.findViewById(R.id.voucherC);

        estadoR.setText(marca);
        //seq.setText(pinblock);
        cedulaR.setText(serial);
        montoR.setText(kernel);
        voucherC.setText(pinpadOS);

        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setIcon(R.drawable.alert)
                .setPositiveButton(
                        "Aceptar",
                        (dialog, which) -> {

                            dialog.cancel();
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());

                        })
                .setNegativeButton("", (dialog, which) -> {
                    ((AlertDialog) dialog).getButton(which).setVisibility(View.INVISIBLE);
                });


        CrearDialog();

    }


    public void AlertDialog_alerta(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_alerta() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            discriminante.saveSmsBody("revisado", "timerC");
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());
                            calibracion calibrar = new calibracion();
                            if (calibrar.ExistePreRegistro(context) == true) {
                                Intent i = new Intent(context, Cargando.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();

                            } else {

                                Intent i = new Intent(context, dashboard.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();
                            }
                        }

                );
        CrearDialog();
    }

    public void AlertDialog_alertaPeticion(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_alerta() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            discriminante.saveSmsBody("revisado", "timerC");
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());
                            calibracion Calibrarpos = new calibracion();
                            AppPreferences Transaccion = new AppPreferences(context, URL_API.transaccion);
                            AlertDialog.Builder alertDialogBuilderSincronizar = new AlertDialog.Builder(context);
                            String capks = Transaccion.getSmsBody("capks");
                            String aids = Transaccion.getSmsBody("aids");
                            String JWT = Calibrarpos.Peticion_Get_JWT(context);
                            Activity activity = (Activity) context;

                            if (capks.equalsIgnoreCase("existe") == false || aids.equalsIgnoreCase("existe") == false) {
                                AlertDialog_alerta_Informar("peticitión de llaves", "procesando petición", context);
                                Calibrarpos.peticion_aids(JWT, context, activity);
                                Calibrarpos.peticion_capks(JWT, context, activity);
                            }

                        }

                );
        CrearDialog();
    }

    public void AlertDialog_alertaBluetooth(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_alertaBluetooth() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            discriminante.saveSmsBody("revisado", "timerC");
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());
                            Intent i = new Intent(context, config_pinpadBluetooth_miscelaneo.class);
                            (context).startActivity(i);
                            ((Activity) (context)).finish();
                        }

                );
        CrearDialog();
    }

    public void AlertDialog_alerta_Informar(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_alerta_Informar() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        AlertDialog.Builder alertDialogBuilderQ;
        alertDialogBuilderQ = new AlertDialog.Builder(context);
        alertDialogBuilderQ.setTitle(titulo);
        alertDialogBuilderQ.setIcon(R.drawable.alert);
        alertDialogBuilderQ.setMessage(mensaje).setCancelable(false)
        ;
        CrearDialog();
    }

    public void AlertDialog_alerta_InformarAction(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_alerta_Informar() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            discriminante.saveSmsBody("revisado", "timerC");
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());
                            calibracion calibrar = new calibracion();
                            if (calibrar.ExistePreRegistro(context) == true) {
                                Intent i = new Intent(context, Cargando.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();

                            } else {
                                Intent i = new Intent(context, dashboard.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();
                            }
                        }

                );
        CrearDialog();
    }


    public void AlertDialog_alertaAnulacion(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_alertaAnulacion() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            discriminante.saveSmsBody("revisado", "timerC");
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());
                            calibracion calibrar = new calibracion();
                            if (calibrar.ExistePreRegistro(context) == true) {
                                Intent i = new Intent(context, Cargando.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();

                            } else {
                                Intent i = new Intent(context, dashboard.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();
                            }

                        }

                );
        CrearDialog();
    }


    public void AlertDialog_alertaRecibo(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_alertaRecibo() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        AppPreferences finalizar = new AppPreferences(context, "voucher");
        String fin = finalizar.getSmsBody("voucherultimo");


        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            discriminante.saveSmsBody("revisado", "timerC");
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());

                            if (fin.equalsIgnoreCase("si")) {
                                Intent i = new Intent(context, dashboard.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();

                            } else {
                                calibracion calibrar = new calibracion();
                                if (calibrar.ExistePreRegistro(context) == true) {
                                    Intent i = new Intent(context, Cargando.class);
                                    (context).startActivity(i);
                                    ((Activity) (context)).finish();
                                } else {
                                    Intent i = new Intent(context, dashboard.class);
                                    (context).startActivity(i);
                                    ((Activity) (context)).finish();

                                }
                            }


                        }

                );
        CrearDialog();
    }


    public void AlertDialog_alertaIntegrado(String titulo, String mensaje,
                                            Context context, Activity activity) {
        Log.d(TAG, "AlertDialog_alertaIntegrado() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "], activity = [" + activity + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());

                            // Intent intent = new Intent();
                            // intent.putExtra("extras", "javier inventa los mensajes");
                            // Log.w(TAG, "AlertDialog_alertaIntegrado: " + activity.getPackageName());
                            // activity.setResult(RESULT_OK, intent);
                            // ((Activity) (context)).finish();
                        }

                );
        CrearDialog();
    }

    public void AlertDialog_alertaDesincronizar(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_alertaDesincronizar() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            int validadorPos = 0;
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            AppPreferences bluetoothM = new AppPreferences(context, "Bluetooth");
                            AppPreferences bluetoothMT = new AppPreferences(context, "Bluetooth");
                            validadorPos = discriminante.getSmsBody_int("EsPOS");
                            if (bluetoothM.getSmsBody("morefun").equalsIgnoreCase("activo") == true) {
                                validadorPos = 1;
                            }

                            if (validadorPos == 1) {
                                Log.w(TAG, "-----------------------------------------------------------------------");
                                Log.d(TAG, "--" + "ENTRO EN POS, Y VA A VOLVERLO CELULAR");
                                Log.d(TAG, "--" + validadorPos);
                                Log.w(TAG, "-----------------------------------------------------------------------");
                                discriminante.saveSmsBody_boolean(0, "EsPOS");
                                bluetoothM.saveSmsBody("", "morefun");


                                Log.d(TAG, "AlertDialog_error: " + context.getPackageName());
                                Intent i = new Intent(context, splashScreenConfiguracion.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();
                            } else {
                                Log.d(TAG, "AlertDialog_error: " + context.getPackageName());
                                Intent i = new Intent(context, splashScreenConfiguracion.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();

                            }


                        }

                )
                .setNegativeButton("Cancelar", (dialog, id) -> {
                            dialog.cancel();
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());
                            calibracion calibrar = new calibracion();
                            if (calibrar.ExistePreRegistro(context) == true) {
                                Intent i = new Intent(context, Cargando.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();

                            } else {
                                Intent i = new Intent(context, dashboard.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();
                            }
                        }

                )

        ;
        CrearDialog();
    }


    public void AlertDialog_Emparejar(String titulo, String mensaje, Context context, Activity A) {
        Log.d(TAG, "AlertDialog_Emparejar() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "], A = [" + A + "]");
        CalibracionBLuetooth bLuetooth = new CalibracionBLuetooth();
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.logo_icono);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {

                            if (bLuetooth.dispositivosBluetooth(context)) {

                                dialog.cancel();
                            }


                        }

                );
        CrearDialog();
    }


    public void AlertDialog_alertaTarjeta(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_alertaTarjeta() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            discriminante.saveSmsBody("revisado", "timerC");
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());
                            AppPreferences cancelarTransaccion = new AppPreferences(context, "cancelado");
                            AppPreferences codigo = new AppPreferences(context, URL_API.transaccion);
                            String codigoRespuesta = codigo.getSmsBody("parametro_codigo");

                            if (codigoRespuesta.equalsIgnoreCase("XA")) {
                                config.calibrar.SetTransaccionCanceladaComunicacion(context);
                            } else {
                                config.calibrar.SetCancelarTransaccion(context,true );
                            }


                            ((Activity) (context)).finish();
                        }

                );
        CrearDialog();
    }

    public void AlertDialog_alertaAnulacionComunicacion(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_alertaTarjeta() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        calibracion calibrar = new calibracion();
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            discriminante.saveSmsBody("revisado", "timerC");
                            Log.d(TAG, "AlertDialog_error: " + context.getPackageName());

                            if (calibrar.MarcaModeloPOS_VERIFONE(context) == true) {
                                Finalizar_VerifoneAnulacion(context);
                            } else {
                                Intent i = new Intent(context, RespuestaTransaccion.class);
                                (context).startActivity(i);
                                ((Activity) (context)).finish();
                            }

                        }

                );
        CrearDialog();
    }

    public void AlertDialog_alerta_monto(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_alerta_monto() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                        }

                );
        CrearDialog();
    }

    public void AlertDialog_advertencia_sin_accion(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_advertencia_sin_accion() called with: alertDialogBuilder = [" + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                        }

                );
        CrearDialog();
    }

    public void AlertDialog_alertabaja(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_alertabaja() called with: titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            discriminante.saveSmsBody("revisado", "timerC");
                            Log.e(TAG, "AlertDialog_error: " + context.getPackageName());
                            Intent i = new Intent(context, dashboard.class);
                            (context).startActivity(i);
                            ((Activity) (context)).finish();
                        }

                );
        CrearDialog();
    }

    public void AlertDialog_CerarSesion(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_CerarSesion() called with: titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        calibracion calibrar = new calibracion();
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            ((Activity) (context)).finish();
                            //creamos un nuevo intent de action_main para el cierre de todo lo que esté abierto
                            calibrar.LimpiarDatos(context);

                            ((Activity) (context)).finishAffinity();
                        }


                ).setNegativeButton("Cancelar", (dialog, id) -> {
                    dialog.cancel();
                });
        CrearDialog();
    }


    public void AlertDialog_alerta_transaccion( String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_alerta_transaccion() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            discriminante.saveSmsBody("revisado", "timerC");
                            //Log.e(TAG, "AlertDialog_error: " + context.getPackageName());

                        }

                );
        CrearDialog();
    }


    public void AlertDialog_alertaFinish(String titulo, String mensaje, Context context) {
        Log.d(TAG, "AlertDialog_alertaFinish() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            //Log.e(TAG, "AlertDialog_error: " + context.getPackageName());
                            Intent i = new Intent(context, login.class);
                            (context).startActivity(i);
                            ((Activity) (context)).finish();
                        }
                );
        CrearDialog();
    }

    public void AlertDialog_MultiEmpresa(String titulo, String mensaje, String Empresa, Context context) {
        Log.d(TAG, "AlertDialog_MultiEmpresa() called with: alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], Empresa = [" + Empresa + "], context = [" + context + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) ->

                        {
                            try {
                                multiEmpresa.ActionFinalizarp2c(context.getApplicationContext().getPackageName(), Empresa, context);
                            } catch (Throwable e) {
                                // //
                                //Log.w(TAG, "-----------------------------------------------------------");
                                //Log.e(TAG, "catch"+ e);
                                //Log.w(TAG, "-----------------------------------------------------------");
                            }
                        }

                );
        CrearDialog();
    }

    public void AlertDialog_OTP(String usuario, String Contraseña,
                                Context context, String titulo, String mensaje,
                                String modelo, Activity activity) {

        Log.d(TAG, "AlertDialog_OTP() called with: usuario = [" + usuario + "], Contraseña = [" + Contraseña + "], context = [" + context + "], alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], modelo = [" + modelo + "], activity = [" + activity + "]");
        datosPOS bodyC2P = new datosPOS();
        manipularJSON json = new manipularJSON();
        String serial = "";
        alertDialogBuilder = new AlertDialog.Builder(context);
        calibracion calibrar = new calibracion();
        bodyC2P = json.ReadingDatosDispositivosPOS(URL_API.JSON_Datospos, context.getApplicationContext().getPackageName());
        Body_DatosDispositivos body_datosDispositivosCelular = new Body_DatosDispositivos();
        body_datosDispositivosCelular = json.ReadingDatosDispositivos(URL_API.JSON_datosDispositivos, context.getApplicationContext().getPackageName());
        AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
        String validadorModelo = discriminante.getSmsBody("modelo_equipo");
        AppPreferences bluetoothM = new AppPreferences(context, "Bluetooth");
        int validadorPos = discriminante.getSmsBody_int("EsPOS");
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        autentificacion login = new autentificacion(true, 200);
        View customLayout = inflater.inflate(R.layout.dialog_otp, null);

        if (calibrar.EsPOS(context) == true) {

            if (calibrar.EsPOSBluetooth(context) == true) {

                Log.e(TAG, "----------: " + " morefun Bluetooth");
                body_datosDispositivosCelular = json.ReadingDatosDispositivos(URL_API.JSON_datosDispositivos, context.getApplicationContext().getPackageName());
                serial = bodyC2P.getDatosDispositivo().getSerial();

            } else if (validadorModelo.equalsIgnoreCase("morefun")) {
                Log.e(TAG, "----------: " + " morefun");
                bodyC2P = json.ReadingDatosDispositivosPOS(URL_API.JSON_Datospos, context.getApplicationContext().getPackageName());
                serial = bodyC2P.getDatosDispositivo().getSerial();
            } else if (validadorModelo.equalsIgnoreCase("sunyard")) {
                Log.e(TAG, "----------: " + " sunyard");
                bodyC2P = json.ReadingDatosDispositivosPOS(URL_API.JSON_Datospos, context.getApplicationContext().getPackageName());
                serial = bodyC2P.getDatosDispositivo().getSerial();
            } else if (validadorModelo.equalsIgnoreCase("verifone")) {
                bodyC2P = json.ReadingDatosDispositivosPOS(URL_API.JSON_Datospos, context.getApplicationContext().getPackageName());
                Log.e(TAG, "BotonLogin:  bodyC2P.getDatosDispositivo().getSerial() " + bodyC2P.getDatosDispositivo().getSerial());
                serial = bodyC2P.getDatosDispositivo().getSerial();
            }

        } else {
            Log.e(TAG, "BotonLogin: " + " ES UN CELULAR");
            body_datosDispositivosCelular = json.ReadingDatosDispositivos(URL_API.JSON_datosDispositivos, context.getApplicationContext().getPackageName());

            serial = body_datosDispositivosCelular.getDatosDispositivo().getPinPadSerial();
            Log.e(TAG, "BotonLogin: ->" + body_datosDispositivosCelular.getDatosDispositivo().getPinPadSerial());

        }
/*
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setIcon(R.drawable.alert);
        datosPOS finalBodyC2P = bodyC2P;
        String serialT = serial;
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton(
                        "Aceptar",
                        (dialog, which) -> {

                            EditText OTP = customLayout.findViewById(R.id.editText);
                            String otp_edittex = OTP.getText().toString().trim();
                            JsonObject Obj_otp = new JsonObject();
                            Obj_otp.addProperty("otp", otp_edittex);
                            Obj_otp.addProperty("serialDispositivo", serialT);
                            Log.e(TAG, "AlertDialog_OTP: " + serialT);
                            try {
                                Log.e(TAG, "AlertDialog_OTP: " + "otp_edittex.length()->" + otp_edittex.length());
                                if (otp_edittex.length() > 0 && otp_edittex.length() <= 6) {
                                    if (otp_edittex.length() == 6) {
                                        dialog.cancel();
                                        AlertDialog_EsperandoRespuestaServer(context, "Enviando petición", "Estamos procesando su usuario, espero un momento", false);
                                        login.OTP(Obj_otp, usuario, Contraseña, context, URL_API.SharedPreferend_JWT, modelo, activity);

                                    } else {
                                        Toast.makeText(context, "OTP debe contener 6 caracteres.", Toast.LENGTH_SHORT).show();
                                        // ((Activity) (context)).finish();
                                        AlertDialog_OTP(usuario, Contraseña, context,
                                                "Ingrese su código OTP", "", modelo, activity);
                                    }

                                } else {
                                    Toast.makeText(context, "OTP debe contener 6 caracteres.", Toast.LENGTH_SHORT).show();
                                    //((Activity) (context)).finish();
                                    AlertDialog_OTP(usuario, Contraseña, context,
                                            "Ingrese su código OTP", "", modelo, activity);
                                }


                            } catch (JSONException e) {
                                // //
                                Log.w(TAG, "-----------------------------------------------------------");
                                Log.e(TAG, "catch" + e.getMessage());
                                Log.w(TAG, "-----------------------------------------------------------");
                            }


                        });
        CrearDialog();


    }
*/
    /*
    public void AlertDialog_SeleccionarVtid(Context context) {
        Log.d(TAG, "AlertDialog_SeleccionarVtid() called with: context = [" + context + "], alertDialogBuilder = [" + alertDialogBuilder + "]");
        AppPreferences MultiEmpresa = new AppPreferences(context, URL_API.multi);
        String BooleanMulti = MultiEmpresa.getSmsBody(URL_API.multi);
        alertDialogBuilder = new AlertDialog.Builder(context);
        AlgorithmAdapter_multiempresa adapter_multiempresa = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customLayout = inflater.inflate(R.layout.seleccionar_vtid, null);
        ArrayList<AlgorithmItem_multi> Items_multiempresa = null;
        Items_multiempresa = multiEmpresa(context);
        Spinner multiempresa;

        multiempresa = customLayout.findViewById(R.id.combo_multiEmpresa);

        PresentarSpinnerMulti(adapter_multiempresa, multiempresa, Items_multiempresa, context);


        alertDialogBuilder.setView(customLayout);


        alertDialogBuilder.setIcon(R.drawable.alert)
                .setPositiveButton(
                        "Aceptar",
                        (dialog, which) -> {


                            if (true) {

                            } else {
                                ((Activity) (context)).finish();
                            }


                        });
        CrearDialog();

    }
*/

    /*
    public void AlerDialog_ActionMarcaReverso(Context context) {
        AppPreferences reverso = new AppPreferences(context, "reverso");
        JSON_preRegistros preRegistros = new JSON_preRegistros();
        calibracion calibrar = new calibracion();
        Activity activity = (Activity) context;
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Hay marca de reverso");
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage("Se detectó un problema ejecutando la última " +
                        "transacción, presione continuar para ver la última operación" +
                        " registrada.").setCancelable(false)
                .setPositiveButton("Continuar", (dialog, id) -> {
                            dialog.cancel();
                            ValidadorReverso Reverso;
                            JsonObject bodyReverso;
                            String _jwt = preRegistros.JWT_preregistro(context);

                            if (calibrar.EsMultiEmpresa(context) == true) {
                                Reverso = new ValidadorReverso(calibrar.VtidmultiEmpresa(context), context);
                            } else {
                                Reverso = new ValidadorReverso(calibrar.VTIDsimple(context), context);
                            }

                            AppPreferences _voucher = new AppPreferences(context, "voucher");
                            _voucher.saveSmsBody("si", "voucherultimo");
                            Log.e(TAG, "VoucherProcesado");
                            AlertDialog_EsperandoRespuestaServer(context, "Preparando transacción", "Espere por favor.", false);

                            bodyReverso = preRegistros.Preregistro_Reverso(context);
                            Reverso.peticion_Reverso(preRegistros.JWT_preregistro(context), bodyReverso, context, config.Dialog, activity);


                        }
                );

        CrearDialog();
    }

*/
    /*
    public void AlertDialog_SeleccionarBLuetooth(Context context, Activity A) {
        Log.d(TAG, "AlertDialog_SeleccionarBLuetooth() called with: context = [" + context + "], alertDialogBuilder = [" + alertDialogBuilder + "], A = [" + A + "]");
        AppPreferences MultiEmpresa = new AppPreferences(context, URL_API.multi);
        String BooleanMulti = MultiEmpresa.getSmsBody(URL_API.multi);
        AppPreferences Transaccion = new AppPreferences(context, URL_API.transaccion);
        calibracion Calibrarpos = new calibracion();
        alertDialogBuilder = new AlertDialog.Builder(context);
        AlgorithmAdapter_multiempresa adapter_multiempresa = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customLayout = inflater.inflate(R.layout.seleccionar_bluetooth, null);
        ArrayList<AlgorithmItem_multi> Items_multiempresa = null;
        LoginLinkit = new AppPreferences(context, "linkit_login");
        discriminante = new AppPreferences(context, URL_API.determinante);
        bluetoothM = new AppPreferences(context, "Bluetooth");
        Items_multiempresa = bluetooth(context);
        Spinner multiempresa;
        this.contextA = context;

        multiempresa = customLayout.findViewById(R.id.combo_multiEmpresa);

        PresentarSpinnerBluetooth(adapter_multiempresa, multiempresa, Items_multiempresa, context);

        alertDialogBuilder.setView(customLayout);
        String capks = Transaccion.getSmsBody("capks");
        String aids = Transaccion.getSmsBody("aids");
        String JWT = Calibrarpos.Get_JWT(context);


        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        alertDialogBuilder.setIcon(R.drawable.alert)
                .setPositiveButton(
                        "Aceptar",
                        (dialog, which) -> {


                            try {
                                AlertDialog_Sincronizando(context, "Sincronizando equipo", "Mantenga el pinpad encendio y cerca del equipo", true);

                                new Handler().postDelayed(new Runnable() {
                                    boolean open = false;

                                    @Override
                                    public void run() {

                                        try {
                                            pos = new MPosDeviceImpl();
                                            pos.open(context);
                                            open = true;
                                        } catch (Exception e) {
                                            Log.e(TAG, "---" + e);
                                        }


                                    }
                                }, 1000);
                            } catch (Exception e) {
                                Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
                                } else {
                                    //deprecated in API 26
                                    v.vibrate(1000);
                                }
                                alertDialogBuilder.show().cancel();
                                AlertDialog_reintento_bluetooth("No se encontro dispositivo", "No se encontro dispositivo pinpad.", context, A);

                            }

                        });
        alertDialogBuilder.setIcon(R.drawable.alert)
                .setNegativeButton(
                        "Cancelar",
                        (dialog, which) -> {
                            Log.e(TAG, "----------: " + " El dispositivo continuara trabajando en modo telefono");
                            alertDialogBuilder.show().cancel();
                            Intent i = new Intent(context, dashboard.class);
                            (context).startActivity(i);
                            ((Activity) (context)).finish();


                        });
        alertDialogBuilder.setCancelable(false);
        CrearDialog();

    }


    public ArrayList<AlgorithmItem_multi> multiEmpresa(Context context) {
        Log.d(TAG, "multiEmpresa() called with: context = [" + context + "]");
        manipularJSON json = new manipularJSON();
        empresas multiempresas = new empresas();
        ArrayList<AlgorithmItem_multi> Items_multiempresa;
        ArrayList<String> SubjectNames;
        SubjectNames = new ArrayList<>();
        Items_multiempresa = new ArrayList<>();

        multiempresas = json.ReadingMultiEmpresa(URL_API.JSON_multiempresa, context.getApplicationContext().getPackageName());

        if (multiempresas != null) {
            for (int i = 0; i < multiempresas.getMultiempresa().size(); i++) {


                Items_multiempresa.add(new AlgorithmItem_multi(multiempresas.getMultiempresa().get(i).getEmpresa()));

                SubjectNames.add(multiempresas.getMultiempresa().get(i).getEmpresa());

            }

        }


        return Items_multiempresa;
    }

    public ArrayList<AlgorithmItem_multi> bluetooth(Context context) {
        Log.d(TAG, "bluetooth() called with: context = [" + context + "]");
        manipularJSON json = new manipularJSON();
        listaEmparejados multiempresas = new listaEmparejados();
        ArrayList<AlgorithmItem_multi> Items_multiempresa;
        ArrayList<String> SubjectNames;
        SubjectNames = new ArrayList<>();
        Items_multiempresa = new ArrayList<>();
        SubjectNames.add("seleccione un dispositivo");
        multiempresas = json.ReadingListaEmparejado("EmparejadosBluetooth.json", context.getApplicationContext().getPackageName());

        if (multiempresas != null) {
            for (int i = 0; i < multiempresas.getEmparejado().size(); i++) {
                Items_multiempresa.add(new AlgorithmItem_multi(multiempresas.getEmparejado().get(i).getName()));
                SubjectNames.add(multiempresas.getEmparejado().get(i).getName());
            }


        }


        return Items_multiempresa;
    }

    public void PresentarSpinnerMulti(AlgorithmAdapter_multiempresa adapter, Spinner spinner, ArrayList<AlgorithmItem_multi> item, Context context) {
        Log.d(TAG, "PresentarSpinnerMulti() called with: adapter = [" + adapter + "], spinner = [" + spinner + "], item = [" + item + "], context = [" + context + "]");
        adapter = new AlgorithmAdapter_multiempresa(context, item);
        AppPreferences GuardarMultiEmpresa = new AppPreferences(context, "EmpresaSeleccionada");
        ValidacionMpos MposValidacionInterna = new ValidacionMpos();
        spinner.setAdapter(adapter);
        final peticion[] ajustes = {new peticion()};
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // It returns the clicked item.
                AlgorithmItem_multi clickedItem = (AlgorithmItem_multi) parent.getItemAtPosition(position);

                String name = clickedItem.getAlgorithmName();
                MultiEmpresaItem = name;
                String MultiEmpresaItempos = String.valueOf(position);

                if (MposValidacionInterna.GetModoIntegrado(context) == true) {
                    Log.e(TAG, " ES INTEGRADO");
                    ajustes[0] = config.PeticionIntegrado.GetPeticionIntegrado();

                    if (GuardarMultiEmpresa.getSmsBody("vtidlista").equalsIgnoreCase("existe")) {
                        GuardarMultiEmpresa.saveSmsBody(ajustes[0].getVtid(), "MultiEmpresaItem");
                    }


                } else {


                    GuardarMultiEmpresa.saveSmsBody(MultiEmpresaItem, "MultiEmpresaItem");
                    GuardarMultiEmpresa.saveSmsBody(MultiEmpresaItempos, "MultiEmpresaItempos");
                    Log.e(TAG, "---------------------------------------------------------");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    public void PresentarSpinnerBluetooth(AlgorithmAdapter_multiempresa adapter, Spinner spinner, ArrayList<AlgorithmItem_multi> item, Context context) {
        Log.d(TAG, "PresentarSpinnerBluetooth() called with: adapter = [" + adapter + "], spinner = [" + spinner + "], item = [" + item + "], context = [" + context + "]");
        adapter = new AlgorithmAdapter_multiempresa(context, item);
        AppPreferences GuardarBluetooth = new AppPreferences(context, "Bluetooth");
        AppPreferences POS = new AppPreferences(context, URL_API.determinante);
        AppPreferences bluetoothM = new AppPreferences(context, "Bluetooth");

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // It returns the clicked item.
                AlgorithmItem_multi clickedItem = (AlgorithmItem_multi) parent.getItemAtPosition(position);

                String name = clickedItem.getAlgorithmName();
                MultiEmpresaItem = name;
                String MultiEmpresaItempos = String.valueOf(position);
                GuardarBluetooth.saveSmsBody(MultiEmpresaItem, "BluetoothName");
                GuardarBluetooth.saveSmsBody(MultiEmpresaItempos, "Bluetoothpos");


                // Use this check to determine whether Bluetooth classic is supported on the device.
// Then you can selectively disable BLE-related features.
                if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)) {
                    Toast.makeText(context, "bluetooth no soportado", Toast.LENGTH_SHORT).show();
                    ((Activity) (context)).finish();
                }

                if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
                    Toast.makeText(context, "bluetooth no soportado", Toast.LENGTH_SHORT).show();
                    ((Activity) (context)).finish();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }


    public void AlertDialog_ActualizarCredenciales(Context context, String titulo, String mensaje, Activity activity) {

        Log.d(TAG, "AlertDialog_ActualizarCredenciales() called with: context = [" + context + "], alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], activity = [" + activity + "]");
        Log.i(TAG, "Hacer AUTOLOGIN para ActualizarCredenciales");
        alertDialogBuilder = new AlertDialog.Builder(context);
        AppPreferences credenciales = new AppPreferences(context, "credenciales");
        autentificacion login = new autentificacion(true, 200);
        calibracion calibrar = new calibracion();
        calibrar.SetActionRelogin(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton(
                        "Aceptar",
                        (dialog, which) -> {

                            JsonObject BodySerial = new JsonObject();

                            datosPOS bodyC2P = new datosPOS();
                            Body_DatosDispositivos body_datosDispositivosCelular = new Body_DatosDispositivos();
                            manipularJSON json = new manipularJSON();
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            JsonObject body = new JsonObject();
                            credenciales.saveSmsBody("sucedio", "Relogin");
                            String usuarioR = credenciales.getSmsBody("usuario");
                            String contraseñaR = credenciales.getSmsBody("contraseña");

                            Log.d(TAG, "usuarioR: " + usuarioR);
                            Log.d(TAG, "contraseñaR: " + contraseñaR);
                            //    usuarioR="desamulti";
                            //    contraseñaR="M3g@s0f7";

                            if (calibrar.EsPOS(context) == true) {

                                if (calibrar.EsPOSBluetooth(context) == true) {
                                    Log.e(TAG, "AlertDialog_ActualizarCredenciales: " + "Bluetooth");
                                    body_datosDispositivosCelular = json.ReadingDatosDispositivos(URL_API.JSON_datosDispositivos, context.getApplicationContext().getPackageName());
                                    body.addProperty("serialDispositivo", body_datosDispositivosCelular.getDatosDispositivo().getPinPadSerial());
                                }
                                if (calibrar.MarcaModeloPOS_MOREFUN(context) == true) {
                                    Log.e(TAG, "----------: " + " morefun");
                                    bodyC2P = json.ReadingDatosDispositivosPOS(URL_API.JSON_Datospos, context.getApplicationContext().getPackageName());
                                    body.addProperty("serialDispositivo", bodyC2P.getDatosDispositivo().getSerial());
                                } else if (calibrar.MarcaModeloPOS_SUNYARD(context)) {
                                    Log.e(TAG, "----------: " + " sunyard");
                                    bodyC2P = json.ReadingDatosDispositivosPOS(URL_API.JSON_Datospos, context.getApplicationContext().getPackageName());
                                    body.addProperty("serialDispositivo", bodyC2P.getDatosDispositivo().getSerial());
                                } else if (calibrar.MarcaModeloPOS_VERIFONE(context)) {
                                    bodyC2P = json.ReadingDatosDispositivosPOS(URL_API.JSON_Datospos, context.getApplicationContext().getPackageName());
                                    body.addProperty("serialDispositivo", bodyC2P.getDatosDispositivo().getSerial());

                                }
                            } else {
                                body_datosDispositivosCelular = json.ReadingDatosDispositivos(URL_API.JSON_datosDispositivos, context.getApplicationContext().getPackageName());
                                body.addProperty("serialDispositivo", body_datosDispositivosCelular.getDatosDispositivo().getPinPadSerial());
                            }
                            calibrar.LimpiarDatosActualizar(context);
                            login.login(usuarioR, contraseñaR, context, URL_API.SharedPreferend_JWT, context.getApplicationContext().getPackageName(), body, pos, activity);


                        });
        CrearDialog();

    }

    public void AlertDialog_calibrar(String usuario, String Contraseña, Context context, String titulo, String mensaje, Activity activity) {

        Log.d(TAG, "AlertDialog_calibrar() called with: usuario = [" + usuario + "], Contraseña = [" + Contraseña + "], context = [" + context + "], alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], activity = [" + activity + "]");
        AtomicReference<String> _jwt = new AtomicReference<>("");
        calibracion calibrar = new calibracion();
        alertDialogBuilder = new AlertDialog.Builder(context);
        AppPreferences save = new AppPreferences(context, "JWT");
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        autentificacion login = new autentificacion(true, 200);
        View customLayout = inflater.inflate(R.layout.dialog_calibrar, null);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setIcon(R.drawable.alert)
                // alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton(
                        "Aceptar",
                        (dialog, which) -> {
                            _jwt.set(save.getSmsBody(URL_API.SharedPreferend_JWT));
                            _jwt.set("Bearer " + _jwt);

                            calibrar.peticion_aids(_jwt.get(), context, activity);

                        });
        CrearDialog();

    }

    public void AlertDialog_AutoActualizar(String usuario, String Contraseña, Context context, String titulo, String mensaje, Activity activity) {

        Log.d(TAG, "AlertDialog_calibrar() called with: usuario = [" + usuario + "], Contraseña = [" + Contraseña + "], context = [" + context + "], alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], activity = [" + activity + "]");
        AtomicReference<String> _jwt = new AtomicReference<>("");
        calibracion calibrar = new calibracion();
        String JWT = calibrar.Peticion_Get_JWT(context);
        AppPreferences save = new AppPreferences(context, "JWT");
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customLayout = inflater.inflate(R.layout.dialog_calibrar, null);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setIcon(R.drawable.alert)
                // alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton(
                        "Aceptar",
                        (dialog, which) -> {
                            _jwt.set(save.getSmsBody(URL_API.SharedPreferend_JWT));
                            _jwt.set("Bearer " + _jwt);
                            calibrar.peticion_aids(JWT, context, activity);

                        });
        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();

    }


    public void AlertDialog_EsperandoRespuestaServer(Context context, String titulo, String mensaje, Boolean yes) {
        Log.d(TAG, "AlertDialog_EsperandoRespuestaServer() called with: context = [" + context + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], yes = [" + yes + "]");
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        autentificacion login = new autentificacion(true, 200);
        View customLayout = inflater.inflate(R.layout.esperar_respuesta, null);
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setIcon(R.drawable.logo_icono).setPositiveButton(
                        "", (dialog, which) -> {
                            ((AlertDialog) dialog).getButton(which).setVisibility(View.INVISIBLE);
                        })
                .setNegativeButton("", (dialog, which) -> {
                    ((AlertDialog) dialog).getButton(which).setVisibility(View.INVISIBLE);
                });
        alertDialogBuilder.setMessage(mensaje).setCancelable(false);


        CrearDialog();


    }

    public void AlertDialog_actualizandoAIDS(Context context, String titulo, String mensaje, Boolean yes) {

        Log.d(TAG, "AlertDialog_actualizandoAIDS() called with: context = [" + context + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], yes = [" + yes + "]");
        alertDialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        autentificacion login = new autentificacion(true, 200);
        calibracion calibrar = new calibracion();
        Boolean termino;
        View customLayout = inflater.inflate(R.layout.esperar_respuesta, null);

        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setIcon(R.drawable.logo_icono).setCancelable(false);

        Log.e(TAG, "AlertDialog_actualizandoAIDS: " + " entro aqui para actualizar");
        if (calibrar.EsPOSBluetooth(context) == true) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, "----------: " + "hilo principal de llamada de AIDS y CAPKS");
                    calibrar.ActualizarAids_CAPKS(context);
                }
            }, 1000);

        }

        CrearDialog();


    }

    public void AlertDialog_Sincronizando(Context context, String titulo, String mensaje, Boolean yes) {

        Log.d(TAG, "AlertDialog_actualizandoAIDS() called with: context = [" + context + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], yes = [" + yes + "]");

        alertDialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        autentificacion login = new autentificacion(true, 200);
        calibracion calibrar = new calibracion();
        Boolean termino;
        View customLayout = inflater.inflate(R.layout.esperar_respuesta, null);

        alertDialogBuilder.setTitle(titulo).setCancelable(false);
        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setIcon(R.drawable.logo_icono);

        if (calibrar.EsPOSBluetooth(context) == true) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, "run: EN OPERACIONES");
                }
            }, 1000);

        }

        CrearDialog();


    }

*/

   /*
    public void AlertDialog_ReactualizandoAIDS(Context context, String titulo, String mensaje, Boolean yes) {
        Log.d(TAG, "AlertDialog_ReactualizandoAIDS() called with: context = [" + context + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], yes = [" + yes + "]");
        AlertDialog.Builder alertDialogBuilderT = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        autentificacion login = new autentificacion(true, 200);
        calibracion calibrar = new calibracion();
        Boolean termino;
        View customLayout = inflater.inflate(R.layout.esperar_respuesta, null);
        Log.i(TAG, "FalloConexionPinPad: " + mensaje);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            discriminante.saveSmsBody("revisado", "timerC");
                            Log.e(TAG, "AlertDialog_error: " + context.getPackageName());
                            AlertDialog_actualizandoAIDS(context, titulo, mensaje, yes);

                        }

                );


        AlertDialog dialog = alertDialogBuilderT.create();
        dialog.show();


    }

    public void FalloConexionPinPad(Context context, String titulo, String mensaje, Boolean yes) {

        Log.i(TAG, "FalloConexionPinPad: " + mensaje);
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();
                            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
                            discriminante.saveSmsBody("revisado", "timerC");
                            Log.e(TAG, "AlertDialog_error: " + context.getPackageName());
                            AlertDialog_actualizandoAIDS(context, titulo, mensaje, yes);

                        }

                );
        alertDialogBuilder.setNegativeButton("Cancelar", (dialog, id) -> {
            dialog.cancel();
            AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
            discriminante.saveSmsBody("revisado", "timerC");
            //Log.e(TAG, "AlertDialog_error: " + context.getPackageName());
            Intent i = new Intent(context, splashScreenConfiguracion.class);
            (context).startActivity(i);
            ((Activity) (context)).finish();
        });

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }

    public void Cancelar_AlertDialog() {

        alertDialogBuilder.setCancelable(true);

    }


    public void InformacionPOS(Context context, PINPad pos) throws Exception {
        Log.d(TAG, "InformacionPOS() called with: context = [" + context + "], pos = [" + pos + "]");
        calibracion calibrar = new calibracion();
        // AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        JsonObject Obj_datoscell = new JsonObject();
        JsonObject datoscell = new JsonObject();
        JsonArray array = new JsonArray();
        manipularJSON json = new manipularJSON();
        //Log.e(TAG, "InformacionPOS: " + " ENTRAMOS AQUI PARA MANEJAR LA INFORMACION DEL PINPAD BLUETOOTH");
        final Serial[] resp = new Serial[1];

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);
        AlertDialog_Sincronizando(context, "Sincronizando equipo", "Mantenga el pinpad encendio y cerca del equipo", true);

        try {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        resp[0] = pos.info();
                    } catch (Exception e) {
                        //  throw new RuntimeException(e);
                    }

                    if (resp[0] != null) {
                        LoginLinkit.saveSmsBody("1", "linkit");
                        discriminante.saveSmsBody_boolean(1, "EsPOS");
                        bluetoothM.saveSmsBody("activo", "morefun");
                        Obj_datoscell.addProperty("App", LimitadorTamaño(resp[0].getApp()));
                        Obj_datoscell.addProperty("Kernel", LimitadorTamaño(resp[0].getKernel()));
                        //Log.e(TAG, "InformacionPOS: Kernel ->" + resp[0].getKernel() + "-" + LimitadorTamaño(resp[0].getKernel()));
                        Obj_datoscell.addProperty("IsContaclessCard", resp[0].getContaclessCard());
                        Obj_datoscell.addProperty("Marca", LimitadorTamaño(resp[0].getMarca()));
                        Obj_datoscell.addProperty("Modelo", LimitadorTamaño(resp[0].getModelo()));
                        Obj_datoscell.addProperty("Os", LimitadorTamaño(resp[0].getOs()));
                        Obj_datoscell.addProperty("Serial", LimitadorTamaño(resp[0].getSerial()));
                        Obj_datoscell.addProperty("Codigo", LimitadorTamaño(resp[0].getCodigo()));
                        Obj_datoscell.addProperty("Mensaje", LimitadorTamaño(resp[0].getMensaje()));
                        Obj_datoscell.addProperty("IsMagCard", resp[0].getMagCard());
                        Obj_datoscell.addProperty("ICCard", resp[0].getIsCCard());
                        Obj_datoscell.addProperty("IsPrinter", resp[0].getPrinter());
                        Obj_datoscell.addProperty("Terminalid", resp[0].getTerminalid());

                    }
                }
            }, 4000);


        } catch (Exception e) {
            Log.e(TAG, "Error solicitando info del dispositivo: " + e.getMessage());
            // dialog.AlertDialog_errorLogin(alertDialogBuilder, "Falla de lectura", e.getMessage(), context);
        }


        datoscell.add("datosDispositivo", Obj_datoscell);
        array.add(Obj_datoscell);

        try {
            json.Save_body_cell(URL_API.JSON_Datospos, datoscell, context.getApplicationContext().getPackageName(), context);

            calibrar.Calibracion_JSONPreRegistro(context);
            Intent i = new Intent(context, config_pinpadBluetooth_miscelaneo.class);
            context.startActivity(i);
            ((Activity) (context)).finish();

        } catch (JSONException e) {

            Log.w(TAG, "-----------------------------------------------------------");
            Log.e(TAG, "catch" + e.getMessage());
            Log.w(TAG, "-----------------------------------------------------------");
        } catch (IOException e) {
            Log.w(TAG, "-----------------------------------------------------------");
            Log.e(TAG, "catch" + e.getMessage());
            Log.w(TAG, "-----------------------------------------------------------");
        }
    }

    public String LimitadorTamaño(String campo) {
        String salida = "";
        int contador = 0;
        Log.e(TAG, "-------------------------------------------");
        Log.d(TAG, "LimitadorTamaño() called with: campo = [" + campo + "]");
        Log.e(TAG, "-------------------------------------------");
        if (campo != null) {
            if (campo.length() > 0) {
                for (int i = 0; i < campo.length(); i++) {
                    if (contador < 14) {
                        salida = salida + campo.charAt(i);
                        contador++;
                    }
                }
            } else {
                salida = "OSAndroid";
            }
        } else {

            salida = "OSAndroid";
        }

        return salida;
    }

    private void Finalizar_VerifoneAnulacion(Context context) {

        Log.d(TAG, "Finalizar_VerifoneAnulacion() called");
        String PinblockData;
        String PinblockKsn;
        String ExtrationMode;
        String getTrack2Data;
        String getTrack2Ksn;
        Activity activity = (Activity) context;
        AppPreferences cancelarTransaccion = new AppPreferences(context, "cancelado");
        AppPreferences Transaccion = new AppPreferences(context, URL_API.transaccion);
        AppPreferences anulacion = new AppPreferences(context, URL_API.anulacion);
        String secuencia = anulacion.getSmsBody(URL_API.secuencia);
        String idTransaccion = anulacion.getSmsBody(URL_API.idTransaccion);
        String numeroControl = anulacion.getSmsBody(URL_API.numeroControl);
        String identificacionCliente = anulacion.getSmsBody(URL_API.identificacionCliente);
        getTrack2Data = anulacion.getSmsBody("getTrack2Data");
        getTrack2Ksn = anulacion.getSmsBody("getTrack2Ksn");
        PinblockData = anulacion.getSmsBody("getPinblockData");
        PinblockKsn = anulacion.getSmsBody("getPinblockKsn");
        ExtrationMode = anulacion.getSmsBody("getExtrationMode");
        String codRespueta = Transaccion.getSmsBody("codRespueta");
        String EstatusEMV = Transaccion.getSmsBody("EstatusEMV");
        JsonObject bodyEMV = prepararJSON_RegistroAnulacion(context, secuencia, idTransaccion
                , numeroControl, identificacionCliente, getTrack2Data, getTrack2Ksn,
                ExtrationMode, PinblockData, PinblockKsn);

        Log.d(TAG, "ANULACION: BODY DE PETICION->" + bodyEMV);

        Tarjeta resp = null;

        try {
            Log.d(TAG, "ANULACION EMPIEZA PETICION A LINKIT DE FINALIZAR" + "ExtrationMode-> " + ExtrationMode);
            Log.d(TAG, "ANULACION EMPIEZA PETICION A LINKIT DE FINALIZAR" + "codRespueta-> " + codRespueta);
            Log.d(TAG, "ANULACION EMPIEZA PETICION A LINKIT DE FINALIZAR" + "EstatusEMV-> " + EstatusEMV);
            resp = config.GetPOS(context).finalizarTrx(ExtrationMode, "FF", "2", "00", "00", "00", context);

            if (resp != null) {
                if (config.calibrar.GetCancelarTransaccion(context) == false) {
                    cancelarTransaccion.saveSmsBody("", "cancelo");
                    cancelarTransaccion.saveSmsBody("", "mensaje_cancelado");

                    ActionFinalizar_modoIntegrado(context);

                } else {
                    cancelarTransaccion.saveSmsBody("", "cancelo");
                    if (cancelarTransaccion.getSmsBody("mensaje_cancelado") != null
                            && cancelarTransaccion.getSmsBody("mensaje_cancelado").length() > 0) {

                        AlertDialog_alertaAnulacion( "Advertencia",
                                cancelarTransaccion.getSmsBody("mensaje_cancelado"), context);
                    } else {
                        if (config.calibrar.ExisteVoucher(context) == true) {

                            Intent i = new Intent(context, RespuestaTransaccion.class);
                            context.startActivity(i);
                            activity.finish();
                        } else {
                            AlertDialog_alertaAnulacion( "Advertencia",
                                    "No fue posible la comunicación con el Vpos Server.", context);
                        }


                    }
                }
            }

        } catch (CanceladoUsuarioException e) {
            Log.d(TAG, "************************************************");
            Log.d(TAG, "onCreate:CanceladoUsuarioException " + e.getMessage());

            Intent intent = new Intent(context, AlertaExcepcion.class);
            intent.putExtra("mensaje", e.getMessage());
            activity.startActivity(intent);
            activity.finish();
        } catch (TimeoutException e) {
            Log.d(TAG, "************************************************");
            Log.d(TAG, "onCreate:TimeoutException " + e.getMessage());
            // ventanaEmergente.AlertDialog_error(anulacionMensajeT, "Tiempo de espera", e.getMessage(), anulacion.this);
            Intent intent = new Intent(context, AlertaExcepcion.class);
            intent.putExtra("mensaje", e.getMessage());
            activity.startActivity(intent);
            activity.finish();
        } catch (DispositivoException e) {
            Log.d(TAG, "************************************************");
            Log.d(TAG, "onCreate:DispositivoException " + e.getMessage());
            Intent intent = new Intent(context, AlertaExcepcion.class);
            intent.putExtra("mensaje", e.getMessage());
            activity.startActivity(intent);
            activity.finish();

        } catch (NoRespuestaException e) {
            Log.d(TAG, "************************************************");
            Log.d(TAG, "onCreate:DispositivoException " + e.getMessage());
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);
            //ventanaEmergente.AlertDialog_error(anulacionMensajeT, "Error Pinpad", e.getMessage(), anulacion.this);
            Intent intent = new Intent(context, AlertaExcepcion.class);
            intent.putExtra("mensaje", e.getMessage());
            activity.startActivity(intent);
            activity.finish();

        } catch (Exception e) {
            Log.d(TAG, "************************************************");
            Log.d(TAG, "onCreate:Exception " + e.getMessage());
            Log.d(TAG, "onCreate:NoRespuestaException " + e.getCause());
            Log.d(TAG, "onCreate:NoRespuestaException " + e);
            // ventanaEmergente.AlertDialog_error(anulacionMensajeT, "Error Pinpad", "No se pudo comunicar con el dispositivo.", anulacion.this);

            Intent intent = new Intent(context, AlertaExcepcion.class);
            intent.putExtra("mensaje", e.getMessage());
            activity.startActivity(intent);
            activity.finish();
        }



    }

    private JsonObject prepararJSON_RegistroAnulacion(Context context, String secuencia, String idTransaccion,
                                                      String numeroControl, String identificacionCliente,
                                                      String track2Encriptado, String track2Ksn,
                                                      String posEntryMode, String PinblockData, String PinblockKsn) {

        JsonObject body = new JsonObject();
        String vtidMulti = "";
        String montoMulti = "";
        String discriminanteMulti = "";
        int EsPos = 0;
        manipularJSON json = new manipularJSON();
        List_BodyMultiEmpresa listaMulti = new List_BodyMultiEmpresa();
        Body_DatosDispositivos body_datosDispositivosCelular = new Body_DatosDispositivos();
        JsonObject Compra_dispositivo = new JsonObject();
        JsonObject body_preregistro_anulacion = new JsonObject();

        AppPreferences Preferendmarca = new AppPreferences(context, "marca");
        String marca = Preferendmarca.getSmsBody("marcaT");

        bodyCompra_DatosDispositivos bodyCompra_DatosDispositivos = new bodyCompra_DatosDispositivos();
        bodyCompra_DatosDispositivos = json.ReadingPreRegistroC2P(URL_API.JSON_preregistro, context.getPackageName());
        JsonObject Datosmobile = new JsonObject();
        JsonObject DatosTransaccion = new JsonObject();


        bodyCompra_DatosDispositivos bodySimple = new bodyCompra_DatosDispositivos();


        AppPreferences Multi = new AppPreferences(context, URL_API.multi);
        AppPreferences POS = new AppPreferences(context, URL_API.determinante);
        bodySimple = json.ReadingPreRegistroC2P(URL_API.JSON_preregistro, context.getApplicationContext().getPackageName());
        listaMulti = json.ReadingPreRegistroMulti(URL_API.JSON_PreRegistroMultiEmpresa, context.getApplicationContext().getPackageName());
        body_datosDispositivosCelular = json.ReadingDatosDispositivos(URL_API.JSON_datosDispositivos, context.getApplicationContext().getPackageName());

        vtidMulti = Multi.getSmsBody("Vtidmulti");
        montoMulti = Multi.getSmsBody("Vtidmulti");
        discriminanteMulti = Multi.getSmsBody(URL_API.multi);
        EsPos = POS.getSmsBody_int("EsPOS");

        if (EsPos == 1) {


            if (discriminanteMulti.equalsIgnoreCase("1") == true) {
                for (int i = 0; i < listaMulti.getMultiempresa().size(); i++) {

                    if (i % 2 == 0) {
                        Log.d(TAG, "PreregistroTarjeta:-> " + listaMulti.getMultiempresa().get(i).getVtid() + " " + i);

                        if (listaMulti.getMultiempresa().get(i).getVtid().equalsIgnoreCase(vtidMulti)) {
                            Compra_dispositivo.addProperty(URL_API.pinPadSerial, listaMulti.getMultiempresa().get(i + 1).getPinPadSerial());
                            Compra_dispositivo.addProperty(URL_API.modeloPOS, listaMulti.getMultiempresa().get(i + 1).getModeloPOS());
                            Compra_dispositivo.addProperty(URL_API.marcaPinpad, listaMulti.getMultiempresa().get(i + 1).getMarcaPinpad());
                            Compra_dispositivo.addProperty(URL_API.pinPadOS, listaMulti.getMultiempresa().get(i + 1).getPinPadOS().toString());
                            Compra_dispositivo.addProperty(URL_API.pinPadApp, listaMulti.getMultiempresa().get(i + 1).getPinPadApp());
                            Compra_dispositivo.addProperty(URL_API.pinPadKernel, listaMulti.getMultiempresa().get(i + 1).getPinPadKernel());

                        }


                    }

                    if (marca.equalsIgnoreCase("Visa Electron") == true ||
                            marca.equalsIgnoreCase("Crédito") == true) {


                        DatosTransaccion.addProperty(URL_API.identificacionCliente, identificacionCliente);
                        DatosTransaccion.addProperty(URL_API.track2Encriptado, track2Encriptado);
                        DatosTransaccion.addProperty(URL_API.posEntryMode, posEntryMode);
                        DatosTransaccion.addProperty("initCero", config.InitCeroPOS(context));


                        body_preregistro_anulacion.addProperty(URL_API.vtid, vtidMulti);
                        body_preregistro_anulacion.addProperty(URL_API.numeroControl, numeroControl);
                        body_preregistro_anulacion.addProperty(URL_API.secuencia, secuencia);
                        body_preregistro_anulacion.addProperty(URL_API.idTransaccion, idTransaccion);

                        body_preregistro_anulacion.add("datosTransaccion", DatosTransaccion);
                        body_preregistro_anulacion.add("datosDispositivo", Compra_dispositivo);

                    }
                    else if (marca.equalsIgnoreCase("Débito") == true) {


                        DatosTransaccion.addProperty(URL_API.identificacionCliente, identificacionCliente);
                        DatosTransaccion.addProperty(URL_API.track2Encriptado, track2Encriptado);
                        //Si la tarjeta no llega a pedir pin, se eliminan los campos de la peticion
                        if (PinblockData == null) {
                            DatosTransaccion.addProperty(URL_API.pinblockEncriptado, (String) null);
                            DatosTransaccion.addProperty(URL_API.pinblockKsn, (String) null);
                        } else if (PinblockData.equalsIgnoreCase("")) {
                            DatosTransaccion.addProperty(URL_API.pinblockEncriptado, (String) null);
                            DatosTransaccion.addProperty(URL_API.pinblockKsn, (String) null);
                        } else {
                            DatosTransaccion.addProperty(URL_API.pinblockEncriptado, PinblockData);
                            DatosTransaccion.addProperty(URL_API.pinblockKsn, PinblockKsn);
                        }
                        DatosTransaccion.addProperty(URL_API.posEntryMode, posEntryMode);
                        DatosTransaccion.addProperty("initCero", config.InitCeroPOS(context));


                        body_preregistro_anulacion.addProperty(URL_API.vtid, vtidMulti);
                        body_preregistro_anulacion.addProperty(URL_API.numeroControl, numeroControl);
                        body_preregistro_anulacion.addProperty(URL_API.secuencia, secuencia);
                        body_preregistro_anulacion.addProperty(URL_API.idTransaccion, idTransaccion);

                        body_preregistro_anulacion.add("datosTransaccion", DatosTransaccion);
                        body_preregistro_anulacion.add("datosDispositivo", Compra_dispositivo);


                    }

                }
            }

            else {
                Compra_dispositivo.addProperty(URL_API.pinPadSerial, bodySimple.getDatosDispositivo().getPinPadSerial());
                Compra_dispositivo.addProperty(URL_API.modeloPOS, bodySimple.getDatosDispositivo().getModeloPOS());
                Compra_dispositivo.addProperty(URL_API.marcaPinpad, bodySimple.getDatosDispositivo().getMarcaPinpad());
                Compra_dispositivo.addProperty(URL_API.pinPadOS, bodySimple.getDatosDispositivo().getPinPadOS().toString());
                Compra_dispositivo.addProperty(URL_API.pinPadApp, bodySimple.getDatosDispositivo().getPinPadApp());
                Compra_dispositivo.addProperty(URL_API.pinPadKernel, bodySimple.getDatosDispositivo().getPinPadKernel());


                body_preregistro_anulacion.addProperty(URL_API.vtid, bodySimple.getVtid());
                body_preregistro_anulacion.addProperty(URL_API.identificacionCliente, identificacionCliente);
                body_preregistro_anulacion.addProperty(URL_API.secuencia, secuencia);
                body_preregistro_anulacion.addProperty(URL_API.idTransaccion, idTransaccion);
                body_preregistro_anulacion.addProperty(URL_API.numeroControl, numeroControl);

                if (marca.equalsIgnoreCase("Visa Electron") == true ||
                        marca.equalsIgnoreCase("Crédito") == true) {


                    DatosTransaccion.addProperty(URL_API.identificacionCliente, identificacionCliente);
                    DatosTransaccion.addProperty(URL_API.track2Encriptado, track2Encriptado);
                    DatosTransaccion.addProperty(URL_API.posEntryMode, posEntryMode);
                    DatosTransaccion.addProperty("initCero", config.InitCeroPOS(context));


                    body_preregistro_anulacion.addProperty(URL_API.vtid, bodySimple.getVtid());
                    body_preregistro_anulacion.addProperty(URL_API.numeroControl, numeroControl);
                    body_preregistro_anulacion.addProperty(URL_API.secuencia, secuencia);
                    body_preregistro_anulacion.addProperty(URL_API.idTransaccion, idTransaccion);

                    body_preregistro_anulacion.add("datosTransaccion", DatosTransaccion);
                    body_preregistro_anulacion.add("datosDispositivo", Compra_dispositivo);

                }

                else if (marca.equalsIgnoreCase("Débito") == true) {


                    DatosTransaccion.addProperty(URL_API.identificacionCliente, identificacionCliente);
                    DatosTransaccion.addProperty(URL_API.track2Encriptado, track2Encriptado);
                    //Si la tarjeta no llega a pedir pin, se eliminan los campos de la peticion
                    if (PinblockData == null) {
                        DatosTransaccion.addProperty(URL_API.pinblockEncriptado, (String) null);
                        DatosTransaccion.addProperty(URL_API.pinblockKsn, (String) null);
                    } else if (PinblockData.equalsIgnoreCase("")) {
                        DatosTransaccion.addProperty(URL_API.pinblockEncriptado, (String) null);
                        DatosTransaccion.addProperty(URL_API.pinblockKsn, (String) null);
                    } else {
                        DatosTransaccion.addProperty(URL_API.pinblockEncriptado, PinblockData);
                        DatosTransaccion.addProperty(URL_API.pinblockKsn, PinblockKsn);
                    }
                    DatosTransaccion.addProperty(URL_API.posEntryMode, posEntryMode);
                    DatosTransaccion.addProperty("initCero", config.InitCeroPOS(context));


                    body_preregistro_anulacion.addProperty(URL_API.vtid, bodySimple.getVtid());
                    body_preregistro_anulacion.addProperty(URL_API.numeroControl, numeroControl);
                    body_preregistro_anulacion.addProperty(URL_API.secuencia, secuencia);
                    body_preregistro_anulacion.addProperty(URL_API.idTransaccion, idTransaccion);

                    body_preregistro_anulacion.add("datosTransaccion", DatosTransaccion);
                    body_preregistro_anulacion.add("datosDispositivo", Compra_dispositivo);


                }


            }

        }

        else {

            if (discriminanteMulti.equalsIgnoreCase("1") == true) {

                for (int i = 0; i < listaMulti.getMultiempresa().size(); i++) {

                    if (i % 2 == 0) {
                        Log.d(TAG, "PreregistroTarjeta:-> " + listaMulti.getMultiempresa().get(i).getVtid() + " " + i);

                        if (listaMulti.getMultiempresa().get(i).getVtid().equalsIgnoreCase(vtidMulti)) {
                            Compra_dispositivo.addProperty(URL_API.pinPadSerial, listaMulti.getMultiempresa().get(i + 1).getPinPadSerial());
                            Compra_dispositivo.addProperty(URL_API.modeloPOS, listaMulti.getMultiempresa().get(i + 1).getModeloPOS());
                            Compra_dispositivo.addProperty(URL_API.marcaPinpad, listaMulti.getMultiempresa().get(i + 1).getMarcaPinpad());
                            Compra_dispositivo.addProperty(URL_API.pinPadOS, listaMulti.getMultiempresa().get(i + 1).getPinPadOS().toString());
                            Compra_dispositivo.addProperty(URL_API.pinPadApp, listaMulti.getMultiempresa().get(i + 1).getPinPadApp());
                            Compra_dispositivo.addProperty(URL_API.pinPadKernel, listaMulti.getMultiempresa().get(i + 1).getPinPadKernel());
                            Compra_dispositivo.addProperty(URL_API.encriptacionPinBlock, listaMulti.getMultiempresa().get(i + 1).getEncriptacionPinBlock());
                            Compra_dispositivo.addProperty(URL_API.encriptacionTrack2, listaMulti.getMultiempresa().get(i + 1).getEncriptacionTrack2());


                        }


                    }

                    if (marca.equalsIgnoreCase("Visa Electron") == true ||
                            marca.equalsIgnoreCase("Crédito") == true) {


                        DatosTransaccion.addProperty(URL_API.identificacionCliente, identificacionCliente);
                        DatosTransaccion.addProperty(URL_API.track2Encriptado, track2Encriptado);
                        DatosTransaccion.addProperty("track2Ksn", track2Ksn);
                        DatosTransaccion.addProperty(URL_API.posEntryMode, posEntryMode);
                        DatosTransaccion.addProperty("initCero", config.InitCeroPOS(context));


                        body_preregistro_anulacion.addProperty(URL_API.vtid, vtidMulti);
                        body_preregistro_anulacion.addProperty(URL_API.numeroControl, numeroControl);
                        body_preregistro_anulacion.addProperty(URL_API.secuencia, secuencia);
                        body_preregistro_anulacion.addProperty(URL_API.idTransaccion, idTransaccion);

                        body_preregistro_anulacion.add("datosTransaccion", DatosTransaccion);
                        body_preregistro_anulacion.add("datosDispositivo", Compra_dispositivo);

                    }

                    else if (marca.equalsIgnoreCase("Débito") == true) {


                        DatosTransaccion.addProperty(URL_API.identificacionCliente, identificacionCliente);
                        DatosTransaccion.addProperty(URL_API.track2Encriptado, track2Encriptado);
                        DatosTransaccion.addProperty("track2Ksn", track2Ksn);

                        if (PinblockData == null) {
                            DatosTransaccion.addProperty(URL_API.pinblockEncriptado, (String) null);
                            DatosTransaccion.addProperty(URL_API.pinblockKsn, (String) null);
                        } else if (PinblockData.equalsIgnoreCase("")) {
                            DatosTransaccion.addProperty(URL_API.pinblockEncriptado, (String) null);
                            DatosTransaccion.addProperty(URL_API.pinblockKsn, (String) null);
                        } else {
                            DatosTransaccion.addProperty(URL_API.pinblockEncriptado, PinblockData);
                            DatosTransaccion.addProperty(URL_API.pinblockKsn, PinblockKsn);
                        }
                        DatosTransaccion.addProperty(URL_API.posEntryMode, posEntryMode);
                        DatosTransaccion.addProperty("initCero", config.InitCeroPOS(context));


                        body_preregistro_anulacion.addProperty(URL_API.vtid, vtidMulti);
                        body_preregistro_anulacion.addProperty(URL_API.numeroControl, numeroControl);
                        body_preregistro_anulacion.addProperty(URL_API.secuencia, secuencia);
                        body_preregistro_anulacion.addProperty(URL_API.idTransaccion, idTransaccion);

                        body_preregistro_anulacion.add("datosTransaccion", DatosTransaccion);
                        body_preregistro_anulacion.add("datosDispositivo", Compra_dispositivo);


                    }
                }

            }
            else {
                Compra_dispositivo.addProperty(URL_API.pinPadSerial, body_datosDispositivosCelular.getDatosDispositivo().getPinPadSerial());
                Compra_dispositivo.addProperty(URL_API.modeloPOS, body_datosDispositivosCelular.getDatosDispositivo().getModeloPOS());
                Compra_dispositivo.addProperty(URL_API.marcaPinpad, body_datosDispositivosCelular.getDatosDispositivo().getMarcaPinpad());
                Compra_dispositivo.addProperty(URL_API.pinPadOS, body_datosDispositivosCelular.getDatosDispositivo().getPinPadOS().toString());
                Compra_dispositivo.addProperty(URL_API.pinPadApp, body_datosDispositivosCelular.getDatosDispositivo().getPinPadApp());
                Compra_dispositivo.addProperty(URL_API.pinPadKernel, body_datosDispositivosCelular.getDatosDispositivo().getPinPadKernel());
                Compra_dispositivo.addProperty(URL_API.encriptacionPinBlock, body_datosDispositivosCelular.getDatosDispositivo().getEncriptacionPinBlock());
                Compra_dispositivo.addProperty(URL_API.encriptacionTrack2, body_datosDispositivosCelular.getDatosDispositivo().getEncriptacionTrack2());


                if (marca.equalsIgnoreCase("Visa Electron") == true ||
                        marca.equalsIgnoreCase("Crédito") == true) {


                    DatosTransaccion.addProperty(URL_API.identificacionCliente, identificacionCliente);
                    DatosTransaccion.addProperty(URL_API.track2Encriptado, track2Encriptado);
                    DatosTransaccion.addProperty("track2Ksn", track2Ksn);
                    DatosTransaccion.addProperty(URL_API.posEntryMode, posEntryMode);
                    DatosTransaccion.addProperty("initCero", config.InitCeroPOS(context));


                    body_preregistro_anulacion.addProperty(URL_API.vtid, bodySimple.getVtid());
                    body_preregistro_anulacion.addProperty(URL_API.numeroControl, numeroControl);
                    body_preregistro_anulacion.addProperty(URL_API.secuencia, secuencia);
                    body_preregistro_anulacion.addProperty(URL_API.idTransaccion, idTransaccion);

                    body_preregistro_anulacion.add("datosTransaccion", DatosTransaccion);
                    body_preregistro_anulacion.add("datosDispositivo", Compra_dispositivo);

                }

                else if (marca.equalsIgnoreCase("Débito") == true) {


                    DatosTransaccion.addProperty(URL_API.identificacionCliente, identificacionCliente);
                    DatosTransaccion.addProperty(URL_API.track2Encriptado, track2Encriptado);
                    DatosTransaccion.addProperty("track2Ksn", track2Ksn);
                    //Si la tarjeta no llega a pedir pin, se eliminan los campos de la peticion
                    if (PinblockData == null) {
                        DatosTransaccion.addProperty(URL_API.pinblockEncriptado, (String) null);
                        DatosTransaccion.addProperty(URL_API.pinblockKsn, (String) null);
                    } else if (PinblockData.equalsIgnoreCase("")) {
                        DatosTransaccion.addProperty(URL_API.pinblockEncriptado, (String) null);
                        DatosTransaccion.addProperty(URL_API.pinblockKsn, (String) null);
                    } else {
                        DatosTransaccion.addProperty(URL_API.pinblockEncriptado, PinblockData);
                        DatosTransaccion.addProperty(URL_API.pinblockKsn, PinblockKsn);
                    }
                    DatosTransaccion.addProperty(URL_API.posEntryMode, posEntryMode);
                    DatosTransaccion.addProperty("initCero", config.InitCeroPOS(context));


                    body_preregistro_anulacion.addProperty(URL_API.vtid, bodySimple.getVtid());
                    body_preregistro_anulacion.addProperty(URL_API.numeroControl, numeroControl);
                    body_preregistro_anulacion.addProperty(URL_API.secuencia, secuencia);
                    body_preregistro_anulacion.addProperty(URL_API.idTransaccion, idTransaccion);

                    body_preregistro_anulacion.add("datosTransaccion", DatosTransaccion);
                    body_preregistro_anulacion.add("datosDispositivo", Compra_dispositivo);


                }
            }

        }

        body = body_preregistro_anulacion;

        Log.d(TAG, "prepararJSON_RegistroAnulacion() returned: " + body);

        return body;
    }

    private void ActionFinalizar_modoIntegrado(Context context) {
        Log.d(TAG, "ActionFinalizar_modoIntegrado: ");
        calibracion calibrar = new calibracion();
        Activity activity = (Activity) context;
        if (calibrar.EsIntegardo(context) == true) {
            if (config.PeticionIntegrado.GetPeticionIntegrado().getVoucher() == true) {
                Intent i = new Intent(context, recibo_pago.class);
                context.startActivity(i);
                activity.finish();
            } else {
                Log.d(TAG, "ActionFinalizar_modoIntegrado:Cargando ");
                if (calibrar.ExistePreRegistro(context) == true) {
                    Intent i = new Intent(context, Cargando.class);
                    (context).startActivity(i);
                    ((Activity) (context)).finish();
                } else {
                    Intent i = new Intent(context, dashboard.class);
                    (context).startActivity(i);
                    ((Activity) (context)).finish();

                }


            }
        } else {
            Log.d(TAG, "ActionFinalizar_modoIntegrado:recibo_pago ");
            Intent i = new Intent(context, recibo_pago.class);
            context.startActivity(i);
            ((Activity) (context)).finish();

        }


    }

*/
    private void CrearDialog() {
        dialog = alertDialogBuilder.create();
        dialog.show();
    }

    public void CerrarDialog() {
        dialog.dismiss();
    }


}
