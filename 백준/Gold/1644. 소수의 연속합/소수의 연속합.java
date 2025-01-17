import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        int limit = (int) Math.sqrt(N);
        boolean[] numbers = new boolean[N + 1];

        for (int i = 2; i <= limit; i++) {
            for (int j = i + i; j <= N; j += i) {
                numbers[j] = true;
            }
        }

        List<Integer> primeList = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (!numbers[i])
                primeList.add(i);
        }

        for (int front = 0; front < primeList.size(); front++) {
            int sum = 0;
            for (int rear = front; rear < primeList.size(); rear++) {
                sum += primeList.get(rear);
                if (sum == N) {
                    ans++;
                    break;
                } else if (sum >= N) {
                    break;
                }
            }
        }

        System.out.println(ans);
        br.close();
    }
}