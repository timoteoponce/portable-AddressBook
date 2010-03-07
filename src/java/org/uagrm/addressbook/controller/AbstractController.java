package org.uagrm.addressbook.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.model.dao.GenericDao;
import org.uagrm.addressbook.view.View;

public abstract class AbstractController<T> implements Controller<T> {
    
    private static final Logger LOG = Logger.getLogger(AbstractController.class);

    private final List<View<T>> viewList = new ArrayList<View<T>>();        

    abstract protected GenericDao<T> getDao();
    
    @Override
    abstract public void save(T element);

    @Override
    abstract public void saveReferences(T element, Class<?> target);

    @Override
    public void addView(View<T> view) {
	if (!viewList.contains(view)) {
	    viewList.add(view);
	    LOG.info("Adding view: " + view);
	} else {
	    LOG.warn("Duplicated view : " + view);
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
    public void modelChanged(T model) {
	LOG.info("Model has changed, updating all views: [ " + model + " ]");
	updateAllViews(model);	
    }

    @Override
    public T preloadEntity(T entity, Class<?> target) {
	getDao().loadReferences(entity, target);
	return entity;
    }

    @Override
    public void removeView(View<T> view) {
	LOG.info("Removing view: " + view);
	viewList.remove(view);	
    }
    
    @Override
    public void updateAllViews(T model) {
	//I'm using a copied list to avoid the exception :java.util.ConcurrentModificationException
	List<View<T>> copyList = new ArrayList<View<T>>(viewList);	
	for (View<T> view : copyList) {
	    view.update(model);
	}	
    }  

}
