package org.uagrm.addressbook.model.dao;

import org.uagrm.addressbook.model.Country;

/**
 * @author Timoteo Ponce
 * 
 */
public interface CountryDao extends GenericDao<Country> {
    static final String TABLE_NAME = "COUNTRY";
}
