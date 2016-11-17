package com.cod3vstudio.bidreminder.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.cod3vstudio.bidreminder.App;
import com.cod3vstudio.bidreminder.R;
import com.cod3vstudio.bidreminder.activities.MainActivity;
import com.cod3vstudio.core.model.entities.Change;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

/**
 * Created by Administrator on 10/12/2016.
 */
public class AppFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FIREBASE";
    private static final String CHANGE_PRICE_MESSAGE = "ChangePrice";
    private static final String BUY_PRODUCT_MESSAGE = "BuyProduct";
    private static final String REACH_BID_PRICE_MESSAGE = "ReachBidPrice";
    private static final String MESSAGE_TYPE = "MessageType";
    private static final String MESSAGE_CONTENT = "Content";

    @Inject
    ModelComponent mModelComponent;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        App.sharedComponent().inject(this);

        String messageType = remoteMessage.getData().get(MESSAGE_TYPE);
        if (messageType.equals(CHANGE_PRICE_MESSAGE)) {
            String messageContent = remoteMessage.getData().get(MESSAGE_CONTENT);
            sendNotification(messageContent);
            String price = remoteMessage.getData().get("Price");
            String productId = remoteMessage.getData().get("ProductID");
            String changedAt = remoteMessage.getData().get("ChangedAt");
            Log.d("Price", price);
            Log.d("ProductID", productId);
            Log.d("ChangedAt", changedAt);
            Change change = new Change();
            change.setPrice(Double.parseDouble(price));
            change.setProductId(Integer.parseInt(productId));
            try {
                change.setChangedAt(new SimpleDateFormat("yyyy-MM-dd").parse(changedAt));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            mModelComponent.getChangeModel().add(change);

        } else if (messageType.equals(BUY_PRODUCT_MESSAGE)) {
            String messageContent = remoteMessage.getData().get(MESSAGE_CONTENT);
            sendNotification(messageContent);
        } else if (messageType.equals(REACH_BID_PRICE_MESSAGE)) {
            String messageContent = remoteMessage.getData().get(MESSAGE_CONTENT);
            sendNotification(messageContent);
        }
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(getString(R.string.app_name));
        bigTextStyle.bigText(messageBody);



        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setStyle(bigTextStyle)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
