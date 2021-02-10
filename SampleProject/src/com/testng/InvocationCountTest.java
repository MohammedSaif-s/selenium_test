package com.testng;

import org.testng.annotations.Test;

public class InvocationCountTest 
{
	// dump the data into the system , we can use invocationCount
	@Test(invocationCount = 10)
	public void sum()
	{
		int a=10;
		int b=20;
		int c=a+b;
		System.out.println("The sum of a and b is = " +c);
	}

}
