package org.uagrm.addressbook.model.dao;

import java.util.Set;

import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Contact;

/**
 * @author Timoteo Ponce
 *
 */
public interface AddressDao extends Home<Address> {
    static final String TABLE_NAME = "ADDRESS";
    
    Set<Address> getByContact(Contact contact);
}
