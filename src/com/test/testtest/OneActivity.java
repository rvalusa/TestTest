package com.test.testtest;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OneActivity extends Activity{

	TextView tv1, tv2;
	EditText ev1, ev2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.oneactivity);
//		NotificationManager nNM = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//		nNM.cancel(R.string.hello);
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
		
		 ev1 = (EditText)findViewById(R.id.EditText01);
		 ev2 = (EditText)findViewById(R.id.EditText01);
		
		tv1 = (TextView)findViewById(R.id.TextView01);
		tv2 = (TextView)findViewById(R.id.TextView02);
		
		((Button)findViewById(R.id.Button01)).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				tv1.setText(ev1.getText());
				
			}
		});
		((Button)findViewById(R.id.Button02)).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				tv2.setText(ev2.getText());
				
			}
		});
		
		
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		Toast.makeText(this, "onConfigurationChanged ...", Toast.LENGTH_SHORT).show();
		super.onConfigurationChanged(newConfig);
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
	}
}
