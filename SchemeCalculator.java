/**

The SchemeCalculator program implements a simple calculator that evaluates arithmetic expressions in
Scheme notation (prefix notation), entered through standard input.
The program reads each line of input, tokenizes it, and evaluates the expression by pushing each token
onto a stack. If the token is an operator, the program performs the operation on the top two values of
the stack and pushes the result back onto the stack. If the token is an opening parenthesis, the program
evaluates the entire expression within the parenthesis and pushes the result onto the stack.
Supported operators are +, -, *, and /. The program throws an IllegalArgumentException if it encounters
an invalid operator.
@author Ibsa Tassew Geleta 
@version 3/06/2023
*/
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class SchemeCalculator {
/**
 * The main method reads each line of input and evaluates the expression. It tokenizes each line of input
 * and pushes each token onto a stack. If the token is an operator, the program performs the operation on
 * the top two values of the stack and pushes the result back onto the stack. If the token is an opening
 * parenthesis, the program evaluates the entire expression within the parenthesis and pushes the result
 * onto the stack. 
 *
 * @param args Not used.
 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            Vector<String> tokens = new Vector<>();
            for (String token : line.split("\\s+")) {
                tokens.add(token);
            }

            Stack<String> stack = new Stack<>();
            for (int i = tokens.size() - 1; i >= 0; i--) {
                String token = tokens.get(i);
                if (token.equals("(")) {
                    double result = evaluateExpression(stack);
                    stack.push(Double.toString(result));
                } else {
                    stack.push(token);
                }
            }

            System.out.println(stack.pop());
        }
    }
/**
 * The evaluateExpression method evaluates an arithmetic expression by popping tokens from the stack.
 * If the token is an operator, the program performs the operation on the top two values of the stack
 * and pushes the result back onto the stack. If the token is a closing parenthesis, the method returns
 * the result of the entire expression within the parenthesis. 
 * 
 * Supported operators are +, -, *, and /. The program throws an IllegalArgumentException if it encounters
 * an invalid operator.
 *
 * @param stack The stack containing the tokens of the expression to be evaluated.
 * @return The result of the expression.
 */
    private static double evaluateExpression(Stack<String> stack) {
        String operator = stack.pop();
        double result = Double.parseDouble(stack.pop());

        while (!stack.isEmpty() && !stack.peek().equals(")")) {
            double operand = Double.parseDouble(stack.pop());
            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    result /= operand;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }

        return result;
    }
}