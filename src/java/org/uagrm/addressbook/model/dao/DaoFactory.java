package org.uagrm.addressbook.model.dao;

import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Country;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.model.Service;
import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.model.dao.impl.SqlAddressDao;
import org.uagrm.addressbook.model.dao.impl.SqlContactDao;
import org.uagrm.addressbook.model.dao.impl.SqlCountryDao;
import org.uagrm.addressbook.model.dao.impl.SqlGroupDao;
import org.uagrm.addressbook.model.dao.impl.SqlPhoneDao;
import org.uagrm.addressbook.model.dao.impl.SqlServiceDao;
import org.uagrm.addressbook.model.dao.impl.SqlVirtualAddressDao;
import org.uagrm.addressbook.util.BaseFactory;

/**
 * Factory class used to instantiate DAOs implementations from DAO interfaces.
 * e.g. {@link AddressDao} could instantiate {@link SqlAddressDao}
 * 
 * @author Timoteo Ponce
 * 
 */
public class DaoFactory {

	private final static BaseFactory factory = new BaseFactory();
	static {
		// <entity,dao>
		factory.bind(Address.class, SqlAddressDao.class);
		factory.bind(Contact.class, SqlContactDao.class);
		factory.bind(Country.class, SqlCountryDao.class);
		factory.bind(Group.class, SqlGroupDao.class);
		factory.bind(Phone.class, SqlPhoneDao.class);
		factory.bind(Service.class, SqlServiceDao.class);
		factory.bind(VirtualAddress.class, SqlVirtualAddressDao.class);
	}

	public static <T> T getInstance(final Class<T> daoClass) {
		return (T) factory.getInstance(daoClass);
	}
}
