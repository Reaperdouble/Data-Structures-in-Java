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

/*
 * The Class Course contains the information of a course
 * such as course name, department, course code, section
 * and name of the instructor
 */
public class Course{

    private String courseName;
    private String department;
    private int code;
    private byte section;
    private String instructor;

    //empty constructor
    /**
     * creates a course object
     *
     */
    public Course() {
    }

    /**
     * constructor creates object with various information attributing to a
     * course
     *
     * @courseName[name of the course]
     * @department[three letter name indicating the department]
     * @code[class code ie, 114, 214 ....]
     * @section[denotes the section of the class]
     * @instructor[name of the instructor]
     *
     */
    public Course(String courseName, String department, int code, byte section,
            String instructor) {
        super();
        this.courseName = courseName;
        this.department = department;
        this.code = code;
        this.section = section;
        this.instructor = instructor;
    }

    //mutators
    /**
     * sets the name of the course using parameter name
     *
     * @param name[name of the course]
     */
    public void setCourseName(String name) {
        courseName = name;
    }

    /**
     * sets the three letter department name using parameter dept
     *
     * @param dept[three letter department name]
     */
    public void setDepartment(String dept) {
        department = dept;
    }

    /**
     * sets the corresponding class code for the class using parameter classCode
     *
     * @param classCode[class code ie, 214, 114..]
     * @throws IllegalArgumentException[thrown when the code is negative]
     */
    public void setCode(int classCode) throws IllegalArgumentException {
        if (classCode < 0) {
            throw new IllegalArgumentException("The values should'nt negative");
        }
        code = classCode;

    }

    /**
     * sets the class section using the parameter sec
     *
     * @param sec[indicates the section of the class]
     * @throws IllegalArgumentException[thrown when the value is negative]
     */
    public void setSection(byte sec) throws IllegalArgumentException {
        if (sec < 0) {
            throw new IllegalArgumentException("The values should'nt be negative");
        }
        section = sec;
    }

    /**
     * sets the name of the instructor using professor
     *
     * @param professor[name of the professor teaching the class]
     */
    public void setInstructor(String professor) {
        instructor = professor;
    }

    //accessors
    /**
     * gets the course name
     *
     * @return[name of the course as string]
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * gets the department name
     *
     * @return [three letter name of the department as string]
     */
    public String getDepartment() {
        return department;
    }

    /**
     * get the course code
     *
     * @return [the code of the class as int]
     */
    public int getCode() {
        return code;
    }

    /**
     * gets the section of the class
     *
     * @return [the class section as byte]
     */
    public byte getSection() {
        return section;
    }

    /**
     * gets the name of the instructor
     *
     * @return [the name of the professor as string]
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * does deep clone of the object
     *
     * @return [the cloned course object]
     * @throws CloneNotSupportedException [when the object]
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Course(courseName, department, code, section, instructor);
    }

    /**
     * checks if the corse is already there
     *
     * @param obj[the course object to be compared with]
     * @return [boolean depending on weather same or not]
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Course)) {
            return false;
        }
        Course obj2 = (Course) obj;

        if (this.courseName.equals(obj2.courseName)
                && this.department.equals(obj2.department)
                && this.code == obj2.code
                && this.instructor.equals(obj2.instructor)
                && this.section == obj2.section) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * print the object with its attributes ie name, dept, code, section,
     * instructor
     *
     * @return [string with the attributes formatted
     */
    public String toString() {
        return (String.format( "%-30s", courseName) + department + "\t" + code + "\t" + section
                + "\t" + instructor);

    }
}
