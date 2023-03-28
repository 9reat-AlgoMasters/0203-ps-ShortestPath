import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static Edge[] edges;
    static long[] distance;

    static class Edge {
        int s;
        int e;
        int weight;

        public Edge(int s, int e, int weight) {
            this.s = s;
            this.e = e;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new Edge[M + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(A, B, C);

        }

        boolean flag = bellmanFord(1);

        if(!flag) {
            System.out.println("-1");
        } else {
            for (int i = 2; i < distance.length ; i++) {
                if (distance[i] == Long.MAX_VALUE) {
                    System.out.println("-1");
                } else {
                    System.out.println(distance[i]);
                }
            }
        }


    }

    static boolean bellmanFord(int start) {
        distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[start] = 0;
        
        // 갱신
        for (int v = 1; v < N + 1; v++) {
            for (int e = 0; e < M; e++) {
                Edge edge = edges[e];
                if (distance[edge.s] == Long.MAX_VALUE) {
                    continue;
                }
                if (distance[edge.e] > distance[edge.s] + edge.weight) {
                    distance[edge.e] = distance[edge.s] + edge.weight;
                }
            }

        }
        
        // 한번 더돌려서 음수 사이클 확인
        for (int e = 0; e < M; e++) {
            Edge edge = edges[e];
            if (distance[edge.s] == Long.MAX_VALUE) {
                continue;
            }
            if (distance[edge.e] > distance[edge.s] + edge.weight) {
                return false;
            }
        }

        return true;

    }
}
