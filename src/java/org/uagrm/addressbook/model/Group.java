package org.uagrm.addressbook.model;

import java.util.HashSet;
import java.util.Set;

import org.uagrm.addressbook.view.dialog.SelectableItem;

/**
 * @author Timo
 * @version 1.0
 * @created 16-ene-2010 0:59:51
 */
public class Group implements SelectableItem {

    private Integer id;
    private String name;
    private String description;
    private final Set<Contact> contacts = new HashSet<Contact>();

    public Group() {

    }

    public Group(Integer id, String name, String description) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Set<Contact> getContacts() {
	return contacts;
    }
    
    @Override
    public String toString() {	
	return id + " , " + name + " , " + description;
    }

    @Override
    public boolean equals(SelectableItem item) {
	return this.id.equals(item.getId());
    }

}