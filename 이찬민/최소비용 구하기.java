import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static List<Node>[] graph;
    static int start;
    static int end;

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
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new List[N+1];

        for (int i = 1; i < N + 1; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        String[] temp = br.readLine().split(" ");
        start = Integer.parseInt(temp[0]);
        end = Integer.parseInt(temp[1]);

        dijikstra(start, end);

    }

    static void dijikstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean [] check = new boolean[N + 1];

        pq.add(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if (check[node.v]) {
                continue;
            }

            check[node.v] = true;

            for (Node n : graph[node.v]) {
                if (distance[n.v]  > distance[node.v] + n.weight) {
                    distance[n.v] = distance[node.v] + n.weight;
                    pq.add(new Node(n.v, distance[n.v]));
                }

            }
        }

        System.out.println(distance[end]);

    }
}
