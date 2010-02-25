package org.ponce.addressbook.controller;

import java.util.Collection;

import org.ponce.addressbook.view.View;

/**
 * @author Timoteo Ponce
 *
 * @param <T>
 */
public interface Controller<T> {
    void addView(View<T> view);

    void removeView(View<T> view);

    void updateAllViews();

    void modelChanged(T model);
    
    Collection<T> getElements();
    
    void save(T element);

    void saveReferences(T element, Class<?> target);

    void delete(T element);
}
