package org.uagrm.addressbook.controller;

import java.util.Collection;

import org.uagrm.addressbook.view.View;

/**
 * @author Timoteo Ponce
 *
 * @param <T>
 */
public interface Controller<T> {
	
    /*MVC*/
    void addView(View<T> view);

    void removeView(View<T> view);

    void updateAllViews();

    void modelChanged(T model);
    
    /*Business*/
    
    Collection<T> getElements();
    
    void save(T element);

    /**
     * Saves the entity references defined by the target class.
     * @param element
     * @param target
     */
    void saveReferences(T element, Class<?> target);

    void delete(T element);
    
    /**
     * Returns the given entity with related references of the given target.
     * @param entity
     * @param target 
     * @return
     */
    T preloadEntity(T entity,Class<?> target);
}
