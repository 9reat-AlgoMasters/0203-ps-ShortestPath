import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static class Edge implements Comparable<Edge>{
		int v, w;

		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

    //우선순위 큐에서 가중치 적은 순으로 빼올수 있도록
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
	static int N, M, s, e;
	static PriorityQueue<Edge> pq;
	static ArrayList<Edge>[] lists;
	static int[] distance;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		lists = new ArrayList[N+1];
		for (int i = 1; i < N+1; i++) {
			lists[i] = new ArrayList<>();
		}
    
		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			int u = Integer.parseInt(input[0]);
			int v = Integer.parseInt(input[1]);
			int w = Integer.parseInt(input[2]);
			//정점 별로 연결된 점과 비용 저장
			lists[u].add(new Edge(v, w));
		}
		String[] input = br.readLine().split(" ");
		s = Integer.parseInt(input[0]);
		e = Integer.parseInt(input[1]);
		
		pq = new PriorityQueue<>();
		visited = new boolean[N+1];
		distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		distance[s] = 0;
		pq.add(new Edge(s,0));
		
		while(!pq.isEmpty()) {
      //현재 갈 수 있는 간선 중 가장 적은 비용의 간선 가져오기 
			Edge edge = pq.poll();
      //비용은 0보다 크거나 같으므로 선택된 간선의 도착점의 비용은 이후에 더 작아질 수 없음 -> 방문체크, 재방문 안하도록 함
			if(visited[edge.v]) continue;
			visited[edge.v] = true;
			if(edge.v == e) {
				System.out.println(distance[e]);
				break;
			}
      //선택한 간선의 도착점에서 출발하는 간선 탐색
			for (int i = 0; i < lists[edge.v].size(); i++) {
				Edge next = lists[edge.v].get(i);
        //현재까지 기록된 비용 vs 선택 간선 도착점을 거쳐서 가는 비용
				if(distance[next.v] > distance[edge.v] + next.w) {
					distance[next.v] = distance[edge.v] + next.w;
					next.w = distance[next.v];
					pq.offer(next);
				}
			}
		}
	}

}
