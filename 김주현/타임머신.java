import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11657 {
    static final long INF = Long.MAX_VALUE;
    static int N, M;
    static Edge[] edges;
    static long[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];
        dist = new long[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
                edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        initDist();
        BellmanFord();
        if (hasCycle()) {
            sb.append(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                sb.append(dist[i] == INF ? -1 : dist[i]).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void BellmanFord() {
        int count = N-1;
        while (count-- > 0) {
            for (Edge edge : edges) {
                if (dist[edge.from] == INF) continue;

                dist[edge.to] = Math.min(dist[edge.to], dist[edge.from] + edge.w);
            }
        }
    }

    private static boolean hasCycle() {
        for (Edge edge : edges) {
            if (dist[edge.from] == INF) continue;

            if (dist[edge.from] + edge.w < dist[edge.to]) {
                return true;
            }
        }
        return false;
    }

    private static void initDist() {
        Arrays.fill(dist, INF);
        dist[1] = 0;
    }

    static class Edge {
        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

}
