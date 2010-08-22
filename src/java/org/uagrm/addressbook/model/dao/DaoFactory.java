package org.uagrm.addressbook.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.model.dao.impl.SqlAddressDao;
import org.uagrm.data.DatabaseProperty;

/**
 * Factory class used to instantiate DAOs implementations from DAO interfaces.
 * e.g. {@link AddressDao} could instantiate {@link SqlAddressDao}
 * 
 * @author Timoteo Ponce
 * 
 */
public class DaoFactory {
	private static final Logger LOG = Logger.getLogger(DaoFactory.class);

	private static final Map<String, Object> daoMap = new HashMap<String, Object>();

	public static <T> T getInstance(Class<T> clazz) {
		T instance = (T) daoMap.get(clazz.getName());
		if (instance == null) {
			instance = createInstance(clazz);
		}
		return instance;
	}

	private static <T> T createInstance(Class<T> clazz) {
		try {
			String daoTypeId = Home.class.getPackage().getName() + ".impl." + DatabaseProperty.DAO_PREFIX.getValue();
			daoTypeId += clazz.getSimpleName();

			if (!daoTypeId.endsWith("Dao")) {
				LOG.info("Given class[" + clazz.getSimpleName() + "] is an entity, using proper Dao class instead");
				daoTypeId += "Dao";
			}

			Object instance = Class.forName(daoTypeId).newInstance();
			daoMap.put(clazz.getName(), instance);
			return (T) instance;
		} catch (Exception e) {
			throw new IllegalStateException("Request Home instance not available");
		}
	}

}
