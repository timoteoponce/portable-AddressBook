package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.uagrm.addressbook.controller.actions.CommonActions;
import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Country;
import org.uagrm.addressbook.model.ReferenceLink;
import org.uagrm.addressbook.model.dao.AddressDao;

/**
 * @author Timoteo Ponce
 *
 */
public class SqlAddressDao extends AbstractSqlDao<Address> implements
		AddressDao {

	@Override
	protected void fillValues(Address entity, ResultSet rs) throws SQLException {
		final Address address = (Address) entity;
		address.setId(rs.getInt(1));
		address.setCity(rs.getString(2));
		address.setCountry(getCountry(rs.getInt(3)));
		address.setNumber(rs.getString(4));
		address.setStreet(rs.getString(5));
	}

	private Country getCountry(int int1) {
		// TODO implement!!
		return null;
	}

	@Override
	protected String getFields(Address entity, CommonActions action) {
		String out = "";
		switch (action) {
		case CREATE:
			// TODO implement
			break;

		case UPDATE:
			// TODO implement
			break;
		}
		return out;
	}

	@Override
	protected String getTableName() {
		// TODO make it constant
		return "ADDRESS";
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
