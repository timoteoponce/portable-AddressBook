package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.uagrm.addressbook.controller.actions.CommonActions;
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
		final Phone phone = (Phone) entity;
		phone.setId(rs.getInt(1));
		phone.setHousePhone(rs.getString(2));
		phone.setMobilePhone(rs.getString(3));
		phone.setWorkPhone(rs.getString(4));
	}

	@Override
	protected String getFields(Phone entity, CommonActions action) {
		String out = "";
		final Phone phone = (Phone) entity;
		switch (action) {
		case CREATE:
			out = "(null,'" + phone.getHousePhone() + "','"
					+ phone.getMobilePhone() + "','" + phone.getWorkPhone()
					+ "');";
			break;

		case UPDATE:
			out = "HOUSE_PHONE='" + phone.getHousePhone() + "',MOBILE_PHONE='"
					+ phone.getMobilePhone() + "',WORK_PHONE='"
					+ phone.getWorkPhone() + "'";
			break;
		}
		return out;
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
