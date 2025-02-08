import java.io.*;
import java.util.*;

/*
19784KB, 260ms

무언가 효율적인 자료구조를 써야하나? 싶었는데 N에 비해 시간 제한이 너무 넉넉해서 대충 구현하면 맞을 것 같았다.
Map이 제일 먼저 떠올라 map을 사용하여 구현
 */

public class Main {
    static class Picture implements Comparable<Picture> {
        int student; //어떤 학생의 사진인지
        int time; //해당 사진이 언제 게시됐는지

        public Picture(int student, int time) {
            this.student = student;
            this.time = time;
        }

        @Override
        public int compareTo(Picture picture) { //학생을 기준으로 비내림차순 정렬
            return Integer.compare(student, picture.student);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        br.readLine();
        TreeMap<Picture, Integer> map = new TreeMap<>(); //사진, 좋아요 횟수
        int[] goodArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < goodArr.length; i++) {
            Picture picture = new Picture(goodArr[i], i);
            if (map.size() < n || map.containsKey(picture)) {
                //사진틀이 비어있거나, 꽉찼지만 이미 존재하는 학생일 경우 map 갱신
                map.put(picture, map.getOrDefault(picture, 0) + 1);
            } else {
                //최소 추천 횟수 탐색
                int min = map.values().stream().mapToInt(Integer::intValue).min().orElse(0);
                //여러 명이라면 가장 오래된 사진을 선택하기 위해 사진 게시 시간을 추가적으로 구함
                int minTime = map.entrySet().stream()
                        .filter(e -> e.getValue() == min)
                        .mapToInt(e -> e.getKey().time)
                        .min().orElse(0);

                Picture removeTarget = null;
                for (Map.Entry<Picture, Integer> entry : map.entrySet()) {
                    if (entry.getKey().time == minTime && entry.getValue() == min) {
                        removeTarget = entry.getKey();
                        break;
                    }
                }

                //제거 대상 사진을 삭제하고 새로운 사진을 게시
                map.remove(removeTarget);
                map.put(picture, map.getOrDefault(picture, 0) + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        map.forEach((k, v) -> sb.append(k.student).append(" "));
        bw.write(sb.toString());
        bw.flush();
    }
}