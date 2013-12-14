package com.android.mobileevent;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;

//import com.projet.mobilEvent.EventsMenuActivity;
//import com.projet.mobilEvent.MainActivity;
//import com.projet.mobilEvent.R;

//import com.projet.mobilEvent.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Menu;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connection);
		// findViewById(R.id.my_button).setOnClickListener(this);

		Button buttonConnected = (Button) findViewById(R.id.save);
		buttonConnected.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View arg0) {
		
		if(textViewEmpty())
		{
			Toast.makeText(getApplicationContext(), "Information manquante", Toast.LENGTH_SHORT).show();
			return;
		}
			
		String user = getUsername();
		String password = getPassword();
		
		Intent intentEventsMenuActiviry = new Intent(MainActivity.this, EventsMenuActivity.class);
		intentEventsMenuActiviry.putExtra("User", user +" "+password);
		MainActivity.this.startActivity(intentEventsMenuActiviry);
		
		//Button b = (Button)findViewById(R.id.my_button);


		//b.setClickable(false);
		//new LongRunningGetIO().execute();
	}

	public String getPassword() {

		//EditText userName = (EditText) findViewById(R.id.screenName);
		 EditText password = (EditText)findViewById(R.id.password);

		//String user = userName.getText().toString();
		 String pass = password.getText().toString();
		if (pass == null)
			return null;

		return pass;
	}
	
	public String getUsername() {

		EditText userName = (EditText) findViewById(R.id.screenName);
		// EditText password = (EditText)findViewById(R.id.password);

		String user = userName.getText().toString();
		// String pass = password.getText().toString();
		if (user == null)
			return null;

		return user;
	}

	public boolean textViewEmpty() {

		EditText userName = (EditText) findViewById(R.id.screenName);
		EditText password = (EditText) findViewById(R.id.password);

		String user = userName.getText().toString();
		String pass = password.getText().toString();

		if (user.length() == 0 || userName.length() == 0)
			return true;
		if (pass.length() == 0 || password.length() == 0)
			return true;

		return false;

	}

	
}
