package Testing;

public class PrintNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int number=1;
		printNumbers(number);
	}
	
	public static void printNumbers(int num)
	{
		if(num<=100)
		{
			System.out.println(num+ "");
			printNumbers(num+1);
		}

	}

}
