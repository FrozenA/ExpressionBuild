import java.util.Random;

public class Builder {
    public static String get_expression(int r) {
        char[] compute = { '+', '-', '*', '/' };
        Random r1 = new Random();
        StringBuilder expression = new StringBuilder(); // 表达式
        int computeNum = r1.nextInt(3); // 运算符随机个数
        String[] s = new String[4];
        char[] ch = new char[3];
        for (int i = 0; i < s.length; ++i) {
            s[i] = generateData(r);
        }
        for (int j = 0; j < ch.length; ++j) {
            ch[j] = compute[r1.nextInt(4)];
        }
        int flag = r1.nextInt(2); // 如果为1，插入括号，否则不插入
        switch (computeNum) {
            case 0:
                expression.append(s[0] + " " + ch[0] + " " + s[1]);
                break;
            case 1:
                if (flag == 1) {
                    int index = r1.nextInt(2);
                    if (index == 0) {
                        expression.append(
                                "(" + " " + s[0] + " " + ch[0] + " " + s[1] + " " + ")" + " " + ch[1] + " " + s[2]);
                    } else {
                        expression.append(
                                s[0] + " " + ch[0] + " " + "(" + " " + s[1] + " " + ch[1] + " " + s[2] + " " + ")");
                    }
                } else {
                    expression.append(s[0] + " " + ch[0] + " " + s[1] + " " + ch[1] + " " + s[2]);
                }
                break;
            case 2:
                if (flag == 1) {
                    int index = r1.nextInt(6);
                    switch (index) {
                        case 0:
                            expression.append("(" + " " + s[0] + " " + ch[0] + " " + s[1] + " " + ")" + " " + ch[1]
                                    + " " + "(" + " " + s[2] + " " + ch[2] + " " + s[3] + " " + ")");
                            break;
                        case 1:
                            expression.append("(" + " " + s[0] + " " + ch[0] + " " + s[1] + " " + ")" + " " + ch[1]
                                    + " " + s[2] + " " + ch[2] + " " + s[3]);
                            break;
                        case 2:
                            expression.append("(" + " " + s[0] + " " + ch[0] + " " + s[1] + " " + ch[1] + " " + s[2]
                                    + " " + ")" + " " + ch[2] + " " + s[3]);
                            break;
                        case 3:
                            expression.append(s[0] + " " + ch[0] + " " + "(" + " " + s[1] + " " + ch[1] + " " + s[2]
                                    + " " + ")" + " " + ch[2] + " " + s[3]);
                            break;
                        case 4:
                            expression.append(s[0] + " " + ch[0] + " " + "(" + " " + s[1] + " " + ch[1] + " " + s[2]
                                    + " " + ch[2] + " " + s[3] + " " + ")");
                            break;
                        case 5:
                            expression.append(s[0] + " " + ch[0] + " " + s[1] + " " + ch[1] + " " + "(" + " " + s[2]
                                    + " " + ch[2] + " " + s[3] + " " + ")");
                        default:
                            expression.append(" ");
                    }
                } else {
                    expression.append(
                            s[0] + " " + ch[0] + " " + s[1] + " " + ch[1] + " " + s[2] + " " + ch[2] + " " + s[3]);
                }
                break;
            default:
                expression.append(" ");
        }
        return expression.toString();
    }

    // 生成1个数据
    public static String generateData(int r) {
        int nums = -1;
        int[] integer = new int[2];
        StringBuilder str = new StringBuilder("");
        Random ran = new Random();
        integer[0] = ran.nextInt(r);
        integer[1] = ran.nextInt(r) + 1;
        if (integer[0] % integer[1] == 0) { // 整数
            nums = integer[0] / integer[1];
            str.append(nums);
        } else { // 分数
            if (integer[0] < integer[1]) { // 真分数
                int g = gcd(integer[0], integer[1]);
                str.append("" + (integer[0] / g) + '/' + (integer[1] / g));
            } else { // 假分数
                str.append(new Builder().falseExchangeTrue(integer[0], integer[1]));
            }
        }
        return str.toString();
    }

    // 最大公因数函数
    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // 将假分数转成真分数
    public String falseExchangeTrue(int a, int b) {
        int c = a % b;
        int d = b;
        int _gcd = gcd(c, d);
        String str = a / b + "\'" + c / _gcd + "/" + d / _gcd;
        return str;
    }
}