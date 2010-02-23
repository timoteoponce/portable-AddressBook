package org.ponce.addressbook.model.dao;

import org.junit.Assert;
import org.junit.Test;
import org.ponce.addressbook.model.Protocol;
import org.ponce.addressbook.model.VirtualAddress;

public class VirtualAddressDaoTest implements GenericDaoTest {

    final GenericDao<VirtualAddress> dao = (GenericDao<VirtualAddress>) CacheFactory
	    .getInstance(VirtualAddressDao.class);

    @Override
    @Test
    public void createEntries() {
	VirtualAddress vAddress = new VirtualAddress();
	vAddress.setIdentifier("timo.slack@mymail.com");
	vAddress.setProtocol(getSmtpProtocol());
	vAddress.setWebsite("http://timoponce.co.nr");

	dao.create(vAddress);
	Assert.assertNotNull(vAddress.getId());
    }

    private Protocol getSmtpProtocol() {
	return createProtocol("SMTP", 25);
    }

    @Override
    @Test
    public void updateEntries() {
	VirtualAddress vAddress = dao.selectAll().iterator().next();
	final Integer id = vAddress.getId();
	vAddress.setIdentifier("msn:timo.com");
	vAddress.setProtocol(getImProtocol());

	dao.update(vAddress);
	
	Assert.assertEquals(id, vAddress.getId());
    }

    private Protocol getImProtocol() {
	return createProtocol("MSN", 1554);
    }

    private Protocol createProtocol(String name, int port) {
	final GenericDao<Protocol> protocolDao = (GenericDao<Protocol>) CacheFactory
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
