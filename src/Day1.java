import java.io.*;
import java.util.*;

public class Day1 {

    public static void main(String[] args) {

        List<Integer> input = new ArrayList<>();

        MyScanner sc = new MyScanner();

        while (true) {
            try{
                input.add(sc.nextInt());
            } catch (NumberFormatException | NullPointerException exception) {
                break;
            }
        }
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                if (input.get(i) + input.get(j) == 2020) {
                    System.out.println("Multiplication of 2 numbers: " + input.get(i) * input.get(j));
                }
                for (int z = j + 1; z < input.size(); z++) {
                    if (input.get(i) + input.get(j) + input.get(z) == 2020) {
                        System.out.println("Multiplication of 3 numbers: " + input.get(i) * input.get(j) * input.get(z));
                    }
                }
            }
        }
    }
}
