package org.ponce.addressbook.model.dao;

import java.util.Set;

import org.ponce.addressbook.model.Entity;

public interface ContactDao extends GenericDao<Entity> {
    static final String TABLE_NAME = "CONTACTS";

    Set<Entity> getByGroup(final Integer groupId);

    void saveAddresses(Entity entity);

    void saveVirtualAddresses(Entity entity);

    void savePhones(Entity entity);
}
