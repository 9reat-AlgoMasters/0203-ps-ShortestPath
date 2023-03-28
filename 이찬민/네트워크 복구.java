import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static List<Node>[] map;
    static int[][] l;

    static class Node implements Comparable<Node> {
        int v;
        int weight;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new List[N + 1];
        l = new int[N+1][2];

        for (int i = 1; i < N + 1; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            map[A].add(new Node(B, C));
            map[B].add(new Node(A, C));
        }


        dijikstra(1);

        System.out.println(l.length - 2);
        for (int i = 2; i < l.length; i++) {
            System.out.println(l[i][0] + " " + l[i][1]);
        }

    }

    static void dijikstra(int start) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if(visited[node.v]) {
                continue;
            }

            visited[node.v] = true;

            for (Node next : map[node.v]) {
                if (distance[next.v] > distance[node.v] + next.weight) {
                    distance[next.v] = distance[node.v] + next.weight;

                    l[next.v] = new int[]{node.v, next.v};

                    pq.add(new Node(next.v, distance[next.v]));
                }

            }

        }

    }
}
