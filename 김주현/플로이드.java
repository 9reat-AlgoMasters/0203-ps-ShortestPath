import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11404 {
    private static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static int[][] dist;
    
    
    public static void setInputFile(String path, String fileName) throws FileNotFoundException {
        String curPath = System.getProperty("user.dir");
        System.setIn(new FileInputStream(curPath + path + fileName));
    }
    public static void main(String[] args) throws IOException {
        String path = "\\solve\\tc\\";
        String fileName = "Q11404.txt";
        setInputFile(path, fileName);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        dist = new int[N][N];
        initDist();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            dist[from][to] = Math.min(dist[from][to], cost);
        }
        
        floydWarshall();
    
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j || dist[i][j] == INF) {
                    sb.append(0);
                } else {
                    sb.append(dist[i][j]);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
    
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void floydWarshall() {
        for (int via = 0; via < N; via++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i != j && dist[i][via] != INF && dist[via][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][via] + dist[via][j]);
                    }
                }
            }
        }
    }
    
    private static void initDist() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }
    }
}
