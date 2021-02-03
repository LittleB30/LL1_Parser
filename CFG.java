import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A class defining a Context-Free Grammar (CFG) containing a list of production rules.
 * @author Alex Smith (alsmi14)
 */
public class CFG {
    private List<Stack<String>> productionRules;

    /**
     * Constucts a grammar from a given string array.
     * @param grammar an array of strings in the form "non-Term ::= ? ? ?".
     */
    public CFG(String[] grammar) {
        productionRules = new ArrayList<>();
        parseGrammar(grammar);
    }

    /**
     * Parses the string array and adds it to the CFG.
     * @param grammar an array of strings in the form "non-Term ::= ? ? ?".
     */
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

    /**
     * Gets a rule from the CFG.
     * @param index
     * @return the rule stored at the specifed index
     */
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