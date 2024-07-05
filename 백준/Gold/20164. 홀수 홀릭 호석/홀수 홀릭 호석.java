import java.util.*;
import java.io.*;

public class Main {
    static String N;
    static int max;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();
        max = 0;
        min = Integer.MAX_VALUE;
        hoseok(N, 0);
        System.out.println(min + " " + max);
        br.close();
    }

    static void hoseok(String num, int count) {
        // 1자리일 경우
        if (num.length() == 1) {
            int sum = count + countOdd(num);
            max = Math.max(max, sum);
            min = Math.min(min, sum);
        }

        // 2자리일 경우
        else if (num.length() == 2) {
            hoseok(Integer.parseInt(num.substring(0, 1)) + Integer.parseInt(num.substring(1, 2)) + "",
                    count + countOdd(num));
        }

        // 3자리일 경우
        else {
            // 가능한거 다 쪼개보기
            for (int i = 1; i < num.length() - 1; i++) {
                for (int j = i + 1; j < num.length(); j++) {
                    int nowSum = count + countOdd(num);
                    hoseok(Integer.parseInt(num.substring(0, i)) +
                                    Integer.parseInt(num.substring(i, j)) +
                                    Integer.parseInt(num.substring(j)) + "",
                            nowSum);
                }
            }
        }
    }


    static int countOdd(String num) {
        int count = 0;
        for (int i = 0; i < num.length(); i++) {
            int cur = Integer.parseInt(num.substring(i, i + 1));
            if ((cur & 1) == 1) count++;
        }
        return count;
    }

}
