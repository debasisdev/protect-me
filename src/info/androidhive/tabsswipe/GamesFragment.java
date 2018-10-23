package info.androidhive.tabsswipe;

import info.androidhive.tabsswipe.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GamesFragment extends Fragment 
{
	Context con;
    int sett=0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View rootView = inflater.inflate(R.layout.fragment_games, container, false);
		con = getActivity().getApplicationContext();
		
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
	        	editor.putInt("ReminderInterval", sett);
	        	editor.commit();
	        	Toast.makeText(con, "Updated", Toast.LENGTH_SHORT).show();
	        }
	    });		
		return rootView;
	}
}
