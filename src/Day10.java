import java.util.*;

public class Day10 {

    static List<Integer> input = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        input.add(0);
        while(sc.hasNextLine()) {
            input.add(Integer.parseInt(sc.nextLine()));
        }
        input.sort(Comparator.naturalOrder());
        input.add(input.get(input.size() - 1) + 3);

        int diff1 = 0, diff3 = 0;
        for(int i = 1; i < input.size(); i++) {
            if(input.get(i) - input.get(i - 1) > 3) break;
            if(input.get(i) - input.get(i - 1) == 3) diff3++;
            else if(input.get(i) - input.get(i - 1) == 1) diff1++;
        }
        System.out.println(diff3*diff1);


        //part 2
        //works in O(n!)  :(
        long waysToConnect = 1;

        List<Integer> tempList = new ArrayList<>();
        tempList.add(input.get(0));

        for(int i = 1; i < input.size(); i++) {
           if (input.get(i) - tempList.get(tempList.size() - 1) < 3) {
               tempList.add(input.get(i));
           } else {
               LinkedHashSet<List<Integer>> setOfLists = new LinkedHashSet<>();

               count(tempList, setOfLists);
               waysToConnect *= setOfLists.size();

               tempList = new ArrayList<>();
               tempList.add(input.get(i));
           }
        }
        System.out.println(waysToConnect);
    }

    static void count(List<Integer> list, LinkedHashSet<List<Integer>> setOfLists) {
        setOfLists.add(list);
        for(int i = 1; i < list.size() - 1; i++) {
            if(list.get(i + 1) - list.get(i - 1) <= 3) {
                List<Integer> temp = new ArrayList<>(list);
                temp.remove(i);
                setOfLists.add(temp);
                count(temp, setOfLists);
            }
        }
    }
}
