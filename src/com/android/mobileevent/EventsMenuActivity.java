package com.android.mobileevent;

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

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class EventsMenuActivity extends Activity implements OnClickListener{
	String userName ;
	String password ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.presentation);
		
		Bundle bundle = getIntent().getExtras();
		String message = bundle.getString("User");
		String[] splited = message.split("\\s+");
		 userName = splited[0];
		 password = splited[1];
//		
//		EditText et = (EditText)findViewById(R.id.editText1);
//		
//		
//		et.setText(userName+"\n");
		
		TextView txtView = (TextView)findViewById(R.id.lick2) ;
		
		txtView.setOnClickListener(this);
		
		//new LongRunningGetIO().execute();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.events_menu, menu);
		return true;
	}
	
	
	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		System.out.println("sadsa");
		
		Intent intentEventsMenuActiviry = new Intent(EventsMenuActivity.this, Consulter.class);
		
		EventsMenuActivity.this.startActivity(intentEventsMenuActiviry);
		
	}

}
