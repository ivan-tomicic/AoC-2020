import java.util.*;

public class Day14 {
    static long[] numbers;
    static Set<Integer> positions = new LinkedHashSet<>();
    static Map<String, Map<Integer, String>> input;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        numbers = new long[100000];
        String mask = sc.nextLine().split("\\s+")[2];
        int position;
        String value;
        String result;

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.startsWith("mask")) {
                mask = line.split("\\s+")[2];
            } else {
                position = Integer.parseInt(line.split("\\[")[1].split("\\]")[0]);
                positions.add(position);

                StringBuilder sbValue = new StringBuilder(Long.toBinaryString(Long.parseLong(line.split("\\s+")[2])));
                int leadingZeroes = sbValue.length();
                for(int i = 0; i < 36 - leadingZeroes; i++) {
                    sbValue.insert(0, "0");
                }
                value = sbValue.toString();


                StringBuilder sbResult = new StringBuilder();
                for(int i = 35; i >= 0; i--) {
                    if(mask.charAt(i) == '0') sbResult.append("0");
                    else if(mask.charAt(i) == '1') sbResult.append("1");
                    else sbResult.append(value.charAt(i));
                }
                result = sbResult.toString();


                long changedNumber = 0;
                for(int i = 0; i < 36; i++) {
                    changedNumber += Long.parseLong(Character.toString(result.charAt(i))) << i;
                }

                numbers[position] = changedNumber;
            }
        }
        long sum = 0;
        for(int pos : positions) {
            sum += numbers[pos];
            System.out.println("pos: " + pos + " number: " + numbers[pos]);
        }
        System.out.println(sum);
    }
}
