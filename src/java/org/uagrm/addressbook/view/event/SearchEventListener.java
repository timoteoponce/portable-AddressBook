package org.uagrm.addressbook.view.event;

import java.util.EventListener;

/**
 * @author Timoteo Ponce
 *
 */
public interface SearchEventListener extends EventListener {
    void eventFired(SearchEvent event);
}
