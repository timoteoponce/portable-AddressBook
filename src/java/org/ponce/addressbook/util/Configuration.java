package org.ponce.addressbook.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * The Class Configuracion.
 * 
 * @author Juan Timoteo Ponce Ortiz
 */
public final class Configuration {

    /** The app properties. */
    private static Properties appProperties;

    /** The Constant SETTINGS_FILE. */
    private static final String SETTINGS_FILE = "/application.properties";

    private static final Logger LOG = Logger.getLogger(Configuration.class);

    /**
     * Gets the param.
     * 
     * @param key
     *            the param
     * 
     * @return key
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static String getConfigKey(final String key) {
	if (appProperties == null) {
	    try {
		loadAppPropertyFile();
	    } catch (IOException e) {
		LOG.error(e.getMessage(), e);
	    }
	}
	return appProperties.getProperty(key);
    }

    /**
     * Load app property file.
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private static void loadAppPropertyFile() throws IOException {
	appProperties = new Properties();
	final InputStream fis = Configuration.class
		.getResourceAsStream(SETTINGS_FILE);
	appProperties.load(fis);
	fis.close();
    }

    /**
     * Sets the param.
     * 
     * @param key
     *            the property name
     * @param value
     *            the property value
     * 
     * @throws FileNotFoundException
     *             the file not found exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static void setConfigKey(final String key, final String value) {
	if (appProperties == null)
	    try {
		loadAppPropertyFile();
	    } catch (IOException e) {
		LOG.error(e.getMessage(), e);
	    }
	appProperties.setProperty(key, value);
	writeChangesToAppFile();
    }

    /**
     * Write changes to app file.
     */
    private static void writeChangesToAppFile() {
	FileOutputStream fos = null;
	try {
	    fos = new FileOutputStream(Configuration.class.getResource(
		    SETTINGS_FILE).toURI().getPath());
	    appProperties.store(fos, null);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (fos != null)
		    fos.close();
	    } catch (IOException e) {
		LOG.error(e.getMessage(), e);
	    }
	}
    }

    /**
     * Load properties.
     * 
     * @param path
     *            the path
     * 
     * @return the properties
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static Properties loadProperties(final String path)
	    throws IOException {
	final File file = new File(path);
	final BufferedReader reader = new BufferedReader(new FileReader(file));
	final Properties out = new Properties();
	out.load(reader);
	reader.close();

	return out;
    }

    /**
     * Save properties.
     * 
     * @param props
     *            the props
     * @param path
     *            the path
     */
    public static void saveProperties(final Properties props, final String path) {
	FileOutputStream fos = null;
	try {
	    fos = new FileOutputStream(path);
	    props.store(fos, null);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (fos != null)
		    fos.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
}
