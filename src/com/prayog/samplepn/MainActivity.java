package com.prayog.samplepn;

import java.io.IOException;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private String tag = "Main";
	private GoogleCloudMessaging gcm;
	private String PROJECT_NUMBER = "490547477553";
	protected String regid;
	protected EditText etRegId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etRegId = (EditText)findViewById(R.id.text);
		System.out.println("Inside MainActivity....");
		//getRegId();
	}

	public void getRegId(){
        new AsyncTask<Void, Void, String>() {
            

			protected void onPreExecute() {};

			@Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regid = gcm.register(PROJECT_NUMBER);
                    msg = "Device registered, registration ID=" + regid;
                    Log.i("GCM",  msg);

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();

                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                etRegId.setText(msg + "\n");
            }
        }.execute(null, null, null);
    }
   
    
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
