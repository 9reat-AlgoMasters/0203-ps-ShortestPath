import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 벨만 포드 참고 블로그 https://chb2005.tistory.com/79
// 참고 유튜브 https://www.youtube.com/watch?v=Ppimbaxm8d8
public class Main {
	static int N;
	static int M;
	static Edge[] edges;
	static long distance[]; // 500 * 6000 * 10000 int 범위를 넘는다.

	static class Edge {
		int s;
		int e;
		int w;

		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		edges = new Edge[M];

		distance = new long[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(s, e, w);
		}
		init(); // 초기화
		// 전체 N-1번의 라운드 반복
		for (int i = 0; i < N - 1; i++) {

			// 매 반복마다 모든 간선 확인
			for (Edge edge : edges) {

				if (distance[edge.s] == Long.MAX_VALUE) {
					continue;
				}
				
				// 현재 간선을 거쳐서 다른 노드로 이동하는 경우가 더 짧은 경우
				if (distance[edge.e] > distance[edge.s] + edge.w) {
					// 갱신
					distance[edge.e] = distance[edge.s] + edge.w;
					// n번째 라운드에서도 값이 갱신된다면 음수 순환이 존재
				}

			}
		}
		for (Edge edge : edges) {// n번째 라운드에서도 값이 갱신된다면 음수 순환이 존재
			// 현재 간선을 거쳐서 다른 노드로 이동하는 경우가 더 짧은 경우

			if (distance[edge.s] == Long.MAX_VALUE) {
				continue;
			}
			// 현재 간선을 거쳐서 다른 노드로 이동하는 경우가 더 짧은 경우
			if (distance[edge.e] > distance[edge.s] + edge.w) {
				System.out.println("-1");
				return;
			}

		}

		for (int i = 2; i < N + 1; i++) {
			if (distance[i] == Long.MAX_VALUE) {
				System.out.println("-1");
			} else {
				System.out.println(distance[i]);
			}
		}
	}

	private static void init() {
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[1] = 0;
	}

}