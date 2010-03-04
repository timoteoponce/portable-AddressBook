package org.uagrm.addressbook.model.dao;

import java.util.Set;

import org.uagrm.addressbook.model.Contact;

/**
 * @author Timoteo Ponce
 *
 */
public interface ContactDao extends GenericDao<Contact> {
    
    	static final String TABLE_NAME = "CONTACTS";

	Set<Contact> getByGroup(final Integer groupId);

	void saveAddresses(Contact entity);

	void saveVirtualAddresses(Contact entity);

	void savePhones(Contact entity);
}
