import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;

    static int[][] change; // 단방향을 양방향으로 바꾼 횟수 저장


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        change = new int[n + 1][n + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i != j) {
                    change[i][j] = 12345;
                }

            }

        }
        
        // 일단 전부 양방향으로 바꾼다고 생각하고 시작
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if (C == 0) {
                change[A][B] = 0;
                change[B][A] = 1;
            }
            if (C == 1) {
                change[A][B] = 0;
                change[B][A] = 0;
            }
        }



        solve();
        StringBuilder sb = new StringBuilder();
        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(change[s][e]).append("\n");
        }

        System.out.println(sb.toString());

    }

    static void solve() {
        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                if (i == k) {
                    continue;
                }
                for (int j = 1; j < n + 1; j++) {
                    if (i == j || j == k) {
                        continue;
                    }

                    if(change[i][j] > change[i][k] + change[k][j]) {
                        change[i][j] = change[i][k] + change[k][j];
                    }
                }
                
            }
        }
    }
}
