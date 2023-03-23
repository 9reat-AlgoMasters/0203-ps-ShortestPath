import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static class Edge {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

    }
  
    static int N, M;
    static ArrayList<Edge>[] lists;
    static long[] distance;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        lists = new ArrayList[N+1];
      //간선 수 6000 소요시간 최대 10000 도시 수 500 => int 범위 넘을 수 있음
        distance = new long[N+1];
        for (int i = 1; i <= N; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            int C = Integer.parseInt(input[2]);
            lists[A].add(new Edge(B, C));
        }

        Arrays.fill(distance, Long.MAX_VALUE);
      //1번에서 출발
        distance[1] = 0;

      //N개의 각 도시로의 최소 비용 구하는데 N-1번의 탐색 필요
        for (int s = 1; s < N; s++) {
          //N개 도시들의 간선(버스)들을 모두 탐색
            for (int i = 1; i <= N; i++) {
              //아직 i번 도시로 갈 방법이 없으면(처음 초기화한 값에서 갱신안됨) 넘김
              if (distance[i] == Long.MAX_VALUE) continue;
                int size = lists[i].size();
                for (int j = 0; j < size; j++) {
                    Edge edge = lists[i].get(j);
                  //i번 거쳐가는게 더 비용 적으면 갱신
                    if (distance[edge.v] > distance[i] + edge.w) {
                        distance[edge.v] = distance[i] + edge.w;
                    }
                }
            }
        }
      //사이클 체크. N-1번 탐색으로 다 찾아야 정상인데 한번 더 진행했을 때 또 갱신이 일어나면 음수 사이클이 있는 것.
        for (int i = 1; i <= N; i++) {
            if (distance[i] == Long.MAX_VALUE) continue;
            int size = lists[i].size();
            for (int j = 0; j < size; j++) {
                Edge edge = lists[i].get(j);
              //갱신이 일어나면 -1
                if (distance[edge.v] > distance[i] + edge.w) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            sb.append(distance[i]==Long.MAX_VALUE ? -1 : distance[i]).append('\n');
        }
        System.out.println(sb);
    }
}
