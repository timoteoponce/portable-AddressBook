package org.uagrm.addressbook.view;

import java.util.Observer;

import org.uagrm.addressbook.controller.Controller;

/**
 * @author Timoteo Ponce
 *
 * @param <T>
 */
public interface View<T> extends Observer{

    void setModel(T model);        

    Controller<T> getController();
    
    /**
     * This operation removes the current view from the  
     * controller view list and close/hide the view.
     */
    void close();    

}
