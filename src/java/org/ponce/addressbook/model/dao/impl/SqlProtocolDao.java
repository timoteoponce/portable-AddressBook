package org.ponce.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.ponce.addressbook.controller.actions.CommonActions;
import org.ponce.addressbook.model.Protocol;
import org.ponce.addressbook.model.ReferenceLink;
import org.ponce.addressbook.model.dao.CacheFactory;
import org.ponce.addressbook.model.dao.GenericDao;
import org.ponce.addressbook.model.dao.ProtocolDao;

public class SqlProtocolDao extends AbstractSqlDao<Protocol> implements
		ProtocolDao {

	@Override
	protected void fillValues(Protocol entity, ResultSet rs)
			throws SQLException {
		final Protocol protocol = (Protocol) entity;
		protocol.setId(rs.getInt(1));
		protocol.setName(rs.getString(2));
		protocol.setPort(rs.getInt(3));
	}

	@Override
	protected String getFields(Protocol entity, CommonActions action) {
		String out = "";
		final Protocol protocol = (Protocol) entity;

		switch (action) {
		case CREATE:
			out = "(null,'" + protocol.getName() + "'," + protocol.getPort()
					+ ");";
			break;

		case UPDATE:
			out = "name='" + protocol.getName() + "',port="
					+ protocol.getPort() + "";
			break;
		}
		return out;
	}

	@Override
	protected String getTableName() {
		// TODO make it constant
		return "PROTOCOL";
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

	public static void main(String[] args) {
		GenericDao<Protocol> dao = (GenericDao<Protocol>) CacheFactory
				.getInstance(ProtocolDao.class);
		Protocol p1 = new Protocol();
		p1.setName("HTTP");
		p1.setPort(80);

		dao.create(p1);

		p1 = new Protocol();
		p1.setName("FTP");
		p1.setPort(20);

		dao.create(p1);

		Collection<Protocol> list = dao.selectAll();

		for (Protocol protocol : list) {
			System.out.println("Id: " + protocol.getId());
			System.out.println("name: " + protocol.getName());
			System.out.println("port: " + protocol.getPort());
		}
	}

	@Override
	protected Collection<ReferenceLink> getReferences(Protocol entity) {
		// TODO Auto-generated method stub
		return null;
	}
}