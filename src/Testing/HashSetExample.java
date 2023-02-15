package Testing;

import java.util.HashSet;

public class HashSetExample {

	public static void main(String[] args) {
		
		HashSet<String> hs = new HashSet<String>();
		
		hs.add("Java");
		hs.add("Python");
		hs.add("C++");
		hs.add("C");
		
		System.out.println("Before adding duplicate keys");
		System.out.println(hs);
		
		hs.add("Python");
		hs.add("C");
		
		System.out.println("After adding duplicate keys");
		System.out.println(hs);
		
		hs.add(null);
		hs.add(null);
		
		System.out.println("After adding null values");
		System.out.println(hs);

	}

}
