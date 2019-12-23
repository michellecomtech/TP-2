package generate_report;

public class Criteria 
{
	private String criteriaText;
	private int criteriaWeight;
	private int studentCriteriaScore;
	private String criteriaName;
	
	public Criteria() 
	{
		this.criteriaText = "Correct_Answer";
		this.criteriaWeight = 4;
		this.studentCriteriaScore = 0;
	}
	
	public Criteria(String text, int weight) 
	{
		this.criteriaText = text;
		this.criteriaWeight = weight;
	}
		
	public Criteria(int i)
	{
		this.criteriaName = "Criteria_" + String.valueOf(i);
		this.criteriaText = "Correct_Answer";
		this.criteriaWeight = 4;
		this.studentCriteriaScore = 0;
	}
	
	public static Criteria[] InitializeCriterias(int numberOfCriterias)
	{
		Criteria[] theCriterias = new Criteria[numberOfCriterias];
		for(int i = 0; i < theCriterias.length; i++)
		{
			theCriterias[i] = new Criteria(i);
		}
		return theCriterias;
	}
		
	public String getText() 
	{
		return this.criteriaText;
	}
	
	public int getWeight() 
	{
		return this.criteriaWeight;
	}

	public int getStudentScore() 
	{
		return this.studentCriteriaScore;
	}
		
	public void setText(String text)
	{
		this. criteriaText= text;
	}

	public void setWeight(int weight)
	{
		this.criteriaWeight = weight;
	}

	public void setStudentScore(int score) 
	{
		this.studentCriteriaScore = score;
	}





}

