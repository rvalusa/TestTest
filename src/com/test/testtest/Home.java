package com.test.testtest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Home extends Activity {
    /** Called when the activity is first created. */
	Intent lIntent;
	LocalService mService;
	OnClickListener mclick = new View.OnClickListener() {
		
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.Button01:startService(lIntent);break;
			case R.id.Button02:stopService(lIntent);break;
			case R.id.Button03:bindService(lIntent, connection, Context.BIND_AUTO_CREATE);break;
			case R.id.Button04:unbindService(connection);break;
			case R.id.Button05:break;
			}
		}
	};
	
	Button[] b = new Button[5];
	int[] resIds ;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        lIntent = new Intent("com.test.testtest.LocalService");
        resIds = new int[]{R.id.Button01, R.id.Button02, R.id.Button03, R.id.Button04, R.id.Button05};
        
        for(int i=0; i<5; i++){
        	b[i] = (Button)findViewById(resIds[i]);
        	b[i].setOnClickListener(mclick);
        }
    }
    
    private ServiceConnection connection = new ServiceConnection() {
		
		public void onServiceDisconnected(ComponentName name) {
			mService = null;
			Toast.makeText(Home.this, "service connected", Toast.LENGTH_SHORT).show();
		}
		
		public void onServiceConnected(ComponentName name, IBinder service) {
			mService = ((LocalService.MyBinder)service).getService();
			Toast.makeText(Home.this, "service disconnected", Toast.LENGTH_SHORT).show();
		}
	};
}