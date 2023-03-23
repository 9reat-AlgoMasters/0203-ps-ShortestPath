import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_11562 {
    static int n, m, k;
  //s에서 e로 갈 때 만나는 역방향 일방통행 도로 수를 세면 됨 
    static int[][] numOfReverseOneWay;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        numOfReverseOneWay = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(numOfReverseOneWay[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int b = Integer.parseInt(input[2]);
            if(b == 0){
              //u->v일방통행 도로
              //u에서 v로 갈 때는 프리패스
              //v에서 u로 갈 때는 양방향으로 바꿔줘야 갈 수 있음 => 비용 1
                numOfReverseOneWay[u][v] = 0;
                numOfReverseOneWay[v][u] = 1;
            }else{
              //양방향 도로는 방향이 어디든 프리패스
                numOfReverseOneWay[u][v] = 0;
                numOfReverseOneWay[v][u] = 0;
            }
        }

      //모든 정점 간 비용 구하기
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i == j) continue;
                for (int k = 1; k <= n; k++) {
                    if(i == k || j == k) continue;
                  //j -> k 와 j -> i -> k 를 비교할건데 j->i, i->k로 가는 경로가 없으면 continue
                    if(numOfReverseOneWay[j][i] == Integer.MAX_VALUE
                            || numOfReverseOneWay[i][k] == Integer.MAX_VALUE) continue;
                    if(numOfReverseOneWay[j][k] > numOfReverseOneWay[j][i] + numOfReverseOneWay[i][k]){
                        numOfReverseOneWay[j][k] = numOfReverseOneWay[j][i] + numOfReverseOneWay[i][k];
                    }
                }
            }
        }

        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            int e = Integer.parseInt(input[1]);
            sb.append(numOfReverseOneWay[s][e]==Integer.MAX_VALUE ? 0 : numOfReverseOneWay[s][e]).append('\n');
        }
        System.out.println(sb);
    }
}
