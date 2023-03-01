package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import misc.Colors;

public class Utils {

	public static <T> ArrayList<Class<T>> getClassesPackage(String packageName, ArrayList<Class<T>> classes) {

		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

		String s = "";
		try {
			while ((s = reader.readLine()) != null) {
				if (s.endsWith(".class") && s.lastIndexOf('$') == -1)
					classes.add(getClass(s, packageName));

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	@SuppressWarnings("unchecked")
	private static <T> Class<T> getClass(String className, String packageName) {
		try {
			return (Class<T>) Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
		} catch (ClassNotFoundException e) {
			// handle the exception
		}
		return null;
	}

	private static Random rand = new Random();

	public static Colors getRandomColor() {
		return Colors.values()[rand.nextInt(Colors.values().length)];
	}

}
