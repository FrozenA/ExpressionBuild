import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Solve {

    public static boolean is_op(String str) {
        if ((str.length() == 1)
                && (str.charAt(0) == '+' || str.charAt(0) == '-' || str.charAt(0) == '*' || str.charAt(0) == '/'))
            return true;
        return false;
    }

    public static boolean compare_op(char a, char b) {
        if (b == '(')
            return true;
        if ((a == '*' || a == '/') && (b == '-' || b == '+'))
            return true;
        return false;
    }

    public static String to_suf(String expression) {
        Stack<String> st = new Stack<String>();
        String[] strs = expression.split(" ");
        StringBuilder suf = new StringBuilder("");
        for (int i = 0; i < strs.length; ++i) {
            if (is_op(strs[i])) {
                while (st.empty() == false && compare_op(strs[i].charAt(0), st.peek().charAt(0)) == false)
                    suf.append(st.pop() + " ");
                st.add(strs[i]);
            } else if (strs[i].charAt(0) == '(') {
                st.add(strs[i]);
            } else if (strs[i].charAt(0) == ')') {
                while (st.peek().charAt(0) != '(')
                    suf.append(st.pop() + " ");
                st.pop();
            } else {
                suf.append(strs[i] + " ");
            }
        }
        while (st.empty() == false)
            suf.append(st.pop() + " ");
        return suf.toString();
    }

    public static void check(Scanner in_exp, Scanner in_ans, PrintWriter out) {
        Vector<Integer> Correct = new Vector<>();
        Vector<Integer> Wrong = new Vector<>();
        for (int i = 1; in_exp.hasNext() && in_ans.hasNext(); ++i) {
            String expression = in_exp.nextLine();
            String ans = in_ans.nextLine();
            String o_ans = calculate(expression.substring(expression.indexOf(".") + 1, expression.indexOf("=")),
                    new StringBuilder("")).toString();
            if (ans.equals(o_ans)) {
                Correct.add(i);
            } else {
                Wrong.add(i);
            }
        }
        out.print("Correct: " + Correct.size() + "( ");
        for (int x : Correct)
            out.print("" + x + " ");
        out.println(")");
        out.print("Wrong: " + Wrong.size() + "( ");
        for (int x : Wrong)
            out.print("" + x + " ");
        out.println(")");
    }

    public static Pair calculate(String expression, StringBuilder cal) {
        String suf_exp = to_suf(expression);
        Stack<Pair> st = new Stack<>();
        String[] strs = suf_exp.split(" ");
        for (int i = 0; i < strs.length; ++i) {
            if (is_op(strs[i])) {
                Pair b = st.pop();
                Pair a = st.pop();
                //
                if (strs[i].charAt(0) == '+' || strs[i].charAt(0) == '*') {
                    if (a.compare(b)) {
                        cal.append(a.toString() + " ");
                        cal.append(b.toString() + " ");
                    } else {
                        cal.append(b.toString() + " ");
                        cal.append(a.toString() + " ");
                    }
                } else {
                    cal.append(b.toString() + " ");
                    cal.append(a.toString() + " ");
                }
                //
                if (strs[i].compareTo("+") == 0) {
                    a.add(b);
                } else if (strs[i].compareTo("*") == 0) {
                    a.mul(b);
                } else if (strs[i].compareTo("/") == 0) {
                    a.div(b);
                    if (a.isLessZero()) {
                        return new Pair(-1, 0);
                    }
                } else if (strs[i].compareTo("-") == 0) {
                    a.dec(b);
                    if (a.isLessZero()) {
                        return new Pair(-1, 0);
                    }
                }
                st.add(a);
                cal.append(strs[i] + " ");
            } else {
                st.add(new Pair(strs[i]));
            }
        }
        return st.peek();
    }

    public static void Build(int r, int n, PrintWriter out_exp, PrintWriter out_ans) {
        HashSet<String> st = new HashSet<String>();
        for (int i = 0; i < n; ++i) {
            StringBuilder cal = new StringBuilder("");
            String expression = Builder.get_expression(r);
            if (expression.equals(" ")) {
                System.out.println("Error");
                return;
            }
            Pair ans = calculate(expression, cal);
            if (ans.isLessZero() || st.contains(cal.toString()) || ans.compare(new Pair(r, 1)) || ans.second >= r) {
                i--;
                continue;
            }
            st.add(cal.toString());
            out_exp.println((i + 1) + "." + expression + " =");
            out_ans.println(ans.toString());
        }
    }

    public static void test_Build(int r, int n, PrintWriter out_exp, PrintWriter out_ans) {
        Scanner in = new Scanner(System.in);
        HashSet<String> st = new HashSet<String>();
        for (int i = 0; i < n; ++i) {
            StringBuilder cal = new StringBuilder("");
            String expression = in.nextLine();
            Pair ans = calculate(expression, cal);

            System.out.println(expression);
            System.out.println("test : " + cal.toString());
            System.out.println(ans.toString());
            System.out.println("");

            if (ans.isLessZero() || st.contains(cal.toString())) {
                i--;
                continue;
            }
            st.add(cal.toString());
            out_exp.println((i + 1) + "." + expression + " =");
            out_ans.println(ans.toString());
        }
        in.close();
    }
}