package org.uagrm.addressbook.view;

import java.util.Observer;

/**
 * @author Timoteo Ponce
 *
 * @param <T>
 */
public interface View<T> extends Observer{

	void setModel(T model);

	T getModel();
    
    /**
     * This operation removes the current view from the  
     * controller view list and close/hide the view.
     */
    void close();    

}
