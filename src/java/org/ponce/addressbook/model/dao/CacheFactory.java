package org.ponce.addressbook.model.dao;

import java.util.HashMap;
import java.util.Map;

public class CacheFactory {
    private static final Map<String, Object> daoMap = new HashMap<String, Object>();
    
    public static Object getInstance(Class<?> clazz) {
	Object instance = daoMap.get(clazz.getName());
	if (instance == null) {
	    instance = createInstance(clazz);
	}
	return instance;
    }

    private static Object createInstance(Class<?> clazz) {
	try {
	    final String daoTypeId = "org.ponce.addressbook.model.dao.impl.Sql";// TODO
										// from
										// configuration
										// !!
	    Object instance = Class.forName(daoTypeId + clazz.getSimpleName())
		    .newInstance();
	    daoMap.put(clazz.getName(), instance);
	    return instance;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

}
