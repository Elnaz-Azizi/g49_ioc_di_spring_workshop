package se.lexicon.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.data_access.StudentDao;
import se.lexicon.exception.StudentNotFoundException;
import se.lexicon.models.Student;
import se.lexicon.util.UserInputService;

import java.util.List;

@Component

public class StudentManagementConsoleImpl implements StudentManagement {
    UserInputService userInputService;
    StudentDao studentDAO;

    @Autowired
    public StudentManagementConsoleImpl(UserInputService userInputService, StudentDao studentDAO) {
        this.userInputService = userInputService;
        this.studentDAO = studentDAO;
    }

    @Override

    public Student create() {
        System.out.println("enter a name:");
        String name = userInputService.getString();
        Student student = new Student(name);
        return studentDAO.save(student);
    }

    @Override
    public Student save(Student student) {
        if (student == null) throw new IllegalArgumentException("Student is null");
        return studentDAO.save(student);
    }

    @Override
    public Student find(int id) {
        if (id <= 0) throw new IllegalArgumentException("id is not valid");
        try {
            return studentDAO.find(id);
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Student remove(int id) {
        if (id <= 0) throw new IllegalArgumentException("id is not valid");
        try {
            Student student = studentDAO.find(id);
            studentDAO.delete(id);
            return student;
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDAO.findAll();
    }

    @Override
    public Student edit(Student student) {
        if (student == null) throw new IllegalArgumentException("Student is null");
        if (student.getId() <= 0) throw new IllegalArgumentException("Student ID is not valid");
        try {
            Student existingStudent = studentDAO.find(student.getId());
            existingStudent.setName(student.getName());
            return studentDAO.save(existingStudent);
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
/*
    public Student edit() {
        System.out.println("Enter the student ID to edit:");
        int id = userInputService.getInt();
        Student existingStudent;
        try {
            existingStudent = studentDAO.find(id);
        } catch (StudentNotFoundException e) {
            System.out.println("Student not found");
            return null;
        }
        System.out.println("Enter the new name for the student:");
        String newName = userInputService.getString();
        existingStudent.setName(newName);
        return studentDAO.save(existingStudent);
    }
}*/




