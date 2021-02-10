package com.testng;

import org.testng.annotations.Test;

public class ExceptionTimeoutTest
{	
	// avoid this infinite loop in the program	
	//@Test(timeOut = 2000)
	// not recommended to use
	
	//@Test(invocationTimeOut = 2000)
	public void infiniteLoopTest()
	{
		int i=1;
		while (i==1)
		{
			System.out.println(i);
		}
	}
	
	@Test(expectedExceptions = NumberFormatException.class)
	public void test1()
	{
		String x = "100A";
		Integer.parseInt(x);
		
	}

}
