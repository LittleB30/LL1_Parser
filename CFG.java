import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CFG {
    private List<Stack<String>> productionRules;

    public CFG(String[] grammar) {
        productionRules = new ArrayList<>();
        parseGrammar(grammar);
    }

    private void parseGrammar(String[] grammar) {
        for (String rule : grammar) {
            String[] r = rule.split("::=");
            Stack<String> s = new Stack<>();
            for (String right : r[1].trim().split("\\s+")) {
                s.push(right);
            }
            productionRules.add(s);
        }
    }

    public Stack<String> getRule(int index) {
        Stack<String> val;
        try {
            val = productionRules.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getStackTrace());
            val = null;
        }
        return val;
    }
}