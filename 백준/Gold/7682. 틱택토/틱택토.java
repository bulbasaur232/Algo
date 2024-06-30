import java.io.*;

/*
1. XO 개수같은겅우 - valid
2. X가 하나더많은경우 - O가 게임을 못 끝냈으면 valid

 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str = br.readLine();
            if (str.equals("end")) break;

            if (ttt(str)) {
                System.out.println("valid");
            } else {
                System.out.println("invalid");
            }
        }
        br.close();
    }

    public static boolean ttt(String str) {
        char[] c = str.toCharArray();
        int x = 0;
        int o = 0;
        int dot = 0;
        int crossx = 0;
        int crosso = 0;

        for (int i = 0; i < c.length; i++) {
            if (c[i] == 'X') x++;
            else if (c[i] == 'O') o++;
            else dot++;
        }

        if ((o > x) || (x - o > 1)) return false;

        // 여기서부터는 x가 o보다 하나 더 크거나 같은 경우
        int[] horizontal = {0, 3, 6};
        for (int i : horizontal) {
            if (c[i] == c[i + 1] && c[i + 1] == c[i + 2]) {
                if (c[i] == 'X') crossx++;
                else if (c[i] == 'O') crosso++;
            }
        }

        int[] vertical = {0, 1, 2};
        for (int i : vertical) {
            if (c[i] == c[i + 3] && c[i + 3] == c[i + 6]) {
                if (c[i] == 'X') crossx++;
                else if (c[i] == 'O') crosso++;
            }
        }

        if (c[0] == c[4] && c[4] == c[8]) {
            if (c[0] == 'X') crossx++;
            else if (c[0] == 'O') crosso++;
        }

        if (c[2] == c[4] && c[4] == c[6]) {
            if (c[2] == 'X') crossx++;
            else if (c[2] == 'O') crosso++;
        }

        // x가 끝내는 경우는 항상 o보다 하나 더 많아야 함, 그리고 o가 끝냈으면 안 됨
        if (crossx > 0) {
            if (x <= o || crosso > 0) return false;
            else return true;
        }

        // o가 끝내는 경우는 항상 개수가 같아야 함
        if (crosso > 0) {
            if (x != o) return false;
            else return true;
        }

        if (dot > 0) return false;

        return true;
    }
}
/*
. o x
x o x
. o x
*/