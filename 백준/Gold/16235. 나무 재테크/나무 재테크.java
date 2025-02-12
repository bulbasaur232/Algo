import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static List<Integer>[][] trees;
    static int[][] energy;
    static int[][] add;
    static int left;
    static int[][] dead;
    static int[] my = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] mx = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        trees = new ArrayList[N][N];
        energy = new int[N][N];
        add = new int[N][N];
        dead = new int[N][N];
        left = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                trees[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(energy[i], 5);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                add[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            trees[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()));
        }

        sort();
        for (int i = 0; i < K; i++) {
            timeGoesBy();
        }

        count();
        System.out.println(left);
        br.close();
    }

    public static void count() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                left += trees[i][j].size();
            }
        }
    }

    public static void timeGoesBy() {
        dead = new int[N][N];

        spring();
        summer();
        fall();
        winter();
    }

    public static void spring() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                List<Integer> list = trees[i][j];
                for (int k = 0; k < list.size(); k++) {
                    int need = list.get(k);
                    if (energy[i][j] >= need) {
                        list.set(k, need + 1);
                        energy[i][j] -= need;
                    } else {
                        int repeat = list.size() - k;
                        for (int a = 0; a < repeat; a++) {
                            int last = list.get(list.size() - 1);
                            list.remove(list.size() - 1);
                            dead[i][j] += last / 2;
                        }
                        break;
                    }
                }
            }
        }
    }

    public static void summer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                energy[i][j] += dead[i][j];
            }
        }
    }

    public static void fall() {
        int[][] newTree = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                List<Integer> list = trees[i][j];
                for (int age : list) {
                    if (age % 5 == 0) {
                        newTree[i][j] += 1;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                while (newTree[i][j] > 0) {
                    for (int k = 0; k < mx.length; k++) {
                        int ny = i + my[k];
                        int nx = j + mx[k];
                        if (isSafe(ny, nx)) {
                            trees[ny][nx].add(0, 1);
                        }
                    }
                    newTree[i][j]--;
                }
            }
        }
    }

    public static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                energy[i][j] += add[i][j];
            }
        }
    }

    public static boolean isSafe(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }

    public static void sort() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                trees[i][j].sort(Comparator.naturalOrder());
            }
        }
    }
}