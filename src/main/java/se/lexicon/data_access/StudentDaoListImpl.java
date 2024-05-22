package se.lexicon.data_access;


import org.springframework.stereotype.Component;
import se.lexicon.data_access.sequencer.StudentIdSequencer;
import se.lexicon.exception.StudentNotFoundException;
import se.lexicon.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentDaoListImpl implements StudentDao {

    private List<Student> students;

    public StudentDaoListImpl() {
        students = new ArrayList<>();
    }

    @Override
    public Student save(Student student) {
        if (student == null) throw new IllegalArgumentException("Student is null");
        student.setId(StudentIdSequencer.nextId());
        students.add(student);
        return student;
    }

    @Override
    public Student find(int id)  {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public void delete(int id)  {
        Optional.ofNullable(find(id)).ifPresent(students::remove);
    }
}
