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
		VirtualAddress vAddress = new VirtualAddress();
		vAddress.setIdentifier("timo.slack@mymail.com");
		vAddress.setService(getService());
		vAddress.setWebsite("http://timouagrm.co.nr");

		dao.create(vAddress);
		Assert.assertNotNull(vAddress.getId());
	}

	private Service getService() {
		return createService("SMTP");
	}

	@Override
	@Test
	public void updateEntries() {
		Collection<VirtualAddress> list = dao.selectAll();
		if (!list.isEmpty()) {
			VirtualAddress vAddress = list.iterator().next();
			final Integer id = vAddress.getId();
			vAddress.setIdentifier("msn:timo.com");
			vAddress.setService(getImProtocol());

			dao.update(vAddress);

			Assert.assertEquals(id, vAddress.getId());
		}
	}

	private Service getImProtocol() {
		return createService("MSN");
	}

	private Service createService(String name) {
		final ServiceDao serviceDao = DaoFactory.getInstance(ServiceDao.class);

		Service service = new Service();
		service.setName(name);
		serviceDao.create(service);

		return service;
	}

	@Override
	@Test
	public void deleteEntries() {
		VirtualAddress vAddress = dao.selectAll().iterator().next();
		dao.delete(vAddress);

		Assert.assertNull(dao.read(vAddress));
	}

}
