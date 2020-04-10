import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class Myapp {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 界面
        Frame f = new Frame();
        f.surface();
        // 终端模式
        // //check
        // //-e <exercisefile> -a <answerfile>
        // for (int i = 0; i < args.length; i++) {
        //     if (args[i].compareTo("-e") == 0) {
        //         if (i + 3 > args.length) {
        //             System.out.println("Fail : no find \"-e <exercisefile> -a <answerfile>\"");
        //             return;
        //         }
        //         if (new File(args[i + 1]).exists() == false) {
        //             System.out.println("Fail : No file found");
        //             return;
        //         }
        //         if (args[i + 2].compareTo("-a") != 0) {
        //             System.out.println("Fail : no find \"-a\"");
        //             return;
        //         }
        //         if (new File(args[i + 3]).exists() == false) {
        //             System.out.println("Fail : No file found");
        //             return;
        //         }

        //         String expressionFile = args[i + 1];
        //         String answerFile = args[i + 3];
        //         if (new File(expressionFile).exists() == false || new File(answerFile).exists() == false) {
        //             System.out.println("Fail : No file found");
        //             return;
        //         }
        //         Scanner in_exp = new Scanner(Paths.get(expressionFile));
        //         Scanner in_ans = new Scanner(Paths.get(answerFile));
        //         PrintWriter out_res = new PrintWriter("Grade.txt");
        //         Solve.check(in_exp, in_ans, out_res);
        //         in_exp.close();
        //         in_ans.close();
        //         out_res.close();
        //         return;
        //     }
        // }
        // // print exercise and ans
        // // -r number -n number
        // int r = 0, n = 0;
        // for (int i = 0; i < args.length; i++) {
        //     if (args[i].compareTo("-r") == 0) {
        //         if (i + 1 >= args.length) {
        //             System.out.println("Fail : no number after \"-r\"");
        //             return;
        //         }
        //         if (args[i + 1].matches("\\d+")) {
        //             r = Integer.parseInt(args[i + 1]);
        //             i++;
        //         } else {
        //             System.out.println("Fail : no number after \"-r\"");
        //             return;
        //         }
        //     }
        //     if (args[i].compareTo("-n") == 0) {
        //         if (i + 1 >= args.length) {
        //             System.out.println("Fail : no number after \"-n\"");
        //             return;
        //         }
        //         if (args[i + 1].matches("\\d+")) {
        //             n = Integer.parseInt(args[i + 1]);
        //             i++;
        //         } else {
        //             System.out.println("Fail : no number after \"-n\"");
        //             return;
        //         }
        //     }
        // }
        // PrintWriter out_exp = new PrintWriter("Exercise.txt");
        // PrintWriter out_ans = new PrintWriter("Answers.txt");
        // Solve.Build(r, n, out_exp, out_ans);
        // out_exp.close();
        // out_ans.close();
    }
}