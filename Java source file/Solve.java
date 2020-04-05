import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

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

    public static void check(String expression, String answer) {

    }

    public static Pair calculate(String expression, StringBuilder cal) {
        String suf_exp = to_suf(expression);
        Stack<Pair> st = new Stack<>();
        String[] strs = suf_exp.split(" ");
        for (int i = 0; i < strs.length; ++i) {
            if (is_op(strs[i])) {
                Pair b = st.pop();
                Pair a = st.pop();
                cal.append(a.toString() + " ");
                cal.append(b.toString() + " ");
                if (strs[i].compareTo("+") == 0) {
                    a.add(b);
                } else if (strs[i].compareTo("*") == 0) {
                    a.mul(b);
                } else if (strs[i].compareTo("/") == 0) {
                    a.div(b);
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
        Scanner in = new Scanner(System.in);
        HashSet<String> st = new HashSet<String>();
        for (int i = 0; i < n; ++i) {
            StringBuilder cal = new StringBuilder("");
            // String expression = Build.get_expression(r);
            String expression = in.nextLine();
            Pair ans = calculate(expression, cal);
            //
            System.out.println("test : " + cal.toString());
            System.out.println(ans.toString());
            //
            if (ans.isLessZero() || st.contains(cal.toString())) {
                i--;
                continue;
            }
            out_exp.println((i + 1) + "." + expression);
            out_ans.println(ans.toString());
        }
        in.close();
    }
}