import java.util.*;

public class Day13 {
    static int timestamp;
    static long timestamp2;
    static List<Integer> busIDs = new ArrayList<>();
    static List<String> SecondLine;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        timestamp = Integer.parseInt(sc.nextLine());
        SecondLine = Arrays.asList(sc.nextLine().split(",").clone());
        for(String s : SecondLine) {
            if(!s.equals("x")) busIDs.add(Integer.parseInt(s));
        }
        System.out.println(busIDs + "    " + timestamp);

        boolean found = false;
        for(int i = timestamp; !found; i++) {
            for(int bus : busIDs) {
                if(i%bus == 0) {
                    System.out.println((i - timestamp)*bus);
                    found = true;
                }

            }
        }

        for(long k = 0; k < Long.MAX_VALUE; k +=419471) {
            if(check(k - 48)) {
                System.out.println(k-48);
            }
        }
    }
    public static boolean check(long i) {
        for(int bus : busIDs) {
            if((i + SecondLine.indexOf(String.valueOf(bus))) % bus != 0) {
                return false;
            }
        }
        return true;
    }
}
