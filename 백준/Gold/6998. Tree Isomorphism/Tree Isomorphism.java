import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 1. 트리를 만든다.
 * 2. 노트별로 자신의 모든 자식 노드 개수 저장한다.
 * 3. 레벨별로 자식 노드 개수 리스트에 추가
 * 4. 비교한다.
 */
public class Main {
    static Node treeA;
    static Node treeB;
    static List<List<Integer>> cntA;
    static List<List<Integer>> cntB;
    static int T;
    static boolean isomorphic;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            treeA = new Node();
            treeB = new Node();
            cntA = new ArrayList<>();
            cntB = new ArrayList<>();
            isomorphic = true;

            StringTokenizer st = new StringTokenizer(br.readLine());
            Node now = treeA;
            st.nextToken();
            while (st.hasMoreTokens()) {
                String s = st.nextToken();
                if (s.equals("#")) {
                    now = now.parent;
                } else {
                    Node child = new Node();
                    child.parent = now;
                    now.children.add(child);
                    now = child;
                }
            }

            st = new StringTokenizer(br.readLine());
            now = treeB;
            st.nextToken();
            while (st.hasMoreTokens()) {
                String s = st.nextToken();
                if (s.equals("#")) {
                    now = now.parent;
                } else {
                    Node child = new Node();
                    child.parent = now;
                    now.children.add(child);
                    now = child;
                }
            }

            countChild(treeA, cntA, 0);
            countChild(treeB, cntB, 0);

            for (List<Integer> list : cntA) {
                Collections.sort(list);
            }

            for (List<Integer> list : cntB) {
                Collections.sort(list);
            }

            loop:
            for (int i = 0; i < cntA.size(); i++) {
                List<Integer> listA = cntA.get(i);
                List<Integer> listB = cntB.get(i);
                if (listA.size() != listB.size()) {
                    isomorphic = false;
                    break;
                }

                for (int j = 0; j < listA.size(); j++) {
                    if (listA.get(j) != listB.get(j)) {
                        isomorphic = false;
                        break loop;
                    }
                }
            }

            if (isomorphic) {
                System.out.println("The two trees are isomorphic.");
            } else {
                System.out.println("The two trees are not isomorphic.");
            }
        }

        br.close();
    }

    static int countChild(Node tree, List<List<Integer>> cntList, int depth) {
        if (cntList.size() <= depth) {
            cntList.add(new ArrayList<>());
        }

        if (tree.children.isEmpty())
            return 1;

        int sum = 0;
        for (Node child : tree.children) {
            sum += countChild(child, cntList, depth + 1);
        }
        cntList.get(depth).add(sum);

        return sum;
    }

    static class Node {
        Node parent;
        List<Node> children = new ArrayList<>();
    }
}