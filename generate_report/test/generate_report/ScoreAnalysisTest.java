package generate_report;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ScoreAnalysisTest {
	
	private ScoreAnalysis scoreAnalysis; 
	private int[] studentScoreEven;
	private int[] studentScoreOdd;


	@Before
	public void setUp() throws Exception
	{
		studentScoreEven = new int[] {20, 33, 33, 40};
		studentScoreOdd = new int[] {20, 33, 33, 33, 40};
		scoreAnalysis = new ScoreAnalysis(studentScoreEven);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test_mean_of_predictable_quiz()
	{
		int mean = (int)ScoreAnalysis.mean(studentScoreEven);
		assertEquals("the mean of student scores is not 31", 31, mean);
	}
	
	@Test
	public void test_median_even_of_predictable_quiz()
	{
		int median = (int)ScoreAnalysis.median(studentScoreEven);
		assertEquals("the median of student scores is not 33", 33, median);
	}
	
	@Test
	public void test_median_odd_of_predictable_quiz()
	{
		int median = (int)ScoreAnalysis.median(studentScoreOdd);
		assertEquals("the median of student scores is not 33", 33, median);
	}
	
	@Test
	public void test_mode_of_predictable_quiz()
	{
		int mode = (int)ScoreAnalysis.mode(studentScoreEven);
		assertEquals("the max mode of student scores is not 33", 33, mode);
	}
	
}