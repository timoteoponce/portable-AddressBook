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
import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.model.dao.PhoneDao;
import org.uagrm.addressbook.model.dao.ReferenceLink;
import org.uagrm.addressbook.model.dao.SqlOperation;

/**
 * @author Timoteo Ponce
 * 
 */
public class SqlPhoneDao extends AbstractSqlDao<Phone> implements PhoneDao {

	@Override
	protected void fillValues(Phone entity, ResultSet rs) throws SQLException {
		final Phone phone = entity;
		phone.setId(rs.getInt("ID"));
		phone.setHousePhone(rs.getString("HOUSE_PHONE"));
		phone.setMobilePhone(rs.getString("MOBILE_PHONE"));
		phone.setWorkPhone(rs.getString("WORK_PHONE"));
	}

	@Override
	protected String getFields(Phone phone, ActionType action) {
		final StrBuilder buffer = new StrBuilder();

		switch (action) {
		case CREATE:
			buffer.append("(null,'" + phone.getHousePhone() + "',");
			buffer.append("'" + phone.getMobilePhone() + "',");
			buffer.append("'" + phone.getWorkPhone() + "')");
			break;

		case UPDATE:
			buffer.append("HOUSE_PHONE='" + phone.getHousePhone() + "',");
			buffer.append("MOBILE_PHONE='" + phone.getMobilePhone() + "',");
			buffer.append("WORK_PHONE='" + phone.getWorkPhone() + "'");
			break;
		}
		return buffer.toString();
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public void loadReferences(Phone entity, Class<?> clazz) {
		// not used
	}

	@Override
	protected Phone loadValues(ResultSet rs) throws SQLException {
		Phone phone = new Phone();
		fillValues(phone, rs);

		return phone;
	}

	@Override
	protected Collection<ReferenceLink> getReferences(Phone entity) {
		return new ArrayList<ReferenceLink>();
	}

	@Override
	public Set<Phone> getByContact(Contact contact) {
		final QueryBuilder builder = QueryBuilder.createQuery(SqlOperation.SQL_SELECT_ALL);
		builder.setVariable(VAR_COLUMNS, "c.*");
		builder.setVariable(VAR_TABLE, TABLE_NAME);
		builder.append(" c INNER JOIN " + "CONTACT_PHONES");
		builder.append(" gc ON c.ID = gc.ID_PHONE WHERE gc.ID_CONTACT = " + contact.getId());

		final Set<Phone> phones = new HashSet<Phone>();
		ResultSet rs = getDatabaseHandler().executeQuery(builder.getQuery());
		try {
			while (rs.next()) {
				Phone phone = new Phone();
				fillValues(phone, rs);
				phones.add(phone);
			}
		} catch (SQLException e) {
			Logger.getLogger(SqlPhoneDao.class).error(e, e);
		} finally {
			getDatabaseHandler().closeQuietly(rs);
		}
		return phones;
	}
}
