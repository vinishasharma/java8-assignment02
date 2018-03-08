package edu.knoldus;

import edu.knoldus.model.ClassRoom;
import edu.knoldus.model.Student;
import edu.knoldus.operation.StudentOperation;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class StudentOperationTest {
    static List<String> subjectList = Arrays.asList("chemistry", "history", "economics");
    private static final Student studentOne = new Student("vinisha", 23, Optional.of(subjectList));
    private static final Student studentTwo = new Student("ramesh", 17, Optional.ofNullable(null));
    private static final Student studentThree = new Student("riya", 10, Optional.ofNullable(null));
    private  static final List<Student> studentsList = Arrays.asList(studentOne, studentTwo, studentThree);
    /**
     * classRoom reference
     * and StudentOperation
     * Object created.
     */
    private static ClassRoom classRoom;
    private StudentOperation studentOperations = new StudentOperation();

    /**
     * classRoom Instance initialization.
     */
    @BeforeClass
    public  static final void setUp() {
        classRoom = new ClassRoom(101, Optional.of(studentsList));
    }

    /**
     * all test cases of classRoom.
     */

    @Test
    public void testStudentsWithNoSubjects() {
        List<Student> actualResult = studentOperations.getStudentsWithNoSubjects(Arrays.asList(classRoom));;
        List<Student> expectedResult = Arrays.asList(studentTwo, studentThree);
        assertEquals("students associated with a room that have no subjects associated.",
                actualResult, expectedResult);
    }

    @Test
    public void testSubjectForStudents() {
        List<List<String>> actualResult = studentOperations.getSubjectForStudents(101, Arrays.asList(classRoom));
        List<List<String>> expectedResult = Arrays.asList(Arrays.asList("chemistry", "history", "economics"));
        assertEquals("students associated with a room that have no subjects associated.", actualResult, expectedResult);
    }

    @Test
    public void testSayHello() {
        List<String> actualResult = studentOperations.sayHello(Arrays.asList(classRoom));
        List<String> expectedResult = Arrays.asList("class room id: 101 Hello Student");
        assertEquals("students associated with a room that have no subjects associated.", actualResult, expectedResult);
    }

}
