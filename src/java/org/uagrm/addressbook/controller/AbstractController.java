package org.uagrm.addressbook.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.model.Entity;
import org.uagrm.addressbook.model.dao.Home;
import org.uagrm.addressbook.model.dto.EntityStatus;
import org.uagrm.addressbook.model.dto.StatusType;

public abstract class AbstractController<T> extends Observable implements
		Controller<T> {

	private final List<T> cachedElementList = new ArrayList<T>();

	private final Logger log;

	private final Home<T> home;

	public AbstractController(final Home<T> home) {
		log = Logger.getLogger(getClass());
		this.home = home;
	}

	protected Home<T> getHome() {
		return this.home;
	}

	@Override
	public void save(T element, boolean updateViews) {
		Entity entity = (Entity) element;
		getHome().setInstance(element);

		// save or update the group
		if (entity.getId() == null) {
			log.debug("Creating " + entity.getClass().getSimpleName());
			getHome().persist();
		} else {
			log.debug("Updating " + entity.getClass().getSimpleName());
			getHome().update();
		}
		if (updateViews) {
			updateAllViews(EntityStatus.create(element, StatusType.UPDATED));
		}
		getHome().clearInstance();
	}

	abstract protected void saveReferences(T element, Class<?> target);

	@Override
	public void addView(Observer observer) {
		log.debug("Adding view: " + observer.getClass().getName());
		this.addObserver(observer);
	}

	@Override
	public void delete(T element, boolean updateViews) {
		getHome().setInstance(element);
		getHome().delete();
		getHome().clearInstance();
		if (updateViews) {
			updateAllViews(EntityStatus.create(element, StatusType.DELETED));
		}
	}

	@Override
	public Collection<T> getElements() {
		if (cachedElementList.isEmpty()) {
			refreshElementList();
		}
		return cachedElementList;
	}

	@Override
	public <K> java.util.Collection<T> getElementsBy(java.lang.Class<K> targetClass, K target) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeView(Observer observer) {
		log.debug("Removing view: " + observer.getClass().getName());
		this.deleteObserver(observer);
	}

	public void updateAllViews(EntityStatus<T> model) {
		refreshElementList();
		setChanged();
		notifyObservers(model);
	}

	protected void refreshElementList() {
		cachedElementList.clear();
		cachedElementList.addAll(getHome().selectAll());
	}

}
