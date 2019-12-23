package generate_report;

public class ScoreAnalysis 
{
	int[] studentScore;
	
	public ScoreAnalysis(int[] studentScore)
	{
		this.studentScore = studentScore;
	}
	
	public static double mean(int[] studentScore) 
	{
	    double sum = 0;
	    for (int i = 0; i < studentScore.length; i++) 
	    {
	        sum += studentScore[i];
	    }
	    return sum / studentScore.length;
	}
	
	public static int median(int[] studentScore) 
	{
	    int middle = studentScore.length/2;
	    if (studentScore.length % 2 == 1) 
	    {
	        return studentScore[middle];
	    } 
	    else 
	    {
	        return (studentScore[middle-1] + studentScore[middle]) / 2;
	    }
	}
	
	public static int mode(int studentScore[])
	{
	    int maxium = 0;
	    int collector = 0 ;
	    for (int i = 0; i < studentScore.length; ++i) 
	    {
	        int count = 0;
	        for (int j = 0; j < studentScore.length; ++j) 
	        {
	            if (studentScore[j] == studentScore[i]) ++count;
	        }
	        if (count > collector) 
	        {
	            collector = count;
	            maxium = studentScore[i];
	        }
	    }
	    return maxium;
	}
}

