package service;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ubnd.attendance.R;

import ui.session.image.SessionImageActivity;

import static ultils.App.CHANNEL_ID;

public class AlarmReceiver extends BroadcastReceiver {
    private NotificationManagerCompat notificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {

        notificationManager = NotificationManagerCompat.from(context);
        String title = "Meeting minutes";
        String message = "Bạn có cuộc họp sắp diễn ra";

        Intent activityIntent = new Intent(context, SessionImageActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, activityIntent, 0);
        PendingIntent actionIntent = PendingIntent.getBroadcast(context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.WHITE)
                .setContentIntent(actionIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Điểm danh", contentIntent)
                .build();
        notificationManager.notify(1, notification);
    }
}
