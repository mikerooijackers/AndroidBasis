package org.fhict.contactreader;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

public class TodoLoaderTask extends AsyncTask<Void, Void, List<String>> {
	private static final String AUTHORITY = "de.vogella.android.todos.contentprovider";
	private static final String BASE_PATH = "todos";

	public static final Uri CONTENT_URI = Uri.parse("content://"
			+ AUTHORITY + "/" + BASE_PATH);

	private OnContactsLoadedListener listener;
	private ContentResolver contentResolver;

	public TodoLoaderTask(OnContactsLoadedListener listener, ContentResolver contentResolver)	{
		this.listener = listener;
		this.contentResolver = contentResolver;
	}

	@Override
	protected List<String> doInBackground(Void... arg0) {
		List<String> todos = new ArrayList<String>();

		String[] projection = new String[] {"summary", "description", "category"};

		Cursor cursor = contentResolver.query(CONTENT_URI, projection, null, null, null);

		if (cursor != null) {
			cursor.moveToFirst();
			String category = cursor.getString(cursor.getColumnIndexOrThrow("category"));
		}
		todos.add(cursor.getString(cursor.getColumnIndexOrThrow("summary")));
		todos.add(cursor.getString(cursor.getColumnIndexOrThrow("description")));

		// Always close the cursor
		cursor.close();

		todos.add("Not empty...");

		return todos;
	}

	@Override
	protected void onPostExecute(List<String> todos) {
		listener.onLoaded(todos);
	}
}
