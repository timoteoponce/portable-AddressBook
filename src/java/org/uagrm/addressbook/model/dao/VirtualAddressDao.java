package org.uagrm.addressbook.model.dao;

import org.uagrm.addressbook.model.VirtualAddress;

/**
 * @author Timoteo Ponce
 * 
 */
public interface VirtualAddressDao extends GenericDao<VirtualAddress> {
    static final String TABLE_NAME = "VIRTUAL_ADDRESS";
}
