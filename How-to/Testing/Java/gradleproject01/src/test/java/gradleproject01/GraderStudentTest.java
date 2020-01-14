package gradleproject01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GraderStudentTest {
	
	GraderStudent grader;
	
	@BeforeEach
	void setUp() {
		grader = new GraderStudent();
//		System.out.println("Test setup");
	}

	@DisplayName("@MethodSource를 이용한 정상 객체 테스트")
	@ParameterizedTest(name="애는 어떻게 인자를 넘기지? {0} {1}")
	@MethodSource("generateValidStudent")
	void should_compute_grade_for_valid_students(Grade g, Student s) {
//		GraderStudent grader = new GraderStudent();
		Grade actual = grader.computeGrade(s);
		assertEquals(g, actual);
	}

	private static Stream<Arguments> generateValidStudent() {
		return Stream.of(
				Arguments.of(Grade.A, new Student(95,85,98)),
				Arguments.of(Grade.B, new Student(85,85,88)),
				Arguments.of(Grade.C, new Student(78,75,68)),
				Arguments.of(Grade.D, new Student(69,55,70)),
				Arguments.of(Grade.F, new Student(55,60,34))
				);
	}
	
	@DisplayName("@MethodSource를 이용한 비정상 객체 테스트")
	@ParameterizedTest(name="애는 어떻게 인자를 넘기지?")
	@MethodSource("generateInvalidStudent")
	void should_compute_grade_for_invalid_students(Student s) {
		assertThrows(InvalidRangeException.class, () -> {
			grader.computeGrade(s);
		});
	}

	private static Stream<Arguments> generateInvalidStudent() {
		return Stream.of(
				Arguments.of(new Student(-10,10,10)),
				Arguments.of(new Student(10,-10,10)),
				Arguments.of(new Student(10,10,-10)),
				Arguments.of(new Student(110,10,10)),
				Arguments.of(new Student(10,110,10)),
				Arguments.of(new Student(10,10,110))
				);
	}
}
