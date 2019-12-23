package generate_report;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuizTest {
	
	private Student[] students;
	private int numberOfStudents;
	private Criteria[] criterias;
	private int[] criteriaScores;
	private int numberOfCriterias;
	private Quiz quiz;

	@Before
	public void setUp() throws Exception
	{
		numberOfStudents = 3;
		criteriaScores = new int[] {20,30,40};
		numberOfCriterias = 4;
		students = Student.InitializeStudents(numberOfStudents);
		criterias = Criteria.InitializeCriterias(numberOfCriterias);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test_initialization_of_predictable_quiz()
	{
		quiz = new Quiz(students, criterias);
		Quiz.quizScore(students, criterias);
		int examScore = students[0].getExamScore();
		assertEquals("the examScore of first student is not 0", 0, examScore);
	}
}