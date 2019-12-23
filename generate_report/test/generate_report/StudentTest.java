package generate_report;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {
	
	private Student[] students;
	private int numberOfStudents;
	private int[] criteriaScores;
	private Student studentDf;

	@Before
	public void setUp() throws Exception
	{
		numberOfStudents = 3;
		criteriaScores = new int[] {20,30,40};
		students = Student.InitializeStudents(numberOfStudents);
		studentDf = new Student();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test_initialization_of_predictable_firstname_student()
	{
		studentDf.getFirstName();
		studentDf.getLastName();
		studentDf.setFirstName("Louis");
		studentDf.setLastName("Armstrong");
		String fname = studentDf.getFirstName();
		assertEquals("the firstname is not Louis", "Louis", fname);
	}

	@Test
	public void test_initialization_of_predictable_lastname_student()
	{
		studentDf.getFirstName();
		studentDf.getLastName();
		studentDf.setFirstName("Louis");
		studentDf.setLastName("Armstrong");
		String lname = studentDf.getLastName();
		assertEquals("the lastname is not Armstrong", "Armstrong", lname);
	}

	@Test
	public void test_Id2_init_of_3_players_of_predictable()
	{
		int sId2 = students[1].getId();
		assertEquals("the ID of the first student is not 2", 2, sId2);
	}
	
	@Test
	public void test_score_of_predictable_student()
	{
		studentDf.setExamScore(10);
		studentDf.addExamScore(5);
		int score = studentDf.getExamScore();
		assertEquals("the score is not 15", 15, score);
	}
	
	@Test
	public void test_score_of_criteria_of_predictable()
	{
		studentDf.addCriteriaScore(criteriaScores);
		int score = studentDf.getCriteriaScores()[0];
		assertEquals("the score of the first criteria is not 2", 20, score);
	}
}

