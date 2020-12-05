import java.util.*;

public class Day5 {
    public static void main(String[] args) {

        List<Integer> seats = new ArrayList<>();
        int max = 0;
        Scanner sc = new Scanner(System.in);
        while(true) {
            int shift = 0, seatID = 0;

            String s = sc.nextLine().strip();
            if(s == "") break;
            for(int i = 9; i >= 0; i--) {
                if(s.charAt(i) == 'R' || s.charAt(i) == 'B') seatID += 1 << shift;
                shift++;
            }
            max = Math.max(max, seatID);
            seats.add(seatID);

        }
        System.out.println("Max seat ID: " + max);
        for(int i = 1; i < 1024; i++) {
            if(!seats.contains(i) && seats.contains(i + 1) && seats.contains(i - 1)) {
                System.out.println("My seat ID: " + i);
                break;
            }
        }
    }
}
