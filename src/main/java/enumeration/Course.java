package enumeration;

public enum Course {
	Appetizer(Food.Appetizer.class),
	MainCourse(Food.MainCourse.class),
	Dessert(Food.Dessert.class),
	Coffee(Food.Coffee.class);
	private Food[] values;
	private Course(Class<? extends Food> clazz) {
		values = clazz.getEnumConstants();
	}
	public Food randomSelection() {
		return Enums.random(values);
	}
}
