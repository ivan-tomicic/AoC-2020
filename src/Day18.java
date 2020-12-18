import java.util.*;


// part 1 solution is just commenting the if(op2 == '*' && op1 == '+') return false; line
// in hasPrecedence method
public class Day18 {
    static int pointer = 0;
    static char[] input;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long sum = 0;
        while(sc.hasNextLine()) {
            sum += evaluateExpression(sc.nextLine().replace("\\s+", ""));
        }

        System.out.println(sum);
    }


    public static long evaluateExpression(String expression) {

        char[] tokens = expression.toCharArray();
        Stack<Long> valueStack = new Stack<>();
        Stack<Character> operationStack = new Stack<>();

        for (int i = 0; i < tokens.length; i++)
        {
            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuffer sbuf = new StringBuffer();
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') sbuf.append(tokens[i++]);
                valueStack.push(Long.parseLong(sbuf.toString()));
                i--;
            }
            else if (tokens[i] == '(') operationStack.push(tokens[i]);
            else if (tokens[i] == ')') {
                while (operationStack.peek() != '(') {
                    valueStack.push(applyOp(operationStack.pop(), valueStack.pop(), valueStack.pop()));
                }
                operationStack.pop();
            }

            else if (tokens[i] == '+' || tokens[i] == '*') {
                while (!operationStack.empty() && hasPrecedence(tokens[i], operationStack.peek())) {
                    valueStack.push(applyOp(operationStack.pop(), valueStack.pop(), valueStack.pop()));
                }
                operationStack.push(tokens[i]);
            }
        }

        while (!operationStack.empty()){
            valueStack.push(applyOp(operationStack.pop(), valueStack.pop(), valueStack.pop()));
        }

        return valueStack.pop();
    }

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;

        //comment this line for part 1 solution
        if(op2 == '*' && op1 == '+') return false;
        else
            return true;
    }


    public static long applyOp(char op, long b, long a) {
        switch (op) {
            case '+':
                return a + b;
            case '*':
                return a * b;
        }
        return 0;
    }
}


