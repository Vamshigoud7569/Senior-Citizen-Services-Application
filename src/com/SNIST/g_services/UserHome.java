package com.SNIST.g_services;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserHome extends Activity {
	TextView uid;
	Button ser,sta,feed,pro,log;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_home);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		uid=(TextView)findViewById(R.id.textView2);
		uid.setText(globalvariabel.GetUsername().toString());
		ser=(Button)findViewById(R.id.button1);
		sta=(Button)findViewById(R.id.button2);
		feed=(Button)findViewById(R.id.button3);
		pro=(Button)findViewById(R.id.button4);
		log=(Button)findViewById(R.id.button5);
		db=openOrCreateDatabase("snist", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists reg(uid varchar,pas varchar,em varchar,mob varchar);");
		ser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(UserHome.this,Ubook.class));
			}
		});
		sta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(UserHome.this,UBstatus.class));
			}
		});
		feed.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(UserHome.this,Ufeed.class));
	}
});
		pro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Cursor c=db.rawQuery("SELECT * FROM reg WHERE uid='"+uid.getText()+"'", null);
	    		if(c.getCount()==0)
	    		{
	    			showMessage("Error", "No records found");
	    			return;
	    		}
	    		StringBuffer buffer=new StringBuffer();
	    		while(c.moveToNext())
	    		{
	    			buffer.append("Password: "+c.getString(1)+"\n");
	    			buffer.append("Email: "+c.getString(2)+"\n");
	    			buffer.append("Mobile No: "+c.getString(3)+"\n\n");
	    		}
	    		showMessage("User Profile", buffer.toString());
			}
		});
		log.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(UserHome.this,Home.class));
	}
});
		}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Toast.makeText(UserHome.this, "Press Logout button", Toast.LENGTH_LONG).show();
	}
	public void showMessage(String title,String message)
    {
    	Builder builder=new Builder(this);
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.show();
	}
	
}
