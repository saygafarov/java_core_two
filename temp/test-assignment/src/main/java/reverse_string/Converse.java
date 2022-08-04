package reverse_string;

public class Converse {
    public static String lineReversalAndTiming(String line) {
        String reverse = reverse(line);
        long thousandTime = definitionOfTime(line, 1000);
        long tenThousandTimes = definitionOfTime(line, 10_000);
        long hundredThousandTimes = definitionOfTime(line, 100_000);
        return String.format("String: %s%n" +
                        "Reverse string: %s%n" +
                        "Thousand time: %d milliseconds%n" +
                        "Ten thousand times: %d milliseconds%n" +
                        "Hundred thousand time: %d milliseconds%n",
                line, reverse, thousandTime, tenThousandTimes, hundredThousandTimes);
    }

    private static String reverse(String line) {
        return new StringBuilder(line).reverse().toString();
    }

    private static long definitionOfTime(String line, int time) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            reverse(line);
        }
        return System.currentTimeMillis() - start;
    }

}
