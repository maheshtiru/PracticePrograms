package com.CoreJavaPrograms;

import com.CodingProblems.demo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTutorialCopy {

    public static void main(String args[]) {
        System.out.println("Using Java 7: ");

        // Count empty strings
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        System.out.println("List: " +strings);
        long count = getCountEmptyStringUsingJava7(strings);

        System.out.println("Empty Strings: " + count);
        count = getCountLength3UsingJava7(strings);

        System.out.println("Strings of length 3: " + count);

        //Eliminate empty string
        List<String> filtered = deleteEmptyStringsUsingJava7(strings);
        System.out.println("Filtered List: " + filtered);

        //Eliminate empty string and join using comma.
        String mergedString = getMergedStringUsingJava7(strings,", ");
        System.out.println("Merged String: " + mergedString);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        //get list of square of distinct numbers
        List<Integer> squaresList = getSquares(numbers);
        System.out.println("Squares List: " + squaresList);
        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);

        System.out.println("List: " +integers);
        System.out.println("Highest number in List : " + getMax(integers));
        System.out.println("Lowest number in List : " + getMin(integers));
        System.out.println("Sum of all numbers : " + getSum(integers));
        System.out.println("Average of all numbers : " + getAverage(integers));
        System.out.println("Random Numbers: ");

        //print ten random numbers
        Random random = new Random();

        for(int i = 0; i < 10; i++) {
            System.out.println(random.nextInt());
        }

        System.out.println("Using Java 8: ");
        System.out.println("List: " +strings);

        count = strings.stream().filter(string->string.isEmpty()).count();
        System.out.println("Empty Strings: " + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("Strings of length 3: " + count);

        filtered = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.toList());
        System.out.println("Filtered List: " + filtered);

        mergedString = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("Merged String: " + mergedString);

        squaresList = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
        System.out.println("Squares List: " + squaresList);
        System.out.println("List: " +integers);

        IntSummaryStatistics stats = integers.stream().mapToInt((x) ->x).summaryStatistics();

        System.out.println("Highest number in List : " + stats.getMax());
        System.out.println("Lowest number in List : " + stats.getMin());
        System.out.println("Sum of all numbers : " + stats.getSum());
        System.out.println("Average of all numbers : " + stats.getAverage());
        System.out.println("Random Numbers: ");

        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    private static int getCountEmptyStringUsingJava7(List<String> strings) {
        System.out.println("// Count empty strings");
        int count = 0;

        for(String string: strings) {

            if(string.isEmpty()) {
                count++;
            }
        }

        //using Java 8:
        long total = strings.stream().filter(s -> s.isEmpty()).count();
        System.out.println("Java 8: "+total);

        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings) {
        System.out.println("/// count strings of length 3: ");
        int count = 0;

        for(String string: strings) {

            if(string.length() == 3) {
                count++;
            }
        }

        long total = strings.stream().filter(s -> s.length() == 3).count();
        System.out.println("Java 8: "+total);

        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings) {
        System.out.println("/// Delete the empty strings: ");
        List<String> filteredList = new ArrayList<String>();

        for(String string: strings) {

            if(!string.isEmpty()) {
                filteredList.add(string);
            }
        }

        Collection<String> c =  strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());

        System.out.println("Java 8: "+c);

        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator) {
        System.out.println("/// Merged String: ");
        StringBuilder stringBuilder = new StringBuilder();

        for(String string: strings) {

            if(!string.isEmpty()) {
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();

        String ans = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(separator));
        System.out.println("Java 8: "+ans);

        return mergedString.substring(0, mergedString.length()-2);
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        System.out.println("/// list of squares: ");
        List<Integer> squaresList = new ArrayList<Integer>();

        for(Integer number: numbers) {
            Integer square = new Integer(number.intValue() * number.intValue());

            if(!squaresList.contains(square)) {
                squaresList.add(square);
            }
        }

        // stream
        List<Integer> ans = numbers.stream().map(i -> i*i).distinct().collect(Collectors.toList());
        System.out.println("Java 8: "+ans);

        return squaresList;
    }

    private static int getMax(List<Integer> numbers) {
        System.out.println("/// Max  value: ");
        int max = numbers.get(0);

        for(int i = 1;i < numbers.size();i++) {

            Integer number = numbers.get(i);

            if(number.intValue() > max) {
                max = number.intValue();
            }
        }

        //java 8
        int maxi = numbers.stream().mapToInt(x -> x).max().getAsInt();
        System.out.println("Java 8: "+maxi);

        return max;
    }

    private static int getMin(List<Integer> numbers) {
        System.out.println("/// Min  value: ");
        int min = numbers.get(0);

        for(int i= 1;i < numbers.size();i++) {
            Integer number = numbers.get(i);

            if(number.intValue() < min) {
                min = number.intValue();
            }
        }

        //java 8
        int mini = numbers.stream().mapToInt(x -> x).min().getAsInt();
        System.out.println("Java 8: "+mini);

        return min;
    }

    private static int getSum(List<Integer> numbers) {
        System.out.println("/// sum  value: ");
        int sum = (int)(numbers.get(0));

        for(int i = 1;i < numbers.size();i++) {
            sum += (int)numbers.get(i);
        }

        //java 8
        int tot = numbers.stream().reduce(0, (partialSum, next) -> partialSum + next);
        System.out.println("Java 8: "+tot);

        return sum;
    }

    private static int getAverage(List<Integer> numbers) {
        System.out.println("/// sum  value: ");

        //Java 8

        IntSummaryStatistics st = numbers.stream().mapToInt(i -> i).summaryStatistics();
        System.out.println("Java 8: "+st.getAverage());

        return getSum(numbers) / numbers.size();
    }
}

