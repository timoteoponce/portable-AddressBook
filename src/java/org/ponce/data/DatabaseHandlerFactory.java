package org.ponce.data;

public class DatabaseHandlerFactory {
    /*
     * Use this if you plan to have multiple databases in runtime private static
     * final Map<String, ConnectionHandler> handlersMap = new HashMap<String,
     * ConnectionHandler>(0);
     */

    private static DatabaseHandler handler = null;

    public static DatabaseHandler getDatabaseHandler() {
	// if sqlite
	final String handlerId = SqliteDatabaseHandler.class.getName();// TODO
								       // read
								       // from
								       // configuration
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
	} catch (InstantiationException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
	throw new RuntimeException("This is wrong!");// TODO use a custom
	// exception
    }

}
