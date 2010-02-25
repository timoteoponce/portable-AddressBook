package org.ponce.addressbook.model.dao;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.ponce.addressbook.model.Country;

/**
 * @author Timoteo Ponce
 *
 */
public class CountryDaoTest implements GenericDaoTest {

    final GenericDao<Country> dao = CacheFactory
	    .getInstance(CountryDao.class);

    @Override
    @Test
    public void createEntries() {
	for (int i = 0; i < TEST_ENTRIES; i++) {
	    Country country = new Country();
	    country.setName("Test country: "
		    + (System.currentTimeMillis() / 1000));
	    dao.create(country);
	    Assert.assertNotNull(country.getId());

	    System.out.println("Created country : " + country.toString());
	}
    }

    @Override
    @Test
    public void updateEntries() {
	Collection<Country> countryList = dao.selectAll();

	for (Country country : countryList) {
	    final Integer id = country.getId();
	    country.setName("Updated country : "
		    + (System.currentTimeMillis() / 1000));
	    dao.update(country);

	    Assert.assertEquals(id, country.getId());
	    System.out.println("Updated country : " + country.toString());
	}
    }

    @Override
    @Test
    public void deleteEntries() {
	Collection<Country> countryList = dao.selectAll();

	int i = 0;
	for (Country country : countryList) {
	    if (i < TEST_ENTRIES) {
		dao.delete(country);
		Assert.assertNull(dao.read(country));
	    } else {
		break;
	    }
	    i++;
	}
    }

}
