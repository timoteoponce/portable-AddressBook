package org.ponce.addressbook.model.dao;

import java.util.Set;

import org.ponce.addressbook.model.Entity;

public interface GroupDao extends GenericDao<Entity> {
    static final String TABLE_NAME = "GROUPS";

    static final String CONTACT_JOIN_TABLE_NAME = "GROUP_CONTACTS";

    Set<Entity> getByContact(final Integer contactId);
    
    void saveContacts(Entity group);
}
