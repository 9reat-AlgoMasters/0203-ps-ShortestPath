import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1865_2 {
    static final long INF = 10_000_000L;
    
    static int N, M, W;
    static long[][] dist;
    
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
            dist = new long[N+1][N+1];
    
            initDist();
            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                dist[v1][v2] = Math.min(dist[v1][v2], cost);
                dist[v2][v1] = Math.min(dist[v2][v1], cost);
            }
            while (W-- > 0) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                dist[v1][v2] = Math.min(dist[v1][v2], -cost);
            }
            
            floydWarshall();
            
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
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
        }
    
        for (int i = 1; i <= N; i++) {
            dist[i][i] = 0;
        }
    }
    
    private static void floydWarshall() {
        for (int via = 1; via <= N; via++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i!=via && j!=via && dist[i][via] != INF && dist[via][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][via] + dist[via][j]);
                    }
                }
            }
        }
    }
    
    private static boolean hasCycle() {
        for (int i = 1; i <= N; i++) {
            if (dist[i][i] < 0) {
                return true;
            }
        }
        return false;
    }
}
