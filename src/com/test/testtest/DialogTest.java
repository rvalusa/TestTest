package com.test.testtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DialogTest extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.oneactivity);
		showDialog(0,null);
//		showDialog(1,null);
		super.onCreate(savedInstanceState);
		mHandler.postDelayed(new Runnable() {
			
			public void run() {
				DialogTest.this.dismissDialog(0);
			}
		}, 15*1000);
//		myDialog(true);
		
		
		//Intect chooser start
		((Button)findViewById(R.id.left)).setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CHOOSER);
				intent.setType("audio/*");
				startActivity(Intent.createChooser(intent, "TEST TEST"));
			}
		});
		//end
	}
	
	private void myDialog(boolean b) {
		AlertDialog.Builder lBuilder = new AlertDialog.Builder(this);
		lBuilder.setTitle("Title");
		lBuilder.setMessage("Message");
		
		lBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogTest.this, Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
			}
		});
		lBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogTest.this, Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
			}
		});
		
		LayoutInflater inflator =  LayoutInflater.from(this);
		View v = inflator.inflate(R.layout.edittext, null);
		EditText et=(EditText)v.findViewById(R.id.dialogedit);
		et.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(s.length() == 0){
					ad.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
				}else{
					ad.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
				}
				Toast.makeText(DialogTest.this, "s:"+s+" start:"+start+" before:"+before+" count:"+count, Toast.LENGTH_SHORT).show();
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				Toast.makeText(DialogTest.this, "s:"+s+" start:"+start+" count:"+count+" after:"+after, Toast.LENGTH_SHORT).show();
			}
			
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		lBuilder.setView(v);
		ad= lBuilder.create();
		
		ad.show();
		ad.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
	}
	Handler mHandler = new Handler();
	
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		Toast.makeText(DialogTest.this, "onPrepareDialog(...)", Toast.LENGTH_SHORT).show();
		
		super.onPrepareDialog(id, dialog);
	}
	AlertDialog ad = null;
	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		
		switch(id){
		case 0:
			AlertDialog.Builder lBuilder = new AlertDialog.Builder(this);
			lBuilder.setTitle("Title");
//			lBuilder.setMessage("Message");
			
			LayoutInflater inflator =  LayoutInflater.from(this);
			View v = inflator.inflate(R.layout.edittext, null);
			EditText et=(EditText)v.findViewById(R.id.dialogedit);
			et.addTextChangedListener(new TextWatcher() {
				
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					if(count > 0){
						Button b = ad.getButton(AlertDialog.BUTTON_POSITIVE);
						b.setEnabled(true);
					}
				}
				
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					if(count == 0){
						Button b = ad.getButton(AlertDialog.BUTTON_POSITIVE);
						b.setEnabled(false);
//						DialogInterface.BUTTON_POSITIVE
					}
					
				}
				
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
					
				}
			});
			lBuilder.setView(v);
			lBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(DialogTest.this, Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
				}
			});
			lBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(DialogTest.this, Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
				}
			});
			ad = lBuilder.create();
			return ad;
			
		case 1:
			ProgressDialog progress = new ProgressDialog(this);
			progress.setMessage("Loading...");
			progress.setCancelable(true);
		return progress;
		}
		return super.onCreateDialog(id, args);
	}
	
}
