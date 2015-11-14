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
/**
 * This is a planner class containing the list of the courses.
 */
public class Planner {

    /**
     * @MAX_COURSES[maximum number of courses to include in the planner]
     * @listOfClasses[array object containing the courses to be entered in
     * planner]
     * @count[keep the count(integer) of the courses being added/removed]
     */
    final static int MAX_COURSES = 50;
    Course[] listOfClasses = new Course[MAX_COURSES];
    private int count;

    /**
     * creates new planner contructor with the given values
     *
     * @param listOfClasses [array of the 50 null objects of type course]
     */
    public Planner(Course[] listOfClasses) {
        //super();
        this.listOfClasses = listOfClasses;
        //count = listOfClasses.length;
    }

    /**
     * creates planner object with initial count value of 0
     */
    public Planner() {
        count = 0;

    }

    /**
     * gets the number of courses entered
     *
     * @return [integer value of the count]
     */
    public int size() {
        return count;
    }

    /**
     * adds a course to the planner at the specified position, if the position
     * is already occupied, the method will move the objects one position below
     * and inserts the newCourse object. The 
     *
     * @param newCourse[course object with attributes of the course]
     * @param position[position(integer) in the array to be inserted]
     * @throws FullPlannerException [throws exception when the list is filled]
     * @throws IllegalArgumentException[when the position is out of range or if there
     * is a gap in between the array]
     */
    public void addCourse(Course newCourse, int position) throws FullPlannerException,
            IllegalArgumentException{
        if (position < 1 || position > count+1||position>MAX_COURSES) {
            throw new IllegalArgumentException("The position range should be "
                    + "from 1 to " + count+1 +"or less than "+MAX_COURSES);
        } else if (count == MAX_COURSES) {
            throw new FullPlannerException();
        } else {
            if (position > count) {
                listOfClasses[position - 1] = newCourse;
            } else {
                Course temp1 = listOfClasses[position - 1];
                listOfClasses[position - 1] = newCourse;
                for (int i = position; i <= count; i++) {
                    Course temp2 = listOfClasses[i];
                    listOfClasses[i] = temp1;
                    temp1 = temp2;
                }
            }
            count++;
            System.out.println("Course added successfully");
        }
    }

    /**
     * similar to the above method, but just adds the course object below the
     * list that is already there
     *
     * @param newCourse [course object containing information about the course
     */
    public void addCourse(Course newCourse) {
        listOfClasses[count] = newCourse;
        count++;
        System.out.println("Course added successfully");
    }

    /**
     * remove the course that is in the array at the specified location/position
     * shifts the contents above and automatically reduces the count.
     *
     * @param position[location of the course object]
     * @throws IllegalArgumentException [thrown when entered position value is
     * out of range]
     */
    public void removeCoures(int position) throws IllegalArgumentException {
        if (position < 1 || position > MAX_COURSES) {
            throw new IllegalArgumentException("position not within range");
        } else {
            String delName = listOfClasses[position - 1].getDepartment()
                    + listOfClasses[position - 1].getCode() + "."
                    + listOfClasses[position - 1].getSection();
            if (position == count) {
                listOfClasses[position - 1] = null;
            } else {
                listOfClasses[position-1] = null;
                //Course temp1 = listOfClasses[position];
                for (int i = position; i < count; i++) {
                    listOfClasses[i - 1] = listOfClasses[i];
                }
            }
            count--;
            System.out.println(delName + " delected successfully");
        }
    }

    /**
     * accessor method to return the count of the number of objects in the
     * planner
     *
     * @return [count value]
     */
    public int getCount() {
        return count;
    }

    /**
     * setter method to set the count with the specified value
     *
     * @param count[int value to be set the count]
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * gets the course object at the specified position
     *
     * @param position[location of the object to be retrieved]
     * @return[course object from the specified position]
     * @throws IllegalArgumentException[thrown when the position is out of
     * range]
     */
    public Course getCourse(int position) throws IllegalArgumentException {
        if (position < 1 || position > MAX_COURSES) {
            throw new IllegalArgumentException("position not within range");
        } 
        else {
            return listOfClasses[position - 1];
        }
    }

    /**
     * filter method that will return the list of courses that matches the
     * specified department name
     *
     * @param planner[planner to be searched in]
     * @param department [department to filter the courses]
     */
    public static void filter(Planner planner, String department) {
        Course temp;
        System.out.println("Pos  CourseName\t\t\tDept\tCode\tSec\tInstructor");
        System.out.println("---------------------------------------------------"
                + "-----------------------------------");
        for (int i = 1; i <= planner.getCount(); i++) {
            temp = planner.getCourse(i);
            if (temp.getDepartment().equalsIgnoreCase(department)) {
                System.out.println(i + "  " + temp.toString() + "\n");
            }
        }
    }

    /**
     * checks if a course already entered into the planner.
     *
     * @param course[course object to see if its already there]
     * @return [boolean depending on weather or not the course already exist]
     */
    public boolean exists(Course course) {
        for (int i = 0; i < count; i++) {
            if (listOfClasses[i].equals(course)) {
                System.out.println("this course is found at position"
                        + ": " + (i + 1));
                return true;
            }
        }
        System.out.println("course does not exist");
        return false;

    }

    /**
     * create a clone(deep copy) of the planner.
     *
     * @return [cloned planner object]
     *@throws [clone is not supported]
     */
    //fix this and ask about catch and add to the javadoc
    public Object clone()throws CloneNotSupportedException{
        
        try {
            Planner clone = new Planner();
            //clone.listOfClasses = this.listOfClasses.clone();
           // clone.count = this.count;
            //return clone;
            for (int i = 0; i < count; i++) {
                clone.listOfClasses[i] = (Course) this.listOfClasses[i].clone();
                clone.count = this.count;
            }
            return clone;
        } catch (Exception e) {
            //System.out.println("The planner is full");
            return null;
        }
    }

    /**
     * prints the courses that have been entered in a table format.
     */
    public void printAllCourses() {
        System.out.println("Pos  CourseName\t\t\tDept\tCode\tSec\tInstructor");
        System.out.println("---------------------------------------------------"
                + "-------------------------------------------");
        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + "  " + listOfClasses[i].toString() + "\n");
        }
    }

    /**
     * toString method override, that will return the course object.
     *
     * @return [sting representation of the courses in the planner]
     */
    @Override
    public String toString() {
        String plannerList = "";
        for (int i = 0; i < count; i++) {
            plannerList += listOfClasses[i].toString() + "\n";
        };
        return plannerList;
    }

}
