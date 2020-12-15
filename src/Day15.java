import java.util.*;

public class Day15 {

    static int[][] numbers;

    public static void main(String[] args) {

        numbers = new int[30000000][2];
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(",");
        for (int i = 0; i < input.length; i++) {
            numbers[Integer.parseInt(input[i])][0] = i + 1;
            numbers[Integer.parseInt(input[i])][1] = i + 1;
        }
        //for part1 splution replace 30 000 000 in for loop with 2020
        //takes about 10 seconds to run for 30 000 000:/
        int lastNumber = Integer.parseInt(input[input.length - 1]);

        for (int j = input.length; j < 30000000; j++) {
            int key = numbers[lastNumber][0] - numbers[lastNumber][1];
            if (numbers[key][1] != 0 && numbers[key][0] != 0) numbers[key][1] = numbers[key][0];
            else numbers[key][1] = j + 1;
            numbers[key][0] = j + 1;
            lastNumber = key;
        }
        System.out.println(lastNumber);
    }
}
