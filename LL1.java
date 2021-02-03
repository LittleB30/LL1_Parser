public class LL1 {
    public static void main(String[] args) {
        // if (args.length < 1) {
        //     System.out.println("Usage: LL1 \"Expression\"");
        //     return;
        // }
        // String exp = args[0];
        String exp = "100.228-(2.45*(5.1-3)-2+3)";  //Valid; 94.083
        //String exp = "100-((2*(5-3)-2+3)";        //Invalid Expression
        String[] gram = {
            "E ::= T E'",
            "E' ::= + T E'", "E' ::= − T E'", "E' ::= λ",
            "T ::= F T'",
            "T' ::= ∗ F T'", "T' ::= / F T'", "T' ::= λ",
            "F ::= ( E )", "F ::= n"
        };
        String[] tab = {
            "E n 1", "E ( 1",
            "E' + 2", "E' - 3", "E' ) 4", "E' $ 4",
            "T n 5", "T ( 5",
            "T' + 8", "T' - 8", "T' * 6", "T' / 7", "T' ) 8", "T' $ 8",
            "F n 10", "F ( 9"
        };
        CFG grammar = new CFG(gram);
        Table table = new Table(tab);
        LL1Parser parser = new LL1Parser(grammar, table);
        parser.verify(exp, "E", "$");
    }
}
