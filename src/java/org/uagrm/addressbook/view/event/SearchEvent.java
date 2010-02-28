package org.uagrm.addressbook.view.event;

import java.util.EventObject;

public class SearchEvent extends EventObject {
    private final SearchEventType type;

    public SearchEvent(Object source, SearchEventType type) {
	super(source);
	this.type = type;
    }

    public SearchEventType getType() {
	return type;
    }
}
