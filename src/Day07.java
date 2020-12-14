import java.util.*;

public class Day07 {

    static Map<String, Map<String, Integer>> bagsMap;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        bagsMap = new LinkedHashMap<>();

        while(sc.hasNextLine()) {
            String[] inputLine = sc.nextLine().strip().split(",");
            String bag = inputLine[0].split("\\s+")[0] + " " + inputLine[0].split("\\s+")[1];
            Map<String, Integer> containedBags = new LinkedHashMap<>();

            if(!inputLine[0].split("\\s+")[4].equals("no")) {
                containedBags.put(inputLine[0].split("\\s+")[5] + " " + inputLine[0].split("\\s+")[6], Integer.parseInt(inputLine[0].split("\\s+")[4]));
            }

            for(int i = 1; i < inputLine.length; i++) {
                if(!inputLine[i].split("\\s+")[1].equals("no")) {
                    containedBags.put(inputLine[i].split("\\s+")[2] + " " + inputLine[i].split("\\s+")[3], Integer.parseInt(inputLine[i].split("\\s+")[1]));
                }
            }
            bagsMap.put(bag, containedBags);
        }
        System.out.println(bagsMap);
        Set<String> bagsThatContain = new LinkedHashSet<>();

        getBagsThatContain("shiny gold", bagsThatContain);
        System.out.println("Number of bags that contain shiny gold: " + bagsThatContain.size());

        System.out.println("Shiny gold bag contains: " + getNumberOfContainedBags("shiny gold") + " bags");

    }

    private static void getBagsThatContain(String bag, Set<String> bagsThatContain) {
        List<String> keys = new ArrayList<>();
        for(String key : bagsMap.keySet()) {
            for(String value : bagsMap.get(key).keySet()) {
                if(value.equals(bag)) {
                    bagsThatContain.add(key);
                    keys.add(key);
                }
            }
        }
        for(String s : keys) {
            getBagsThatContain(s, bagsThatContain);
        }
    }

    private static int getNumberOfContainedBags(String bag) {
        int number = 0;
        Map<String, Integer> containedBags = bagsMap.get(bag);
        for(String containedBag : containedBags.keySet()) {
            number += containedBags.get(containedBag)*(1 + getNumberOfContainedBags(containedBag));
        }
        return number;
    }
}
