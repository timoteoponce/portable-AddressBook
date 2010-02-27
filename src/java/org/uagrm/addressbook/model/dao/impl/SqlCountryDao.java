package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.uagrm.addressbook.controller.actions.CommonActions;
import org.uagrm.addressbook.model.Country;
import org.uagrm.addressbook.model.ReferenceLink;
import org.uagrm.addressbook.model.dao.CountryDao;

/**
 * @author Timoteo Ponce
 *
 */
public class SqlCountryDao extends AbstractSqlDao<Country> implements
		CountryDao {

	@Override
	protected void fillValues(Country entity, ResultSet rs) throws SQLException {
		final Country country = (Country) entity;
		country.setId(rs.getInt(1));
		country.setName(rs.getString(2));
	}

	@Override
	protected String getFields(Country entity, CommonActions action) {
		String out = "";
		final Country country = (Country) entity;

		switch (action) {
		case CREATE:
			out = "(null,'" + country.getName() + "');";
			break;

		case UPDATE:
			out = "name='" + country.getName() + "'";
			break;
		}
		return out;
	}

	@Override
	protected String getTableName() {
		// TODO make it constant
		return "COUNTRY";
	}

	@Override
	public void loadReferences(Country entity, Class<?> clazz) {
		// not used

	}

	@Override
	protected Country loadValues(ResultSet rs) throws SQLException {
		final Country country = new Country();
		fillValues(country, rs);
		return country;
	}

	@Override
	protected Collection<ReferenceLink> getReferences(Country entity) {
		return new ArrayList<ReferenceLink>();
	}

}