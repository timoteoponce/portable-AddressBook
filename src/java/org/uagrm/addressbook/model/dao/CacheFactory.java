package org.uagrm.addressbook.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.uagrm.addressbook.util.ConfigKeys;
import org.uagrm.addressbook.util.Configuration;

/**
 * @author Timoteo Ponce
 * 
 */
public class CacheFactory {
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
	    final String daoTypeId = GenericDao.class.getPackage().getName()
		    + ".impl."
		    + Configuration.getConfigKey(ConfigKeys.DAO_PREFIX);
	    
	    Object instance = Class.forName(daoTypeId + clazz.getSimpleName())
		    .newInstance();
	    daoMap.put(clazz.getName(), instance);
	    return (T) instance;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

}
