package _final_exam02;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PassOrNotTest {

	@DisplayName("문장 커버리지")
	@Nested
	class Instruction {
		@Test
		void cover_line7() {
			PassOrNot pon = new PassOrNot();
			assertThrows(InvalidRangeException.class, () -> {
				pon.ckScore(-10, 10);
			});
		}
		
		@Test
		void cover_line9() {
			PassOrNot pon = new PassOrNot();
			assertThrows(InvalidRangeException.class, () -> {
				pon.ckScore(10, 101);
			});
		}
		
		@Test
		void cover_line11() {
			PassOrNot pon = new PassOrNot();
			String actual = pon.ckScore(10, 60);
			assertEquals("과락", actual);
		}
		
		@Test
		void cover_line13() {
			PassOrNot pon = new PassOrNot();
			String actual = pon.ckScore(60, 60);
			assertEquals("통과", actual);
		}
		
		@Test
		void cover_line14() {
			PassOrNot pon = new PassOrNot();
			String actual = pon.ckScore(55, 55);
			assertEquals("실패", actual);
		}
	}
	
	@DisplayName("분기 커버리지")
	@Nested
	class Branch {
		
	}
}
