import java.util.ArrayList;
import java.util.List;

public class Day3 {


    public static void main(String[] args) {
        MyScanner sc = new MyScanner();

        List<String> map = new ArrayList<>();

        while(true) {
            String s = sc.nextLine().strip();
            if(s == "") break;
            map.add(s);
        }

        int columns = map.get(0).length();
        int rows = map.size();

        long result = 1;

        int[][] slopes = {{1,1}, {3,1}, {5,1}, {7,1}, {1,2}};

        for(int i = 0; i < slopes.length; i++) {
            int right = slopes[i][0];
            int treeCounter = 0;
            for(int down = slopes[i][1]; down < rows; down += slopes[i][1]) {
                if(map.get(down).charAt(right) == '#') {
                    treeCounter++;
                }
                System.out.println(right + "    " + down);
                right = (right + slopes[i][0]) % columns;


            }
            result *= treeCounter;
            System.out.println("Trees encountered for slope {" + slopes[i][0] + ", " + slopes[i][1] + "}" + ": " + treeCounter);
        }
        System.out.println("Result: " + result);

    }
}
