import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) {

        Map<Integer, Integer> seats = new LinkedHashMap<>();
        int max = 0;
        Scanner sc = new Scanner(System.in);
        while(true) {
            int shift = 0, seat = 0, seatID;

            String s = sc.nextLine().strip();
            if(s == "") break;
            for(int i = 9; i >= 0; i--) {
                if(s.charAt(i) == 'R' || s.charAt(i) == 'B') seat += 1 << shift;
                shift++;
            }
            seatID = ((seat & 1016) >> 3) * 8 + (seat & 7);
            max = Math.max(max, seatID);
            seats.put(seatID, seat);

        }
        System.out.println("Max Seat ID: " + max);
        for(int i = 1; i < 127; i++) {
            for(int j = 0; j < 8; j++) {
                int seatID = i * 8 + j;
                if(!seats.containsKey(seatID) && seats.containsKey(seatID - 1) && seats.containsKey(seatID + 1)) {
                    System.out.println("My seat: " + seatID);
                }
            }
        }
    }
}
