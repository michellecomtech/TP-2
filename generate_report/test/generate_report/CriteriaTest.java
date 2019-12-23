package generate_report;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CriteriaTest {
	
	private Criteria[] criterias;
	private int[] criteriaScores;
	private int numberOfCriterias;
	private int studentCriteriaScore;
	private Criteria criteriaDf;

	@Before
	public void setUp() throws Exception
	{
		criteriaScores = new int[] {20,30,40};
		numberOfCriterias = 4;
		criteriaDf = new Criteria();
		criterias = Criteria.InitializeCriterias(numberOfCriterias);
		studentCriteriaScore = 0 ;
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test_initialization_text_of_predictable_quiz()
	{
		criteriaDf.setText("Correct_Answer!");
		String text = criteriaDf.getText();
		assertEquals("the description is not Correct_Answer!", "Correct_Answer!", text);
	}
	
	@Test
	public void test_initialization_score_of_predictable_quiz()
	{
		criteriaDf.setStudentScore(0);
		int score = criteriaDf.getStudentScore();
		assertEquals("the studentScore is not 0", 0, score);
	}
	
	@Test
	public void test_weight_of_predictable_quiz()
	{
		Criteria criteria = new Criteria("Correct", 3);
		criteria.setWeight(4);
		int weight = criteria.getWeight();
		assertEquals("the weight is not 4", 4, weight);
	}
}
