package ve.com.bivfrostgroup.demobluetoothfull.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Intent;
import android.os.Bundle;

import ve.com.bivfrostgroup.demobluetoothfull.R;
import ve.com.bivfrostgroup.demobluetoothfull.controladores.BluetoothControladores;


public class splash_screen extends AppCompatActivity {
    private MotionLayout motionLayout1;
    static BluetoothControladores BTControlador = new BluetoothControladores();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);
        BTControlador.BTPermissions(this);
        BTControlador.PermisoUbicacion(this);
        BTControlador.checkPermissions(this);
        motionLayout1=findViewById(R.id.motion_base);
        motionLayout1.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                if (i==R.id.end)
                {
                    Intent intento=new Intent(splash_screen.this, MainActivity.class);
                    startActivity(intento);
                    finishAffinity();
                }
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

            }
        });
    }


}


