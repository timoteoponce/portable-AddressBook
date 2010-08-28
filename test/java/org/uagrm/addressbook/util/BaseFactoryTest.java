package org.uagrm.addressbook.util;

import org.junit.Test;
import org.uagrm.addressbook.model.dao.AddressDao;
import org.uagrm.addressbook.model.dao.impl.SqlAddressDao;
import org.uagrm.addressbook.view.component.ActionPanelList;
import org.uagrm.addressbook.view.component.AddressActionPanelList;

public class BaseFactoryTest {

	@Test
	public void testNewInstance() {
		BaseFactory factory = new BaseFactory();
		factory.bind(AddressDao.class, SqlAddressDao.class);
		factory.bind(ActionPanelList.class, AddressActionPanelList.class);

		factory.newInstance(AddressDao.class);
		factory.newInstance(ActionPanelList.class, Boolean.TRUE);
	}

}
