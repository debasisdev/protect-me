package info.androidhive.tabsswipe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;

public class GpsBroad extends BroadcastReceiver 
{
	  @Override
	  public void onReceive(Context context, Intent intent) 
	  {
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
      	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,new LocationListener()
      	{
			public void onLocationChanged(Location location) 
			{
				String x=Double.toString(location.getLatitude());
				String y=Double.toString(location.getLongitude());
				SmsManager smsManager = SmsManager.getDefault();
            	String sendTo = pref.getString("h2", "111");
            	String myMessage = "My Position: Lat: "+x+"Long: "+y;
            	smsManager.sendTextMessage(sendTo, null, myMessage, null, null);
            	/*Log.v("hi", x+y);
            	Log.v("hix", sendTo);*/
			}
			public void onStatusChanged(String s, int i, Bundle b) 
			{
			}
			public void onProviderDisabled(String s) 
			{
			}
			public void onProviderEnabled(String s) 
			{
			}
      	});
	  }	  
} 