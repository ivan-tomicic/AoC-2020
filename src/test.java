import java.util.*;

public class test {

    //first part if input
    static Map<String, List<Integer>> rules = new LinkedHashMap<>();

    //second part if input
    static List<Integer> myTicket = new ArrayList<>();

    //third part if input
    static List<List<Integer>> nearbyTickets = new ArrayList<>();

    //maps a fieldName to all the columns that can contain it
    static Map<String, List<Integer>> possibleColumns = new LinkedHashMap<>();

    //maps a fieldName to only one column that can contain it
    static Map<String, Integer> fields = new LinkedHashMap<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 20; i++) {
            String line = sc.nextLine();
            String field = line.split(":")[0];
            List<Integer> ranges = Arrays.asList(
                    Integer.parseInt(line.split(":")[1].split("\\s+")[1].split("-")[0]),
                    Integer.parseInt(line.split(":")[1].split("\\s+")[1].split("-")[1]),
                    Integer.parseInt(line.split(":")[1].split("\\s+")[3].split("-")[0]),
                    Integer.parseInt(line.split(":")[1].split("\\s+")[3].split("-")[1])
            );
            rules.put(field,ranges);
        }
        sc.nextLine();
        sc.nextLine();

        String[] ticket = sc.nextLine().split(",");
        for(String num : ticket) myTicket.add(Integer.parseInt(num));

        sc.nextLine();
        sc.nextLine();
        while(sc.hasNextLine()) {
            ticket = sc.nextLine().split(",");
            List<Integer> nearbyTicket = new ArrayList<>();
            for(String num : ticket) nearbyTicket.add(Integer.parseInt(num));
            nearbyTickets.add(nearbyTicket);
        }

        //finished parsing input
        //part 1
        int sum = 0;
        Collection<List<Integer>> allRules = rules.values();
        List<List<Integer>> ticketsToRemove = new ArrayList<>();
        for(var _ticket : nearbyTickets) {
            for(int number : _ticket) {
                boolean valid = false;
                for(var ranges : allRules) {
                    if(number >= ranges.get(0) && number <= ranges.get(1)
                            || number >= ranges.get(2) && number <= ranges.get(3)) {
                        valid = true;
                        break;
                    }
                }
                if(!valid) {
                    sum += number;
                    ticketsToRemove.add(_ticket);
                }
            }
        }
        System.out.println("Part 1 solution: " + sum);

        for(var _ticket : ticketsToRemove) nearbyTickets.remove(_ticket);

        //part 2
        //go thrugh every column in nearby tickets and add it to list of fields that can be that column
        for(int i = 0; i < nearbyTickets.get(0).size(); i++) {
            Map<String,Boolean> validForFieldName = new LinkedHashMap<>();
            for(String fieldName : rules.keySet()) validForFieldName.put(fieldName, true);

            for(int j = 0; j < nearbyTickets.size(); j++) {
                int number = nearbyTickets.get(j).get(i);
                for(String fieldName : rules.keySet()) {
                    boolean valid = false;
                    if(number >= rules.get(fieldName).get(0) && number <= rules.get(fieldName).get(1)
                    || number >= rules.get(fieldName).get(2) && number <= rules.get(fieldName).get(3)) valid = true;
                    if(!valid) validForFieldName.put(fieldName, false);
                }
            }


            for(String fieldName : validForFieldName.keySet()) {
                if(validForFieldName.get(fieldName)) {
                    if(!possibleColumns.containsKey(fieldName)) {
                        List<Integer> arrayOfColumns = new ArrayList<>();
                        arrayOfColumns.add(i);
                        possibleColumns.put(fieldName, arrayOfColumns);
                    }
                    else possibleColumns.get(fieldName).add(i);
                }
            }
        }

        //assigns each field a column, always trying to assign it to the column that
        //has minimum possible columns
        List<Integer> takenColumns = new ArrayList<>();
        for(int i = 0; i < rules.keySet().size(); i++) {
            int min = rules.keySet().size();

            String field = "";
            for(String fieldName : possibleColumns.keySet()) {
                if(possibleColumns.get(fieldName).size() <= min) {
                    min = possibleColumns.get(fieldName).size();
                    field = fieldName;
                }
            }
            for(int column : possibleColumns.get(field)) {
                if(!takenColumns.contains(column)) {
                    fields.put(field, column);
                    takenColumns.add(column);
                    possibleColumns.remove(field);
                }
            }
        }
        //System.out.println(fields);

        long answer = 1;
        for(String fieldName : fields.keySet()) {
            if(fieldName.startsWith("departure")) answer *= myTicket.get(fields.get(fieldName));
        }
        System.out.println("Part 1 solution: " + answer);
    }

}
