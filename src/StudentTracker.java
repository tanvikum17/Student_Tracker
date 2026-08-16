import javax.swing.JOptionPane;

/**
 * Driver class for the Student Tracker program.
 */
public class StudentTracker
{
    public static void main(String[] args)
    {
        Tracker tracker = new Tracker();

        while (true)
        {
            String menu =
                    "Welcome to Student Tracker\n\n" +
                            "1. Add Student\n" +
                            "2. Mark Attendance\n" +
                            "3. Add Lesson\n" +
                            "4. View All Students\n" +
                            "5. Save to File\n" +
                            "6. Load from File\n" +
                            "7. Delete Student\n" +
                            "8. View One Student\n" +
                            "9. Edit Student\n" +
                            "10. Exit";
            String input = JOptionPane.showInputDialog(menu);
            if (input == null) break;

            int choice;
            try// utilized a try catch because crashing shouldn't occur if user enters the wrong type of input (string instead of int for example)
            {
                choice = Integer.parseInt(input);
            }
            catch (NumberFormatException e) //prevents the StudentTracker from crashing by showing invalid input
            {
                JOptionPane.showMessageDialog(null, "Invalid input.");
                continue;
            }

            switch (choice)
            {
                case 1:
                    tracker.addStudent(
                            JOptionPane.showInputDialog("First name:"),
                            JOptionPane.showInputDialog("Last name:"),
                            Integer.parseInt(
                                    JOptionPane.showInputDialog("Grade level:"))
                    );
                    break;

                case 2:
                    tracker.markAttendance(
                            JOptionPane.showInputDialog("First name:"),
                            JOptionPane.showInputDialog("Last name:")
                    );
                    break;

                case 3:
                    tracker.addCompletedLesson(
                            JOptionPane.showInputDialog("First name:"),
                            JOptionPane.showInputDialog("Last name:"),
                            JOptionPane.showInputDialog("Lesson:")
                    );
                    break;

                case 4:
                    tracker.viewAllStudents();
                    break;

                case 5:
                    tracker.saveToFile();
                    break;

                case 6:
                    tracker.loadFromFile();
                    break;

                case 7:
                    tracker.deleteStudent(
                            JOptionPane.showInputDialog("First name:"),
                            JOptionPane.showInputDialog("Last name:")
                    );
                    break;

                case 8:
                    tracker.viewStudent(
                            JOptionPane.showInputDialog("First name:"),
                            JOptionPane.showInputDialog("Last name:")
                    );
                    break;

                case 9:
                    tracker.editStudent(
                            JOptionPane.showInputDialog("Enter current first name:"),
                            JOptionPane.showInputDialog("Enter current last name:")
                    );
                    break;

                case 10:
                    JOptionPane.showMessageDialog(null, "Tracker closed.");
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
    }
}
