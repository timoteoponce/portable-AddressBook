package org.ponce.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.ponce.addressbook.controller.actions.CommonActions;
import org.ponce.addressbook.model.Protocol;
import org.ponce.addressbook.model.ReferenceLink;
import org.ponce.addressbook.model.VirtualAddress;
import org.ponce.addressbook.model.dao.CacheFactory;
import org.ponce.addressbook.model.dao.GenericDao;
import org.ponce.addressbook.model.dao.ProtocolDao;
import org.ponce.addressbook.model.dao.VirtualAddressDao;

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
		GenericDao<Protocol> protocolDao = (GenericDao<Protocol>) CacheFactory
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
		// TODO make it constant
		return "VIRTUAL_ADDRESS";
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
		// TODO Auto-generated method stub
		return null;
	}

}
