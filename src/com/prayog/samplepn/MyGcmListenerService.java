package com.prayog.samplepn;

import com.google.android.gms.gcm.GcmListenerService;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.support.v7.app.NotificationCompat.Builder;
import android.util.Log;


public class MyGcmListenerService extends GcmListenerService {

	private static final String TAG = "MyGcmListenerService";

	@Override
	public void onMessageReceived(String from, Bundle data) {
	    String message = data.getString("message");
	    Log.d(TAG, "From: " + from);
	    Log.d(TAG, "Message: " + message);

	    NotificationCompat.Builder mBuilder =
	            (Builder) new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.ic_launcher)
		.setContentTitle("My notification")
		.setContentText(message);
	    
	    // Creates an explicit intent for an Activity in your app
	    Intent resultIntent = new Intent(this, ResultsActivity.class);

	    // The stack builder object will contain an artificial back stack for the
	    // started Activity.
	    // This ensures that navigating backward from the Activity leads out of
	    // your application to the Home screen.
	    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
	    // Adds the back stack for the Intent (but not the Intent itself)
	    stackBuilder.addParentStack(ResultsActivity.class);
	    // Adds the Intent that starts the Activity to the top of the stack
	    stackBuilder.addNextIntent(resultIntent);
	    PendingIntent resultPendingIntent =
	            stackBuilder.getPendingIntent(
	                0,
	                PendingIntent.FLAG_UPDATE_CURRENT
	            );
	    mBuilder.setContentIntent(resultPendingIntent);
	    NotificationManager mNotificationManager =
	        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	    // mId allows you to update the notification later on.
	    mNotificationManager.notify(0, mBuilder.build());
	    if (from.startsWith("/topics/")) {
	        // message received from some topic.
	    } else {
	        // normal downstream message.
	    }

	    // ...
	}

}
