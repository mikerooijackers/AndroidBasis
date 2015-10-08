package org.fhict.contactreader;

import java.util.List;

public interface OnContactsLoadedListener {
	void onLoaded(List<String> contactNames);
}
