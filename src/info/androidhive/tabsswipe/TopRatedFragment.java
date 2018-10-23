package info.androidhive.tabsswipe;

import java.util.Calendar;

import info.androidhive.tabsswipe.R;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class TopRatedFragment extends Fragment
{
	Context con;
	BroadcastReceiver CustomOutGoingCallListener;
	int sett=0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View rootView = inflater.inflate(R.layout.fragment_top_rated, container, false);
		con = getActivity().getApplicationContext();
		SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
		
		Switch toggle = (Switch) rootView.findViewById(R.id.switch1);
		Switch toggle1 = (Switch) rootView.findViewById(R.id.switch2);
		Switch toggle2 = (Switch) rootView.findViewById(R.id.switch3);
		Switch toggle3 = (Switch) rootView.findViewById(R.id.switch4);
		
		if(pref.getInt("Rem",0)==1) toggle.setChecked(true);
		if(pref.getInt("Black",0)==1) toggle1.setChecked(true);
		if(pref.getInt("Gps",0)==1) toggle2.setChecked(true);
		if(pref.getInt("Sob",0)==1) toggle3.setChecked(true);
		
		toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) 
		        {
		        	Toast.makeText(con, "Reminders-SMS Service is ON", Toast.LENGTH_LONG).show();
		        	SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
		        	Editor editor = pref.edit();
		        	editor.putInt("Rem",1);
		        	editor.commit();
		        	int interval = pref.getInt("ReminderInterval",1);
			   		if(interval==1)			 interval=2*3600000;
			   		else if(interval==2)     interval=4*3600000;
			   		else               		 interval=8*3600000;
		   		    Calendar calendar = Calendar.getInstance();
		   		    Intent intent = new Intent(getActivity(), AlaBroad.class);
		   		    PendingIntent pendingIntent = PendingIntent.getBroadcast(con, 0, intent, 0);
		   		    AlarmManager alarmManager = (AlarmManager) con.getSystemService(Context.ALARM_SERVICE);
		            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),interval, pendingIntent);  
		        } 
		        else 
		        {
		        	SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
		        	Editor editor = pref.edit();
		        	editor.putInt("Rem",0);
		        	editor.commit();
		        	Intent myIntent = new Intent(getActivity(), AlaBroad .class);
		        	PendingIntent pendingIntent = PendingIntent.getBroadcast(con, 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		        	AlarmManager  alarmManager = (AlarmManager) con.getSystemService(Context.ALARM_SERVICE);
		        	alarmManager.cancel(pendingIntent);
		        	Toast.makeText(con, "Reminders-SMS Service is OFF", Toast.LENGTH_LONG).show();
		        }
		    }
		});
		
		toggle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) 
		        {
		        	SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
		        	Editor editor = pref.edit();
		        	editor.putInt("Black",1);
		        	editor.commit();
		        	CustomOutGoingCallListener = new BroadcastReceiver(){
		        		@Override
		        		public void onReceive(Context context, Intent intent) 
		        		{
		        			SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
		        			String number=intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER).toString();
		        			if (number.equals(pref.getString("bL1", null))||
		        					number.equals(pref.getString("bL2", null))||	
		        					    number.equals(pref.getString("bL3", null))||
		        					        number.equals(pref.getString("bL4", null))||
		        					           number.equals(pref.getString("bL5", null)))
		        			{
			        			setResultData(null);
			        			Toast.makeText(context, "Call Can't Proceed. Give a Test or Wait", Toast.LENGTH_LONG).show();
		        			}
		        		}
		        	};
		        	IntentFilter filter= new IntentFilter("android.intent.action.NEW_OUTGOING_CALL");
		        	getActivity().registerReceiver(CustomOutGoingCallListener, filter);
		        	Toast.makeText(con, "BlackListing Service is ON", Toast.LENGTH_SHORT).show();
		        } 
		        else 
		        { 
		        	SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
		        	Editor editor = pref.edit();
		        	editor.putInt("Black",0);
		        	editor.commit();
		        	if (CustomOutGoingCallListener != null)
		        	{
		        	 getActivity().unregisterReceiver(CustomOutGoingCallListener);
		        	 CustomOutGoingCallListener = null;
		        	}
		        	Toast.makeText(con, "BlackListing Service is OFF", Toast.LENGTH_SHORT).show();
		        }
		    }
		});
		
		toggle2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) 
		        {
		        	Toast.makeText(con, "GPS Location Sharing is ON", Toast.LENGTH_SHORT).show();
		        	SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
		        	Editor editor = pref.edit();
		        	editor.putInt("Gps",1);
		        	editor.commit();
		        	Calendar calendar = Calendar.getInstance();
		        	Intent intent = new Intent(getActivity(), GpsBroad.class);
		   		    PendingIntent pendingIntent = PendingIntent.getBroadcast(con, 0, intent, 0);
		   		    AlarmManager alarmManager = (AlarmManager) con.getSystemService(Context.ALARM_SERVICE);
		            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),1*60*60*1000, pendingIntent);
		           	//getActivity().startService(new Intent(getActivity(), MyAlaService.class));
		        } 
		        else 
		        {
		        	SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
		        	Editor editor = pref.edit();
		        	editor.putInt("Gps",0);
		        	editor.commit();
		        	Intent myIntent = new Intent(getActivity(), GpsBroad.class);
		        	PendingIntent pendingIntent = PendingIntent.getBroadcast(con, 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		        	AlarmManager  alarmManager = (AlarmManager) con.getSystemService(Context.ALARM_SERVICE);
		        	alarmManager.cancel(pendingIntent);
		        	Toast.makeText(con, "GPS Location Sharing is OFF", Toast.LENGTH_SHORT).show();
		        	//getActivity().stopService(new Intent(getActivity(), MyAlaService.class));
		        }
		    }
		});
		
		toggle3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) 
		        {
		        	Toast.makeText(con, "Sobriety Test Service is ON", Toast.LENGTH_SHORT).show();
		        	SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
		        	Editor editor = pref.edit();
		        	editor.putInt("Sob",1);
		        	editor.commit();
		        } 
		        else 
		        {
		        	Toast.makeText(con, "Sobriety Test Service is OFF", Toast.LENGTH_SHORT).show();
		        	SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
		        	Editor editor = pref.edit();
		        	editor.putInt("Sob",0);
		        	editor.commit();
		        }
		    }
		});
		
		RadioGroup radioGroupMenu = (RadioGroup) rootView.findViewById(R.id.radioGroup1);
		radioGroupMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() 
	    {
	        public void onCheckedChanged(RadioGroup group, int checkedId) 
	        {
	        	switch (checkedId) 
	        	{
		            case R.id.radioButton1: sett=1; break;
		            case R.id.radioButton2: sett=2; break;
		            case R.id.radioButton3: sett=3; break;
		            default: sett=1; break;
	            }
	        	SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
	        	Editor editor = pref.edit();
	        	editor.putInt("SobrietyTestDifficulty", sett);
	        	editor.commit();
	        	Toast.makeText(con, "Updated", Toast.LENGTH_SHORT).show();
	        }
	    });
		return rootView;
	}
}
