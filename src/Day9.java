import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Day9 {

    static List<Long> array = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) array.add(Long.parseLong(sc.nextLine()));


        for(int i = 25; i < array.size(); i++) {
            if(!checkValid(i)) {
                System.out.println("Invalid number is: " + array.get(i));
                System.out.println("Sum of min and max of the contiguous set is: " + findContiguousSet(i));
                break;
            }
        }
    }

    static boolean checkValid(int index) {
        for(int i = index - 25; i < index - 1; i++) {
            for(int j = i + 1; j < index; j++) {
                if(array.get(i) + array.get(j) == array.get(index)) return true;
            }
        }
        return false;
    }
    static long findContiguousSet(int index) {
        int start = 0, end = 1;
        long sum = array.get(0);
        long number = array.get(index);

        while(true) {
            if (start >= end) end++;
            if (sum == number) {
                return array.subList(start, end).stream().min(Comparator.naturalOrder()).get() +
                        array.subList(start, end).stream().max(Comparator.naturalOrder()).get();
            }
            if (sum > number) sum -= array.get(start++);
            else sum += array.get(end++);
        }
    }
}
