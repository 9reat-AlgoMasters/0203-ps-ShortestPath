import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1719 {
    static final int INF = Integer.MAX_VALUE;
    
    static int N, M;
    static int[][] dist;
    static int[][] paths;
    
    
    public static void setInputFile(String path, String fileName) throws FileNotFoundException {
        String curPath = System.getProperty("user.dir");
        System.setIn(new FileInputStream(curPath + path + fileName));
    }
    public static void main(String[] args) throws IOException {
        String path = "\\solve\\tc\\";
        String fileName = "Q1719.txt";
        setInputFile(path, fileName);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        dist = new int[N][N];
        paths = new int[N][N];
        initDist();
        initPath();
        
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken())-1;
            int v2 = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            
            dist[v1][v2] = w;
            paths[v1][v2] = v2;
            
            dist[v2][v1] = w;
            paths[v2][v1] = v1;
        }
        
        floydWarshall();
    
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    sb.append("-");
                } else {
                    sb.append(paths[i][j]+1);
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
    
    private static void initDist() {
        for (int i=0; i<N; i++) {
            Arrays.fill(dist[i], INF);
        }
    }
    
    private static void initPath() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(paths[i], -1);
        }
    }
    
    private static void floydWarshall() {
        for (int via = 0; via < N; via++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i != j && dist[i][via] != INF && dist[via][j] != INF
                    && dist[i][via] + dist[via][j] < dist[i][j]) {
                        dist[i][j] = dist[i][via] + dist[via][j];
                        paths[i][j] = paths[i][via];
                    }
                }
            }
        }
    }
}
