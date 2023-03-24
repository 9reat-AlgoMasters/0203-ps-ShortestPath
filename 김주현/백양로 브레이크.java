import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Q11562 {
    static final int INF = Integer.MAX_VALUE;
    static final int IMPOSSIBLE = -1;
    static int N, M;
    static int[][] dist;
    static boolean[][] canMakeRoad;


    static class Node {
        int v,cnt;

        public Node(int v, int cnt) {
            this.v = v;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N][N];
        canMakeRoad = new boolean[N][N];

        initDist();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            boolean isTwoWay = Integer.parseInt(st.nextToken()) == 1;
            dist[from][to] = 1;
            if (isTwoWay) {
                dist[to][from] = 1;
            } else {
                canMakeRoad[to][from] = true;
            }
        }

        floydWarshall();

        int queriesCnt = Integer.parseInt(br.readLine());
        while (queriesCnt-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            sb.append(findPath(from, to)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int findPath(int from, int to) {
        if (from == to) return 0;
        Deque<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        q.add(new Node(from, 0));
        visited[from] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int next = 0; next < N; next++) {
                if (!visited[next] && (canMakeRoad[now.v][next] || dist[now.v][next] != INF)) {
                    if (dist[now.v][next] == INF && canMakeRoad[now.v][next]) {
                        q.add(new Node(next, now.cnt + 1));
                    } else {
                        q.add(new Node(next, now.cnt));
                    }

                    if (next == to) {
                        return q.peekLast().cnt;
                    }
                    visited[next] = true;
                }

            }
        }

        return IMPOSSIBLE;
    }

    private static void initDist() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }
    }

    private static void floydWarshall() {
        for (int via = 0; via < N; via++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i != j && i != via && j != via && dist[i][via] != INF && dist[via][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][via] + dist[via][j]);
                    }
                }
            }
        }
    }
}
