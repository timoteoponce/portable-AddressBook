package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.text.StrBuilder;
import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.actions.ActionType;
import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Country;
import org.uagrm.addressbook.model.dao.AddressDao;
import org.uagrm.addressbook.model.dao.CountryDao;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.ReferenceLink;
import org.uagrm.addressbook.model.dao.SqlOperation;

/**
 * @author Timoteo Ponce
 * 
 */
public class SqlAddressDao extends AbstractSqlDao<Address> implements
		AddressDao {

	public SqlAddressDao() {
		super(Address.class);
	}

	@Override
	protected void fillValues(Address entity, ResultSet rs) throws SQLException {
		final Address address = entity;
		address.setId(rs.getInt("ID"));
		address.setCity(rs.getString("CITY"));
		address.setCountry(getCountry(rs.getInt("ID_COUNTRY")));
		address.setNumber(rs.getString("NUMBER"));
		address.setStreet(rs.getString("STREET"));
	}

	private Country getCountry(int countryId) {
		CountryDao countryDao = DaoFactory.getInstance(CountryDao.class);
		return countryDao.find(new Country(countryId));
	}

	@Override
	protected String getFields(ActionType action) {
		final StrBuilder buffer = new StrBuilder();
		final Address address = getInstance();

		switch (action) { 
		case CREATE:
			buffer.append("(null,");
			buffer.append("'" + address.getStreet() + "',");
			buffer.append("'" + address.getNumber() + "',");
			buffer.append("'" + address.getCity() + "',");
			buffer.append(address.getCountry().getId() + ")");
			break;
		case UPDATE:
			buffer.append("STREET = '" + address.getStreet() + "',");
			buffer.append("NUMBER = '" + address.getNumber() + "',");
			buffer.append("CITY = '" + address.getCity() + "',");
			buffer.append("ID_COUNTRY = " + address.getCountry().getId());
			break;
		}
		return buffer.toString();
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public void loadReferences(final Address address) {
		// not used
	}

	@Override
	protected Address loadValues(ResultSet rs) throws SQLException {
		Address address = new Address();
		fillValues(address, rs);

		return address;
	}

	@Override
	protected Collection<ReferenceLink> getReferences() {
		return new ArrayList<ReferenceLink>();
	}

	@Override
	public Set<Address> getByContact(Contact contact) {
		final QueryBuilder builder = QueryBuilder.createQuery(SqlOperation.SQL_SELECT_ALL);
		builder.setVariable(VAR_COLUMNS, "c.*");
		builder.setVariable(VAR_TABLE, TABLE_NAME);
		builder.append(" c INNER JOIN " + "CONTACT_ADDRESSES");
		builder.append(" gc ON c.ID = gc.ID_ADDRESS WHERE gc.ID_CONTACT = " + contact.getId());

		final Set<Address> addresses = new HashSet<Address>();
		ResultSet rs = getDatabaseHandler().executeQuery(builder.getQuery());
		try {
			while (rs.next()) {
				Address address = new Address();
				fillValues(address, rs);
				addresses.add(address);
			}
		} catch (SQLException e) {
			Logger.getLogger(SqlAddressDao.class).error(e, e);
		} finally {
			getDatabaseHandler().closeQuietly(rs);
		}
		return addresses;
	}

}
