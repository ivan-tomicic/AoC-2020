import java.util.*;

public class Day17_part1 {

    static char[][][] space;
    static int z = 15;
    static int y = 22;
    static int x = 22;

    public static void main(String[] args) {
        space = new char[z][y][x];
        Scanner sc = new Scanner(System.in);

        for(int j = 0; j < 8; j++) {
            char[] row = sc.nextLine().toCharArray();
            for(int i = 0; i < 8; i++) {
                space[z/2][j+7][i+7] = row[i];
            }
        }

        //do 6 cycles
        for(int cycle = 0; cycle < 6; cycle++) {
            char[][][] tempSpace = new char[z][y][x];
            //for every point in 3d space
            for(int k = 1; k < z-1; k++) {
                for(int j = 1; j < y-1; j++) {
                    for(int i = 1; i < x-1; i++) {
                        int activeNeighbors = calculateNeighbors(k,j,i);
                        if (space[k][j][i] == '#') {
                            if(activeNeighbors == 2 || activeNeighbors == 3) tempSpace[k][j][i] = '#';
                            else tempSpace[k][j][i] = '.';
                        } else {
                            if(activeNeighbors == 3) tempSpace[k][j][i] = '#';
                            else tempSpace[k][j][i] = '.';
                        }
                    }
                }
            }
            space = tempSpace.clone();
        }

        System.out.println("Active states after 6 cycles: " + calculateActiveStates());

    }

    static int calculateNeighbors(int zz, int yy, int xx){
        int activeNeighbors = 0;
        for(int k = zz-1; k < zz+2; k++) {
            for(int j = yy-1; j < yy+2; j++) {
                for(int i = xx-1; i < xx+2; i++) {
                    if(!(xx == i && yy == j && zz == k)) {
                        if(space[k][j][i] == '#') {
                            activeNeighbors++;
                        }
                    }
                }
            }
        }
        return activeNeighbors;

    }

    static int calculateActiveStates() {
        int activeStates = 0;
        for(int k = 0; k < z; k++) {
            for(int j = 0; j < y; j++) {
                for(int i = 0; i < x; i++) {
                    if(space[k][j][i] == '#') activeStates++;
                }
            }
        }
        return activeStates;
    }
}
