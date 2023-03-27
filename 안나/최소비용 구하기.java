import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;//정점의 개수
	static int M;//버스의 개수
	static int start;
	static int end;
	static int cost[];
	static boolean checked[];
	static class Edge{
		int e;
		int w;
		
		public Edge(int e, int w) {
			this.e = e;
			this.w = w;
			
		}
	}
	static ArrayList<Edge> [] adj;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			adj[i] = new ArrayList<>();
		}
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[s].add(new Edge(e, w));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		cost= new int[N+1];
		checked = new boolean[N+1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		cost[start]=0;

		dijkstra();
		System.out.println(cost[end]);
		
	}
	private static void dijkstra() {
		for (int i = 1; i <= N; i++) {
			int min = Integer.MAX_VALUE;
			int w =-1;
			
			for (int j = 1; j <= N; j++) {
				if(!checked[j] && min>cost[j]) {
					min = cost[j];
					w = j;
				}
			}
			
			if(w == -1) {
				break;
			}
			
			checked[w] = true;
			
			for (Edge next : adj[w]) {
				if(!checked[next.e] && cost[next.e]>cost[w] + next.w) {
					cost[next.e] =cost[w] + next.w;
				}
			}
//			System.out.println("------- "+i+"=========");
//			System.out.println("cost : "+ Arrays.toString(cost));
//			System.out.println("checked : "+ Arrays.toString(checked));
//			System.out.println();
		}	
		
	}
}