package org.uagrm.addressbook.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.model.dao.GenericDao;
import org.uagrm.addressbook.view.View;

public abstract class AbstractController<T> implements Controller<T> {

	private final List<View<T>> viewList = new ArrayList<View<T>>();
	
	private final List<T> cachedElementList = new ArrayList<T>();

	private final Logger log;

	abstract protected GenericDao<T> getDao();

	public AbstractController() {
		log = Logger.getLogger(getClass());
	}

	@Override
	abstract public void save(T element);

	abstract protected void saveReferences(T element, Class<?> target);

	@Override
	public void addView(View<T> view) {
		if (!viewList.contains(view)) {
			viewList.add(view);
			log.info("Adding view: " + view.getClass().getName());
		} else {
			log.warn("Duplicated view : " + view.getClass().getName());
		}
	}

	@Override
	public void delete(T element) {
		getDao().delete(element);
		updateAllViews(null);
	}

	@Override
	public Collection<T> getElements() {
		if(cachedElementList.isEmpty()){
			refreshElementList();
		}
		return cachedElementList;
	}

	@Override
	public T preloadEntity(T entity, Class<?> target) {
		getDao().loadReferences(entity, target);
		return entity;
	}

	@Override
	public void removeView(View<T> view) {
		log.debug("Removing view: " + view.getClass().getName());
		viewList.remove(view);
	}

	@Override
	public void updateAllViews(T model) {
		refreshElementList();
		// I'm using a copied list to avoid the exception
		// :java.util.ConcurrentModificationException
		List<View<T>> copyList = new ArrayList<View<T>>(viewList);
		for (View<T> view : copyList) {
			view.update(model);
		}
	}

	private void refreshElementList() {
		cachedElementList.clear();
		cachedElementList.addAll(getDao().selectAll());
	}

}
