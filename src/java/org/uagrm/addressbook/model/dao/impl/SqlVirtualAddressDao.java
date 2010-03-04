package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.uagrm.addressbook.controller.actions.CommonActions;
import org.uagrm.addressbook.model.Protocol;
import org.uagrm.addressbook.model.ReferenceLink;
import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GenericDao;
import org.uagrm.addressbook.model.dao.ProtocolDao;
import org.uagrm.addressbook.model.dao.VirtualAddressDao;

/**
 * @author Timoteo Ponce
 *
 */
public class SqlVirtualAddressDao extends AbstractSqlDao<VirtualAddress>
		implements VirtualAddressDao {

	@Override
	protected void fillValues(VirtualAddress entity, ResultSet rs)
			throws SQLException {
		final VirtualAddress vAddress = (VirtualAddress) entity;
		vAddress.setId(rs.getInt(1));
		vAddress.setIdentifier(rs.getString(2));
		vAddress.setProtocol(getProtocol(rs.getInt(3)));
		vAddress.setWebsite(rs.getString(4));
	}

	private Protocol getProtocol(int protocolId) {
		GenericDao<Protocol> protocolDao = DaoFactory
				.getInstance(ProtocolDao.class);
		Protocol protocol = protocolDao.read(new Protocol(protocolId, null,
				null));
		return protocol;
	}

	@Override
	protected String getFields(VirtualAddress entity, CommonActions action) {
		String out = "";
		final VirtualAddress vAddress = (VirtualAddress) entity;
		switch (action) {
		case CREATE:
			out = "(null,'" + vAddress.getIdentifier() + "','"
					+ vAddress.getWebsite() + "',"
					+ vAddress.getProtocol().getId() + ");";
			break;

		case UPDATE:
			out = "identifier = '" + vAddress.getIdentifier() + "',website='"
					+ vAddress.getWebsite() + "',id_protocol="
					+ vAddress.getProtocol().getId();
			break;
		}
		return out;
	}

	@Override
	protected String getTableName() {		
		return TABLE_NAME;
	}

	@Override
	public void loadReferences(VirtualAddress entity, Class<?> clazz) {
		// not used
	}

	@Override
	protected VirtualAddress loadValues(ResultSet rs) throws SQLException {
		VirtualAddress vAddress = new VirtualAddress();
		fillValues(vAddress, rs);

		return vAddress;
	}

	@Override
	protected Collection<ReferenceLink> getReferences(VirtualAddress entity) {
		return new ArrayList<ReferenceLink>();
	}

}
