package Testing;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class JunitAnnotationEample {
	
	@Test
	  public void test1() {
		  System.out.println("Im Test1");
	  }
	@Test
	  public void test2() {
		  System.out.println("Im Test2");
	  }
	  
	  @Ignore
	  public void test3() {
		  System.out.println("Im Test3");
	  }

	  @BeforeClass
	  public static void beforeClass() {
		  System.out.println("Im Before Class");
	  }

	  @AfterClass
	  public static void afterClass() {
		  System.out.println("Im After Class");
	  }

	  @Before
	  public void before() {
		  System.out.println("Im Before");
	  }

	  @After
	  public void after() {
		  System.out.println("Im After");
	  }

}
