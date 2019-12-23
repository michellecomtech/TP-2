package generate_report;

public class Student 
{
	private String firstName;
	private String lastName;
	private int studentId;
	private int examScore;
	private int [] criteriaScores; 

	
	public Student()
	{	
		this.firstName = "Shengguo";
		this.lastName = "Sun";
		this.studentId = 1;
		this.examScore = 0;
		this.criteriaScores = new int [255];
	}
	
	public Student(int i)
	{
		this.firstName = "Student_" + String.valueOf(i);
		this.studentId = i;
		this.examScore = 0;
		this.criteriaScores = new int [255];
	}
	
	public static Student[] InitializeStudents(int numberOfStudents)
	{
		Student[] theStudents = new Student[numberOfStudents];
		for(int i = 1; i < theStudents.length + 1; i++)
		{
			theStudents[i-1] = new Student(i);
		}
		return theStudents;
	}
	
	public void addCriteriaScore(int[] criteriaScores)
	{
		for (int i = 0; i < criteriaScores.length; i++)
		{
			this.criteriaScores[i] = criteriaScores[i];
		}
	}	
	
	public int [] getCriteriaScores()
	{
		return this.criteriaScores;
	}
		
	public void addExamScore(int problemScore)
	{	
		this.examScore += problemScore;
	}
	
	public void setExamScore(int examScore)
	{	
		this.examScore = examScore;
	}
	
	public void setFirstName(String firstname)
	{
		this.firstName = firstname;
	}
	
	public void setLastName(String lastname)
	{
		this.lastName = lastname;
	}
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public int getId()
	{
		return this.studentId;
	}
		
	public int getExamScore()
	{
		return this.examScore;
	}
}