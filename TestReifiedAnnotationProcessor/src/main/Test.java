package main;

import java.time.ZonedDateTime;
import java.util.Date;

import reified.Reified;

public class Test { 
	
	static class A {
		@Reified
		String name;
		@Reified
		int value;
	}
	
	@Reified
	static class B {
		ZonedDateTime date;
		int value;
	}
	
	
	static class C { 
		@Reified
		String name;
		@Reified
		Date date;
		// the below is not reified, so will not conflict with A.value and B.value
		long value;
	}
}
