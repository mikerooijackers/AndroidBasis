package org.fhict.contactreader;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnContactsLoadedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //new ContactLoaderTask(this, getContentResolver()).execute();
        new TodoLoaderTask(this, getContentResolver()).execute();
        
        TextView tv = (TextView) findViewById(R.id.textViewContactsUri);
        tv.setText(ContactsContract.Contacts.CONTENT_URI.toString());
    }

	@Override
	public void onLoaded(List<String> contactNames) {
		String[] array = new String[contactNames.size()];
		contactNames.toArray(array);
		
		ListView listView = (ListView) findViewById(R.id.listViewContacts);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_list_item_1, array);
		
		listView.setAdapter(adapter);
	}
}
