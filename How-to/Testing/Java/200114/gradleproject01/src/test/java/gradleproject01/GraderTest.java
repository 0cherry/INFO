package gradleproject01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;


@DisplayName("���� ��� ���α׷� �׽�Ʈ")
/* test method���� instance�� �ϳ��� �����Ǵµ� �� annotation�� ����ϸ� instance �ϳ��� ��� �׽�Ʈ���̽� ���� */
//@TestInstance(Lifecycle.PER_CLASS)
class GraderTest {
	
	Grader grader;
	
	@BeforeEach
	void setUp() {
		grader = new Grader();
//		System.out.println("Test setup");
	}
	
	@AfterEach
	void tearDown() {
//		grader = new Grader();
//		System.out.println("Test end");
	}
	
	@DisplayName("@MethodSource�� �̿��� ���� �׽�Ʈ")
	@ParameterizedTest(name="�߰����� {1} �⸻���� {2} �������� {3} {0}���")
	@MethodSource("generateValidData")
	void should_giv_correct_grade_for_valid_scores(Grade g, double m, double f, double h) {
		Grade actual = grader.computeGrade(m, f, h);
		assertEquals(g, actual);
	}
	private static Stream<Arguments> generateValidData() {
		return Stream.of(
				Arguments.of(Grade.A, 99, 99, 99),
				Arguments.of(Grade.B, 89, 89, 89),
				Arguments.of(Grade.C, 78, 78, 78),
				Arguments.of(Grade.D, 67, 67, 67),
				Arguments.of(Grade.F, 56, 56, 56)
				);
	}
	
	@DisplayName("@MethodSource�� �̿��� ������ �׽�Ʈ")
	@ParameterizedTest(name="�߰����� {0} �⸻���� {1} �������� {2} Exception �߻�")
	@MethodSource("generateInvalidData")
	void should_give_exception(double m, double f, double h) {
		assertThrows(InvalidRangeException.class, () -> {
			grader.computeGrade(m, f, h);
		});
	}
	private static Stream<Arguments> generateInvalidData() {
		return Stream.of(
				Arguments.of(-10,10,10),
				Arguments.of(10,-10,10),
				Arguments.of(10,10,-10),
				Arguments.of(110,10,10),
				Arguments.of(10,110,10),
				Arguments.of(10,10,110)
				);
	}
	
	@DisplayName("@CsvFileSource�� �̿��� �׽�Ʈ")
	@Nested
	class CSVFileSourceExample {
		@DisplayName("�������� ���� �Է�")
		@ParameterizedTest(name="�߰����� {1} �⸻���� {2} �������� {3} {0}���")
		/* base_path is src/test/recources */
		@CsvFileSource(resources="/valid_data.csv")
		void should_give_correct_grade_for_valid_scores(Grade g, double m, double f, double h) {
			Grade actual = grader.computeGrade(m, f, h);
			assertEquals(g, actual);
		}
		
		@DisplayName("���������� ���� �Է�")
		@ParameterizedTest(name="�߰����� {0} �⸻���� {1} �������� {2} Exception �߻�")
		@CsvFileSource(resources="/invalid_data.csv")
		void should_give_exception(double m, double f, double h) {
			assertThrows(InvalidRangeException.class, () -> {
				grader.computeGrade(m, f, h);
			});
		}
	}
	
	@DisplayName("@CsvSource�� �̿��� �׽�Ʈ")
	@Nested
	class CsvSourceExample {
		@DisplayName("�������� ���� �Է�")
		@ParameterizedTest(name="�߰����� {1} �⸻���� {2} �������� {3} {0}���")
		@CsvSource({
			"A,95,85,98",
			"B,85,85,88",
			"C,78,75,68",
			"D,69,55,70",
			"F,55,60,34"
		})
		void should_give_correct_grade_for_valid_scores(Grade g, double m, double f, double h) {
			Grade actual = grader.computeGrade(m, f, h);
			assertEquals(g, actual);
		}
		
		@DisplayName("���������� ���� �Է�")
		@ParameterizedTest(name="�߰����� {1} �⸻���� {2} �������� {3} Exception �߻�")
		@CsvSource({
			"InvalidRangeException,-10,10,10",
			"InvalidRangeException,10,-10,10",
			"InvalidRangeException,10,10,-10",
			"InvalidRangeException,110,10,10",
			"InvalidRangeException,10,110,10",
			"InvalidRangeException,10,10,110"
		})
		void should_give_exception(Exception ie, double m, double f, double h) {
			assertThrows(ie.getClass(), () -> {
				grader.computeGrade(m, f, h);
			});
		}
	}

	@DisplayName("�������� ���� �Է�")
	/* attach tag */
	@Tag("smoke")
	/* represent hierarchy */
	@Nested
	class WhenValidScoresEntered {
		@DisplayName("90�� �̻��̸� A ���")
		 @Test
		void should_give_A_for_students_with_more_than_90pts() {
			/* Arrange */
			double mids = 90;
			double finals = 92;
			double homeworks = 90;

			/* Act */
			Grade actual = grader.computeGrade(mids, finals, homeworks);

			/* Assert */
			assertEquals(Grade.A, actual, "Should be A");
		}

		@DisplayName("80�� �̻��̸� B ���")
		@Test
		void should_give_B_for_students_with_more_than_80pts() {
			/* Arrange */
			double mids = 80;
			double finals = 92;
			double homeworks = 90;

			/* Act */
			Grade actual = grader.computeGrade(mids, finals, homeworks);

			/* Assert */
			assertEquals(Grade.B, actual, "Should be B");
		}

		@DisplayName("70�� �̻��̸� C ���")
		@Test
		void should_give_C_for_students_with_more_than_70pts() {
			/* Arrange */
			double mids = 80;
			double finals = 72;
			double homeworks = 70;

			/* Act */
			Grade actual = grader.computeGrade(mids, finals, homeworks);

			/* Assert */
			assertEquals(Grade.C, actual, "Should be C");
		}

		@DisplayName("60�� �̻��̸� D ���")
		@Test
		void should_give_D_for_students_with_more_than_60pts() {
			/* Arrange */
			double mids = 60;
			double finals = 62;
			double homeworks = 70;

			/* Act */
			Grade actual = grader.computeGrade(mids, finals, homeworks);

			/* Assert */
			assertEquals(Grade.D, actual, "Should be D");
		}

		@DisplayName("60�� �̸��̸� F ���")
		@Tag("unstable")
		@Test
		void should_give_F_for_students_with_less_than_60pts() {
			/* Arrange */
			double mids = 50;
			double finals = 92;
			double homeworks = 30;

			/* Act */
			Grade actual = grader.computeGrade(mids, finals, homeworks);

			/* Assert */
			assertEquals(Grade.F, actual, "Should be F");
		}
	}
	
	@DisplayName("���������� ���� �Է�")
	@Tag("smoke")
	@Nested
	class WhenInvalidScoresEntered {
		@DisplayName("�߰����� 100 �ʰ� Too Big Score Exception")
		@Test
		void should_give_too_big_score_exception() {
			double mids = 120;
			double finals = 92;
			double homeworks = 30;

			assertThrows(InvalidRangeException.class, () -> {
				grader.computeGrade(mids, finals, homeworks);
			});
		}

		@DisplayName("�߰����� 0 �̸� Negative Score Exception")
		@Test
		void should_give_negative_score_exception() {
			double mids = -100;
			double finals = 44;
			double homeworks = 23;

			assertThrows(InvalidRangeException.class, () -> {
				grader.computeGrade(mids, finals, homeworks);
			});
		}
	}

}