package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

class ServiceTest {
    private static Service service;

    @BeforeAll
    public static void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    void findAllStudents() {
        Assertions.assertNotNull(service.findAllStudents());
    }

    @Test
    void findAllHomework() {
        Assertions.assertNotNull(service.findAllHomework());
    }

    @Test
    void findAllGrades() {
        Assertions.assertNotNull(service.findAllGrades());
    }

    @Test
    void studentShouldBeSaved() {
        Iterable<Student> students = service.findAllStudents();
        for (Student student : students) {
            if (student.getID().equals("Student1")) {
                service.deleteStudent("Student1");
                break;
            }
        }

        int result = service.saveStudent("Student1", "Szili", 533);

        students = service.findAllStudents();
        boolean exists = false;
        for (Student student : students) {
            if (student.getID().equals("Student1")) {
                exists = true;
                break;
            }
        }

        Assertions.assertTrue(exists);
    }

    @Test
    void studentShouldNotBeSaved() {
        Iterable<Student> students = service.findAllStudents();
        boolean exists = false;
        for (Student student : students) {
            if (student.getID().equals("Student1")) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            service.saveStudent("Student1", "Szili", 533);
        }

        int result = service.saveStudent("Student1", "Szili", 533);
        Assertions.assertEquals(0, result);
    }

    @Test
    void saveHomework() {
    }

    @Test
    void saveGrade() {
    }

    @Test
    void StudentShouldBeDeleted() {
        Iterable<Student> students = service.findAllStudents();
        boolean exists = false;
        for (Student student : students) {
            if (student.getID().equals("Student1")) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            service.saveStudent("Student1", "Szili", 533);
        }

        int result = service.deleteStudent("Student1");
        Assertions.assertEquals(1, result);
    }

    @Test
    void deleteHomework() {
    }

    @ParameterizedTest
    @ValueSource(ints = {111, 112, 315, 936, 937})
    void StudentShouldBeUpdated(int group) {
        Iterable<Student> students = service.findAllStudents();
        boolean exists = false;
        for (Student student : students) {
            if (student.getID().equals("Student1")) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            service.saveStudent("Student1", "Szili", 533);
        }

        int result = service.updateStudent("Student1", "New Szili", group);
        Assertions.assertEquals(1, result);

    }

    @Test
    void updateHomework() {
    }

    @Test
    void extendDeadline() {
    }

    @Test
    void createStudentFile() {
    }
}