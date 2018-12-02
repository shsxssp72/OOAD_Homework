package OOAD_Lab03_gizmo.Utilities;

import java.util.Scanner;

public class IO_Interface
{
	public static int ReadInteger()
	{
		try
		{
			int result=0;
			Scanner sc=new Scanner(System.in);
			String input=sc.nextLine();
			result=Integer.parseInt(input);
			return result;
		}
		catch(NumberFormatException e)
		{
			IO_Interface.ConsoleWrite("Please enter a vaild number.");
			return ReadInteger();
		}
	}

	public static void ConsoleWriteLine(String input)
	{
		//Temp, can be redirected.
		System.out.println(input);
	}

	public static void ConsoleWrite(String input)
	{
		System.out.print(input);
	}

	public static void DebugWriteLine(String input)
	{
		System.out.println("DEBUG:	"+input);
	}
}