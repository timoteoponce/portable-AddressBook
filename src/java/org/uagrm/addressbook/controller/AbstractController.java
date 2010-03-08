package org.uagrm.addressbook.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.model.dao.GenericDao;
import org.uagrm.addressbook.view.View;

public abstract class AbstractController<T> implements Controller<T> {    

    private final List<View<T>> viewList = new ArrayList<View<T>>();  
    
    private final Logger log;

    abstract protected GenericDao<T> getDao();    
    
    public AbstractController() {
	log = Logger.getLogger(getClass());
    }
    
    @Override
    abstract public void save(T element);

    @Override
    abstract public void saveReferences(T element, Class<?> target);

    @Override
    public void addView(View<T> view) {
	if (!viewList.contains(view)) {
	    viewList.add(view);
	    log.info("Adding view: " + view);
	} else {
	    log.warn("Duplicated view : " + view);
	}	
    }

    @Override
    public void delete(T element) {
	getDao().delete(element);	
    }

    @Override
    public Collection<T> getElements() {
	return getDao().selectAll();
    }

    @Override
    public void fireChange(T model) {
	log.info("Model has changed, updating all views: [ " + model + " ]");
	updateAllViews(model);	
    }

    @Override
    public T preloadEntity(T entity, Class<?> target) {
	getDao().loadReferences(entity, target);
	return entity;
    }

    @Override
    public void removeView(View<T> view) {
	log.info("Removing view: " + view);
	viewList.remove(view);	
    }
    
    @Override
    public void updateAllViews(final T model) {
	//I'm using a copied list to avoid the exception :java.util.ConcurrentModificationException
	List<View<T>> copyList = new ArrayList<View<T>>(viewList);	
	for (final View<T> view : copyList) {
	    SwingUtilities.invokeLater(new Runnable() {	        
	        @Override
	        public void run() {
	            view.update(model);
	        }
	    });	    
	}	
    }  

}
