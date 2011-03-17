package com.test.testtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class LocalService extends Service{

	NotificationManager mNm;
	
	@Override
	public void onCreate() {
		mNm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		Toast.makeText(this, "Service onCreate()", Toast.LENGTH_SHORT).show();
		super.onCreate();
		Notification notification = new Notification(R.drawable.icon, "Hurray started", System.currentTimeMillis());
		PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, OneActivity.class), 0);
		notification.setLatestEventInfo(this, "Rakesh Valusa", "frm Rakesh", pi);
		
		mNm.notify(R.string.hello, notification);
		
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		Toast.makeText(this, "Service onBind()", Toast.LENGTH_SHORT).show();
		return new MyBinder();
	}

	class MyBinder extends Binder{
		com.test.testtest.LocalService getService(){
			return (LocalService.this);
		}
	}
	
	protected String localMethod(){
		return "got result";
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		Toast.makeText(this, "Service onStart()", Toast.LENGTH_SHORT).show();
		super.onStart(intent, startId);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "Service onStartCommand()", Toast.LENGTH_SHORT).show();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Toast.makeText(this, "Service onDestroy()", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}
}
