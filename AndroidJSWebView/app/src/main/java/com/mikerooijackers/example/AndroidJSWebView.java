package com.mikerooijackers.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.mikerooijackers.example.R;


public class AndroidJSWebView extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        WebView browser;
        browser=(WebView)findViewById(R.id.webkit);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.addJavascriptInterface(new WebAppInterface(this), "Android");
        browser.loadUrl("http://apps.programmerguru.com/examples/androidjs.html");
    }
    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }
        
        /**
         * Show Toast Message
         * @param toast
         */
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
        
        /**
         * Show Dialog 
         * @param dialogMsg
         */
        public void showDialog(String dialogMsg){
        	AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();

            alertDialog.setTitle("JS triggered Dialog");
            alertDialog.setMessage(dialogMsg);
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	Toast.makeText(mContext, "Dialog dismissed!", Toast.LENGTH_SHORT).show();
                }
            });

            alertDialog.show();
        }
        
        /**
         * Intent - Move to next screen
         */
        public void moveToNextScreen(){
        	 AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
             alertDialog.setTitle("Alert");
             alertDialog.setMessage("Are you sure you want to leave to next screen?");
             alertDialog.setPositiveButton("YES",
                     new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int which) {
                        	 Intent chnIntent = new Intent(AndroidJSWebView.this, ChennaiIntent.class);  
                        	 startActivity(chnIntent);  
                         }
                     });
             alertDialog.setNegativeButton("NO",
                     new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int which) {
                             dialog.cancel();
                         }
                     });
             alertDialog.show();
        }
    }
}
