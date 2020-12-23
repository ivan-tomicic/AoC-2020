import java.util.*;

public class Day23 {

    static LinkedList<Integer> cups = new LinkedList<>();

    public static void main(String[] args) {


        //part 1;
        cups.addAll(Arrays.asList(5, 8, 6, 4, 3, 9, 1, 7, 2));
        playGame(9, 100);
        System.out.print("Part 1 solution: ");
        for (int i = cups.indexOf(1) + 1; i < 7 + cups.get(1); i++) System.out.print(cups.get(i % 9));


        cups.removeAll(cups);
        cups.addAll(Arrays.asList(5, 8, 6, 4, 3, 9, 1, 7, 2));
        for (int i = 10; i <= 1000000; i++) cups.add(i);
        playGame(1000000, 10000000);
        System.out.print("\nPart 2 splution: ");
        System.out.print(cups.get(cups.indexOf(1) + 1)* cups.get(cups.indexOf(1) + 2));

    }

    static void playGame(int size, int moves) {
        int currentCupIndex = 0;
        int currentCup = cups.get(currentCupIndex);

        for (int i = 0; i < moves; i++) {
            if(i%10000 == 0) System.out.println(i);
            Integer removeCup3 = cups.get((currentCupIndex + 3) % size);
            Integer removeCup2 = cups.get((currentCupIndex + 2) % size);
            Integer removeCup1 = cups.get((currentCupIndex + 1) % size);

            cups.remove(removeCup1);
            cups.remove(removeCup2);
            cups.remove(removeCup3);

            int destination = currentCup - 1;
            while (!cups.contains(destination) && destination > 0) destination--;
            if (destination == 0) destination = cups.stream().max(Comparator.naturalOrder()).get();

            int destionationIndex = cups.indexOf(destination);
            cups.add((destionationIndex + 1)%size, removeCup1);
            cups.add((destionationIndex + 2)%size, removeCup2);
            cups.add((destionationIndex + 3)%size, removeCup3);

            currentCupIndex = (cups.indexOf(currentCup) + 1) % size;
            currentCup = cups.get(currentCupIndex);

        }
    }
}
