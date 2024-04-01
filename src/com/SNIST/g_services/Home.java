package com.SNIST.g_services;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {
	TextView reg;
	Button log;
	EditText uid,pas;
	String uidd;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		uid=(EditText)findViewById(R.id.editText1);
		pas=(EditText)findViewById(R.id.editText2);
		reg=(TextView)findViewById(R.id.textView2);
		log=(Button)findViewById(R.id.button1);
		uidd=uid.getText().toString();
		db=openOrCreateDatabase("snist", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists reg(uid varchar,pas varchar,em varchar,mob varchar);");
		
		log.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(uid.getText().toString().trim().length()==0||pas.getText().toString().trim().length()==0)
				{
					Toast.makeText(Home.this, "Enter UserName or Password", Toast.LENGTH_LONG).show();
					return;
				}
				if(uid.getText().toString().equals("snist")&&pas.getText().toString().equals("snist#"))
				{
					Toast.makeText(Home.this, "Welcome to Admin Home" +uidd, Toast.LENGTH_LONG).show();
					globalvariabel.Setusername(uid.getText().toString());
					startActivity(new Intent(Home.this,AdminHome.class));
					clr();
				}
				Cursor cc=db.rawQuery("select * from reg where uid='"+uid.getText()+"' and pas='"+pas.getText()+"'", null);
				if(cc.moveToNext())
				{
					Toast.makeText(Home.this, "Welcome to User Home" +uidd, Toast.LENGTH_LONG).show();
					globalvariabel.Setusername(uid.getText().toString());
					startActivity(new Intent(Home.this,UserHome.class));
					clr();
				}
				
				
			}
		});
		
		
		
		//registration
reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Home.this,Reg.class));
			}
		});
	}
	public void clr()
	{
		uid.setText("");
		pas.setText("");
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Toast.makeText(Home.this, "Application Closing", Toast.LENGTH_LONG).show();
		finish();
	}

}
