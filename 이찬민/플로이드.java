import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] map1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        map1 = new int[n + 1][n + 1];

        init();

        StringTokenizer st = null;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            map1[A][B] = Math.min(map1[A][B], C);
        }

        for (int k = 1; k < n + 1; k++) { // 거치는 정점
            for (int i = 1; i < n + 1; i++) { // 시작 지점
                if (i == k) {
                    continue;
                }
                for (int j = 1; j < n + 1; j++) { // 다음지점
                    if (i == j || j == k) {
                        continue;
                    }
                    if (map1[i][j] > map1[i][k] + map1[k][j]) {
                        map1[i][j] = map1[i][k] + map1[k][j];
                    }
                }
            }
        }
        
        StringBuilder sb  = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (map1[i][j] == Integer.MAX_VALUE/2 - 1) {
                    map1[i][j] = 0;
                }
                sb.append(map1[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void init() {
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i != j) {
                    map1[i][j] = Integer.MAX_VALUE/2 - 1;
                }
            }
        }
    }
}
