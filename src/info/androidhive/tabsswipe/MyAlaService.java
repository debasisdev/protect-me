package info.androidhive.tabsswipe;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyAlaService extends Service 
{
	 public MyAlaService() 
	 {
	 }
	 @Override
	 public IBinder onBind(Intent intent) 
	 {
		 throw new UnsupportedOperationException("Not Yet Implemented Service. Software Error");
	 }
	 @Override
	 public void onCreate() 
	 {
	 }
	 @Override
	 public void onStart(Intent intent, int startId) 
	 {
	 }
	 @Override
	 public void onDestroy() 
	 {
		 Toast.makeText(this, "Reminders-SMS Service is OFF", Toast.LENGTH_LONG).show();
		 super.onDestroy();
	 }
}
