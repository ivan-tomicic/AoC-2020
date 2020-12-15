import java.lang.reflect.Array;
import java.util.*;

public class Day15 {

    static Map<Integer, List<Integer>> numbers;

    public static void main(String[] args) {

        numbers = new LinkedHashMap<>();
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(",");
        for(int i = 0; i < input.length; i++) {
            numbers.put(Integer.parseInt(input[i]), Arrays.asList(i, i));
        }
        //for part1 splution replace 30000000 in for loop with 2020
        //takes some time to run :/
        int lastNumber = Integer.parseInt(input[input.length - 1]);
        List<Integer> value;
        for(int j = input.length; j < 30000000; j++) {
            int key = numbers.get(lastNumber).get(0) - numbers.get(lastNumber).get(1);
            if(numbers.containsKey(key)) value = Arrays.asList(j, numbers.get(key).get(0));
            else value = Arrays.asList(j, j);
            numbers.put(key, value);
            lastNumber = key;
        }
        System.out.println(lastNumber);

    }


}
