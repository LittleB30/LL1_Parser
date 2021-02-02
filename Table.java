import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private Map<List<String>,Integer> table;

    public Table(String[] t) {
        table = new HashMap<>();
        parseTable(t);
    }

    private void parseTable(String[] t) {
        for (String s : t) {
            String[] rel = s.trim().split("\\s+");
            put(rel[0],rel[1], Integer.parseInt(rel[2]));
        }
    }

    public void put(String row, String col, int value) {
        List<String> temp = new ArrayList<>();
        temp.add(row);
        temp.add(col);
        table.put(temp, value);
    }

    public int get(String row, String col) {
        List<String> temp = new ArrayList<>();
        temp.add(row);
        temp.add(col);
        return table.get(temp);
    }
}