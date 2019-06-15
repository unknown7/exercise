package generator;

import java.util.ArrayList;

public class CollectionData<T> extends ArrayList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6502937948991333484L;
	
	public CollectionData(Generator<T> gen, int length) {
		for (int i = 0; i < length; i++)
			add(gen.next());
	}
}
