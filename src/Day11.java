import java.util.*;

public class Day11 {
    static ArrayList<ArrayList<Character>> input;
    static char[][] seats;
    static char[][] nextSeats;
    static int rows;
    static int columns;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        input = new ArrayList<>();
        while(sc.hasNextLine()) {
            ArrayList<Character> row = new ArrayList<>();
            String s = sc.nextLine();
            for(Character seat : s.toCharArray()) {
                row.add(seat);
            }
            input.add(row);
        }

        rows = input.size() + 2;
        columns = input.get(0).size() + 2;

        //part 1
        runSimulation(4);

        //part 2
        runSimulation(5);

    }

    static int getOccupied(int i, int j) {
        int occupied = 0;
        if(seats[i-1][j-1] == '#') occupied++;
        if(seats[i-1][j] == '#') occupied++;
        if(seats[i-1][j+1] == '#') occupied++;
        if(seats[i][j-1] == '#') occupied++;
        if(seats[i][j+1] == '#') occupied++;
        if(seats[i+1][j-1] == '#') occupied++;
        if(seats[i+1][j] == '#') occupied++;
        if(seats[i+1][j+1] == '#') occupied++;
        return occupied;
    }

    static int getOccupiedDiagonal(int i, int j) {
        int occupied = 0;

        for(int a = i - 1, b = j - 1; a >= 0 && b >= 0;) {
            if(seats[a][b] == '#') {
                occupied++;
                break;
            } else if(seats[a][b] == 'L') break;
            b--;
            a--;
        }

        for(int a = i - 1, b = j; a >= 0;) {
            if(seats[a][b] == '#') {
                occupied++;
                break;
            } else if(seats[a][b] == 'L') break;
            a--;
        }

        for(int a = i - 1, b = j + 1; a >= 0 && b < columns;) {
            if(seats[a][b] == '#') {
                occupied++;
                break;
            } else if(seats[a][b] == 'L') break;
            b++;
            a--;
        }

        for(int a = i, b = j - 1; b >= 0;) {
            if(seats[a][b] == '#') {
                occupied++;
                break;
            } else if(seats[a][b] == 'L') break;
            b--;
        }

        for(int a = i, b = j + 1; b < columns;) {
            if(seats[a][b] == '#') {
                occupied++;
                break;
            } else if(seats[a][b] == 'L') break;
            b++;
        }

        for(int a = i + 1, b = j - 1; a < rows && b >= 0;) {
            if(seats[a][b] == '#') {
                occupied++;
                break;
            } else if(seats[a][b] == 'L') break;
            b--;
            a++;
        }

        for(int a = i + 1, b = j; a < rows;) {
            if(seats[a][b] == '#') {
                occupied++;
                break;
            } else if(seats[a][b] == 'L') break;
            a++;
        }

        for(int a = i + 1, b = j + 1; b < columns && a < rows;) {
            if(seats[a][b] == '#') {
                occupied++;
                break;
            } else if(seats[a][b] == 'L') break;
            b++;
            a++;
        }
        return occupied;
    }

    static void runSimulation(int maxOccupied) {
        seats = new char[rows][columns];
        for(int i = 0; i < rows - 2; i++) {
            for(int j = 0; j < columns - 2; j++) {
                seats[i+1][j+1] = input.get(i).get(j);
            }
        }


        nextSeats= new char[rows][columns];
        while(true) {
            for(int i = 1; i < rows - 1; i++) {
                for(int j = 1; j < columns - 1; j++) {
                    int occupied;
                    if(maxOccupied == 4) occupied = getOccupied(i, j);
                    else occupied = getOccupiedDiagonal(i, j);
                    if(seats[i][j] == 'L') {
                        if(occupied == 0) nextSeats[i][j] = '#';
                    } else if(seats[i][j] == '#') {
                        if(occupied >= maxOccupied) nextSeats[i][j] = 'L';
                    } else{
                        nextSeats[i][j] = '.';
                    }
                }
            }

            boolean equal = true;
            for(int i = 1; i < rows - 1; i++) {
                for (int j = 1; j < columns - 1; j++) {
                    if(nextSeats[i][j] != seats[i][j]) {
                        equal = false;
                        seats[i][j] = nextSeats[i][j];
                    }
                }
            }
            if(equal) break;
        }

        int result = 0;
        for(int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                if(seats[i][j] == '#') {
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
