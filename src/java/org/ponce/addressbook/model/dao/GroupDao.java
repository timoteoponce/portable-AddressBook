package org.ponce.addressbook.model.dao;

import java.util.Set;

import org.ponce.addressbook.model.Group;

/**
 * @author Timoteo Ponce
 *
 */
public interface GroupDao extends GenericDao<Group> {
    static final String TABLE_NAME = "GROUPS";

    static final String CONTACT_JOIN_TABLE_NAME = "GROUP_CONTACTS";

    Set<Group> getByContact(final Integer contactId);
    
    void saveContacts(Group group);
}
