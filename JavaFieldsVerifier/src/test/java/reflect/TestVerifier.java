package reflect;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

@SuppressWarnings("unused")
public class TestVerifier {

	static class A {
		String name;
		int value;
	}
	
	static class B {
		ZonedDateTime date;
		int value;
	}
	
	
	static class C {
		String name;
		Date date;
		long value;
	}
	
	
	@Test
	public void testReifiedOneField() throws ReflectiveOperationException {
		Field field = A.class.getDeclaredField("name");
		JavaField.checkReified(Arrays.asList(field));
	}

	@Test
	public void testReifiedTwoColumns() throws ReflectiveOperationException {
		Field field1 = A.class.getDeclaredField("value");
		Field field2 = B.class.getDeclaredField("value");
		JavaField.checkReified(Arrays.asList(field1, field2));
	}
	
	@Test(expected=ConflictingTypeException.class)
	public void testConflictingTypes() throws ReflectiveOperationException {
		Field field1 = B.class.getDeclaredField("date");
		Field field2 = C.class.getDeclaredField("date");
		JavaField.checkReified(Arrays.asList(field1, field2));
	}


}
