package homework;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }

    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };

    public static void main(String[] args) {
        System.out.println("Raw data:\n");

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println("\n**************************************************\n");
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:\n");

        Map<String, Long> processedData = processedData(RAW_DATA);
        for (Map.Entry<String, Long> entry : processedData.entrySet()) {
            System.out.println("Key: " + entry.getKey());
            System.out.println("Value: " + entry.getValue());
        }

        System.out.println("\n**************************************************\n");
        int [] arr = new int[]{3, 4, 2, 7};
        int sum = 10;
        int[] pair = searchPair(arr, sum);
        System.out.println("Search for the first pair of elements certain sum. \n" +
                Arrays.toString(arr) + ", " + sum + " -> " + Arrays.toString(pair));

        System.out.println("\n**************************************************\n");
        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel"));
        System.out.println(fuzzySearch("cwhl", "cartwheel"));
        System.out.println(fuzzySearch("cwhee", "cartwheel"));
        System.out.println(fuzzySearch("cartwheel", "cartwheel"));
        System.out.println(fuzzySearch("cwheeel", "cartwheel"));
        System.out.println(fuzzySearch("lw", "cartwheel"));

        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться
                Key:Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */



        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */



        /*
        Task3
            Реализовать функцию нечеткого поиска
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */
    }

    private static boolean fuzzySearch(String regex, String text) {
        var count = 0;
        var lengthRegex = regex.length();
        var lengthText = text.length();
        var charRegex = regex.toCharArray();
        var charText = text.toCharArray();

        if (lengthRegex > lengthText) {
            return false;
        }
        for (var ch : charText) {
            if (charRegex[count] == ch) {
                count++;
                if (count == lengthRegex) {
                    break;
                }
            }
        }
        return count >= lengthRegex;
    }

    private static int[] searchPair(int[] arr, int sum) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                int value = map.get(arr[i]);
                return new int[]{arr[value], arr[i]};
            } else {
                map.put(sum - arr[i], i);
            }
        }
        return new int[2];
    }

    private static Map<String, Long> processedData(Person[] rawData) {
        return Arrays
                .stream(rawData)
                .filter(Objects::nonNull)
                .distinct()
                .sorted(Comparator.comparing(Person::getId))
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()));
    }
}

