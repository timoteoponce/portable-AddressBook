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
    
	<K> Collection<T> getElementsBy(Class<K> targetClass, K target);

	void save(T element, boolean updateViews);

	void delete(T element, boolean updateViews);
    
}
