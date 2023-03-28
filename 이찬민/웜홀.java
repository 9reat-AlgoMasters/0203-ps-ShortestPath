import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int W;
    static List<Edge> edges;

    static class Edge {
        int s;
        int e;
        int t;

        public Edge(int s, int e, int t) {
            this.s = s;
            this.e = e;
            this.t = t;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int t = 0; t < TC; t++) {
            edges = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            int s = 0;
            int e = 0;
            int time = 0;
            // 일반도로와 웜훨 input을 받아들이는 것을 합칠수도 있으나 보기 편하게 하기위해 나누어줌
            // 일반 도로
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                s = Integer.parseInt(st.nextToken());
                e = Integer.parseInt(st.nextToken());
                time = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, time));
                edges.add(new Edge(e, s, time));
            }
            
            //웜홀
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                s = Integer.parseInt(st.nextToken());
                e = Integer.parseInt(st.nextToken());
                time = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s,e, time*(-1)));
            }

            boolean flag = bellmanFord();

            if (flag) {
                System.out.println("YES");
            }
            if (!flag) {
                System.out.println("NO");
            }


        }
    }

    // 모든 정점에서 시작해서 출발점으로 돌아온는 경우 출발했을때보다 적은 경우 구하기
    // 여기서 distance의 의미는 정점v를 방문했을때 최소값이 distance[v]에 나온 값 (Integer.Max_VALUE 주의)
    // 모든정점을 다확인하면 O(N^2M)이 됨
    // 줄이려면??
    // 이문제에서 웜홀은 음수간선을 가지게하는 값, 출발점에 돌아왔을때 값이 줄어들었다는것은? => 음수 싸이클 발생했다는것
    // 그런데 벨만포드에선 distance배열에서 초기화 값이 나오면 넘어가게함(방문 될수 없는곳을 들려서 최소값이 갱신되는경우 때문에)

    // 1. 이문제에선 시작정점을 아무 곳이나 잡는다
    // 2. 음수 사이클을 확인하면됨 => 즉, 어디서 시작하든 음수 사이클을 확인할수있다면?? 한번만 돌려도 된다는 것
    //      사이클 유무만 파악할 때는 dist[]를 통해 거리를 구하는 게 아니라 마지막 한번 더 돌릴때 값의 변동에 의해
    //      음수 사이클이 존재하는지만 확인하면됨 (그래프가 단절?되있고 거기서 음수사이클이 발생한다해도 결국 마지막엔 값의 변동으로 확인 가능)
    // 3. 초기화값을 넘어가지 않고 밸만 포드를 돌리면 모두 방문한 상태로 보고 값의 변동을 distance에 담아줌(음수사이클이 이딨든 찾기 가능)
    // 4. 음수 사이클 확인
    static boolean bellmanFord () {
        int[] distance = new int[N + 1];
        

        for (int i = 1; i < N + 1; i++) {
            for (Edge edge : edges) {
                if (distance[edge.e] > distance[edge.s] + edge.t) {
                    distance[edge.e] = distance[edge.s] + edge.t;
                    if (i == N) {
                        return true;
                    }
                }

            }
        }
        
        return false;
    }
}
