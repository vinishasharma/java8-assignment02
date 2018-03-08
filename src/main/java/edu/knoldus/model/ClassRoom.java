package edu.knoldus.model;

import java.util.List;
import java.util.Optional;

/**
 * class for classroom properties.
 */
public class ClassRoom {
    int roomID;
    Optional<List<Student>> studentList;

    /**
     * @param roomIDValue is for room ID.
     * @param studentListValue is for Student List.
     */
    public ClassRoom(int roomIDValue, Optional<List<Student>> studentListValue) {
        this.roomID = roomIDValue;
        this.studentList = studentListValue;
    }

    /**
     * @return student list.
     */
    public Optional<List<Student>> getStudentList() {
        return studentList;
    }

    /**
     * @return room ID.
     */
    public int getRoomID() {
        return roomID;
    }

}
