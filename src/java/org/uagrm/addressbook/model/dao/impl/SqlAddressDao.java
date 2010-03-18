package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.uagrm.addressbook.controller.actions.ActionType;
import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Country;
import org.uagrm.addressbook.model.ReferenceLink;
import org.uagrm.addressbook.model.dao.AddressDao;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Timoteo Ponce
 * 
 */
public class SqlAddressDao extends AbstractSqlDao<Address> implements
	AddressDao {

    @Override
    protected void fillValues(Address entity, ResultSet rs) throws SQLException {
	final Address address = entity;
	address.setId(rs.getInt(1));
	address.setCity(rs.getString(2));
	address.setCountry(getCountry(rs.getInt(3)));
	address.setNumber(rs.getString(4));
	address.setStreet(rs.getString(5));
    }

    private Country getCountry(int int1) {
	// TODO implement!!
	throw new NotImplementedException();
    }

    @Override
    protected String getFields(Address entity, ActionType action) {
	// TODO implement
	throw new NotImplementedException();	
    }

    @Override
    protected String getTableName() {
	return TABLE_NAME;
    }

    @Override
    public void loadReferences(Address entity, Class<?> clazz) {
	// not used
    }

    @Override
    protected Address loadValues(ResultSet rs) throws SQLException {
	Address address = new Address();
	fillValues(address, rs);

	return address;
    }

    @Override
    protected Collection<ReferenceLink> getReferences(Address entity) {
	return new ArrayList<ReferenceLink>();
    }

}
