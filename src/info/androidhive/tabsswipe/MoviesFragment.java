package info.androidhive.tabsswipe;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.tabsswipe.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MoviesFragment extends Fragment
{
	Context con;
	EditText edt1, edt2, edt3;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
		con = getActivity().getApplicationContext();
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
		
		Button btn = (Button) rootView.findViewById(R.id.button1);
		Button btn1 = (Button) rootView.findViewById(R.id.Button01);
		Button btn2 = (Button) rootView.findViewById(R.id.Button02);
		Button btn3 = (Button) rootView.findViewById(R.id.button2);
		Button btn4 = (Button) rootView.findViewById(R.id.Button03);
		Button btn5 = (Button) rootView.findViewById(R.id.button3);   //Show Helpers
		Button btn6 = (Button) rootView.findViewById(R.id.button4);   //Show Black
		Button btn7 = (Button) rootView.findViewById(R.id.button5);   //Remove Black
		edt1 = (EditText) rootView.findViewById(R.id.editText1);
		edt2 = (EditText) rootView.findViewById(R.id.EditText01);
		edt3 = (EditText) rootView.findViewById(R.id.EditText02);
		edt1.setText(pref.getString("h1", null));
		edt2.setText(pref.getString("h2", null));
		
		btn.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v) 
            {
            	Intent intent= new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
                startActivityForResult(intent, 12774);
            }
        });
		
		btn1.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v) 
            {
                 Intent intent= new Intent(Intent.ACTION_PICK,  Contacts.CONTENT_URI);
                 startActivityForResult(intent, 12768);
            }
        });
		
		btn2.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v) 
            {
                 Intent intent= new Intent(Intent.ACTION_PICK,  Contacts.CONTENT_URI);
                 startActivityForResult(intent, 12789);
            }
        });
		
		btn3.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v) 
            {
            	SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
	        	Editor editor = pref.edit();
	        	editor.putString("h1", edt1.getText().toString());
	        	editor.putString("h2", edt2.getText().toString());
	        	editor.commit();
            	Toast.makeText(con, "Saved Helpers", Toast.LENGTH_SHORT).show();
            }
        });
		
		btn4.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v) 
            {
            	SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
	        	Editor editor = pref.edit();
	        	if(pref.getInt("MyCnt",0)==0) 
	        	{
	        		editor.putInt("MyCnt",1);
	        		editor.putString("bL"+pref.getInt("MyCnt", 1), edt3.getText().toString());
		        	editor.putInt("MyCnt",pref.getInt("MyCnt", 1)+1);
	        	}
	        	else
	        	{
		        	editor.putString("bL"+pref.getInt("MyCnt", 1), edt3.getText().toString());
		        	editor.putInt("MyCnt",pref.getInt("MyCnt", 1)+1);
		        	if(pref.getInt("MyCnt", 1)==6)
		        		editor.putInt("MyCnt",1);
	        	}
	        	editor.commit();
            	Toast.makeText(con, "Saved BlackList", Toast.LENGTH_SHORT).show();
            }
        });
		
		btn5.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v) 
            {
            	SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
            	new AlertDialog.Builder(getActivity())
	            .setTitle("Current Helpers")
	            .setMessage("<1>:"+pref.getString("h1", null)+"\n<2>:"+pref.getString("h2", null))
	            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() 
	             {
	                public void onClick(DialogInterface dialog, int which) 
	                { 
	                    dialog.dismiss();
	                }
	             })
	            .setIcon(android.R.drawable.ic_dialog_info).show();
            }
        });
		
		btn6.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v) 
            {
            	SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
            	new AlertDialog.Builder(getActivity())
	            .setTitle("Current Black List")
	            .setMessage("<1>:"+pref.getString("bL1", null)+"\n<2>:"+pref.getString("bL2", null)
	            		+"\n<3>:"+pref.getString("bL3", null)+"\n<4>:"+pref.getString("bL4", null)
	            		+"\n<5>:"+pref.getString("bL5", null))
	            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() 
	             {
	                public void onClick(DialogInterface dialog, int which) 
	                { 
	                    dialog.dismiss();
	                }
	             })
	            .setIcon(android.R.drawable.ic_menu_info_details).show();
            }
        });
		
		btn7.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v) 
            {
            	SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
	        	Editor editor = pref.edit();
	        	if(edt3.getText().toString().equals(pref.getString("bL1", null)))
	        		editor.remove("bL1");
	        	else if(edt3.getText().toString().equals(pref.getString("bL2", null)))
	        		editor.remove("b32");
	        	else if(edt3.getText().toString().equals(pref.getString("bL3", null)))
	        		editor.remove("bL3");
	        	else if(edt3.getText().toString().equals(pref.getString("bL4", null)))
	        		editor.remove("bL4");
	        	else
	        		editor.remove("bL5");
	        	editor.commit();
	        	Toast.makeText(con, "Removed", Toast.LENGTH_SHORT).show();
            }
        });
		return rootView;
	}
	
	@SuppressWarnings("static-access")
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
        if (resultCode == getActivity().RESULT_OK) 
        {
        	 Cursor cursor = null;  
			 String phoneNumber = "";
			 List<String> allNumbers = new ArrayList<String>();
			 int phoneIdx = 0;
            switch (requestCode) 
            {
	            case 12774:  try 
	            			 {  
	            				 Uri result = data.getData();  
	            				 String id = result.getLastPathSegment();  
	            				 cursor = getActivity().getContentResolver().query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + "=?", new String[] { id }, null);  
	            				 phoneIdx = cursor.getColumnIndex(Phone.DATA);
	            				 if (cursor.moveToFirst()) 
	            				 {
	            					 while (cursor.isAfterLast() == false) 
	            					 {
	            						 phoneNumber = cursor.getString(phoneIdx);
	            						 allNumbers.add(phoneNumber);
	            						 cursor.moveToNext();
	            					 }
	            				 } 
	            				 else 
	            				 {
	            				 }  
	            			 } 
	            			 catch (Exception e) {} 
	            			 finally 
	            			 {  
				                if (cursor != null) {  
				                    cursor.close();
				                }
	            			 }
	            			final CharSequence[] items = allNumbers.toArray(new String[allNumbers.size()]);
	                       	edt1.setText(items[0].toString());
	            			break;
	            			
	            case 12768: try 
				   			 {  
				   				 Uri result = data.getData();  
				   				 String id = result.getLastPathSegment();  
				   				 cursor = getActivity().getContentResolver().query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + "=?", new String[] { id }, null);  
				   				 phoneIdx = cursor.getColumnIndex(Phone.DATA);
				   				 if (cursor.moveToFirst()) 
				   				 {
				   					 while (cursor.isAfterLast() == false) 
				   					 {
				   						 phoneNumber = cursor.getString(phoneIdx);
				   						 allNumbers.add(phoneNumber);
				   						 cursor.moveToNext();
				   					 }
				   				 } 
				   				 else 
				   				 {
				   				 }  
				   			 } 
				   			 catch (Exception e) {} 
				   			 finally 
				   			 {  
					                if (cursor != null) {  
					                    cursor.close();
					                }
				   			 }
				   			final CharSequence[] itemx = allNumbers.toArray(new String[allNumbers.size()]);
	            			edt2.setText(itemx[0].toString());
	            			break;
	            			
	            case 12789: try 
				   			 {  
				   				 Uri result = data.getData();  
				   				 String id = result.getLastPathSegment();  
				   				 cursor = getActivity().getContentResolver().query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + "=?", new String[] { id }, null);  
				   				 phoneIdx = cursor.getColumnIndex(Phone.DATA);
				   				 if (cursor.moveToFirst()) 
				   				 {
				   					 while (cursor.isAfterLast() == false) 
				   					 {
				   						 phoneNumber = cursor.getString(phoneIdx);
				   						 allNumbers.add(phoneNumber);
				   						 cursor.moveToNext();
				   					 }
				   				 } 
				   				 else 
				   				 {
				   				 }  
				   			 } 
				   			 catch (Exception e) {} 
				   			 finally 
				   			 {  
					                if (cursor != null) {  
					                    cursor.close();
					                }
				   			 }
				   			final CharSequence[] itemz = allNumbers.toArray(new String[allNumbers.size()]);
	            			edt3.setText(itemz[0].toString());
	            			break;
	            default: Toast.makeText(con, "No request code matched.", Toast.LENGTH_SHORT).show();
	            		 break;
            }
        } 
        else 
        {
        	Toast.makeText(con, "Warning: Activity result not ok", Toast.LENGTH_SHORT).show();
        }
    }
}
