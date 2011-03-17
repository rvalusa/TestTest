package com.test.testtest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class AsyncTaskTest extends Activity{
	public static final String TAG="rakesh";
	AsyncTaskTest mCtx;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mCtx = this;
		
		MyAsyncTask task = new MyAsyncTask();
		Toast.makeText(mCtx, "onCreate() :"+Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
		
		Log.e(TAG,"onCreate() :"+Thread.currentThread().getName());
		task.execute(12,32,53,54);
		
		Log.e(TAG,"onCreate() msg Q:"+Looper.getMainLooper().myQueue().hashCode());
	}
	
	private class MyAsyncTask extends AsyncTask<Integer, Integer, String>{

		int i=0;
		@Override
		protected String doInBackground(Integer... params) {
			
			Looper.prepare();
			int sum = 0;
//			mCtx.runOnUiThread(new Runnable(){
//				public void run() {
//			Toast.makeText(mCtx, "doInBackground() "+Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
//			}
//			});
			
			Log.e(TAG,"doInBackground() "+Thread.currentThread().getName());
			Log.e(TAG,"onCreate() :"+Looper.myLooper().myQueue().hashCode());
			
			for(i=0;i<params.length;i++){
//				mCtx.runOnUiThread(new Runnable(){
//					public void run() {
//						Toast.makeText(mCtx, "For:"+i, Toast.LENGTH_SHORT).show();
//					}
//				});
				sum+=params[i];
			}
			Looper.loop();
			
			return ""+sum;
		}
		
		@Override
		protected void onPreExecute() {
			Toast.makeText(mCtx, "onPreExecute() :"+Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
			
			Log.e(TAG,"onPreExecute() :"+Thread.currentThread().getName());
			super.onPreExecute();
		}
		
		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(mCtx, "onPostExecute() :"+Thread.currentThread().getName()+" "+result, Toast.LENGTH_SHORT).show();
			
			Log.e(TAG,"onPostExecute() :"+Thread.currentThread().getName()+" "+result);
			
			super.onPostExecute(result);
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			Toast.makeText(mCtx, "onProgressUpdate() :"+Thread.currentThread().getName() +" "+values.length, Toast.LENGTH_SHORT).show();
			
			Log.e(TAG,"onProgressUpdate() :"+Thread.currentThread().getName());
			
			super.onProgressUpdate(values);
		}
	}
}
