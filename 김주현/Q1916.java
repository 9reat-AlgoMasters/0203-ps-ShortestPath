import java.io.*;
import java.util.*;

public class Q1916 {
    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static int start, end;
    static Graph g;
    static int[] dist;
    static boolean[] visited;


    public static void setInputFile(String path, String fileName) throws FileNotFoundException {
        String curWorkingDir = System.getProperty("user.dir");
        System.setIn(new FileInputStream(curWorkingDir + path + fileName));
    }
    public static void main(String[] args) throws IOException {
        String remainPath = "\\solve\\tc\\";
        String fileName = "Q1916.txt";
        setInputFile(remainPath, fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        g = new Graph(N);
        dist = new int[N + 1];
        visited = new boolean[N + 1];

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            g.addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        initDist();
        dijkstra();

        sb.append(dist[end]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (visited[now.to]) continue;
            visited[now.to] = true;

            for (Node next : g.adjList[now.to]) {
                if (!visited[next.to] && dist[now.to] + next.w < dist[next.to]) {
                    dist[next.to] = dist[now.to] + next.w;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }
    }

    private static void initDist() {
        Arrays.fill(dist, INF);
        dist[start] = 0;
    }

    static class Graph {
        List<Node>[] adjList;

        public Graph(int size) {
            adjList = new List[size + 1];
            for (int i = 1; i <= size; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        public void addEdge(int from, int to, int w) {
            adjList[from].add(new Node(to, w));
        }
    }

    static class Node implements Comparable<Node> {
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
