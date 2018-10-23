package info.androidhive.tabsswipe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;

public class MyAlarmActivity extends Activity 
{
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		long pattern[] = { 0, 100, 200, 300, 400 };
		final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
		vibrator.vibrate(pattern, 0);
		new AlertDialog.Builder(this)
        .setTitle("Your Reminder")
        .setMessage("Are you feeling Vulnerable?\nAre you drunk?\nAre you unsafe?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() 
         {
            public void onClick(DialogInterface dialog, int which) //+4915218547736
            { 
            	vibrator.cancel();
            	r.play();
            	
            	SmsManager smsManager = SmsManager.getDefault();
            	SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            	String sendTo = pref.getString("h1", "111");
            	String myMessage = "Help Me!! I'm Vulnerable. I need your attention.";
            	smsManager.sendTextMessage(sendTo, null, myMessage, null, null);
            	/*Toast.makeText(getApplicationContext(),pref.getString("h1", "111"),Toast.LENGTH_LONG).show();
            	Intent smsIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("sms:+4917672607164"));
            	smsIntent.putExtra("sms_body", "Press send to send me");
            	startActivity(smsIntent);*/
            	dialog.dismiss();
            }
         })
         .setNegativeButton("No", new DialogInterface.OnClickListener() 
         {
             public void onClick(DialogInterface dialog, int which) 
             { 
            	 vibrator.cancel();
            	 r.stop();
                 dialog.dismiss();
             }
          })
        .setIcon(android.R.drawable.ic_lock_idle_alarm).show();
	}
}
