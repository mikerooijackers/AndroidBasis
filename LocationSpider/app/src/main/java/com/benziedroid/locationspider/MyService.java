package com.benziedroid.locationspider;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by mikerooijackers on 21-09-15.
 */
public class MyService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        DeviceInfoProvider deviceInfoProvider = new DeviceInfoProvider(this);
        SendToServerTask sendToServerTask = new SendToServerTask(deviceInfoProvider.getDevideId(), deviceInfoProvider.getLocation());
        sendToServerTask.execute();
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
