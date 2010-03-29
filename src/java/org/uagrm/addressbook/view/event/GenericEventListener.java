package org.uagrm.addressbook.view.event;

import java.util.EventListener;

public interface GenericEventListener extends EventListener {
	void eventFired(GenericEvent event);
}
