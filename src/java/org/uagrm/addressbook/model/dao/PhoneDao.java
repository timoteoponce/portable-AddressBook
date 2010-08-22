package org.uagrm.addressbook.model.dao;

import java.util.Set;

import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Phone;

/**
 * @author Timoteo Ponce
 * 
 */
public interface PhoneDao extends Home<Phone> {
    static final String TABLE_NAME = "PHONE";
    
    Set<Phone> getByContact(Contact contact);
}
