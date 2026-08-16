import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a student with their first, last name,
 * their grade level, attendance count, and list of
 * completed lessons
 */
public class Student implements Comparable<Student>
{
    private String firstName;// instance field stating first name of the student
    private String lastName;// instance field stating last name of the student
    private int gradeLevel;// instance field stating grade level of the student
    private int attendanceCount;// instance field with attendance count of the student
    private ArrayList<String> completedLessons; //instance field of arraylist holding lessons a student completed

    /**
     * Constructs a Student with full data.
     */
    public Student(String firstName, String lastName, int gradeLevel,
                   int attendanceCount, ArrayList<String> lessons)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gradeLevel = gradeLevel;
        this.attendanceCount = attendanceCount;
        this.completedLessons = lessons;
    }

    /**
     * adds a day for attendance
     */
    public void markAttendance()
    {
        attendanceCount++;
    }

    public int getAttendanceCount()
    {
        return attendanceCount;
    }

    public int getGradeLevel()
    {
        return gradeLevel;
    }
    /**
     * returns the student's first name
     * @return String of the first name
     */

    public String getFirstName()
    {
        return firstName;
    }
    /**
     * returns the student's last name
     * @return String last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * returns the student's full name
     * @return String name
     */
    public String getName()
    {
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setGradeLevel(int gradeLevel)
    {
        this.gradeLevel = gradeLevel;
    }

    /**
     * Adds a lesson for a given student
     */
    public void addCompletedLesson(String lesson)
    {
        completedLessons.add(lesson);
    }

    /**
     * Sorts completed lessons alphabetically
     */
    public void sortCompletedLessons()
    {
        Collections.sort(completedLessons);
    }

    public ArrayList<String> getCompletedLessons()
    {
        return completedLessons;
    }

    /**
     * Compares students alplahebtically by first name.
     */
    public void bubbleSortLessons()
    {
        for (int i = 0; i < completedLessons.size() - 1; i++)
        {
            for (int j = 0; j < completedLessons.size() - i - 1; j++)
            {
                String a = completedLessons.get(j);
                String b = completedLessons.get(j + 1);

                if (a.compareToIgnoreCase(b) > 0)
                {
                    // swap
                    completedLessons.set(j, b);
                    completedLessons.set(j + 1, a);
                }
            }
        }
    }
    /**
     * compareto method
     * orders the students by last name, then first, then grade level
     * @param other is the reference object with which to compare.
     */
    public int compareTo(Student other)
    {
        // Compares the last names
        int last = this.lastName.compareToIgnoreCase(other.lastName);
        if (last != 0)
            return last;

        // If last names same then compares first names
        int first = this.firstName.compareToIgnoreCase(other.firstName);
        if (first != 0)
            return first;

        // If names are identical compare grade
        return this.gradeLevel - other.gradeLevel;
    }

    /**
     * Equals method
     * @param object o the reference object with which to compare.
     * @returns true if param o equals the input name
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student other = (Student) o;
        return firstName.equalsIgnoreCase(other.firstName)
                && lastName.equalsIgnoreCase(other.lastName);
    }

    @Override
    /**
     * toString method
     */
    public String toString()
    {
        return getName() +
                " [grade level: " + gradeLevel + "]" +
                "  [attendance: " + attendanceCount + "]";
    }
}