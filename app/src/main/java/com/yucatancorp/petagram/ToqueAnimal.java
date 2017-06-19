package com.yucatancorp.petagram;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.Gravity;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by marcos on 18/06/2017.
 */

public class ToqueAnimal extends BroadcastReceiver {

    Context context;
    Activity activity = (Activity) context;

    @Override
    public void onReceive(Context context, Intent intent) {
        String ACCION_KEY = "TOQUE_ANIMAL";
        String accion = intent.getAction();

        if (ACCION_KEY.equals(accion)) {
            onMessageReceived();
        }
    }


    public void onMessageReceived() {

        Intent intent3 = new Intent();
        intent3.setAction("TOQUE_ANIMAL");
        PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, intent3, PendingIntent.FLAG_ONE_SHOT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_perfil, "Devuelve el toque", pendingIntent)
                        .build();

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setGravity(Gravity.CENTER_VERTICAL);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(activity)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notificacion")
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .extend(wearableExtender.addAction(action))
                //.addAction(R.drawable.ic_perfil, "Devuelve el toque", pendingIntent)
                ;

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(activity);
        notificationManager.notify(001, notificacion.build());
    }
}
