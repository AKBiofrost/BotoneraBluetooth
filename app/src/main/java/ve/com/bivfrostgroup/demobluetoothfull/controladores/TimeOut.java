package ve.com.bivfrostgroup.demobluetoothfull.controladores;

import android.content.Context;
import android.os.CountDownTimer;

public class TimeOut {
    private CountDownTimer cTimer = null;
    private toastCustomer toast= new toastCustomer();

   public void startTimer(int TimerOut, int count_intervalo, String texto, Context context) {
        cTimer = new CountDownTimer(TimerOut, count_intervalo) {
            public void onTick(long millisUntilFinished) {
                int minute = (int) (millisUntilFinished / 1000) / 60;
                int second = (int) (millisUntilFinished / 1000) % 60;

            }

            public void onFinish() {
                toast.toastGrande(context,texto, 25);

            }
        };
        cTimer.start();
    }

    public  void cancelTimer() {
        if (cTimer != null)
            cTimer.cancel();
    }

    public  void initTimer() {
        if (cTimer != null)
            cTimer.start();
    }
}
