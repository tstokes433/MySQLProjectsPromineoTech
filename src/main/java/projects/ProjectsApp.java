package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import project.dao.DbException;
import projects.entity.Project;
import projects.service.ProjectService;

//import java.sql.Connection;

public class ProjectsApp {
	
	//Added below
	
//2. TWO   11. eleven
	private Scanner scanner = new Scanner(System.in);
	private ProjectService projectService = new ProjectService();
	private Project curProject;

//1. ONE
	//@formatter:off	
	private List<String> operations = List.of(
		"1) Add a project",	
		"2) List projects",
		"3) Select a project"
	);
	//@formatter:on
	


	
	public static void main(String[] args) {
	//DbConnection.getConnection();	

		
//3. THREE
		new ProjectsApp().processUserSelections();
	}

	
//4. FOUR
	private void processUserSelections() {
		boolean done = false;
		
		while(!done) {
			try { 
				int selection = getUserSelection();
		switch(selection) {
		   case -1:
			done = exitMenu();
			break;
			
		   case 1: 
			createProject();
			break;
		   case 2 : 
			listProjects();
			break;
		   case 3 : 
			selectProject();
			break;
			
		   default:
			System.out.println("\n" + selection + " is not a valid selection. Try again.");
			break;
		}
		 } catch(Exception e) {
			 System.out.println("\nError: " + e + " Try again.");	
//			 e.printStackTrace();
		}
		}		
	}
private void selectProject() {
	listProjects();
	Integer projectId = getIntInput("Enter a project ID to select a project");

	curProject = null;
	
	curProject = projectService.fetchProjectById(projectId);
}
private void listProjects() {
	List<Project> projects = projectService.fetchAllProjects();
		System.out.println("\nProjects:");
		
		projects.forEach(project -> System.out.println("  " + project.getProjectId()
				+ ": " + project.getProjectName()));
}


private void createProject() {
	String projectName = getStringInput("Enter the Project Name.");
	BigDecimal estimatedHours = getDecimalInput("Enter the Estimated Hours.");
	BigDecimal actualHours = getDecimalInput("Enter the Actual Hours.");
	Integer difficulty = getIntInput("Enter the Project Difficulty (1-5)");
	String notes = getStringInput("Enter the Project Notes");
	
	Project project = new Project();
	
	project.setProjectName(projectName);
	project.setEstimatedHours(estimatedHours);
	project.setActualHours(actualHours);
	project.setDifficulty(difficulty);
	project.setNotes(notes);
	
	Project dbProject = projectService.addProject(project);
	System.out.println("You have successfully created project: " + dbProject);
}

private BigDecimal getDecimalInput(String prompt) {
	String input = getStringInput(prompt);
	
	if(Objects.isNull(input)) {
		return null;
	}
	
	try {
		return new BigDecimal(input).setScale(2);
	}
	catch(NumberFormatException e) {
		throw new DbException(input + "  is not a valid decimal number.");
	}
}


private boolean exitMenu() {
	System.out.println("Exiting the menu.");
	return true;
}


//5. FIVE
	private int getUserSelection() {
		printOperations();
		
		Integer input = getIntInput("Enter a menu selection");
		
		return Objects.isNull(input) ? -1 : input;
	}
//7. SEVEN
	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		
		try {
			return Integer.valueOf(input);
		}
		catch(NumberFormatException e) {
			throw new DbException(input + "  is not a valid number.");
		}
	}
//	private Double getDoubleInput(String prompt) {
//		String input = getStringInput(prompt);
//		
//		if(Objects.isNull(input)) {
//			return null;
//		}
//		
//		try {
//			return Double.valueOf(input);
//		}
//		catch(NumberFormatException e) {
//			throw new DbException(input + " is not a valid number.");
//		}
//	}
//8. EIGHT
	private String getStringInput(String prompt) {
		System.out.print(prompt + ": ");
		String input = scanner.nextLine();
		
		return input.isBlank() ? null : input.trim();
	}

//6. SIX
	private void printOperations() {
		System.out.println();
		System.out.println("\nThese are the avaliable selections. Press the Enter key to quit:");
		
		operations.forEach(line -> System.out.println(" " + line));
		
		if(Objects.isNull(curProject)) {
			System.out.println("\nYou are not working with a project.");
		}
		else {
			System.out.println("\nYou are working with project: " + curProject);
		}
	}
}
