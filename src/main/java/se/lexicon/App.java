package se.lexicon;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.lexicon.config.ComponentScanConfig;
import se.lexicon.data_access.StudentDao;
import se.lexicon.models.Student;
import se.lexicon.service.StudentManagement;
import se.lexicon.service.StudentManagementConsoleImpl;
import se.lexicon.util.ScannerInputService;
import se.lexicon.util.UserInputService;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        StudentDao studentDao = context.getBean(StudentDao.class);
        UserInputService userInputService =context.getBean(UserInputService.class);

        StudentManagement studentManagement = new StudentManagementConsoleImpl(userInputService,studentDao);

        System.out.println("Testing create method:");
        Student createdStudent = studentManagement.create();
        System.out.println("Created Student: " + createdStudent.getName());

        System.out.println("Testing save method:");
        Student savedStudent = studentManagement.save(createdStudent);
        System.out.println("Saved Student: " + savedStudent.getName());

        System.out.println("Testing find method:");
        Student foundStudent = studentManagement.find(savedStudent.getId());
        System.out.println("Found Student: " + foundStudent.getName());

        System.out.println("Testing findAll method:");
        List<Student> allStudents = studentManagement.findAllStudents();
        System.out.println("All Students: " + allStudents);

        System.out.println("Testing edit method:");
        savedStudent.setName("New Name");
        Student editedStudent = studentManagement.edit(savedStudent);
        System.out.println("Edited Student: " + editedStudent.getName());

        System.out.println("Testing findAll method:");
        List<Student> allStudentsAfterEdit = studentManagement.findAllStudents();
        System.out.println("All Students: " + allStudents);

        System.out.println("Testing remove method:");
        Student removedStudent = studentManagement.remove(editedStudent.getId());
        System.out.println("Removed Student: " + removedStudent.getName());

    }
}
