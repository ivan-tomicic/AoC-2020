import java.util.*;

public class Day22 {

    static LinkedList<Integer> player1 = new LinkedList<>();
    static LinkedList<Integer> player2 = new LinkedList<>();

    static LinkedList<Integer> winner;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        sc.nextLine();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.strip().equals("")) break;
            player1.add(Integer.parseInt(line));
        }

        sc.nextLine();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.strip().equals("")) break;
            player2.add(Integer.parseInt(line));
        }

        for(int i = 0; i < 2; i++) {
            LinkedList<Integer> player_1 = new LinkedList<>();
            player_1.addAll(player1);
            LinkedList<Integer> player_2 = new LinkedList<>();
            player_2.addAll(player2);
            winner = i == 0 ? (combat(player_1, player_2) ? player_1 : player_2) : (recursiveCombat(player_1, player_2) ? player_1 : player_2);
            System.out.print("Score after part " + (i+1) +": ");
            printScore(winner);
        }
    }


    //return true if player 1 won, false if player 2 won
    static boolean recursiveCombat(LinkedList<Integer> player_1, LinkedList<Integer> player_2) {
        Set<LinkedList<Integer>> roundsPlayer1 = new LinkedHashSet<>();
        Set<LinkedList<Integer>> roundsPlayer2 = new LinkedHashSet<>();
        while(player_1.size() != 0 && player_2.size() != 0) {
            if(roundsPlayer1.contains(player_1) || roundsPlayer2.contains(player_2)) return true;
            roundsPlayer1.add(player_1);
            roundsPlayer2.add(player_2);

            int card1 = player_1.poll();
            int card2 = player_2.poll();
            boolean player1_wins;
            if(player_1.size() < card1 || player_2.size() < card2) {
                if(card1 > card2) player1_wins = true;
                else player1_wins = false;

            } else {
                //just copy the elements of player_1 to a new deck of cards, but so
                //that it only contains first 'card1' elements of player_1's deck.
                //same for player_2
                LinkedList<Integer> player_1_sub = new LinkedList<>();
                LinkedList<Integer> player_2_sub = new LinkedList<>();
                player_1_sub.addAll(player_1);
                player_2_sub.addAll(player_2);
                int remove1 = player_1_sub.size() - card1;
                int remove2 = player_2_sub.size() - card2;
                for(int i = 0; i < remove1; i++) {
                    player_1_sub.pollLast();
                }
                for(int i = 0; i < remove2; i++) {
                    player_2_sub.pollLast();
                }

                player1_wins = recursiveCombat(player_1_sub, player_2_sub);
            }
            if(player1_wins) {
                player_1.add(card1);
                player_1.add(card2);
            } else {
                player_2.add(card2);
                player_2.add(card1);
            }
        }
        return player_1.size() != 0 ? true : false;
    }

    //return true if player 1 won, false if player 2 won
    static boolean combat(LinkedList<Integer> player_1, LinkedList<Integer> player_2) {
        while(player_1.size() != 0 && player_2.size() != 0) {
            int card1 = player_1.poll();
            int card2 = player_2.poll();
            if(card1 > card2) {
                player_1.add(card1);
                player_1.add(card2);
            } else {
                player_2.add(card2);
                player_2.add(card1);
            }
        }
        return player_1.size() != 0 ? true : false;
    }


    static void printScore(LinkedList<Integer> winner) {
        int score = 0;
        for(int i = winner.size(); i > 0; i--) {
            score += i*winner.poll();
        }
        System.out.println("Score is: " + score);
    }

}
