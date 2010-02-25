package org.ponce.addressbook.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.ponce.addressbook.model.Contact;
import org.ponce.addressbook.view.View;

public class ContactController implements Controller<Contact> {
    
    private static final Logger LOG = Logger.getLogger(ContactController.class);

    private static Controller<Contact> instance;

    private final List<View<Contact>> viewList = new ArrayList<View<Contact>>();

    private ContactController() {
    }

    public static Controller<Contact> getInstance() {
	if (instance == null) {
	    instance = new ContactController();
	}
	return instance;
    }

    @Override
    public void addView(View<Contact> view) {
	// TODO Auto-generated method stub

    }

    @Override
    public void modelChanged(Contact model) {
	// TODO Auto-generated method stub

    }

    @Override
    public void removeView(View<Contact> view) {
	// TODO Auto-generated method stub

    }

    @Override
    public void updateAllViews() {
	// TODO Auto-generated method stub

    }

    @Override
    public Set<Contact> getElements() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void delete(Contact element) {
	// TODO Auto-generated method stub

    }

    @Override
    public void save(Contact element) {
	// TODO Auto-generated method stub

    }

    @Override
    public void saveReferences(Contact element, Class<?> target) {
	// TODO Auto-generated method stub

    }

}
