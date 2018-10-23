package info.androidhive.tabsswipe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlaBroad extends BroadcastReceiver 
{
	  @Override
	  public void onReceive(Context context, Intent intent) 
	  {
		  Intent i = new Intent(context, MyAlarmActivity.class);
          i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          context.startActivity(i);
	  }
} 
