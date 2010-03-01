package org.uagrm.addressbook.model.dao;

import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.uagrm.addressbook.model.Protocol;
import org.uagrm.addressbook.model.VirtualAddress;

/**
 * @author Timoteo Ponce
 *
 */
public class VirtualAddressDaoTest implements GenericDaoTestNot {

	final GenericDao<VirtualAddress> dao = CacheFactory
			.getInstance(VirtualAddressDao.class);

	@Override
	@Test
	public void createEntries() {
		VirtualAddress vAddress = new VirtualAddress();
		vAddress.setIdentifier("timo.slack@mymail.com");
		vAddress.setProtocol(getSmtpProtocol());
		vAddress.setWebsite("http://timouagrm.co.nr");

		dao.create(vAddress);
		Assert.assertNotNull(vAddress.getId());
	}

	private Protocol getSmtpProtocol() {
		return createProtocol("SMTP", 25);
	}

	@Override
	@Test
	public void updateEntries() {
		Collection<VirtualAddress> list = dao.selectAll();
		if(!list.isEmpty()){
			VirtualAddress vAddress = list.iterator().next();
			final Integer id = vAddress.getId();
			vAddress.setIdentifier("msn:timo.com");
			vAddress.setProtocol(getImProtocol());

			dao.update(vAddress);

			Assert.assertEquals(id, vAddress.getId());
		}
	}

	private Protocol getImProtocol() {
		return createProtocol("MSN", 1554);
	}

	private Protocol createProtocol(String name, int port) {
		final ProtocolDao protocolDao = CacheFactory
				.getInstance(ProtocolDao.class);

		Protocol protocol = new Protocol();
		protocol.setName(name);
		protocol.setPort(port);
		protocolDao.create(protocol);

		return protocol;
	}

	@Override
	@Test
	public void deleteEntries() {
		VirtualAddress vAddress = dao.selectAll().iterator().next();
		dao.delete(vAddress);

		Assert.assertNull(dao.read(vAddress));
	}

}
