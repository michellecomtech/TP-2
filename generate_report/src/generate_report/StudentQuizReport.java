package generate_report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import edu.princeton.cs.introcs.StdOut;
import java.util.Scanner;

public class StudentQuizReport
{
	public static Student[] students;
	public static Criteria[] criterias;
	public static int numberOfProblems;
	public static int numberOfCriterias;
	public static int numberOfStudents;		
	
    public static void main(String[] args) throws IOException 
    {	
    	Scanner scanner = new Scanner(System.in);   	
    	StdOut.println("-----------------------------------------------------------------"); 
    	StdOut.println("                       Dear Professor!");
    	StdOut.println("-----------------------------------------------------------------"); 
    	StdOut.println("          Welcome to Student Quiz Report Generator!");
    	StdOut.println("-----------------------------------------------------------------"); 	
    	StdOut.println("Please enter the number of Problems in your quiz: ");
    	numberOfProblems = Integer.valueOf(scanner.nextLine());   	
    	StdOut.println("Please enter the number of Criterias in each problem: ");
    	numberOfCriterias = Integer.valueOf(scanner.nextLine());   	  	
    	StdOut.println("Please enter the number of Students in your class: ");
    	numberOfStudents = Integer.valueOf(scanner.nextLine());  	 	  	
    	     	
    	XSSFWorkbook studentQuizBook = new XSSFWorkbook();
        XSSFSheet studentQuizSheet = studentQuizBook.createSheet("StudentQuizRecords");
        XSSFRow row1 = studentQuizSheet.createRow(0);   
    	for(int i = 0; i < 3; i++)
    	{
    		XSSFCell cell0 = row1.createCell(i);        
    		cell0.setCellValue("*"); 
    	}
	
		int c = 0;
		for(int p = 1; p <= numberOfProblems; p++)
		{
			
			for(int cr = 1; cr <= numberOfCriterias; cr++)
			{
				XSSFCell cell0Temp = row1.createCell(c+3);       
				cell0Temp.setCellValue("P"+String.valueOf(p)+"-C"+String.valueOf(cr)+" Text");
				c++;
			}
			
		}	
		XSSFRow row2 = studentQuizSheet.createRow(1);
		XSSFCell cell0 = row2.createCell(0);        
		cell0.setCellValue("#"); 
		XSSFCell cell11 = row2.createCell(1);        
		cell11.setCellValue("First Name");		
		XSSFCell cell12 = row2.createCell(2);   
		cell12.setCellValue("Last Name");		
		for (int j = 0; j < (numberOfProblems * numberOfCriterias); j++) 
		{
			XSSFCell cell1Temp = row2.createCell(j + 3);
			cell1Temp.setCellValue("Add Weight");	
		}
		
		for (int r = 0; r < numberOfStudents; r++) 
		{
			XSSFRow rowTemp = studentQuizSheet.createRow(r + 2);
			XSSFCell cellTemp = rowTemp.createCell(0);
			cellTemp.setCellValue(String.valueOf(r + 1));
		}    
        try (FileOutputStream outputStream = new FileOutputStream("StudentQuizRecords.xlsx")) 
        {
            studentQuizBook.write(outputStream);
        }
        finally 
        {
            if (studentQuizBook != null) studentQuizBook.close();
        }
        StdOut.println("-----------------------------------------------------------------"); 
        StdOut.println("<StudentQuizRecords.xlsx> has been created! ");
        StdOut.println("Please fill in the excel with criteria texts & scores! ");
        StdOut.println("-----------------------------------------------------------------"); 
        boolean notReady = true;		
		while(notReady)
		{
			StdOut.println("Is your studentQuizRecords.xlsx filled completely? Press [Y] to continue...");
			String ready = scanner.nextLine();
			if("Y".equalsIgnoreCase(ready))
			{	
				notReady = false;
			}
		}
		StdOut.println("Student_Quiz_Reports are generating... ");
		StdOut.println("-----------------------------------------------------------------"); 
        
     // After filling in the created excel, the system will read the excel(Sample here) to generate student reports.    
        
        File studentScoresFile = new File("StudentQuizRecordsSample.xlsx");
		numberOfProblems = 4;
		numberOfCriterias = 3;
		numberOfStudents = 10; 
		String[][] information = new String[numberOfStudents + 2][numberOfProblems * numberOfCriterias + 3];
		int[] quizScoresData = new int[numberOfStudents];
	    FileInputStream strm = new FileInputStream(studentScoresFile);
	    XSSFWorkbook quizSampleBook = new XSSFWorkbook(strm);
	    XSSFSheet quizSheet = quizSampleBook.getSheetAt(0);
	    Iterator<Row> rowTp = quizSheet.iterator();     	    
	    int rowPointer = 0;
	    int colPointer = 0;

	    while(rowTp.hasNext())
	    {
	      Row row = rowTp.next();
	      Iterator<Cell> cellIterator = row.cellIterator();    	    		  
	      while (cellIterator.hasNext()) 
	      {
	        Cell cell = cellIterator.next();
	        information[rowPointer][colPointer] = cell.toString();
	        colPointer++;
			if (colPointer == numberOfProblems * numberOfCriterias + 3)
			{
				colPointer = 0;
				rowPointer++;
			}    
	      }
	    }	    
    	quizSampleBook.close();
        strm.close();

        students = Student.InitializeStudents(numberOfStudents + 1);
		for(int i = 1; i < numberOfStudents + 1; i++)
		{
			students[i].setLastName(information[1 + i][1]);
			students[i].setFirstName(information[1 + i][2]);
		}
		
		criterias = Criteria.InitializeCriterias(numberOfProblems * numberOfCriterias + 1);
		for(int i = 0; i < numberOfProblems * numberOfCriterias; i++)
		{
			criterias[i].setText(information[0][3 + i]);
			criterias[i].setWeight((int)Double.parseDouble(information[1][3 + i]));
		}
		
		for (int j = 1; j < students.length; j++)
		{
			int[] tempScores = new int[numberOfProblems * numberOfCriterias];
			for(int i = 0; i < numberOfProblems * numberOfCriterias; i++)
			{
				tempScores[i] = (int)Double.parseDouble(information[1 + j][3 + i]);
			}
			students[j].addCriteriaScore(tempScores);
		}		
		Quiz.quizScore(students, criterias);
				
	  //Generate quiz report for each student:
		
	    for(int k = 0; k < numberOfStudents; k++)
	    {
		    XWPFDocument studentDocu = new XWPFDocument();
		    FileOutputStream quizReports = new FileOutputStream(new File("QuizReport_" + students[k+1].getFirstName() + "_" + students[k+1].getLastName() + ".docx"));
		    
	        XWPFParagraph name = studentDocu.createParagraph();  
	        name.setAlignment(ParagraphAlignment.CENTER);
	        XWPFRun runName = name.createRun();  
	        runName.setText(students[k+1].getFirstName() + " " +students[k+1].getLastName());
	        runName.setFontSize(20);

	        
	        XWPFParagraph text1 = studentDocu.createParagraph();  
	        text1.setAlignment(ParagraphAlignment.CENTER);
	        XWPFRun runText1 = text1.createRun();  
	        runText1.setText("In-Class Midterm Score Diagnostic Report");
	        runText1.addBreak();
	        runText1.addBreak();
	        
	        XWPFParagraph text2 = studentDocu.createParagraph();  
	        text2.setAlignment(ParagraphAlignment.CENTER);
	        XWPFRun runText2 = text2.createRun();  
	        runText2.setText("Your Scores by Problem Component");
	        runText2.addBreak();
	      
	        
	        XWPFTable reportsTable = studentDocu.createTable(); 
	        reportsTable.setTableAlignment(TableRowAlign.CENTER);
		    XWPFTableRow tableRow0 = reportsTable.getRow(0);
		    tableRow0.getCell(0).setText("Problem");		    
		    tableRow0.addNewTableCell().setText("Score Component Description");
		    tableRow0.addNewTableCell().setText("Available Points");
		    tableRow0.addNewTableCell().setText("Your Score");
		    tableRow0.addNewTableCell().setText("Deductions");		    
		    	          
		    for(int m = 0; m < numberOfProblems * numberOfCriterias; m++)
		    {
			    XWPFTableRow tableRowTemp = reportsTable.createRow();
			    tableRowTemp.getCell(0).setText(Integer.toString(1 + (m/numberOfCriterias)));    
			    tableRowTemp.getCell(1).setText(criterias[m].getText());
			    tableRowTemp.getCell(2).setText(Integer.toString(criterias[m].getWeight()));		    
			    tableRowTemp.getCell(3).setText(Integer.toString(students[k + 1].getCriteriaScores()[m] * criterias[m].getWeight()));			    			 			    
			    tableRowTemp.getCell(4).setText(Integer.toString(criterias[m].getWeight() - students[k + 1].getCriteriaScores()[m]* criterias[m].getWeight()));
		    }
			

		    XWPFParagraph text5 = studentDocu.createParagraph();  
	        text5.setAlignment(ParagraphAlignment.CENTER);
	        XWPFRun runText5 = text5.createRun();  
	        runText5.addBreak();
	        runText5.setText("_____________________________________________________________________________" );
	        runText5.addBreak();		    
	        
		    XWPFParagraph text3 = studentDocu.createParagraph();  
	        text3.setAlignment(ParagraphAlignment.CENTER);
	        XWPFRun runText3 = text3.createRun();  
	        runText3.setText("Your Total in-Class Score = " + Integer.toString(students[k + 1].getExamScore()) + " out of 50" );
	        runText3.addBreak();
	        
	        XWPFParagraph text4 = studentDocu.createParagraph();  
	        text4.setAlignment(ParagraphAlignment.CENTER);
	        XWPFRun runText4 = text4.createRun();  
	        runText4.setText("Nice Job, " + students[k+1].getFirstName() + " " +students[k+1].getLastName() + " !");
	        runText4.addBreak();
	        runText4.setText("_____________________________________________________________________________" );	        
		    studentDocu.write(quizReports);
		    quizReports.close();
		    System.out.println("QuizReport_" + students[k+1].getFirstName() + "_" + students[k+1].getLastName() + ".docx is generated successully!");
		    quizScoresData[k] = students[k + 1].getExamScore();
	    }
	    
	    StdOut.println("-----------------------------------------------------------------"); 
        boolean noNeed = true;		
		while(noNeed)
		{
			StdOut.println("Do you need a special analysis report? [Y] or [N]");
			String need = scanner.nextLine();
			if("Y".equalsIgnoreCase(need))
			{	
				noNeed = false;
				StdOut.println("Student_Quiz_Reports are generating... ");
				StdOut.println("-----------------------------------------------------------------"); 
				
				XWPFDocument professorDocu = new XWPFDocument();
			    FileOutputStream analysisReport = new FileOutputStream(new File("SpecialAnalysisReport.docx"));
			    
		        XWPFParagraph title = professorDocu.createParagraph();  
		        title.setAlignment(ParagraphAlignment.CENTER);
		        XWPFRun runTitle = title.createRun();  
		        runTitle.setText("Special Analysis Report");
		        runTitle.setFontSize(20);
		        runTitle.addBreak();
		        
		        XWPFParagraph analysisText1 = professorDocu.createParagraph();  
		        analysisText1.setAlignment(ParagraphAlignment.CENTER);
		        XWPFRun runAnalysisText1 = analysisText1.createRun();  
		        runAnalysisText1.setText("The mean of this quiz for all students is: " + String.valueOf((ScoreAnalysis.mean(quizScoresData))));
		        runAnalysisText1.addBreak();
		        runAnalysisText1.addBreak();
		        
		        XWPFParagraph analysisText2 = professorDocu.createParagraph();  
		        analysisText2.setAlignment(ParagraphAlignment.CENTER);
		        XWPFRun runAnalysisText2 = analysisText2.createRun();  
		        runAnalysisText2.setText("The median of this quiz for all students is: " + String.valueOf((ScoreAnalysis.median(quizScoresData))));
		        runAnalysisText2.addBreak();
		        runAnalysisText2.addBreak();
		        
		        XWPFParagraph analysisText3 = professorDocu.createParagraph();  
		        analysisText3.setAlignment(ParagraphAlignment.CENTER);
		        XWPFRun runAnalysisText3 = analysisText3.createRun();  
		        runAnalysisText3.setText("The max mode of this quiz for all students is: " + String.valueOf((ScoreAnalysis.mode(quizScoresData))));
		        runAnalysisText3.addBreak();
		        runAnalysisText3.addBreak();
		        	        
		        professorDocu.write(analysisReport);
		        analysisReport.close();
			    System.out.println("SpecialAnalysisReport.docx is generated successully!");		
			}
			else if ("N".equalsIgnoreCase(need))
			{	
				noNeed = false;
			}
			else
			{
				StdOut.println("Invalid Input!");
			}
		}
		scanner.close();
		StdOut.println("-----------------------------------------------------------------"); 
		StdOut.println("--------Thank you for using StudentQuizReportGenerator!----------");
		StdOut.println("------------------------Enjoy the Holiday!-----------------------");	
    }
}
