package com.example.chinmay_pingale.paynotify;

import android.net.Uri;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.data.FreezableUtils;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by chinmay_pingale on 2/12/2015.
 */
public class DataLayerListenerService extends WearableListenerService {


        private static final String WEARABLE_DATA_PATH = "/notifyAW";

        @Override
        public void onDataChanged(DataEventBuffer dataEvents) {

            DataMap dataMap;
            for (DataEvent event : dataEvents) {

                // Check the data type
                if (event.getType() == DataEvent.TYPE_CHANGED) {
                    // Check the data path
                    String path = event.getDataItem().getUri().getPath();
                    if (path.equals(WEARABLE_DATA_PATH)) {}
                    dataMap = DataMapItem.fromDataItem(event.getDataItem()).getDataMap();
                    Log.v("myTag", "DataMap received on watch: " + dataMap);
                }
            }
        }
    }
