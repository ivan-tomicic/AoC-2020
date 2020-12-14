import java.util.*;

public class Day12 {
    static int north = 0;
    static int east = 0;

    static int wayPointEast = 10;
    static int wayPointNorth = 1;


    static int direction = 0;



    //east = 0, north = 1, west = 2, south = 3

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> instructions = new ArrayList<>();

        while(sc.hasNextLine()) {
            String s = sc.nextLine();
            int value = Integer.parseInt(s.split("[a-zA-Z]")[1]);
            instructions.add(s);
            switch(s.charAt(0)) {
                case 'S':
                    north -= value;
                    break;
                case 'N':
                    north += value;
                    break;
                case 'W':
                    east -= value;
                    break;
                case 'E':
                    east += value;
                    break;
                case 'F':
                    if(direction == 0) east += value;
                    if(direction == 1) north += value;
                    if(direction == 2) east -= value;
                    if(direction == 3) north -= value;
                    break;
                case 'L':
                    direction = (direction + (value/90))%4;
                    if(direction < 0) direction += 4;
                    break;
                case 'R':
                    direction = (direction - (value/90))%4;
                    if(direction < 0) direction += 4;
                    break;
            }

        }
        System.out.println("Manhattan distance (part1): " + (Math.abs(north) + Math.abs(east)));


        //part2
        north = 0; east = 0;
        for(String s : instructions) {
            int value = Integer.parseInt(s.split("[a-zA-Z]")[1]);
            char letter = s.charAt(0);
            int E, N;
            switch(letter) {
                case 'S':
                    wayPointNorth -= value;
                    break;
                case 'N':
                    wayPointNorth += value;
                    break;
                case 'W':
                    wayPointEast -= value;
                    break;
                case 'E':
                    wayPointEast += value;
                    break;
                case 'F':
                    east += wayPointEast*value;
                    north += wayPointNorth*value;
                    break;
                case 'L':
                    E = wayPointEast;
                    N = wayPointNorth;
                    if(value == 90) {
                        wayPointNorth = E;
                        wayPointEast = -1*N;
                    }

                    else if(value == 180) {
                        wayPointNorth = -1*N;
                        wayPointEast = -1*E;
                    } else {
                        wayPointNorth = -1*E;
                        wayPointEast = N;
                    }
                    break;
                case 'R':
                    E = wayPointEast;
                    N = wayPointNorth;
                    if(value == 90) {
                        wayPointNorth = -1*E;
                        wayPointEast = N;
                    }

                    else if(value == 180) {
                        wayPointNorth = -1*N;
                        wayPointEast = -1*E;
                    } else {
                        wayPointNorth = E;
                        wayPointEast = -1*N;
                    }
                    break;
            }
        }

        System.out.println("Manhattan distance (part1): " + (Math.abs(north) + Math.abs(east)));


    }
}
