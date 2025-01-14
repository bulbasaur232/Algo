import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static boolean[] sc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sc = new boolean[26];

        loop:
        for (int i = 0; i < n; i++) {
            String word = br.readLine();

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < word.length(); j++) {
                if (j == 0 || word.charAt(j - 1) == ' ') {
                    if (!sc[toLowerCase(word.charAt(j)) - 'a']) {
                        sc[toLowerCase(word.charAt(j)) - 'a'] = true;
                        System.out.print(sb + "[" + word.charAt(j) + "]");
                        if(j + 1 < word.length()) System.out.println(word.substring(j + 1));
                        else System.out.println();
                        continue loop;
                    } else {
                        sb.append(word.charAt(j));
                    }
                } else {
                    sb.append(word.charAt(j));
                }
            }

            sb = new StringBuilder();
            for (int j = 0; j < word.length(); j++) {
                if (!(j == 0 || word.charAt(j) == ' ')) {
                    if (!sc[toLowerCase(word.charAt(j)) - 'a']) {
                        sc[toLowerCase(word.charAt(j)) - 'a'] = true;
                        System.out.print(sb + "[" + word.charAt(j) + "]");
                        if(j + 1 < word.length()) System.out.println(word.substring(j + 1));
                        else System.out.println();
                        continue loop;
                    } else {
                        sb.append(word.charAt(j));
                    }
                } else {
                    sb.append(word.charAt(j));
                }
            }

            System.out.println(word);
        }

        br.close();
    }

    static char toLowerCase(char c) {
        if (c >= 'A' && c <= 'Z')
            c = (char) (c - ('A' - 'a'));
        return c;
    }
}