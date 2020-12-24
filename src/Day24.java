import java.util.*;

public class Day24 {

    static final int Xmax = 300;
    static final int Ymax = 300;

    static List<String> directions = Arrays.asList("e","w","se","sw","ne","nw");

    //tiles[x][y] -> x = NW-SE coordinate, y = NE-SW coordinate
    //the reference tile is at coordinate 150,150
    //\    /
    // \  /      above
    //__\/__________________
    //  /\        below
    // /  \
    ///    \
    // blackTile = true, white = false (at the start all tiles are white)
    static boolean[][] tiles = new boolean[Xmax][Ymax];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //part 1
        while(sc.hasNextLine()) {
            int x = 150, y = 150;
            String input = sc.nextLine();
            for(int i = 0; i < input.length(); i++) {
                String direction = String.valueOf(input.charAt(i));
                if(!directions.contains(direction)) {
                    direction += String.valueOf(input.charAt(++i));
                }
                if(direction.equals("e")) {
                    y++;
                    x--;
                } else if(direction.equals("w")) {
                    x++;
                    y--;
                } else if(direction.equals("nw")) x++;
                else if(direction.equals("se")) x--;
                else if(direction.equals("ne")) y++;
                else if(direction.equals("sw")) y--;
            }
            tiles[x][y] = !tiles[x][y];
        }

        System.out.println("Number of black tiles after flipping: " + printNumberOfBlackTiles());


        //part 2
        for(int day = 1; day <= 100; day++) {
            boolean[][] tempTiles = new boolean[Xmax][Ymax];
            for(int i = 1; i < Xmax - 1; i++) {
                for(int j = 1; j < Ymax - 1; j++) {
                    int adjacentBlackTiles = countAdjacentBlackTiles(i, j);
                    if(tiles[i][j]) {
                        if(adjacentBlackTiles == 0 || adjacentBlackTiles > 2) tempTiles[i][j] = false;
                        else tempTiles[i][j] = true;
                    } else {
                        if(adjacentBlackTiles == 2) tempTiles[i][j] = true;
                        else tempTiles[i][j] = false;
                    }
                }
            }
            tiles = tempTiles.clone();
        }

        System.out.println("Number of black tiles after 100 days: " + printNumberOfBlackTiles());
    }

    static int countAdjacentBlackTiles(int i, int j) {
        int adjacentBlackTiles = 0;
        adjacentBlackTiles += tiles[i + 1][j] ? 1 : 0;
        adjacentBlackTiles += tiles[i - 1][j] ? 1 : 0;
        adjacentBlackTiles += tiles[i][j + 1] ? 1 : 0;
        adjacentBlackTiles += tiles[i][j - 1] ? 1 : 0;
        adjacentBlackTiles += tiles[i + 1][j - 1] ? 1 : 0;
        adjacentBlackTiles += tiles[i - 1][j + 1] ? 1 : 0;

        return adjacentBlackTiles;
    }


    static int printNumberOfBlackTiles() {
        int blackTiles = 0;
        for(int i = 0; i < Xmax; i++) {
            for(int j = 0; j < Ymax; j++) {
                if(tiles[i][j]) blackTiles++;
            }
        }
        return blackTiles;
    }
}
