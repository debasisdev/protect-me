package info.androidhive.tabsswipe;

import java.util.TimeZone;

import info.androidhive.tabsswipe.adapter.TabsPagerAdapter;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener 
{
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "HOME", "REMINDERS", "CALLS" };

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Berlin"));  
		//Initialisation
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
		String rem,sob;
		// Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_settings:
	        	new AlertDialog.Builder(this)
	            .setTitle("Developers")
	            .setMessage("HCI Dummy Project by\nDebasis Kar\nAbhilash Mishra\nDeepak Kumar\n\nGuided By:\nJun.-Prof. Dr. Niels Henze")
	            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() 
	             {
	                public void onClick(DialogInterface dialog, int which) 
	                { 
	                    dialog.dismiss();
	                }
	             })
	            .setIcon(android.R.drawable.ic_dialog_info).show();
	            return true;
	        case R.id.help:
	        	Toast.makeText(getApplicationContext(), "Help Section Under Development", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.feedback:
	        	        final Dialog rankDialog1 = new Dialog(this, R.style.FullHeightDialog);
	        	        rankDialog1.setContentView(R.layout.userstudy);
	        	        rankDialog1.setCancelable(true);
	        	        final RatingBar ratingBar1 = (RatingBar)rankDialog1.findViewById(R.id.dialog_ratingbar);
	        	        ratingBar1.setRating(2);
	        	        Button updateButton = (Button) rankDialog1.findViewById(R.id.rank_dialog_button);
	        	        final EditText ete = (EditText) rankDialog1.findViewById(R.id.editText1);
	        	        updateButton.setOnClickListener(new View.OnClickListener() 
	        	        {
	        	            @Override
	        	            public void onClick(View v) 
	        	            {
	        	            	Intent email = new Intent(Intent.ACTION_SEND);
	        	            	email.putExtra(Intent.EXTRA_EMAIL, new String[]{"deepakkumar.ce@gmail.com"});		  
	        	            	email.putExtra(Intent.EXTRA_SUBJECT, "App Feedback");
	        	            	email.putExtra(Intent.EXTRA_TEXT, "Rating: "+Float.toString(ratingBar1.getRating())+
	        	            			ete.getText().toString());
	        	            	email.setType("message/rfc822");
	        	            	startActivity(Intent.createChooser(email, "Choose An Email Client :"));
	        	            	rankDialog1.dismiss();
	        	            }
	        	        });  
	        	        rankDialog1.show();   
	        	        return true;
	        case R.id.MySet:
	        	if(pref.getInt("ReminderInterval",1)==1) 
	        		rem="2 Hours";
	        	else if (pref.getInt("ReminderInterval",1)==2)
	        		rem="4 Hours";
	        	else
	        		rem="8 Hours";
	        	if(pref.getInt("SobrietyTestDifficulty",1)==1) 
	        		sob="Easy";
	        	else if (pref.getInt("SobrietyTestDifficulty",1)==2)
	        		sob="Medium";
	        	else
	        		sob="Difficult";
	        	new AlertDialog.Builder(this)
	            .setTitle("Current Settings")
	            .setMessage("Reminder Interval: "+rem+"\n"+"Sobriety Test Difficulty: "+sob+"\n"
	            		+"Reminder Type: "+pref.getInt("ReminderType",1)+"\n")
	            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() 
	             {
	                public void onClick(DialogInterface dialog, int which) 
	                { 
	                    // continue with delete
	                }
	             })
	            .setIcon(android.R.drawable.ic_dialog_info).show();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}	
}
