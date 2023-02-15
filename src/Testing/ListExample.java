package Testing;

import java.util.ArrayList;
import java.util.List;

public class ListExample {

	public static void main(String[] args) {
		
		List<String> names = new ArrayList<String>();
		
		names.add("Emma");
		names.add("Adele");
		names.add("Aria");
		names.add("Ally");
		
		for(String name:names)
		{
			System.out.println(name);
		}

	}

}
