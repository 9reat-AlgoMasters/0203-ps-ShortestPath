import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int n, m;
	static int[][] distance;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		distance = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i < m; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
      //a b 노선 여러개이면 비용 적은 것을 기록
			if(distance[a-1][b-1] > c ) {
				distance[a-1][b-1] = c;
			}
		}
		
    //j -> i -> k vs j->k
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i == j) continue;
				for (int k = 0; k < n; k++) {
					if(i == k || j == k) continue;
          //아직 안 가본 경로라면 넘겨도 됨
					if(distance[j][i] == Integer.MAX_VALUE
							||distance[i][k] == Integer.MAX_VALUE) continue;
					if(distance[j][k] > distance[j][i] + distance[i][k]) {
						distance[j][k] = distance[j][i] + distance[i][k];
					}
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
        //갱신 한번도 안된 곳은 경로 없는것
				sb.append(distance[i][j] == Integer.MAX_VALUE ? 0 : distance[i][j]).append(" ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
