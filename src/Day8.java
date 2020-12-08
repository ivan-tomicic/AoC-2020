import java.util.*;

public class Day8 {

    static int acc;
    static List<String> input = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int i = 0;
        while(sc.hasNextLine()) {
            input.add(sc.nextLine());
        }

        runTheCode();
        System.out.println("Acc before any instruction is executed 2nd time: " + acc);

        for(i = 0; i < input.size(); i++) {

            String operation = input.get(i);
            if(operation.startsWith("nop") || operation.startsWith("jmp")) {
                if (operation.startsWith("nop")) input.set(i, operation.replace("nop", "jmp"));
                else if (operation.startsWith("jmp")) input.set(i, operation.replace("jmp", "nop"));

                if(runTheCode()) {
                    System.out.println("Acc after fixing the code: " + acc);
                    break;
                } else {
                    input.set(i, operation);
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
