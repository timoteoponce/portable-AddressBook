package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.text.StrBuilder;
import org.uagrm.addressbook.controller.actions.ActionType;
import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.model.ReferenceLink;
import org.uagrm.addressbook.model.dao.PhoneDao;

/**
 * @author Timoteo Ponce
 * 
 */
public class SqlPhoneDao extends AbstractSqlDao<Phone> implements PhoneDao {

	@Override
	protected void fillValues(Phone entity, ResultSet rs) throws SQLException {
		final Phone phone = entity;
		phone.setId(rs.getInt(1));
		phone.setHousePhone(rs.getString(2));
		phone.setMobilePhone(rs.getString(3));
		phone.setWorkPhone(rs.getString(4)); 
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
}
