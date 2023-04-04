import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q22116 {
    static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static final int INF = Integer.MAX_VALUE;
    
    static int N;
    static int[][] map;
    static int[][] dist;
    static boolean[][] visited;
    
    static class Node implements Comparable<Node> {
        int x, y;
        int cost;
    
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dist = new int[N][N];
        visited = new boolean[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dijkstra();
    
        sb.append(dist[N - 1][N - 1]);
    
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 0));
    
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (visited[now.x][now.y]) {
                continue;
            }
            dist[now.x][now.y] = now.cost;
            visited[now.x][now.y] = true;
            
            for (int[] d : DIR) {
                int nextX = now.x + d[0];
                int nextY = now.y + d[1];
    
                if (isInRange(nextX, nextY) && !visited[nextX][nextY]) {
                    int edgeCost = Math.abs(map[now.x][now.y] - map[nextX][nextY]);
                    pq.add(new Node(nextX, nextY, Math.max(edgeCost, dist[now.x][now.y])));
                }
            }
            
        }
    }
    
    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
