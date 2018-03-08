package edu.knoldus.operation;

import edu.knoldus.model.ClassRoom;
import edu.knoldus.model.Student;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.List;

/**
 * class defining
 * students operations.
 */
public class StudentOperation {

    /**
     * predicate checks
     * student has subject
     * or not.
     */
    private Predicate<Student> isStudentWithSubject = student -> student.getSubjectList().isPresent();

    /**
     * predicate checks
     * class room has students
     * or not.
     */
    private Predicate<ClassRoom> isClassRoomWithStudents = classRoom -> classRoom.getStudentList().isPresent();

    /**
     * @param classRoomList takes all classrooms.
     * @return students with no subjects.
     */
    public List<Student> getStudentsWithNoSubjects(List<ClassRoom> classRoomList) {
        List<Student> studentWithNoSubjectList = classRoomList.stream()
                .filter(isClassRoomWithStudents)
                .flatMap(studentClassroom -> studentClassroom.getStudentList().get().stream())
                .filter(student -> !(student.getSubjectList().isPresent()))
                .collect(Collectors.toList());
        return studentWithNoSubjectList;
    }

    /**
     * @param classRoomID is taken as input.
     * @param classRoomList is taken as input.
     * @return subjects for students.
     */
    public List<List<String>> getSubjectForStudents(int classRoomID, List<ClassRoom> classRoomList) {
        List<List<String>> studentSubjectList = classRoomList.stream()
                .filter(isClassRoomWithStudents).filter(classRoom -> classRoom.getRoomID() == classRoomID)
                .flatMap(studentClassroom -> studentClassroom.getStudentList().get().stream())
                .filter(isStudentWithSubject)
                .map(student -> student.getSubjectList().get())
                .collect(Collectors.toList());
        return studentSubjectList;
    }

    /**
     * @param classRoomList is taken as input.
     */
    public List<String> sayHello(List<ClassRoom> classRoomList) {
        List<String> studentsWithClassroomHello = new ArrayList<>();
        classRoomList.stream()
                .filter(isClassRoomWithStudents)
                .forEach(classRoom -> studentsWithClassroomHello
                        .add("class room id: " + classRoom.getRoomID()
                        + " Hello Student"));
        return studentsWithClassroomHello;
    }

}
