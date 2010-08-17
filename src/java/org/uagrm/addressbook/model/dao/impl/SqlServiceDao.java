package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.text.StrBuilder;
import org.uagrm.addressbook.controller.actions.ActionType;
import org.uagrm.addressbook.model.Service;
import org.uagrm.addressbook.model.dao.ReferenceLink;
import org.uagrm.addressbook.model.dao.ServiceDao;

/**
 * @author Timoteo Ponce
 * 
 */
public class SqlServiceDao extends AbstractSqlDao<Service> implements ServiceDao {

	@Override
	protected void fillValues(Service entity, ResultSet rs) throws SQLException {
		final Service service = entity;
		service.setId(rs.getInt("ID"));
		service.setName(rs.getString("NAME"));
		service.setDescription(rs.getString("DESCRIPTION"));
		service.setImage(rs.getString("IMAGE"));
	}

	@Override
	protected String getFields(Service service, ActionType action) {
		final StrBuilder buffer = new StrBuilder();

		switch (action) {
		case CREATE:
			buffer.append("(null,'" + service.getName() + "',");
			buffer.append("'" + service.getDescription() + "',");
			buffer.append("'" + service.getImage() + "')");
			break;
		case UPDATE:
			buffer.append("NAME='" + service.getName() + "',");
			buffer.append("DESCRIPTION='" + service.getDescription() + "',");
			buffer.append("IMAGE='" + service.getImage() + "'");
			break;
		}
		return buffer.toString();
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public void loadReferences(Service entity, Class<?> clazz) {
		// not used
	}

	@Override
	protected Service loadValues(ResultSet rs) throws SQLException {
		Service service = new Service();
		fillValues(service, rs);

		return service;
	}

	@Override
	protected Collection<ReferenceLink> getReferences(Service entity) {
		return new ArrayList<ReferenceLink>();
	}
}
