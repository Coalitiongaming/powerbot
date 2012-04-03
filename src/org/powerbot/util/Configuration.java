package org.powerbot.util;

import java.io.File;
import java.net.MalformedURLException;

import org.powerbot.util.io.IOHelper;
import org.powerbot.util.io.Resources;

/**
 * @author Paris
 */
public class Configuration {
	public static final String NAME = "RSBot";
	public static final boolean FROMJAR;
	public static boolean DEVMODE = false;
	public static final int VERSION;
	public static final String STORE, BOOTSETTINGS;
	public static final OperatingSystem OS;
	public static String SCRIPTPATH;

	public enum OperatingSystem {
		MAC, WINDOWS, LINUX, UNKNOWN
	}

	public interface URLs {
		public static final String DOMAIN = "powerbot.org";
		public static final String CONTROL = "http://links." + DOMAIN + "/control";

		public static final String GAME = "runescape.com";
	}

	static {
		FROMJAR = Configuration.class.getClassLoader().getResource(Resources.Paths.VERSION) != null;

		int v = 0;
		try {
			v = Integer.parseInt(IOHelper.readString(Resources.getResourceURL(Resources.Paths.VERSION)).trim());
		} catch (final MalformedURLException ignored) {
		}
		VERSION = v;

		final String appdata = System.getenv("APPDATA"), home = System.getProperty("user.home");
		final String root = appdata != null && new File(appdata).isDirectory() ? appdata : home == null ? "~" : home;
		STORE = root + File.separator + NAME + ".db";
		BOOTSETTINGS = root + File.separator + NAME + ".ini";

		final String os = System.getProperty("os.name");
		if (os.contains("Mac")) {
			OS = OperatingSystem.MAC;
		} else if (os.contains("Windows")) {
			OS = OperatingSystem.WINDOWS;
		} else if (os.contains("Linux")) {
			OS = OperatingSystem.LINUX;
		} else {
			OS = OperatingSystem.UNKNOWN;
		}
	}
}
