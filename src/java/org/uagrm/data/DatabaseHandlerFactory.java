package org.uagrm.data;

import org.uagrm.addressbook.util.ConfigKeys;
import org.uagrm.addressbook.util.Configuration;

/**
 * Factory class used to instantiate {@link DatabaseHandler} objects, this class
 * uses values from {@link Configuration} to resolve the current database in
 * use.
 * 
 * @author Timoteo Ponce
 * 
 */
public class DatabaseHandlerFactory {
	/*
	 * Use this if you plan to have multiple databases in runtime private static
	 * final Map<String, ConnectionHandler> handlersMap = new HashMap<String,
	 * ConnectionHandler>(0);
	 */

	private static DatabaseHandler handler = null;

	/**
	 * Returns or create the {@link DatabaseHandler} configured for this
	 * application.
	 * 
	 * @return database handler for this application.
	 */
	public static DatabaseHandler getDatabaseHandler() {
		// if sqlite
		final String handlerId = Configuration
				.getConfigKey(ConfigKeys.DATASOURCE_HANDLER);
		// file(complete class name or just a key
		// name 'sqlite'? )

		/*
		 * Use this if you plan to have multiple databases in runtime
		 * ConnectionHandler handler = handlersMap.get(handlerId);
		 */
		if (handler == null) {
			handler = createDatabaseHandler(handlerId);
		}

		return handler;
	}

	private static DatabaseHandler createDatabaseHandler(String handlerId) {
		try {
			DatabaseHandler handler = (DatabaseHandler) Class
					.forName(handlerId).newInstance();
			/*
			 * Use this if you plan to have multiple databases in runtime
			 * handlersMap.put(handlerId, handler);
			 */
			return handler;
		} catch (Exception e) {
			throw new RuntimeException("Database handler can't be created: "
					+ handlerId, e);
		}
	}

}
