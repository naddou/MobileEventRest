package com.android.mobileevent;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class Consulter extends Activity {

	EditText textNom;
	EditText textTitre;
	EditText textEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sondage_event);
		
		 textNom = (EditText)findViewById(R.id.editTextNom);
		 textTitre = (EditText)findViewById(R.id.editTextTitre);
		 textEmail = (EditText)findViewById(R.id.editTextEmail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consulter, menu);
		return true;
	}

}
