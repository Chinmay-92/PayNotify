package com.example.chinmay_pingale.mylibrary;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;

import static com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;


public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button submit=(Button)findViewById(R.id.submit_button);

    }

public void submit(){
    GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
            .addConnectionCallbacks(new ConnectionCallbacks() {
                @Override
                public void onConnected(Bundle connectionHint) {
                    Log.d("TAG","onConnected: " + connectionHint);

                    // Now you can use the Data Layer API
                }
                @Override
                public void onConnectionSuspended(int cause) {
                    Log.d("TAG", "onConnectionSuspended: " + cause);
                }
            })
            .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {

                @Override
                public void onConnectionFailed(ConnectionResult connectionResult) {
                    Log.d("TAG", "onConnectionFailed: " + connectionResult);

                }
            })
                    // Request access only to the Wearable API
            .addApi(Wearable.API)
            .build();

}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
