package gradleproject01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ErrorExample01Test {

	@Test
	void should_return_2_for_10_divide_5() {
		/* Arrange */
		int x = 10;
		int y = 5;
		ErrorExample01 ee = new ErrorExample01();
		
		/* Act */
		double divide_result = ee.devide(x, y);
		
		/* Assert */
		/* assertEquals(?, ?, x); // x is floating point */
		assertEquals(2.0, divide_result, 0.01);
	}
	
	@Test
	void should_error_by_divide_0() {
		int x = 10;
		int y = 0;
		
		ErrorExample01 ee = new ErrorExample01();
		
		double error = ee.devide(x, y);
		
		/* unreachable */
		assertEquals(2.0, error, 0.01);
	}
	
}
