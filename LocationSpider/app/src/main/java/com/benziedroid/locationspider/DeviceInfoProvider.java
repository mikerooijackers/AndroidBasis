package com.benziedroid.locationspider;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public class DeviceInfoProvider {
	
	private Context context;

	public DeviceInfoProvider(Context context) {
		this.context = context;
	}
	
	public Location getLocation() {
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);

		String provider = locationManager.getBestProvider(criteria, true);
		if(!locationManager.isProviderEnabled(provider)) {
			Log.d(Configuration.TAG, String.format("%s is not enabled",provider));
			return null;
		}

		Location lastKnownLocation = locationManager.getLastKnownLocation(provider);
		if(lastKnownLocation == null) {
			Log.d(Configuration.TAG, String.format("Provider %s has no last known location.",provider));
			return null;
		}
		
		Log.d(Configuration.TAG, String.format("Provider %s (lat,lon) = (%g, %g)", provider,
				lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude()));
		
		return lastKnownLocation;
	}
	
	public String getDevideId() {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = telephonyManager.getDeviceId();
		
		//Let's not really share all this information
		deviceId = "*****" + deviceId.substring(5);
		
		Log.d(Configuration.TAG, String.format("Device id: %s",deviceId));
		
		return deviceId;
	}
}
