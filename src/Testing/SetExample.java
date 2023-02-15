package Testing;

import java.util.HashSet;
import java.util.Set;

public class SetExample {

	public static void main(String[] args) {
		
		Set<String> veg = new HashSet<String>();
		
		veg.add("Ginger");
		veg.add("Garlic");
		veg.add("Onion");
		veg.add("Ginger");
		
		System.out.println(veg);

	}

}
