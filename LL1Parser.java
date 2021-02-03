import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LL1Parser {
    private CFG grammar;
    private Table table;

	public LL1Parser(CFG g, Table t) {
        grammar = g;
        table = t;
	}

	public void verify(String expression, String startSymbol, String endOfProgram) {
        Stack<String> operations = new Stack<>();
        Queue<String> tokens = tokenize(expression);
        operations.push(startSymbol);
        tokens.add(endOfProgram);
    }
    
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