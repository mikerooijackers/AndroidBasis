package org.fhict.contactreader;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;

public class ContactLoaderTask extends AsyncTask<Void, Void, List<String>> {
	private OnContactsLoadedListener listener;
	private ContentResolver contentResolver;

	public ContactLoaderTask(OnContactsLoadedListener listener,
			ContentResolver contentResolver)	{
		this.listener = listener;
		this.contentResolver = contentResolver;
	}

	@Override
	protected List<String> doInBackground(Void... arg0) {
		List<String> contactNames = new ArrayList<String>();

		Uri uri = ContactsContract.Contacts.CONTENT_URI;
		String[] projection = new String[] {
				ContactsContract.Contacts.DISPLAY_NAME,
		};

		String selection = null;
		String[] selectionArgs = null;
		String sortOrder = null;

		Cursor cursor = contentResolver.query(uri, projection, 
							      selection, selectionArgs, sortOrder);

		if(cursor.moveToFirst()) {
			while(!cursor.isAfterLast()) {

				int nameColumnId = cursor.getColumnIndex(projection[0]);
				String name = cursor.getString(nameColumnId);
				contactNames.add(name);

				cursor.moveToNext();
			}
		}

		return contactNames;
	}

	@Override
	protected void onPostExecute(List<String> contactNames) {
		listener.onLoaded(contactNames);
	}
}
