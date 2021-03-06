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
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Service;
import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.Home;
import org.uagrm.addressbook.model.dao.ReferenceLink;
import org.uagrm.addressbook.model.dao.ServiceDao;
import org.uagrm.addressbook.model.dao.SqlOperation;
import org.uagrm.addressbook.model.dao.VirtualAddressDao;

/**
 * @author Timoteo Ponce
 * 
 */
public class SqlVirtualAddressDao extends AbstractSqlDao<VirtualAddress> implements VirtualAddressDao {

	public SqlVirtualAddressDao() {
		super(VirtualAddress.class);
	}

	@Override
	protected void fillValues(VirtualAddress entity, ResultSet rs) throws SQLException {
		final VirtualAddress vAddress = entity;
		vAddress.setId(rs.getInt("ID"));
		vAddress.setIdentifier(rs.getString("IDENTIFIER"));
		vAddress.setService(getService(rs.getInt("ID_SERVICE")));
		vAddress.setWebsite(rs.getString("WEBSITE"));
	}

	private Service getService(int serviceId) {
		Home<Service> serviceDao = DaoFactory.getInstance(ServiceDao.class);
		Service service = serviceDao.find(new Service(serviceId));
		return service;
	}

	@Override
	protected String getFields(ActionType action) {
		final StrBuilder buffer = new StrBuilder();
		final VirtualAddress vAddress = getInstance();

		switch (action) {
		case CREATE:
			buffer.append("(null,'" + vAddress.getIdentifier() + "',");
			buffer.append("'" + vAddress.getWebsite() + "',");
			buffer.append(vAddress.getService().getId() + ")");
			break;

		case UPDATE:
			buffer.append("identifier = '" + vAddress.getIdentifier() + "',");
			buffer.append("website='" + vAddress.getWebsite() + "',");
			buffer.append("ID_SERVICE=" + vAddress.getService().getId());
			break;
		}
		return buffer.toString();
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public void loadReferences(VirtualAddress entity) {
		// not used
	}

	@Override
	protected VirtualAddress loadValues(ResultSet rs) throws SQLException {
		VirtualAddress vAddress = new VirtualAddress();
		fillValues(vAddress, rs);

		return vAddress;
	}

	@Override
	protected Collection<ReferenceLink> getReferences() {
		return new ArrayList<ReferenceLink>();
	}

	@Override
	public Set<VirtualAddress> getByContact(Contact contact) {
		final QueryBuilder builder = QueryBuilder.createQuery(SqlOperation.SQL_SELECT_ALL);
		builder.setVariable(VAR_COLUMNS, "c.*");
		builder.setVariable(VAR_TABLE, TABLE_NAME);
		builder.append(" c INNER JOIN " + "CONTACT_VIRTUAL_ADDRESSES");
		builder.append(" gc ON c.ID = gc.ID_VIRTUAL_ADDRESS WHERE gc.ID_CONTACT = " + contact.getId());

		final Set<VirtualAddress> vAddresses = new HashSet<VirtualAddress>();
		ResultSet rs = getDatabaseHandler().executeQuery(builder.getQuery());
		try {
			while (rs.next()) {
				VirtualAddress vAddress = new VirtualAddress();
				fillValues(vAddress, rs);
				vAddresses.add(vAddress);
			}
		} catch (SQLException e) {
			Logger.getLogger(SqlVirtualAddressDao.class).error(e, e);
		} finally {
			getDatabaseHandler().closeQuietly(rs);
		}
		return vAddresses;
	}

}
