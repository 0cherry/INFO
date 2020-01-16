package whitebox;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import whitebox.Coverage.C3Score;

class CoverageTest {

	@DisplayName("Statement (Block) Testing")
	@Nested
	class Statement {
		@DisplayName("foo 함수")
		@Test
		void cover_full_coverage_foo() {
			Coverage c = new Coverage();
			int actual;

			actual = c.foo(-10, 10, 10);
			assertEquals(10, actual);

			//		actual = c.foo(10, 10, 10);
			//		assertEquals(10, actual);
		}

		@DisplayName("coverage 함수")
		@Test
		void cover_full_coverage_coverage() {
			Coverage c = new Coverage();
			int actual;

			actual = c.coverage(2, 2, 1);
			assertEquals(1, actual);
		}
	}
	
	@DisplayName("Branch Testing")
	@Nested
	class Branch {
		@DisplayName("foo1 함수")
		@Test
		void cover_full_coverage_foo() {
			Coverage c = new Coverage();
			int actual;

			actual = c.foo(-1, 1, 10);
			assertEquals(10, actual);

			actual = c.foo(-1, -1, -1);
			assertEquals(0, actual);
			
			actual = c.foo(1, -1, 0);
			assertEquals(0, actual);
		}
		
		@DisplayName("coverage 함수")
		@Test
		void cover_full_coverage_coverage() {
			Coverage c = new Coverage();
			int actual;

			actual = c.coverage(1, 1, 1);
			assertEquals(1, actual);
			
			actual = c.coverage(-1, -1, -1);
			assertEquals(-1, actual);
			
			actual = c.coverage(3, -1, -1);
			assertEquals(0, actual);
		}
	}
	
	@DisplayName("Decision Testing")
	@Test
	void cover_full_coverage_exam() {
		Coverage c = new Coverage();
		int actual;

		actual = c.exam(70, 30, C3Score.PASS);
		assertEquals(50, actual);
		
		actual = c.exam(30, 70, C3Score.FAIL);
		assertEquals(0, actual);

		actual = c.exam(30, 30, C3Score.PASS);
		assertEquals(0, actual);
	}
}
