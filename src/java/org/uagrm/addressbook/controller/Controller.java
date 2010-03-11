package org.uagrm.addressbook.controller;

import java.util.Collection;
import java.util.Observer;

/**
 * @author Timoteo Ponce
 *
 * @param <T> Target entity take from org.uagrm.addressboo.model.
 */
public interface Controller<T> {
	
    /*MVC*/
    void addView(Observer view);

    void removeView(Observer view);	
    
    /*Business*/
    
    Collection<T> getElements();
    
	void save(T element);

    void delete(T element);
    
    /**
     * Returns the given entity with related references of the given target.
     * @param entity
     * @param target 
     * @return
     */
    T preloadEntity(T entity,Class<?> target);
}
