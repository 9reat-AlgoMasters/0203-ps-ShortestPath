import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] map1;
    static int[][] map2;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map1 = new int[n + 1][n + 1]; // 최소값 저장
        map2 = new int[n + 1][n + 1]; // 최소값 일때의 정점 저장

        init();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            map1[A][B] = Math.min(map1[A][B], C);
            map1[B][A] = Math.min(map1[B][A], C);
        }

        for (int k = 1; k < n + 1; k++) { // 거치는 정점
            for (int i = 1; i < n + 1; i++) { // 시작 지점
                for (int j = 1; j < n + 1; j++) { // 다음지점
                    if (map1[i][j] > map1[i][k] + map1[k][j]) {
                        map1[i][j] = map1[i][k] + map1[k][j];
                        map2[i][j] = map2[i][k];// i->j 경로를 i->k로 수정
                    }
                }
            }
        }
        StringBuilder sb  = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == j) {
                    sb.append("-").append(" ");
                    continue;
                }
                sb.append(map2[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void init() {
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == j) {
                    map1[i][j] = 0;
                    map2[i][j] = j;
                    continue;
                }
                map1[i][j] = 200001;
                map2[i][j] = j;
            }
        }
    }
}
