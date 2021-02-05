import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class defining an LL(1), recursive-decent, parser containing a Context-Free Grammar (CFG) and a parse table. 
 * @author Alex Smith (alsmi14)
 */
public class LL1Parser {
    private CFG grammar;
    private Table parseTable;

    /**
     * Constructs a LL(1) parser with a given CFG and parse table.
     * @param g the CFG
     * @param t the parse table
     */
	public LL1Parser(CFG g, Table t) {
        grammar = g;
        parseTable = t;
	}

    /**
     * Verifies an expression as valid or invalid.
     * @param expression the expression to be verified
     * @param startSymbol the start symbol of the CFG
     * @param endOfProgram the end of program sybol of the CFG
     */
	public void verify(String expression, String startSymbol, String endOfProgram) {
        boolean isValid = true;
        Stack<String> operations = new Stack<>();
        Queue<String> tokens = tokenize(expression);
        operations.push(startSymbol);
        tokens.add(endOfProgram);

        // Continue parsing while there are both operations and tokens left
        while (!tokens.isEmpty() && !operations.isEmpty() && isValid) {
            isValid = parse(tokens, operations);
        }

        // Print the conclusion
        if (isValid) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid Expression");
        }
    }
    
    /**
     * Converts a string expression into tokens and adds them to a queue.
     * @param expression the expression to be tokenized
     * @return a queue of token strings from the given expression.
     */
    private Queue<String> tokenize(String expression) {
        Queue<String> tokens = new LinkedList<>();
        Pattern regex = Pattern.compile("\\d+(\\.\\d+)?|\\p{Punct}");
        // \\d+(\\.\\d+)? catches integers and doubles
        // \\p{Punct} catches all operator symbols
        Matcher matcher = regex.matcher(expression.replace("\\s+", ""));

        while(matcher.find()){
            tokens.add(matcher.group(0));
            //group 0 is the entire regular expression \\d+(\\.\\d+)?|\\p{Punct}
        }

        return tokens;
    }

    /**
     * Parses one iteration from the tokens and operations stacks.
     * @param tokens the stack of string tokens
     * @param operations the stack of string operations
     * @return true if successful, false otherwise
     */
    private boolean parse(Queue<String> tokens, Stack<String> operations) {
        boolean isValid = true;
        String curToken = tokens.peek();
        String curOp = operations.pop();

        // Convert the current token into n if it is a number
        if (Pattern.matches("\\d+(\\.\\d+)?", curToken)) curToken = "n";

        // Check if the operation requires removing the token from the string
        if (Pattern.matches("n|\\p{Punct}", curOp)) {
            if (curToken.equals(curOp)) {
                tokens.poll();
            } else {
                isValid = false;
            }
        } else {
            // Apply the rule in the table at (curOp, curToken) by adding it to the operations stack
            try { // Prepare for null to be returned when getting from the parse table
                int ruleNum = parseTable.get(curOp, curToken) - 1;  // -1 to start from 0
                List<String> temp = grammar.getRule(ruleNum);
                Collections.reverse(temp);                          // Reverse for correct stack order
                operations.addAll(temp);                            // Add the production rule to the top of the stack
                if (operations.peek().equals("λ")) operations.pop();
            } catch (NullPointerException e) {
                isValid = false;
            }
        }

        return isValid;
    }
}