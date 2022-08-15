package com.CoreJavaPrograms;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String... args){

        List<Exam> scores = Arrays.asList(
                new Exam("001", "Maths", 35.5, 1),
                new Exam("003", "English", 77.5, 3),
                new Exam("002", "Maths", 75.5, 1),
                new Exam("003", "Maths", 55.5, 3),
                new Exam("004", "Maths", 60.5, 2),
                new Exam("001", "Chemistry", 85.5, 1),
                new Exam("002", "Chemistry", 22.5, 1),
                new Exam("004", "Chemistry", 85.5, 2),
                new Exam("005", "Chemistry", 19.5, 3),
                new Exam("006", "Chemistry", 55.5, 2),
                new Exam("001", "English", 81.5, 1),
                new Exam("002", "English", 91.5, 1),
                new Exam("004", "English", 69.5, 2),
                new Exam("005", "English", 45.5, 3),
                new Exam("008", "English", 43.5, 1),
                new Exam("003", "Physics", 71.5, 3),
                new Exam("004", "Physics", 52.5, 2),
                new Exam("006", "Physics", 42.5, 2),
                new Exam("003", "French", 38.5, 3),
                new Exam("001", "Physics", 91.5, 1),
                new Exam("007", "French", 39.5, 1),
                new Exam("003", "Economics", 44.5, 3),
                new Exam("004", "Economics", 98.5, 2));

        //Intermediate functions: filter(), map(),sorted(), flatMap(), distinct(), limit(), skip()
        //Terminal: max(),forEach(), min(), toArray(),     reduce(), collect(), count(), anyMatch(), allMatch(), noneMatch(), findFirst(), findAny()

        // ************************************************ // [filter(), map()], [max()]
        // Get first record with highest marks in Chemistry :
        // 1. use filter() to filter out chemistry records
        // 2. use map() to transform stream to steam of marks(Exam object -> double)
        // 3. use max() terminal function to get max marks
        Exam ans1 = scores.stream()
                .filter(x -> x.subject.equalsIgnoreCase("chemistry"))
                .max((x1, x2) -> {return (int)Math.ceil(x1.marks - x2.marks);})
                .get();

        System.out.println("\n***Chemistry top marks-> "+ ans1);

        // ************************************************ // [sorted(), map()], [forEach()]
        // sort according to subject and marks in each subject
        // Use: comparator1.thenComparing(comparator2)
        System.out.println("\n***sort according to subject and then marks ->");

        Comparator<Exam> c1 = (x1, x2) -> x1.subject.compareTo(x2.subject);
        Comparator<Exam> c2 = (x1, x2) -> (int)(x1.marks - x2.marks);
        scores.stream()
                .sorted(c1.thenComparing(c2))
                .map(x -> x.subject+":"+x.marks+", ")
                .forEach(System.out::print);

        // ************************************************ // [sorted(), map(), filter()], [forEach(), collect()]
        // sort according to subject and print least mark record in each subject
        // 1. sort like above and collect all values to a map using a merge function which retains old value of it sees duplicates and then stream all values and sort again!
        // 2. Build a predicate which maintains state and use that predicate in filter method
        System.out.println("\n\n***sort according to subject and get least marks per subject ->");
        System.out.println("Method 1 ->");
        scores.stream()
                .sorted(c1.thenComparing(c2))
                .collect(Collectors.toMap(x -> x.subject, x -> x, (oldKey, newKey) -> (oldKey)))
                .values()
                .stream()
                .sorted(c1)
                .map(x -> x.subject+":"+x.marks+", ")
                .forEach(System.out::print);

        System.out.println("\nMethod 2 ->");
        scores.stream()
                .sorted(c1.thenComparing(c2))
                .filter(statePredicate::test)
                .map(x -> x.subject+":"+x.marks+", ")
                .forEach(System.out::print);

        // ************************************************ // [reduce(), map(), filter()]
        System.out.println("\n \n***Sum, average ->");

        double sum1 = scores.stream()
                .filter(x -> x.subject.equalsIgnoreCase("chemistry"))
                .map(x -> x.marks)
                .reduce(0.0, (prev, curr) -> prev + curr);

        System.out.println("\nSum of chemistry marks-> "+sum1);

        DoubleSummaryStatistics dsChemistry = scores.stream()
                .filter(x -> x.subject.equalsIgnoreCase("chemistry"))
                .mapToDouble(x -> x.marks)
                .summaryStatistics();

        System.out.println("\nchemistry marks summary stats-> \n"
                +"sum: "+ dsChemistry.getSum()+"\n"
                +"avg: "+ dsChemistry.getAverage()+"\n"
                +"max: "+ dsChemistry.getMax()+"\n"
                +"min: "+ dsChemistry.getMin()+"\n"
                +"count: "+ dsChemistry.getCount());

    }

    //stateful predicate used to see if given element occured before based on a condition
    static Predicate<Exam> statePredicate = new Predicate<Exam>() {
        HashSet<String> set = new HashSet<>();

        @Override
        public boolean test(Exam exam) {
            if(set.contains(exam.subject)) return false;

            set.add(exam.subject);
            return true;
        }
    };

    static class Exam {
        public String studentId;
        public String subject;
        public double marks;
        public int year;
        public Exam(String studentId, String subject, double marks, int year) {
            this.studentId = studentId;
            this.subject = subject;
            this.marks = marks;
            this.year = year;
        }

        @Override
        public String toString() {
            return "Exam{" +
                    "studentId='" + studentId + '\'' +
                    ", subject='" + subject + '\'' +
                    ", marks=" + marks +
                    ", year=" + year +
                    '}';
        }
    }
}


