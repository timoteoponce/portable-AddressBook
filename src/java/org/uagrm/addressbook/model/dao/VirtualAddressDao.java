package org.uagrm.addressbook.model.dao;

import java.util.Set;

import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.VirtualAddress;

/**
 * @author Timoteo Ponce
 * 
 */
public interface VirtualAddressDao extends GenericDao<VirtualAddress> {
    static final String TABLE_NAME = "VIRTUAL_ADDRESS";
    
    Set<VirtualAddress> getByContact(Contact contact);
}
