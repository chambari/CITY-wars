import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class MathEquation {

    private static final String[] OPERATORS = {"PLUS", "MINUS", "TIMES"};
    private static final Random RANDOM = new Random();

    public static String generateEquation() {
        int num1 = RANDOM.nextInt(100) + 1;
        int num2 = RANDOM.nextInt(100) + 1;
        int num3 = RANDOM.nextInt(100) + 1;
        String operator1 = OPERATORS[RANDOM.nextInt(OPERATORS.length)];
        String operator2 = OPERATORS[RANDOM.nextInt(OPERATORS.length)];
        System.out.println(num1 + " " + operator1 + " " + num2 + " " + operator2 + " " + num3);
        return evaluateEquation(num1 + " " + operator1 + " " + num2 + " " + operator2 + " " + num3)+"";
    }

    private static int evaluateEquation(String equation) {
        String[] tokens = equation.split(" ");
        Stack<Integer> values = new Stack<>();
        Stack<String> ops = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                values.push(Integer.parseInt(token));
            } else if (isOperator(token)) {
                while (!ops.isEmpty() && hasPrecedence(token, ops.peek())) {
                    values.push(applyOperator(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(token);
            }
        }

        while (!ops.isEmpty()) {
            values.push(applyOperator(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return token.equals("PLUS") || token.equals("MINUS") || token.equals("TIMES");
    }

    private static boolean hasPrecedence(String op1, String op2) {
        if ((op1.equals("TIMES")) && (op2.equals("PLUS") || op2.equals("MINUS"))) {
            return false;
        } else {
            return true;
        }
    }

    private static int applyOperator(String operator, int b, int a) {
        switch (operator) {
            case "PLUS":
                return a + b;
            case "MINUS":
                return a - b;
            case "TIMES":
                return a * b;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
