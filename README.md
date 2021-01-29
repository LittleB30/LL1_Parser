# LL1_Parser
Use Java to implement an LL(1) parser, also known as Recursive-Descent parser, according to the following grammar and its parsing table.

Context-Free Grammar:
 1.  E → T E'
 2.  E' → +T E'
 3.  E' → −T E'
 4.  E' → λ
 5.  T → F T'
 6.  T' → ∗F T'
 7.  T' → /F T'
 8.  T' → λ
 9.  F → (E)
10.  F → n

Parsing Table:
   n  +  −  ∗  /  (  )  $
E  1              1
E'    2  3           4  4
T  5              5
T'    8  8  6  7     8  8
F  10             9

Note:
Let n be any decimal number (double or float).
$ denotes the end of the expression.

Sample Runs:
java LL1 "100.228-(2.45*(5.1-3)-2+3)"
Valid; 94.083

java LL1 "100-((2*(5-3)-2+3)"
Invalid Expression
