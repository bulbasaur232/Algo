import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
1. x1 좌표가 작은것 기준으로 정렬
2. 맨 앞 통나무 무터 다음 통나무를 보면서 최대한 갈 수 있는 좌표 기록
3. 만약 다음으로 못가면 그 다음 통나무 부터 탐색하되, 시작지점은 이전 통나무의 최대 지점
4. 주의할거는 이전 통나무 최대 지점이 다음것보다 작은 경우, Q의 좌표 순서가 이상한 경우(순서반대)
 */

public class Main {
    static int N, Q;
    static PriorityQueue<Log> pq;
    static List<Log> sortedlist, list;
    static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>(Comparator.comparing(Log::getStart).thenComparing(Log::getEnd));
        sortedlist = new ArrayList<>(N);
        list = new ArrayList<>(N);
        map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Log log = new Log(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            pq.add(log);
            list.add(log);
            sortedlist.add(log);
            st.nextToken();
        }

        sortedlist.sort(Comparator.comparing(Log::getStart).thenComparing(Log::getEnd));
        pq.poll();
        int maxEnd = -1;
        for (int i = 0; i < N; i++) {
            Log now = sortedlist.get(i);
            while (!pq.isEmpty()) {
                Log next = pq.peek();
                if (now.getEnd() >= next.getStart()) {
                    maxEnd = next.getEnd();
                    pq.poll();
                } else {
                    break;
                }
            }

            map.put(now.getStart(), maxEnd);
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            Log fromLog = list.get(from);
            Log toLog = list.get(to);

            if (fromLog.getStart() > toLog.getStart()) {
                Log temp = fromLog;
                fromLog = toLog;
                toLog = temp;
            }

            if (map.get(fromLog.getStart()) >= toLog.getStart()) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }


        br.close();
    }

    static class Log {
        int start;
        int end;

        public Log(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}