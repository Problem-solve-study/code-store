import java.io.*;
import java.util.*;

/*
13136KB, 84ms

단순한 가위바위보 시뮬레이션 문제
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T --> 0) {
            int N = Integer.parseInt(br.readLine());
            TreeMap<Integer, String> map = new TreeMap<>();
            for (int i = 1; i <= N; i++) {
                map.put(i, br.readLine());
            }

            //현재 라운드에서 가위, 바위, 보를 낸 사람들을 저장하는 리스트
            ArrayList<Integer> rList = new ArrayList<>();
            ArrayList<Integer> sList = new ArrayList<>();
            ArrayList<Integer> pList = new ArrayList<>();
            //현재 라운드에서 패배한 사람들을 저장하는 리스트
            ArrayList<Integer> deleteTargets = new ArrayList<>();
            outer:
            for (int i = 0; map.size() > 1; i++) {
                rList.clear(); sList.clear(); pList.clear(); deleteTargets.clear();
                //현재까지 생존한 로봇들을 대상으로 가위, 바위, 보를 낸 사람들을 저장
                for (Map.Entry<Integer, String> robot : map.entrySet()) {
                    int idx = robot.getKey();
                    String str = robot.getValue();
                    if (str.length() == i) break outer;
                    if (str.charAt(i) == 'R') {
                        rList.add(idx);
                    } else if (str.charAt(i) == 'S') {
                        sList.add(idx);
                    } else {
                        pList.add(idx);
                    }
                }

                if (rList.isEmpty() && !sList.isEmpty()) { //보가 지는 경우
                    deleteTargets.addAll(pList);
                } else if (sList.isEmpty() && !pList.isEmpty()) { //바위가 지는 경우
                    deleteTargets.addAll(rList);
                } else if (pList.isEmpty() && !rList.isEmpty()) { //가위가 지는 경우
                    deleteTargets.addAll(sList);
                }

                //진 로봇이 있다면 map에서 삭제
                for (int idx : deleteTargets) {
                    map.remove(idx);
                }
            }

            sb.append(map.size() == 1 ? (int) map.firstKey() : 0).append('\n');
        }
        System.out.print(sb);
    }
}
