import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1865 {
    static final long INF = 5_000_000;
    
    static int N, M, W;
    static Edge[] edges;
    static long[] dist;
    
    static class Edge {
        int from, to, cost;
    
        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            edges = new Edge[M + M + W];
            dist = new long[N+1];
            int idx = 0;
            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges[idx++] = new Edge(v1, v2, cost);
                edges[idx++] = new Edge(v2, v1, cost);
            }
            while (W-- > 0) {
                st = new StringTokenizer(br.readLine());
                edges[idx++] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), -Integer.parseInt(st.nextToken()));
            }
            
            initDist();
            bellmanFord();
            if (hasCycle()) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }
            sb.append("\n");
        }
        
    
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void initDist() {
        Arrays.fill(dist, INF);
    }
    
    private static void bellmanFord() {
        int count = N-1;
        while (count-- > 0) {
            for (Edge edge : edges) {
                dist[edge.to] = Math.min(dist[edge.to], dist[edge.from] + edge.cost);
            }
        }
    }
    
    private static boolean hasCycle() {
        for (Edge edge : edges) {
            if (dist[edge.from] + edge.cost < dist[edge.to]) {
                return true;
            }
        }
        return false;
    }
}
