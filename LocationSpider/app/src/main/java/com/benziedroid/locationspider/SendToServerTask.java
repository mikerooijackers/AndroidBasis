package com.benziedroid.locationspider;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

class SendToServerTask extends AsyncTask<Void,Void,Void> {
	//private static final String baseUrl = "http://192.168.2.32:8080/LocationSpy/";
	private static final String baseUrl = "http://freakyfriday-benziedroid.rhcloud.com/LocationSpy-0.1/";
	private static final String insertUrl = baseUrl + "spyMessage/insert";
	private String deviceId;
	private Location location;

	public SendToServerTask(String deviceId, Location location) {
		this.deviceId = deviceId;
		this.location = location;
	}

	@Override
	protected Void doInBackground(Void... params) {
		String result = "";

		try {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//			nameValuePairs.add(new BasicNameValuePair("deviceId", deviceId));
//			
//			if(location != null) {
//				nameValuePairs.add(new BasicNameValuePair("latitude", String.format(Locale.US, "%g",location.getLatitude())));
//				nameValuePairs.add(new BasicNameValuePair("longitude", String.format(Locale.US, "%g",location.getLongitude())));
//			}
//			
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs);
			String url = insertUrl;
			url += "?deviceId=" +deviceId;
			if(location != null) {
				url += "&lattitude=" + location.getLatitude();
				url += "&longitude=" + "**.**"; //Better hide this... location.getLongitude();
			}

			result = postToAndGetResult(url, urlEncodedFormEntity);
		} catch (UnsupportedEncodingException e) {
			appendExceptionToLog("UnsupportedEncodingException",e);
		}

		Log.d(Configuration.TAG, "Network send " + result);

		return null;
	}

	private String postToAndGetResult(String url, HttpEntity httpEntity){
		String result = "NOK";

		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			post.setEntity(httpEntity);

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			result = client.execute(post,responseHandler);

		} catch (ClientProtocolException e) {
			appendExceptionToLog("ClientProtocalException",e);
		} catch (IOException e) {
			appendExceptionToLog("IOException",e);
		}

		return result;
	}

	private void appendExceptionToLog(String message, Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		Log.d(Configuration.TAG, sw.toString());
	}
}