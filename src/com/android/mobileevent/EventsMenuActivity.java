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

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EventsMenuActivity extends Activity {
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
		EditText et = (EditText)findViewById(R.id.editText1);
		
		
		et.setText(userName+"\n");
		
//		TextView textview=(TextView)findViewById(R.id.textView1);
//		
//		textview.append(userName.toString());
		
		new LongRunningGetIO().execute();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.events_menu, menu);
		return true;
	}
	
	
	
	private class LongRunningGetIO extends AsyncTask <Void, Void, Document> {

			@Override
			protected Document doInBackground(Void... params) {
				HttpClient client = new DefaultHttpClient();

				//Get the default settings from APN
				@SuppressWarnings("deprecation")
				String proxyHost = android.net.Proxy.getDefaultHost();
				@SuppressWarnings("deprecation")
				int proxyPort = android.net.Proxy.getDefaultPort();
				//Set Proxy params of client, if they are not the standard
				if (proxyHost != null && proxyPort > 0) {
					HttpHost proxy = new HttpHost(proxyHost, proxyPort);
					client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
				}
				    
				HttpContext localContext = new BasicHttpContext();
		        HttpGet httpGet = new HttpGet("http://doodle-test.com/api1WithoutAccessControl/polls/6xcezf6kq7gqn8vt");
		        Document doc;
		         
		        try {
		         	HttpResponse response = client.execute(httpGet, localContext);
		            HttpEntity entity = response.getEntity();
		                   
		            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		            DocumentBuilder db = dbf.newDocumentBuilder();
		            doc = db.parse(entity.getContent());
		        } catch (Exception e) {
		        	return null;
		        }
		             
		        return doc;
			}	
			
			protected void onPostExecute(Document doc) {
				if (doc != null) {
					EditText et = (EditText)findViewById(R.id.editText1);
					Sondage sondage = new Sondage();
					
					sondage.deserialiserSondage(doc);
					
					et.setText(sondage.toString());
				}
			}			
	}

}
