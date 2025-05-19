// 11572KB 64ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        String maxPocketmon = null;
        int maxCount = 0;
        int sumCount = 0;
        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            st = new StringTokenizer(br.readLine());
            int needs = Integer.parseInt(st.nextToken());
            int candies = Integer.parseInt(st.nextToken());

            int count = 0;
            while (candies >= needs) {
                candies -= needs;
                candies += 2;
                count++;
            }

            sumCount += count;
            if (count > maxCount) {
                maxCount = count;
                maxPocketmon = name;
            }
        }

        System.out.println(sumCount);
        System.out.println(maxPocketmon);
    }
}
