package org.uagrm.addressbook.view.event;

import java.util.EventObject;

public class GenericEvent extends EventObject {

	private final GenericEventType type;

	public GenericEvent(Object source, GenericEventType type) {
		super(source);
		this.type = type;
	}

	public GenericEventType getType() {
		return type;
	}

}
