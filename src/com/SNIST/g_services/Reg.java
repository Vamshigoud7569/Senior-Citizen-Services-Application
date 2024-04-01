package com.SNIST.g_services;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reg extends Activity {
	TextView reg;
	Button log;
	EditText uid,pas,em,mob;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		uid=(EditText)findViewById(R.id.editText1);
		pas=(EditText)findViewById(R.id.editText2);
		em=(EditText)findViewById(R.id.editText3);
		mob=(EditText)findViewById(R.id.editText4);
		log=(Button)findViewById(R.id.button1);
		db=openOrCreateDatabase("snist", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists reg(uid varchar,pas varchar,em varchar,mob varchar);");
		
		log.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(uid.getText().toString().trim().length()==0||pas.getText().toString().trim().length()==0)
				{
					Toast.makeText(Reg.this, "Enter UserName or Password", Toast.LENGTH_LONG).show();
					return;
				}
				if(!android.util.Patterns.EMAIL_ADDRESS.matcher(em.getText().toString()).matches())
				{
					em.setError("Enter Proper Email id");
					return;
				}
				if(mob.getText().toString().length()!=10)
				{
					mob.setError("Enter Valid Mobile No");
					return;
				}
				db.execSQL("insert into reg values('"+uid.getText()+"','"+pas.getText()+"','"+em.getText()+"','"+mob.getText()+"');");
				Toast.makeText(Reg.this, "Registration Successfully Done", Toast.LENGTH_LONG).show();
				clr();
				
			}
		});
		
		
	}
public void clr()
{
	uid.setText("");
	pas.setText("");
	em.setText("");
	mob.setText("");
}
	

}
