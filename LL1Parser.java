import java.util.LinkedList;
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
        Stack<String> operations = new Stack<>();
        Queue<String> tokens = tokenize(expression);
        operations.push(startSymbol);
        tokens.add(endOfProgram);

        while (!operations.isEmpty() && !tokens.isEmpty()) {
            String curOp = operations.pop();
            String curToken = tokens.poll();
            boolean isNumber;
            double num;
            try {
                num = Double.parseDouble(curToken);
                isNumber = true;
            } catch (NumberFormatException e) {
                isNumber = false;
            }
        }
    }
    
    /**
     * Converts an string expression into tokens and adds them to a queue.
     * @param expression the expression to be tokenized
     * @return a queue of token strings from the given expression.
     */
    private Queue<String> tokenize(String expression) {
        Queue<String> tokens = new LinkedList<>();
        Pattern regex = Pattern.compile("\\d+(\\.\\d+)?|\\p{Punct}");
        Matcher matcher = regex.matcher(expression);

        while(matcher.find()){
            tokens.add(matcher.group(0));
        }

        return tokens;
    }
}