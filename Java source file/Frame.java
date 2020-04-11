import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Frame {

    JFrame jframe = new JFrame("Myapp");

    JPanel jPanel = new JPanel();
    JPanel jPanel1 = new JPanel();

    JButton ok = new JButton("确定");
    JButton check = new JButton("检查");
    JButton display = new JButton("显示答案");

    JLabel parameter_n = new JLabel("parameter n:");
    JLabel parameter_r = new JLabel("parameter r:");

    JTextField n = new JTextField(30);
    JTextField r = new JTextField(30);

    JTextArea jTextArea1 = new JTextArea(20, 20);
    JTextArea jTextArea2 = new JTextArea(20, 20);

    public void surface() throws IOException, InterruptedException {

        JFrame.setDefaultLookAndFeelDecorated(true);
        jframe.setBounds(0, 0, 400, 80);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //关闭窗口


        parameter_n.setFont(new Font("字体", Font.PLAIN, 20));
        parameter_r.setFont(new Font("字体", Font.PLAIN, 20));
        jPanel.setLayout(new FlowLayout());

        n.addFocusListener(new JTextFieldHintListener(n, "please enter the parameter n"));
        r.addFocusListener(new JTextFieldHintListener(r, "please enter the parameter r"));

        //ok按钮实现参数的输入并生成题目

        ok.addActionListener(e -> {
            if (n.getText().equals("please enter the parameter n") || r.getText().equals("please enter the parameter r")) {
                JOptionPane.showMessageDialog(null, "请输入参数!");
            } else {
                PrintWriter out_exp = null;
                try {
                    out_exp = new PrintWriter("Exercise.txt");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                PrintWriter out_ans = null;
                try {
                    out_ans = new PrintWriter("Answers.txt");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                Solve.Build(Integer.parseInt(r.getText()), Integer.parseInt(n.getText()), out_exp, out_ans);
                out_exp.close();
                out_ans.close();
                out_exp.flush();
                String filepath = System.getProperty("user.dir") + "/Exercise.txt";
                File file = new File(filepath);
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(
                            new FileInputStream(file)));
                    jTextArea1.read(input, "READING FILE :-)");
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        });

        jPanel.add(parameter_n);
        jPanel.add(n);
        jPanel.add(parameter_r);
        jPanel.add(r);
        jPanel.add(ok);

        JScrollPane jscrollpane = new JScrollPane(jTextArea1);
        jTextArea1.setFont(new Font("字体", Font.PLAIN, 30));

        jTextArea2.setFont(new Font("字体", Font.PLAIN, 30));
        JScrollPane jScrollPane1 = new JScrollPane(jTextArea2);

        //将输入的内容输入到inputAnswers.txt中，按check按钮检查
        check.addActionListener(e1 -> {
            PrintWriter Grade = null;
            try {
                Grade = new PrintWriter("Grade.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            StringTokenizer st = new StringTokenizer(jTextArea2.getText(), "\n");

            FileWriter inputAnswers = null;
            try {
                inputAnswers = new FileWriter("inputAnswers.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (st.hasMoreTokens()) {
                try {
                    inputAnswers.append(st.nextToken() + "\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                inputAnswers.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scanner in_exp = null;
            try {
                in_exp = new Scanner(Paths.get("Exercise.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ;
            Scanner in_ans = null;
            try {
                in_ans = new Scanner(Paths.get("inputAnswers.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Solve.check(in_exp, in_ans, Grade);
            in_exp.close();
            in_ans.close();
            Grade.close();

            //把Grade的内容以消息框显示出来
            String filepath = System.getProperty("user.dir") + "/Grade.txt";
            File file = new File(filepath);
            String s;
            StringBuilder str = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                while ((s = br.readLine()) != null) {
                    str.append(s);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, str.toString());
        });

        display.addActionListener(e -> {
            //把题目和标准答案整合成一个文件
            PrintWriter exerciseAndAnswer = null;
            try {
                exerciseAndAnswer = new PrintWriter("exerciseAndAnswer.txt");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            String E;
            String A;
            String filepath = System.getProperty("user.dir") + "/Exercise.txt";
            File file = new File(filepath);
            String filepath1 = System.getProperty("user.dir") + "/Answers.txt";
            File file1 = new File(filepath1);
            try {
                BufferedReader inputE = new BufferedReader(new FileReader(file));
                BufferedReader inputA = new BufferedReader(new FileReader(file1));
                while ((E = inputE.readLine()) != null && (A = inputA.readLine()) != null) {
                    StringBuilder s = new StringBuilder();
                    s.append(E + " " + A);
                    exerciseAndAnswer.println(s.toString());
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            exerciseAndAnswer.close();
            //读取
            String filepath2 = System.getProperty("user.dir") + "/exerciseAndAnswer.txt";
            File file2 = new File(filepath2);
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file2)));
                jTextArea1.read(input, "READING FILE :-)");
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        });

        jPanel1.setLayout(new FlowLayout());
        jPanel1.add(check);
        jPanel1.add(display);

        jframe.setLayout(new BorderLayout());
        jframe.add(jPanel, BorderLayout.NORTH);
        jframe.add(jscrollpane, BorderLayout.CENTER);
        jframe.add(jScrollPane1, BorderLayout.EAST);
        jframe.add(jPanel1, BorderLayout.SOUTH);
        jframe.pack();
        jframe.setVisible(true);
    }

}

