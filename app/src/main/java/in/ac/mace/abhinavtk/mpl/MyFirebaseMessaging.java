package in.ac.mace.abhinavtk.mpl;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessaging extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        Log.d("Recived FCM", String.valueOf(remoteMessage.getData()));
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,"MSLNOTI");
        mBuilder.setSmallIcon(R.drawable.ic_stat_picsart_02_03_01_21_52);
        mBuilder.setContentTitle(remoteMessage.getData().get("head"));
        mBuilder.setContentText(remoteMessage.getData().get("message"));
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,mBuilder.build());
    }



}
