import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class Day233 {

    static int[] cups;

    public static void main(String[] args) {
        //part 1;
        cups = new int[10];
        fillFirstEight();
        cups[2] = 5;
        playGame(100);
        System.out.print("Part 1 solution: ");
        for(int i = cups[1], j = 0; j < 8; j++) {
            System.out.print(i);
            i = cups[i];
        }

        //part 2
        cups = new int[1000001];
        fillFirstEight();
        cups[2] = 10;
        for (int i = 10; i < 1000000; i++) {
            cups[i] = i+1;
        }
        cups[1000000] = 5;

        playGame(10000000);
        System.out.print("\nPart 2 solution: ");
        System.out.println((long)cups[1]*(long)cups[cups[1]]);

    }

    static void playGame(int moves) {
        int currentCup= 5;

        for (int i = 0; i < moves; i++) {
            int cup1 = cups[currentCup];
            int cup2 = cups[cups[currentCup]];
            int cup3 = cups[cups[cups[currentCup]]];
            cups[currentCup] = cups[cups[cups[cups[currentCup]]]];

            int destination = currentCup - 1;
            while(destination > 0 && (destination == cup1 || destination == cup2 || destination == cup3)) destination--;
            if(destination == 0) {
                int[] copy = cups.clone();
                int finalCurrentCup = currentCup;
                destination = Arrays.stream(copy).filter(a -> a != cup1 && a!= cup2 && a!= cup3 && a!= finalCurrentCup).max().getAsInt();
            }
            int nextOfDestination = cups[destination];
            cups[destination] = cup1;
            cups[cup3] = nextOfDestination;
            currentCup = cups[currentCup];
        }
    }

    static void fillFirstEight() {
        cups[5] = 8;
        cups[8] = 6;
        cups[6] = 4;
        cups[4] = 3;
        cups[3] = 9;
        cups[9] = 1;
        cups[1] = 7;
        cups[7] = 2;
    }
}
