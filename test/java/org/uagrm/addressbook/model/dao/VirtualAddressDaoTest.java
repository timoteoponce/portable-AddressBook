package org.uagrm.addressbook.model.dao;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.uagrm.addressbook.model.Service;
import org.uagrm.addressbook.model.VirtualAddress;

/**
 * @author Timoteo Ponce
 * 
 */
public class VirtualAddressDaoTest implements GenericDaoTestNot {

	final VirtualAddressDao dao = DaoFactory.getInstance(VirtualAddressDao.class);

	@Override
	@Test
	public void createEntries() {
		dao.getInstance().setIdentifier("timo.slack@mymail.com");
		dao.getInstance().setService(getService());
		dao.getInstance().setWebsite("http://timouagrm.co.nr");

		dao.persist();
		Assert.assertNotNull(dao.getInstance().getId());
	}

	private Service getService() {
		return createService("SMTP");
	}

	@Override
	@Test
	public void updateEntries() {
		Collection<VirtualAddress> list = dao.selectAll();
		if (!list.isEmpty()) {
			dao.setInstance(list.iterator().next());
			final Integer id = dao.getInstance().getId();
			dao.getInstance().setIdentifier("msn:timo.com");
			dao.getInstance().setService(getImProtocol());

			dao.update();

			Assert.assertEquals(id, dao.getInstance().getId());
		}
	}

	private Service getImProtocol() {
		return createService("MSN");
	}

	private Service createService(String name) {
		final ServiceDao serviceDao = DaoFactory.getInstance(ServiceDao.class);
		serviceDao.getInstance().setName(name);
		serviceDao.persist();

		return serviceDao.getInstance();
	}

	@Override
	@Test
	public void deleteEntries() {
		VirtualAddress vAddress = dao.selectAll().iterator().next();
		dao.setInstance(vAddress);
		dao.delete();

		Assert.assertNull(dao.find(vAddress));
	}

}
