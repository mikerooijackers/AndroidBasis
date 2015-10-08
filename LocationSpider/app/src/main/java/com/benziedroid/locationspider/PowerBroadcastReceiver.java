package com.benziedroid.locationspider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

/**
 * Created by mikerooijackers on 21-09-15.
 */
public class PowerBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, MyService.class);
        context.startService(intent1);
    }
}
