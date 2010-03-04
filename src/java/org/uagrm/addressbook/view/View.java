package org.uagrm.addressbook.view;

import org.uagrm.addressbook.controller.Controller;

/**
 * @author Timoteo Ponce
 *
 * @param <T>
 */
public interface View<T> {

    void setModel(T model);

    void update();    

    Controller<T> getController();
    
    /**
     * This operation removes the current view from the  
     * controller view list and close/hide the view.
     */
    void close();

}
