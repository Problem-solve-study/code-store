//36692KB, 220ms

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int n;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static int[] distances;
    static HashSet<Integer> cyclePath = new HashSet<>();
    static HashSet<Integer> connectStations = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        adj = IntStream.rangeClosed(0, n).mapToObj(ArrayList::new).toArray(ArrayList[]::new);
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            adj[input[0]].add(input[1]);
            adj[input[1]].add(input[0]);
        }

        //먼저 사이클 경로를 찾음
        visited = new boolean[n + 1];
        findCyclePath(1, 0, new ArrayList<>());

        //지선 -> 순환선으로 가는 역의 간선은 반드시 3개 이상
        for (int station : cyclePath) {
            if (adj[station].size() > 2) {
                connectStations.add(station);
            }
        }

        //순환선과 지선을 연결하는 역들로부터 거리 계산
        distances = new int[n + 1];
        visited = new boolean[n + 1];
        for (int connectStation : connectStations) {
            findDistance(connectStation, 0);
        }

        IntStream.rangeClosed(1, n).forEach(d -> sb.append(distances[d]).append(" "));
        bw.write(sb.toString());
        bw.flush();
    }

    public static void findCyclePath(int station, int prevStation, ArrayList<Integer> path) {
        if (!cyclePath.isEmpty())
            return;

        visited[station] = true;
        path.add(station);

        for (int nextStation : adj[station]) {
            if (!visited[nextStation]) {
                findCyclePath(nextStation, station, path);
            } else {
                if (nextStation != prevStation && cyclePath.isEmpty()) {
                    path.add(station);
                    for (int i = path.size() - 1; i >= 0; i--) {
                        cyclePath.add(path.get(i));
                        if (path.get(i) == nextStation) {
                            return;
                        }
                    }
                }
            }
        }

        path.remove(path.size() - 1);
    }

    public static void findDistance(int station, int distance) {
        visited[station] = true;
        distances[station] = distance;

        for (int nextStation : adj[station]) {
            if (!visited[nextStation] && !cyclePath.contains(nextStation)) {
                findDistance(nextStation, distance + 1);
            }
        }
    }
}