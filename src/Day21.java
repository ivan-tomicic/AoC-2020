import java.util.*;

public class Day21 {

    static Map<String, ArrayList<Set<String>>> allergens = new LinkedHashMap<>();
    static List<String> allIngredients = new ArrayList<>();
    static Set<String> ingredientsWithAllergens = new LinkedHashSet<>();

    static Map<String, Set<String>> possibleAllergens = new LinkedHashMap<>();

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        Set<String> ingredients = new LinkedHashSet<>();
        while(sc.hasNextLine()) {

            String line = sc.nextLine();

            String[] _ingredients = line.split("\\(")[0].strip().split("\\s+");
            for(String ingredient : _ingredients) {
                ingredients.add(ingredient);
                allIngredients.add(ingredient);
            }

            String[] _allergens = line.split("\\(")[1].replace(")", "").replace("contains", "").strip().split(",");
            for(String allergen : _allergens) {
                allergen = allergen.strip();
                if(!allergens.containsKey(allergen)) {
                    allergens.put(allergen, new ArrayList<>());
                }
                allergens.get(allergen).add(ingredients);
            }
            ingredients = new LinkedHashSet<>();

        }

        /*for(var key : alergens.keySet()) {
            for(var set : alergens.get(key)) {
                System.out.println(key + ": " + set);
            }
        }*/


        //part 1
        for(String allergen : allergens.keySet()) {
            possibleAllergens.put(allergen, new LinkedHashSet<>());
            List<Set<String>> listOfIngredients = allergens.get(allergen);

            Set<String> setForIntersection = new LinkedHashSet<>();
            setForIntersection.addAll(listOfIngredients.get(0));
            for(int i = 1; i < listOfIngredients.size(); i++) {
                setForIntersection.retainAll(listOfIngredients.get(i));
            }
            for(var ingredient : setForIntersection) {
                ingredientsWithAllergens.add(ingredient);
            }
            for(var ingredient : setForIntersection) {
                possibleAllergens.get(allergen).add(ingredient);
            }
        }

        int sum = 0;
        for(var food : allIngredients) {
            if(!ingredientsWithAllergens.contains(food)) {
                sum++;
            }
        }

        System.out.println("Number of times ingredients witfout any alergens appear in the input is: " + sum);
        for(var key : possibleAllergens.keySet()) {
            System.out.println(key + ": " + possibleAllergens.get(key));
        }

        //part 2 can easily be done by hand, was too lazy to write the algorithm :/
    }
}
