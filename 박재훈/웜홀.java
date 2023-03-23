import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static class Edge{
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    static int TC, N, M, W;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Edge> list;
    static long[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());
        for (int t = 0; t < TC; t++) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            W = Integer.parseInt(input[2]);

            list = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                input = br.readLine().split(" ");
                int S = Integer.parseInt(input[0]);
                int E = Integer.parseInt(input[1]);
                int T = Integer.parseInt(input[2]);
              //도로는 양방향이므로 시작, 도착 바꿔서 2개의 간선을 넣어줌
                list.add(new Edge(S, E, T));
                list.add(new Edge(E, S, T));
            }
            for (int i = 0; i < W; i++) {
                input = br.readLine().split(" ");
                int S = Integer.parseInt(input[0]);
                int E = Integer.parseInt(input[1]);
                int T = Integer.parseInt(input[2]);
              //웜홀은 시간이 줄어듬
                list.add(new Edge(S, E, T * (-1)));
            }
            //시간 범위 -10000~10000 간선 약 5000 지점수 500 => int 범위 넘을 수 있음
            distance = new long[N + 1];

            if (bellmanFord()) {
              //시간이 줄어들면서 출발위치로 돌아온다 = 음수사이클이 존재한다
                sb.append("YES\n");
            } else
                sb.append("NO\n");
        }

        System.out.println(sb);
    }

    static boolean bellmanFord(){
      //임의의 수로 채움. 최소시간을 구하는 문제가 아니라 음수 사이클이 있는지 판단하는게 중요하므로 초기화 값은 그리 중요하지 않다..?
        Arrays.fill(distance, 10000);
      //출발점은 문제에서 정해져있지 않음, 임의로 1번 지점 선택
        distance[1] = 0;

      //N-1번 동안 모든 간선을 확인하며 최소를 갱신
        for (int i = 1; i < N; i++) {
            boolean changed = false;
            for (Edge edge : list) {
                if(distance[edge.v] > distance[edge.u] + edge.w){
                    distance[edge.v] = distance[edge.u] + edge.w;
                    changed = true;
                }
            }
          //모든 간선을 도는 동안 단 한번도 갱신이 일어나지 않음 -> 최소 시간 모두 결정됨, 사이클 없음(false)을 리턴
            if(!changed) {
                return false;
            }
        }
      
      //추가적으로 한번더 간선들을 볼 때 갱신이 또 일어나면 음수 사이클 존재
        for (Edge edge : list) {
            if (distance[edge.v] > distance[edge.u] + edge.w) {
                return true;
            }
        }
        return false;
    }
}
