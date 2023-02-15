package Testing;

import java.util.HashMap;

public class HashMapExample {

	public static void main(String[] args) {
		
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		
		hm.put("John", 23);
		hm.put("Monty", 27);
		hm.put("Richard", 21);
		hm.put("Devid", 19);
		
		System.out.println("Before adding duplicate keys");
		System.out.println(hm);
		
		hm.put("Monty", 25);
		hm.put("Devid", 19);
		
		System.out.println("After adding duplicate keys");
		System.out.println(hm);

	}

}
