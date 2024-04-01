package com.SNIST.g_services;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Ubook extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubook);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ubook, menu);
		return true;
	}

}
