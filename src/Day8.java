import java.util.*;

public class Day8 {

    static int acc;
    static Map<Integer, String> input = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int i = 0;
        while(sc.hasNextLine()) {
            input.put(i++, sc.nextLine());
        }

        runTheCode();
        System.out.println("Acc before any instruction is executed 2nd time: " + acc);

        for(int line : input.keySet()) {
            String operation = input.get(line);
            if(operation.startsWith("nop") || operation.startsWith("jmp")) {
                if (operation.startsWith("nop")) input.put(line, operation.replace("nop", "jmp"));
                else if (operation.startsWith("jmp")) input.put(line, operation.replace("jmp", "nop"));

                if(runTheCode()) {
                    System.out.println("Acc after fixing the code: " + acc);
                    break;
                } else {
                    input.put(line, operation);
                }
            }
        }
    }

    static boolean runTheCode() {
        int i = 0;
        acc = 0;
        Set<Integer> executedLines = new LinkedHashSet<>();

        while(true) {
            if(executedLines.contains(i)) return false;
            if(i >= input.size()) return true;
            executedLines.add(i);

            if(input.get(i).startsWith("nop")) {
                i++;
            }
            else if(input.get(i).startsWith("acc")) {
                acc += Integer.parseInt(input.get(i).split("\\s+")[1]);
                i++;

            }
            else {
                i += Integer.parseInt(input.get(i).split("\\s+")[1]);
            }
        }
    }
}
