package com.android.mobileevent;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.my_button).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClick(View arg0) {
		Button b = (Button)findViewById(R.id.my_button);


		b.setClickable(false);
		new LongRunningGetIO().execute();
		}
	
    private class LongRunningGetIO extends AsyncTask <Void, Void, String> {
		
		protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
	       InputStream in = entity.getContent();
	         StringBuffer out = new StringBuffer();
	         int n = 1;
	         while (n>0) {
	             byte[] b = new byte[4096];
	             n =  in.read(b);
	             if (n>0) out.append(new String(b, 0, n));
	         }
	         return out.toString();
	    }
		
		@Override
		protected String doInBackground(Void... params) {
			HttpClient client = new DefaultHttpClient();

			//Get the default settings from APN
			  @SuppressWarnings("deprecation")
			String proxyHost = android.net.Proxy.getDefaultHost();
			  int proxyPort = android.net.Proxy.getDefaultPort();
			//Set Proxy params of client, if they are not the standard
			    if (proxyHost != null && proxyPort > 0) {
			        HttpHost proxy = new HttpHost(proxyHost, proxyPort);
			        client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			    }
			 HttpContext localContext = new BasicHttpContext();
             HttpGet httpGet = new HttpGet("http://doodle-test.com/api1WithoutAccessControl/polls/bxtfvni8kgbm4ifx");
             String text = null;
             try {
                   HttpResponse response = client.execute(httpGet, localContext);
                   HttpEntity entity = response.getEntity();
                   text = getASCIIContentFromEntity(entity);
             } catch (Exception e) {
            	 return e.getLocalizedMessage();
             }
             return text;
		}	
		
		protected void onPostExecute(String results) {
			if (results!=null) {
				EditText et = (EditText)findViewById(R.id.my_edit);
				et.setText(results);
			}
			Button b = (Button)findViewById(R.id.my_button);
			b.setClickable(true);
		}
    }
}
