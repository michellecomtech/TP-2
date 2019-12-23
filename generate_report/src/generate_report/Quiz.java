package generate_report;

public class Quiz 
{
	public Student[] students = new Student[255];
	public Criteria[] criterias = new Criteria[255];
	
	public Quiz(Student[] students,  Criteria[] criterias)
	{
		this.students = students;
		this.criterias = criterias;
	}
	
	public static void quizScore(Student[] students,  Criteria[] criterias)
	{
		for (int j = 1; j < students.length; j++)
		{
			int quizScore = 0;
			
			for (int i = 0; i < criterias.length - 1; i++)
			{
				quizScore += (students[j].getCriteriaScores()[i]) * (criterias[i].getWeight());
			}
			students[j].addExamScore (quizScore);

		}
	}
}
