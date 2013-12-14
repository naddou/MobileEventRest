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
import android.widget.EditText;

public class Consulter extends Activity {

	EditText textNom;
	EditText textTitre;
	EditText textEmail;
	EditText jour1;
	EditText jour2;
	EditText jour3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sondage_event);
		
		 textNom = (EditText)findViewById(R.id.editTextNom);
		 textTitre = (EditText)findViewById(R.id.editTextTitre);
		 textEmail = (EditText)findViewById(R.id.editTextEmail);
		 
		 jour1 = (EditText)findViewById(R.id.editTextJour1);
		 jour2 = (EditText)findViewById(R.id.editTextJour2);
		 jour3 = (EditText)findViewById(R.id.editTextJour3);
		 
		 new LongRunningGetIO().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consulter, menu);
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
					//EditText et = (EditText)findViewById(R.id.editText1);
					Sondage sondage = new Sondage();
					
					sondage.deserialiserSondage(doc);
					textTitre.setText(sondage.getTitreSondage());
					
					
					
					System.out.println(sondage.getInitiateur().getNom().toString());
					textNom.setText( sondage.getInitiateur().getNom());
					textEmail.setText(sondage.getInitiateur().getCourriel());
					
					if(sondage.getPlageHoraire()==null)
					{
						System.out.println("cest null");
						return;
					}
					
					String Tjour1 = sondage.getPlageHoraire().get(0).getJour().toString();
					String Tjour2 = sondage.getPlageHoraire().get(1).getJour().toString();
					String Tjour3 = sondage.getPlageHoraire().get(2).getJour().toString();
					//String []split = jour.split("-");
					jour1.setText(Tjour1);
					jour2.setText(Tjour2);
					jour3.setText(Tjour3);
					
					
					//et.setText(sondage.toString());
				}
			}
	}

}
