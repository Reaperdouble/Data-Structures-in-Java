package hw1;
/**
 * Rajith Radhakrishnan 
 * 109061463
 * HW#1 
 * CSE 214- R06 
 * TA - Frank Migliorino
 *
 * @author radra_000
 */

import java.util.Scanner;   //to get user input

public class PlannerManager {

    /**
     * main method that show a menu of option for the user to use to add their
     * course schedule. method will run until the user enters Q in which case
     * the program will terminate.
     *
     * @param args [no arguments in returned]
     */
    public static void main(String[] args) {
        Planner planner = new Planner();
        Planner backup = new Planner();
        Scanner input = new Scanner(System.in);
        int run = 1;
        while (run == 1) {
            System.out.println("A - ADD COURSE \n"
                    + "G - GET COURSE \n"
                    + "R - REMOVE COURSE\n"
                    + "P - PRINT COURSES IN THE PLANNER \n"
                    + "F = FILTER BY DEPT \n"
                    + "L = LOOK FOR COURSE \n"
                    + "S - SIZE OF THE PLANNER \n"
                    + "B - BACKUP PLANNER \n"
                    + "PB - PRINT COURSES IN BACKUP \n"
                    + "RB - REVERT T0 BACKUP\n"
                    + "Q - QUIT THE PROGRAM");
            System.out.print("Enter a selection: ");
            String choice = input.next().toUpperCase();
            switch (choice) {
                case ("A"):
                    Course newCourse = new Course();
                    System.out.print("Enter Course Name:");
                    input.nextLine();
                    newCourse.setCourseName(input.nextLine());
                    System.out.print("Enter the Department: ");
                    newCourse.setDepartment(input.nextLine());
                    System.out.print("");
                    System.out.print("Enter the Course Code: ");
                    newCourse.setCode(input.nextInt());
                    System.out.print("Enter the Cousrse Section: ");
                    byte section= 1;
                    int loop = 1;
                    while (loop == 1) {
                        try {
                            section = input.nextByte(); 
                            loop = 0;
                        } catch (Exception e) {
                            System.out.println("The input should be a positive number");
                            input.next();
                            System.out.print("Enter the Cousrse Section: ");
                            loop = 1;
                        }
                    }
                    newCourse.setSection(section);
                    input.nextLine();
                    System.out.print("Enter the Name of the "
                            + "Instructor: ");
                    newCourse.setInstructor(input.nextLine());
                    System.out.print("Enter the position to be "
                            + "placed at: ");
                    int position = input.nextInt();
                    // System.out.println(newCourse.getInstructor() + position);
                    try {
                        planner.addCourse(newCourse, position);
                    } catch (Exception e) {
                        System.out.println("planner is full or the position is "
                                + "not within the range");
                    }
                    break;
                case ("G"):
                    System.out.print("Enter the position to be retrieved: ");
                    int pos = input.nextInt();
                    System.out.print(pos);
                    System.out.println("CourseName\t\t\tDept\tCode\tSec\tInstructor");
                    System.out.println("---------------------------------------------------"
                    + "-----------------------------------");
                    System.out.println(planner.getCourse(pos));
                    break;
                case ("R"):
                    System.out.print("Enter the position to be removed: ");
                    planner.removeCoures(input.nextInt());
                    break;
                case ("P"):
                    planner.printAllCourses();
                    break;
                // fix the filter code
                case ("F"):
                    System.out.print("Enter the Department: ");
                    input.nextLine();
                    String deptName = input.nextLine();
                    Planner.filter(planner, deptName);
                    break;
                // ask weather i could use the same object newCourse
                case ("L"):
                    Course Course = new Course();
                    System.out.print("Enter Course Name: ");
                    input.nextLine();
                    Course.setCourseName(input.nextLine());
                    System.out.print("Enter the Department: ");
                    Course.setDepartment(input.nextLine());
                    System.out.print("Enter the Course Code: ");
                    Course.setCode(input.nextInt());
                    System.out.print("Enter the Cousrse Section: ");
                    Course.setSection(input.nextByte());
                    input.nextLine();
                    System.out.print("Enter the Name of the "
                            + "Instructor: ");
                    Course.setInstructor(input.nextLine());
                    planner.exists(Course);
                    break;
                case ("S"):
                    System.out.println("There are " + planner.size()
                            + " classes in the planner");
                    break;
                // ask how to fix this
                case ("B"):
                    try {
                        backup = (Planner) planner.clone();
                        System.out.println("Planner backup succesfully");
                    } catch (Exception e) {
                        System.out.println("backup not created");
                    }
            
                     break;


                case ("PB"):
                    backup.printAllCourses();
                    break;
                case ("RB"):
                    try{
                    planner = (Planner) backup.clone();
                    System.out.println("Planner is reverted "
                            + "to backup");
                    }
                    catch(Exception e){
                        System.out.println("the clone is not reverted");
                    }
                    break;
                case ("Q"):
                    run = 0;
                    System.out.println("program quitting");
                    break; // ask if unneccessary
				
                default:
                 System.out.println("selection does not exist");
                 run = 1;
            }
        }
        input.close();
    }

}
