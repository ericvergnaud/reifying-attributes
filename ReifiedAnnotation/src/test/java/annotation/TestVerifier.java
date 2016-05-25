package annotation;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

@SuppressWarnings("unused")
public class TestVerifier {

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
		String name;
		@Reified
		Date date;
		@Reified
		long value;
	}
	
	
	@Test
	public void testReifiedOneClass() throws ReflectiveOperationException {
		JavaClass.checkReified(Arrays.asList(A.class));
	}

	@Test
	public void testReifiedTwoColumns() throws ReflectiveOperationException {
		JavaClass.checkReified(Arrays.asList(A.class, B.class));
	}
	
	@Test(expected=ConflictingTypeException.class)
	public void testConflictingTypes() throws ReflectiveOperationException {
		JavaClass.checkReified(Arrays.asList(B.class, C.class));
	}


}
