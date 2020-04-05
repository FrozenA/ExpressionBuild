import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class Myapp {
    public static void main(String[] args) throws IOException {
        // check
        // -e <exercisefile> -a <answerfile>
        for (int i = 0; i < args.length; i++) {
            if (args[i].compareTo("-e") != 0) {
                if (i + 3 >= args.length) {
                    System.out.println("Fail : no find \"-e <exercisefile> -a <answerfile>\"");
                    return;
                }
                if (new File(args[i + 1]).exists() == false) {
                    System.out.println("Fail : No file found");
                    return;
                }
                if (args[i + 2].compareTo("-a") != 0) {
                    System.out.println("Fail : no find \"-e <exercisefile> -a <answerfile>\"");
                    return;
                }
                if (new File(args[i + 3]).exists() == false) {
                    System.out.println("Fail : No file found");
                    return;
                }
            }
            Solve.check(args[i + 1], args[i + 3]);
        }
        // print exercise and ans
        // -r number -n number
        int r = 20, n = 10;
        for (int i = 0; i < args.length; i++) {
            if (args[i].compareTo("-r") == 0) {
                if (i + 1 >= args.length) {
                    System.out.println("Fail : no number after \"-r\"");
                    return;
                }
                if (args[i + 1].matches("\\d+")) {
                    r = Integer.parseInt(args[i + 1]);
                    i++;
                } else {
                    System.out.println("Fail : no number after \"-r\"");
                    return;
                }
            }
            if (args[i].compareTo("-n") == 0) {
                if (i + 1 >= args.length) {
                    System.out.println("Fail : no number after \"-n\"");
                    return;
                }
                if (args[i + 1].matches("\\d+")) {
                    n = Integer.parseInt(args[i + 1]);
                    i++;
                } else {
                    System.out.println("Fail : no number after \"-n\"");
                    return;
                }
            }
        }
        Solve.Build(r, n);
    }
}