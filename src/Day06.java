import java.util.*;
import java.util.stream.Collectors;

public class Day06 {
    public static void main(String[] args) {
        String alphabet = "abcdefghijklmnoprstuvzxyqw";
        int unionSum = 0, intersectionSum = 0;

        Scanner sc = new Scanner(System.in);
        Set<Character> unionSet = new LinkedHashSet<>();

        Set<Character> intersectionSet = alphabet.chars().mapToObj(e -> (char)e).collect(Collectors.toSet());

        while(sc.hasNextLine()) {
            String s = sc.nextLine().strip();
            if(s == "") {
                unionSum += unionSet.size();
                unionSet = new TreeSet<>();

                intersectionSum += intersectionSet.size();
                intersectionSet = alphabet.chars().mapToObj(e -> (char)e).collect(Collectors.toSet());

            } else {
                List<Character> tempList = new ArrayList<>();
                for(Character answer : s.toCharArray()) {
                    unionSet.add(answer);
                    tempList.add(answer);
                }
                intersectionSet.retainAll(tempList);
            }
        }
        System.out.println("Sum of all group's yes answers (part1): " + unionSum);
        System.out.println("Sum of all group's yes answers (part2): " + intersectionSum);
    }
}
