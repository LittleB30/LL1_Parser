import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple table object for strings with rows and columns implmented with a map.
 * @author Alex Smith (alsmi14)
 */
public class Table {
    private Map<List<String>,Integer> table;

    /**
     * Create a table.
     * @param t an array of strings in the form "row column value"
     */
    public Table(String[] t) {
        table = new LinkedHashMap<>();
        parseTable(t);
    }

    /**
     * Parses the string array and adds it to the table.
     * @param t an array of strings in the form "row column value"
     */
    private void parseTable(String[] t) {
        for (String s : t) {
            String[] rel = s.trim().split("\\s+");
            put(rel[0],rel[1], Integer.parseInt(rel[2]));
        }
    }

    /**
     * Adds a value into the table at (row, column).
     * @param row 
     * @param col
     * @param value
     */
    public void put(String row, String column, int value) {
        List<String> temp = new ArrayList<>();
        temp.add(row);
        temp.add(column);
        table.put(temp, value);
    }

    /**
     * Gets the value in the table stored at (row, column).
     * @param row
     * @param col
     * @return the value in the table stored at (row, column), null in not present.
     */
    public int get(String row, String column) {
        List<String> temp = new ArrayList<>();
        temp.add(row);
        temp.add(column);
        return table.get(temp);
    }
}