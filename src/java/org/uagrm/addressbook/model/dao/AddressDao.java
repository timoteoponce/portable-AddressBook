package org.uagrm.addressbook.model.dao;

import org.uagrm.addressbook.model.Address;

/**
 * @author Timoteo Ponce
 *
 */
public interface AddressDao extends GenericDao<Address> {
    static final String TABLE_NAME = "ADDRESS";
}
