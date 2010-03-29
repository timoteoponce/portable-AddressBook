package org.uagrm.addressbook.view.event;

public interface GenericEventTrigger {
	void addEventListener(GenericEventListener listener);

	void fireEvent(GenericEventType type);
}
