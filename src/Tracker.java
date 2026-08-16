import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages a collection of Student objects
 */
public class Tracker {
    private ArrayList<Student> tracker;

    /**
     * Constructs an empty student tracker.
     */
    public Tracker() {
        tracker = new ArrayList<>();
    }

    /**
     * Adds a new student if they do not already exist.
     */
    public boolean addStudent(String first, String last, int grade)
    {
        if (grade < 1 || grade > 12)
        {
            JOptionPane.showMessageDialog(null, "Grade must be between 1 and 12.");
            return false;
        }

        if (findStudent(first, last) != null)
        {
            JOptionPane.showMessageDialog(null, "Student already exists.");
            return false;
        }

        tracker.add(new Student(first, last, grade, 0, new ArrayList<>()));
        return true;
    }

    private Student findStudent(String first, String last) {
        for (Student s : tracker) {
            if (s.getFirstName().equalsIgnoreCase(first)
                    && s.getLastName().equalsIgnoreCase(last)) {
                return s;
            }
        }
        return null;
    }

    public void markAttendance(String first, String last) {
        Student s = findStudent(first, last);
        if (s != null) {
            s.markAttendance();
            JOptionPane.showMessageDialog(null, "Attendance updated.");
        } else {
            JOptionPane.showMessageDialog(null, "Student not found.");
        }
    }

    public void addCompletedLesson(String first, String last, String lesson) {
        Student s = findStudent(first, last);
        if (s != null) {
            s.addCompletedLesson(lesson);
        } else {
            JOptionPane.showMessageDialog(null, "Student not found.");
        }
    }

    /**
     * Deletes a student and their information
     */

    public void deleteStudent(String first, String last) {
        Student s = findStudent(first, last);

        if (s != null) {
            tracker.remove(s);
            JOptionPane.showMessageDialog(null, "Student deleted.");
        } else {
            JOptionPane.showMessageDialog(null, "Student not found.");
        }
    }

    /**
     * Displays one student's information
     * alphabetically orders the lessons
     */

    public void viewStudent(String first, String last) {
        Student s = findStudent(first, last);

        if (s == null) {
            JOptionPane.showMessageDialog(null, "Student not found.");
            return;
        }

        // bubble sorts the lessons before they print
        s.bubbleSortLessons();
        StringBuilder output = new StringBuilder();
        output.append(s.toString());
        output.append("\n\nCompleted Lessons:\n");
        if (s.getCompletedLessons().isEmpty()) {
            output.append("None");
        } else {
            for (String lesson : s.getCompletedLessons()) {
                output.append("• ").append(lesson).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, output.toString());
    }

     /**
     * Displays all students in a single window.
     */
    public void viewAllStudents()
    {
        if (tracker.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No students available.");
            return;
        }

        Collections.sort(tracker);

        StringBuilder output = new StringBuilder("All Students:\n\n");
        for (Student s : tracker)
        {
            output.append(s).append("\nLessons: ")
                    .append(s.getCompletedLessons()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, output.toString());
    }

    /**
     * Loads student data from a file selected by the user.
     */
    public void loadFromFile()
    {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) return;

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(chooser.getSelectedFile())))
        {
            tracker.clear();
            String line;

            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(" ", 5);

                String first = parts[0];
                String last = parts[1];
                int grade = Integer.parseInt(parts[2]);
                int attendance = Integer.parseInt(parts[3]);

                ArrayList<String> lessons = new ArrayList<>();
                if (parts.length == 5)
                {
                    for (String l : parts[4].split(","))
                    {
                        lessons.add(l.trim());
                    }
                }

                tracker.add(new Student(first, last, grade, attendance, lessons));
            }

            JOptionPane.showMessageDialog(null, "File loaded.");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "File error.");
        }
    }

    /**
     * Saves all student data to a file.
     */
    public void saveToFile()
    {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) return;

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(chooser.getSelectedFile())))
        {
            for (Student s : tracker)
            {
                writer.write(s.getFirstName() + " " +
                        s.getLastName() + " " +
                        s.getGradeLevel() + " " +
                        s.getAttendanceCount() + " " +
                        String.join(",", s.getCompletedLessons()));
                writer.newLine();
            }

            JOptionPane.showMessageDialog(null, "File saved.");
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Save error.");
        }
    }

    /**
     * editstudent method allows to edit a student's name
     * @param first
     * @param last
     */
    public void editStudent(String first, String last)
    {
        Student s = findStudent(first, last);

        if (s == null)
        {
            JOptionPane.showMessageDialog(null, "Student not found.");
            return;
        }

        String newFirst = JOptionPane.showInputDialog("New first name:", s.getFirstName());
        String newLast = JOptionPane.showInputDialog("New last name:", s.getLastName());
        String gradeInput = JOptionPane.showInputDialog("New grade level (1-12):", s.getGradeLevel());

        if (newFirst == null || newLast == null || gradeInput == null)
            return;

        int newGrade;

        try
        {
            newGrade = Integer.parseInt(gradeInput);
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Grade must be a number.");
            return;
        }

        if (newGrade < 1 || newGrade > 12)
        {
            JOptionPane.showMessageDialog(null, "Grade must be between 1 and 12.");
            return;
        }

        s.setFirstName(newFirst);
        s.setLastName(newLast);
        s.setGradeLevel(newGrade);

        JOptionPane.showMessageDialog(null, "Student updated.");
    }

}
