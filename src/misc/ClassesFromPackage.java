package misc;

import java.util.ArrayList;

import src.Utils;

public class ClassesFromPackage<T> {

	public ArrayList<T> instances = new ArrayList<>();
	public ArrayList<Class<T>> classes = new ArrayList<>();

	public ClassesFromPackage(String packageName) {
		Utils.getClassesPackage(packageName, new ArrayList<Class<T>>()).forEach(c -> {
			classes.add(c);
		});
	}

	public void instantiate() {

		classes.forEach(c -> {
			try {
				T instance = c.newInstance();
				instances.add(instance);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}

		});
	}

	public void instantiate(InstantiateEvent<T> ev) {
		classes.forEach(c -> {
			try {
				T instance = c.newInstance();
				ev.onInstance(instance);
				instances.add(instance);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}

		});
	}

	public interface InstantiateEvent<T> {

		public abstract void onInstance(T instance);

	}

}
