package edu.knoldus.application;

import edu.knoldus.model.ClassRoom;
import edu.knoldus.model.Student;
import edu.knoldus.operation.StudentOperation;
import edu.knoldus.operation.TwitterOperation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OperationCall {
    public static void main(final String[] args) {

        List<String> subject = Arrays.asList("chemistry", "history", "economics");
        StudentOperation studentOperation = new StudentOperation();
        Student student1 = new Student("vinisha", 10, Optional.of(subject));
        Student student2 = new Student("ramesh", 12, Optional.ofNullable(null));
        Student student3 = new Student("riya", 11, Optional.ofNullable(null));
        List<Student> studentList = Arrays.asList(student1, student2, student3);
        ClassRoom classRoom1 = new ClassRoom(101, Optional.of(studentList));

        List<String> studentsWithClassroomHello = studentOperation.sayHello(Arrays.asList(classRoom1));
        System.out.println(studentsWithClassroomHello);
        List<Student> studentsWithNoSubjects = studentOperation.getStudentsWithNoSubjects(Arrays.asList(classRoom1));
        System.out.println(studentsWithNoSubjects);
        System.out.println(studentOperation.getSubjectForStudents(101, Arrays.asList(classRoom1)));

        TwitterOperation twitter = new TwitterOperation("#apple");
        twitter.getTweets()
                .thenAccept(tweets -> System.out.println("Tweets list: " + tweets));
        twitter.getNumberOfTweets()
                .thenAccept(numberOfTweets -> System.out.println("number of tweets " + numberOfTweets));
        twitter.getAverageTweetsPerDay()
                .thenAccept(averageTweets -> System.out.println("average tweets " + averageTweets));
        twitter.getAverageLikes()
                .thenAccept(averageLikes -> System.out.println("average likes " + averageLikes));
        twitter.getAverageReTweets()
                .thenAccept(averageReTweets -> System.out.println("average reTweets " + averageReTweets));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            exception.getMessage();
        }

    }
}
