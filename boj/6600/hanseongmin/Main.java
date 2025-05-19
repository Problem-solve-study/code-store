import java.io.*;
import java.util.*;

/*
12084KB, 72ms

세 점이 주어졌으므로 원을 유일하게 결정할 수 있음
문제에서 제시하는 원은 곧 세 점을 잇는 삼각형의 외접원으로 볼 수 있고
삼각형의 세 변을 a, b, c라고, 외접원의 반지름을 r이라고 했을 때
삼각형의 넓이 = a * b * c / 4r이다.

이를 이용하여 반지름을 구하고 원주를 출력해주면 됨
삼각형의 넓이는 헤론의 공식을 사용하면 쉽게 구할 수 있음
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            double[][] points = new double[3][2];
            StringTokenizer st = new StringTokenizer(line);
            for (int i = 0; i < 3; i++) {
                points[i][0] = Double.parseDouble(st.nextToken()); points[i][1] = Double.parseDouble(st.nextToken());
            }

            //삼각형의 세 변의 길이를 계산
            ArrayList<Double> dist = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = i + 1; j < 3; j++) {
                    dist.add(getDistance(points[i], points[j]));
                }
            }

            //헤론의 공식을 이용하여 삼각형의 넓이 계산
            double area = getArea(dist.get(0), dist.get(1), dist.get(2));
            //반지름 계산
            double r = (dist.get(0) * dist.get(1) * dist.get(2)) / (4 * area);
            sb.append(String.format("%.2f", 2 * Math.PI * r)).append('\n');
        }

        System.out.print(sb);
    }

    static double getArea(double a, double b, double c) {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    static double getDistance(double[] p1, double[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }
}
