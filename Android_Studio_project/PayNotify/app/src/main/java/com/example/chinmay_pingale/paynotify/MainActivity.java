package com.example.chinmay_pingale.paynotify;

import android.app.Activity;
import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.Wearable;

import static com.example.chinmay_pingale.paynotify.R.drawable.pay_ic;

public class MainActivity extends Activity implements DataApi.DataListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private TextView mTextView;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });
        stub.setBackgroundResource(pay_ic);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), pay_ic);
        builder.setLargeIcon(bm);
        builder.setContentTitle("PayNotify App");
        builder.setContentText("Notification of credit card payment by HDFC of amt:500 at 12:02:2015 at pune for Flipkart");

        builder.setColor(getResources().getColor(R.color.black));
        builder.setSmallIcon(pay_ic);
        NotificationCompat.BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
        secondPageStyle.setBigContentTitle("Detailed summary")
                .bigText("A lot of text...");
        secondPageStyle.setSummaryText("Summary Text:test.........");

        //secondPageStyle.setBuilder(builder);

    // Create second page notification
        Notification secondPageNotification =
                new NotificationCompat.Builder(this)
                        .setStyle(secondPageStyle)
                        .setSmallIcon(pay_ic)

                        .build();
        NotificationCompat.WearableExtender extender = new NotificationCompat.WearableExtender()
                .addPage(secondPageNotification)
                .setStartScrollBottom(true);
        //extender.setContentIcon(R.drawable.pay_ic);
        //extender.setBackground();
    // Extend the notification builder with the second page

        Notification notification = builder
                .extend(extender)
                .build();



    // Build the notification and issues it with notification manager.
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1, builder.build());
        // notification above

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onConnected(Bundle bundle) {
        if (Log.isLoggable("TAG", Log.DEBUG)) {
            Log.d("TAG", "Connected to Google Api Service");
        }
        Wearable.DataApi.addListener(mGoogleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent event : dataEvents) {
            if (event.getType() == DataEvent.TYPE_DELETED) {
                Log.d("TAG", "DataItem deleted: " + event.getDataItem().getUri());
            } else if (event.getType() == DataEvent.TYPE_CHANGED) {
                Log.d("TAG", "DataItem changed: " + event.getDataItem().getUri());
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    protected void onStop() {
        if (null != mGoogleApiClient && mGoogleApiClient.isConnected()) {
            Wearable.DataApi.removeListener(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
        super.onStop();    }
}
