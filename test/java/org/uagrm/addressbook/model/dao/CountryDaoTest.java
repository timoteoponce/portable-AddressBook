package org.uagrm.addressbook.model.dao;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.uagrm.addressbook.model.Country;

/**
 * @author Timoteo Ponce
 * 
 */
public class CountryDaoTest implements GenericDaoTestNot {

	final CountryDao dao = DaoFactory.getInstance(CountryDao.class);

	@Override
	@Test
	public void createEntries() {
		for (int i = 0; i < TEST_ENTRIES; i++) {
			dao.getInstance().setName("Test country: " + (System.currentTimeMillis() / 1000));
			dao.persist();
			Assert.assertNotNull(dao.getInstance().getId());

			System.out.println("Created country : " + dao.getInstance().toString());
		}
	}

	@Override
	@Test
	public void updateEntries() {
		Collection<Country> countryList = dao.selectAll();

		for (Country country : countryList) {
			dao.setInstance(country);
			final Integer id = country.getId();
			dao.getInstance().setName("Updated country : " + (System.currentTimeMillis() / 1000));
			dao.update();

			Assert.assertEquals(id, country.getId());
			System.out.println("Updated country : " + country.toString());
			dao.clearInstance();
		}
	}

	@Override
	@Test
	public void deleteEntries() {
		Collection<Country> countryList = dao.selectAll();

		int i = 0;
		for (Country country : countryList) {
			dao.setInstance(country);
			if (i < TEST_ENTRIES) {
				dao.delete();
				Assert.assertNull(dao.find(country));
			} else {
				break;
			}
			dao.clearInstance();
			i++;
		}
	}

}
