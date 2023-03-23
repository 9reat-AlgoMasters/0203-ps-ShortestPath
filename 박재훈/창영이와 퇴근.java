import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static class Point implements Comparable<Point>{
		int r, c, w;

		public Point(int r, int c, int w) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
	static int N;
	static int[][] map;
	static int[][] height;
	static boolean[][] visited;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static PriorityQueue<Point> pq;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		height = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(height[i], Integer.MAX_VALUE);
		}
		
		pq = new PriorityQueue<>();
		pq.add(new Point(0, 0, 0));
		height[0][0] = 0;
		
		while(!pq.isEmpty()) {
			Point p = pq.poll();
      if(p.r==N-1 && p.c==N-1) break;
			if(visited[p.r][p.c]) continue;
			visited[p.r][p.c] = true;
			for (int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				if(!check(nr,nc)) continue;
				if(visited[nr][nc]) continue;
				height[nr][nc] = 
						Math.max(height[p.r][p.c], 
								Math.min(height[nr][nc], Math.abs(map[nr][nc]-map[p.r][p.c])));
				pq.add(new Point(nr, nc, height[nr][nc]));
			}
		}
		System.out.println(height[N-1][N-1]);
	}
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
