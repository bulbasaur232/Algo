import java.util.*;
import java.io.*;

/**
 * 플로이드 워셜
 */
public class Main {
    static int N; // 물건 개수
    static int M; // 물건 쌍의 개수
    static boolean[][] connect;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        connect = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            connect[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(connect[i][k] && connect[k][j]){
                        connect[i][j] = true;
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            int result = N;
            for (int j = 1; j <= N; j++) {
                if (connect[i][j] || connect[j][i]) {
                    result--;
                }
            }
            System.out.println(result - 1);
        }

        br.close();
    }
}

