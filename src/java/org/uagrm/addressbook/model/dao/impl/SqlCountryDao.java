package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.text.StrBuilder;
import org.uagrm.addressbook.controller.actions.ActionType;
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
		final Country country = entity;
		country.setId(rs.getInt("ID"));
		country.setName(rs.getString("NAME"));
		country.setImage(rs.getString("IMAGE"));
	}

	@Override
	protected String getFields(Country country, ActionType action) {
		final StrBuilder buffer = new StrBuilder();

		switch (action) {
		case CREATE:
			buffer.append("(null,'" + country.getName() + "',");
			buffer.append("'" + country.getImage() + "')");
			break;

		case UPDATE:
			buffer.append("NAME='" + country.getName() + "',");
			buffer.append("IMAGE='" + country.getImage() + "'");
			break;
		}
		return buffer.toString();
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
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
