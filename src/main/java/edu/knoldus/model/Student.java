package edu.knoldus.model;

import java.util.List;
import java.util.Optional;

/**
 * class for classroom properties.
 */
public class Student {
    private String name;
    private int rollNumber;
    private Optional<List<String>> subject;

    /**
     * This is a constructor for Class Student.
     * @param nameValue takes student's name.
     * @param rollNumberValue takes roll number.
     * @param subjectValue take subject list.
     */
    public Student(String nameValue, int rollNumberValue, Optional<List<String>> subjectValue) {
        this.name = nameValue;
        this.rollNumber = rollNumberValue;
        this.subject = subjectValue;
    }

    /**
     * @return return student subject list.
     */
    public Optional<List<String>> getSubjectList() {
        return subject;
    }

    @Override
    public String toString() {
        return "student name: " + name
                + " roll number: " + rollNumber + " subject list:  " + subject;
    }
}
