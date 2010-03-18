package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.text.StrBuilder;
import org.uagrm.addressbook.controller.actions.ActionType;
import org.uagrm.addressbook.model.Protocol;
import org.uagrm.addressbook.model.ReferenceLink;
import org.uagrm.addressbook.model.dao.ProtocolDao;

/**
 * @author Timoteo Ponce
 * 
 */
public class SqlProtocolDao extends AbstractSqlDao<Protocol> implements
		ProtocolDao {

	@Override
	protected void fillValues(Protocol entity, ResultSet rs)
			throws SQLException {
		final Protocol protocol = entity;
		protocol.setId(rs.getInt(1));
		protocol.setName(rs.getString(2)); 
		protocol.setPort(rs.getInt(3));
	}

	@Override
	protected String getFields(Protocol protocol, ActionType action) {
		final StrBuilder buffer = new StrBuilder();

		switch (action) {
		case CREATE:
			buffer.append("(null,'" + protocol.getName() + "',");
			buffer.append(protocol.getPort() + ")");
			break;
		case UPDATE:
			buffer.append("name='" + protocol.getName() + "',");
			buffer.append("port=" + protocol.getPort());
			break;
		}
		return buffer.toString();
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public void loadReferences(Protocol entity, Class<?> clazz) {
		// not used
	}

	@Override
	protected Protocol loadValues(ResultSet rs) throws SQLException {
		Protocol protocol = new Protocol();
		fillValues(protocol, rs);

		return protocol;
	}

	@Override
	protected Collection<ReferenceLink> getReferences(Protocol entity) {
		return new ArrayList<ReferenceLink>();
	}
}
