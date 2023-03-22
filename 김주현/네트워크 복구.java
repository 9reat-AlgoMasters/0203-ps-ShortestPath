import java.io.*;
import java.util.*;

public class Q2211 {
    static final int INF = Integer.MAX_VALUE;
    
    static int N, M;
    static Graph g;
    static int[] dist;
    static int[] paths;
    static boolean[] visited;
    
    public static void setInputFile(String path, String fileName) throws FileNotFoundException {
        String curPath = System.getProperty("user.dir");
        System.setIn(new FileInputStream(curPath + path + fileName));
    }
    public static void main(String[] args) throws IOException {
        String path = "\\solve\\tc\\";
        String fileName = "Q2211.txt";
        setInputFile(path, fileName);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    
        g = new Graph(N);
        dist = new int[N + 1];
        paths = new int[N + 1];
        visited = new boolean[N + 1];
    
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            g.addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        init();
        dijkstra();
    
        sb.append(N - 1).append("\n");
        for (int i = 2; i <= N; i++) {
            sb.append(i).append(" ").append(paths[i]).append("\n");
        }
    
    
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    
    private static void init() {
        Arrays.fill(dist, INF);
        dist[1] = 0;
        Arrays.fill(paths, -1);
    }
    
    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
    
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (visited[now.to]) continue;
            visited[now.to] = true;
    
            for (Node next : g.adjList[now.to]) {
                if (!visited[next.to] && now.w + next.w < dist[next.to]) {
//                    System.out.printf("now : %d next : %d, 원래 : %d, 갱신 : %d\n"
//                            , now.to, next.to, dist[next.to], now.w + next.w);
                    dist[next.to] = now.w + next.w;
                    paths[next.to] = now.to;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }
    }
    
    static class Graph {
        List<Node>[] adjList;
    
        public Graph(int size) {
            adjList = new ArrayList[size + 1];
            for (int i = 1; i <= size; i++) {
                adjList[i] = new ArrayList<>();
            }
        }
    
        public void addEdge(int v1, int v2, int w) {
            adjList[v1].add(new Node(v2, w));
            adjList[v2].add(new Node(v1, w));
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
