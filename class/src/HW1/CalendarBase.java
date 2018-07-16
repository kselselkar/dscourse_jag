package HW1;

import utils.IntUtil;

/**
 * File Name: CalendarBase.java 
 * Calendar base class
 * 
 * To Compile: IntUtil.java RandomInt.java CalendarBase.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */

/*
 * YOU CANNOT CHANGE ANYTHING IN THIS FILE. READ ONLY
 */

abstract class CalendarBase{
	protected int month ;
	protected int date ;
	protected int year ;
	protected Days day ;
	protected IntUtil u = new IntUtil();
	
	protected enum Days {
		Sunday, Monday, Tuesday,Wednesday, Thursday, Friday, Saturday, Error ;
	}
	
	CalendarBase() {
		month = 0 ;
		date = 0 ;
		year = 0 ;
		day = Days.Error ;
	}
	
	//I don't know how to write it
  //Override by the concrete class
  abstract protected void computeDay() ;
  
  protected void testBench() {
		simpleTests() ;
	}	
 
  private void printDay() {
  	switch (day) {
  	case Sunday: System.out.print("Sunday") ;
  					break ;
  	case Monday: System.out.print("Monday") ;
  					break ;
  	case Tuesday: System.out.print("Tuesday") ;
  					break ;
  	case Wednesday: System.out.print("Wednesday") ;
						break ;
  	case Thursday: System.out.print("Thursday") ;
						break ;
  	case Friday: System.out.print("Friday") ;
						break ;
  	case Saturday: System.out.print("Saturday") ;
						break ;
		default: System.out.print("Error");
						break ;
  	}
  }
  
  private void test1(int m, int d, int y, Days expected) {
  	month = m ;
		date = d ;
		year = y ;
		day = Days.Error ;
		computeDay();
		System.out.print(m + "/" + d + "/" + y + " = " ) ;
		printDay();
		System.out.println();
		u.myassert(day == expected) ;
  }
  
  private void simpleTests() {
  	{
  		int m = 4 ;
  		int d = 7 ;
  		int y = 2018 ;
  		test1(m,d,y,Days.Saturday) ;
  	}
  	{
  		int m = 10 ;
  		int d = 12 ;
  		int y = 1043 ;
  		test1(m,d,y,Days.Error) ;
  	}
  	{
  		int m = 2 ;
  		int d = 15 ;
  		int y = 2011 ;
  		test1(m,d,y,Days.Tuesday) ;
  	}
  	{
  		int m = 2 ;
  		int d = 29 ;
  		int y = 1896 ;
  		test1(m,d,y,Days.Saturday) ;
  	}
  	{
  		int m = 2 ;
  		int d = 28 ;
  		int y = 1754 ;
  		test1(m,d,y,Days.Error) ;
  	}
  	{
  		int m = 1 ;
  		int d = 1 ;
  		int y = 2034 ;
  		test1(m,d,y,Days.Error) ;
  	}
  	{
  		int m = 7 ;
  		int d = 4 ;
  		int y = 1776 ;
  		test1(m,d,y,Days.Thursday) ;
  	}
  	{
  		int m = 8 ;
  		int d = 14 ;
  		int y = 1957 ;
  		test1(m,d,y,Days.Wednesday) ;
  	}
  	{
  		int m = 2 ;
  		int d = 29 ;
  		int y = 2016 ;
  		test1(m,d,y,Days.Monday) ;
  	}
  	{
  		int m = 2 ;
  		int d = 30 ;
  		int y = 2016 ;
  		test1(m,d,y,Days.Error) ;
  	}
  	{
  		int m = 2 ;
  		int d = 28 ;
  		int y = 2016 ;
  		test1(m,d,y,Days.Sunday) ;
  	}
  }
  
  public static void main(String[] args) {
		System.out.println("CalendarBase.java STARTS");
		System.out.println("You cannot instantiate CalendarBase class: CalendarBase p = new CalendarBase() ; ");
		//CalendarBase p = new CalendarBase() ;
		System.out.println("CalendarBase.java ENDS");
	}
}