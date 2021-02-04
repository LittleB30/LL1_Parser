import java.util.ArrayList;
import java.util.List;

/**
 * A class defining a Context-Free Grammar (CFG) containing a list of production rules.
 * @author Alex Smith (alsmi14)
 */
public class CFG {
    private List<List<String>> productionRules;

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
            ArrayList<String> s = new ArrayList<>();
            for (String right : r[1].trim().split("\\s+")) {
                s.add(right);
            }
            productionRules.add(s);
        }
    }

    /**
     * Gets a rule from the CFG.
     * @param index
     * @return the rule stored at the specifed index
     */
    public List<String> getRule(int index) {
        List<String> val;
        try {
            val = new ArrayList<>(productionRules.get(index));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getStackTrace());
            val = null;
        }
        return val;
    }
}