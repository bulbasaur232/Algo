import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Integer> operands;
    static List<Character> opcodes;
    static int ans;
    static boolean[] brackets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        operands = new LinkedList<>();
        opcodes = new LinkedList<>();
        ans = Integer.MIN_VALUE;

        char[] chars = br.readLine().toCharArray();
        for (char c : chars) {
            if (c >= '0' && c <= '9')
                operands.add(c - '0');
            else
                opcodes.add(c);
        }
        brackets = new boolean[operands.size() - 1];

        dfs(0);
        System.out.println(ans);
        br.close();
    }

    static void dfs(int depth) {
        if (depth >= operands.size() - 1) {
            ans = Math.max(ans, calculate());
            return;
        }

        brackets[depth] = true;
        dfs(depth + 2);
        brackets[depth] = false;
        dfs(depth + 1);
    }

    static int calculate() {
        Queue<Integer> operandQ = new ArrayDeque<>();
        Queue<Character> opcodeQ = new ArrayDeque<>();

        for (int i = 0; i < brackets.length; i++) {
            if (brackets[i]) {
                operandQ.offer(calc(operands.get(i), operands.get(i + 1), opcodes.get(i)));
            } else {
                if (i > 0 && brackets[i - 1]) {
                    opcodeQ.offer(opcodes.get(i));
                } else {
                    operandQ.offer(operands.get(i));
                    opcodeQ.offer(opcodes.get(i));
                }
            }
        }

        if (brackets.length > 0 && !brackets[brackets.length - 1]) {
            operandQ.offer(operands.get(operands.size() - 1));
        }

        if (N == 1) {
            operandQ.offer(operands.get(0));
        }

        int result = operandQ.poll();
        while (!operandQ.isEmpty()) {
            result = calc(result, operandQ.poll(), opcodeQ.poll());
        }

        return result;
    }

    static int calc(int a, int b, char opcode) {
        if (opcode == '+')
            return a + b;
        else if (opcode == '-')
            return a - b;
        else return a * b;
    }
}