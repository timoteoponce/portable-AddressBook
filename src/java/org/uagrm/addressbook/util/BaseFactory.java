package org.uagrm.addressbook.util;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Generic factory cache component, which binds an specific class definition
 * with a class instance. It provides caching and creation facilities. Note: All
 * bind classes should use classes(Boolean) as constructor parameters, native
 * types(boolean) are not supported.
 * 
 * @author Timoteo Ponce
 * 
 */
public class BaseFactory {

	private final Map<Class<?>, Class<?>> classMapper = new HashMap<Class<?>, Class<?>>();

	private final Map<Class<?>, Object> instanceCache = new HashMap<Class<?>, Object>();

	/**
	 * @param classDefinition
	 * @param instanceDefinition
	 */
	public void bind(final Class<?> classDefinition, final Class<?> instanceDefinition) {
		classMapper.put(classDefinition, instanceDefinition);
	}

	/**
	 * @param mappingDefinition
	 */
	public void bindAll(final Map<Class<?>, Class<?>> mappingDefinition) {
		classMapper.putAll(mappingDefinition);
	}

	/**
	 * @param <T>
	 * @param classDefinition
	 * @return
	 */
	public Object getInstance(final Class<?> classDefinition, final Object... args) {
		if (classMapper.containsKey(classDefinition)) {
			return getOrCreateInstance(classDefinition, args);
		} else {
			throw new IllegalArgumentException("Class definition " + classDefinition + " not bind");
		}
	}

	/**
	 * @param <T>
	 * @param classDefinition
	 * @return
	 */
	private Object getOrCreateInstance(final Class<?> classDefinition, final Object... args) {
		Object instance = instanceCache.get(classDefinition);
		if (instance == null) {
			instance = newInstance(classDefinition, args);
			instanceCache.put(classDefinition, instance);
		}
		return instance;
	}

	/**
	 * @param <T>
	 * @param classDefinition
	 * @param args
	 * @return
	 */
	public Object newInstance(final Class<?> classDefinition, final Object... args) {
		try {
			Object instance = null;
			final Class<?> classInstance = classMapper.get(classDefinition);
			
			if (args.length > 0) {
				final Constructor<?>[] constructorArray = classInstance.getConstructors();
				for (Constructor<?> constructor : constructorArray) {
					if (constructor.getParameterTypes().length == args.length) {
						instance = constructor.newInstance(args);
						break;
					}
				}
			} else {				
				instance = classInstance.newInstance();
			}
			return instance;
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid instance class definition", e);
		}
	}

}
