//48776KB, 360ms

import java.io.*;
import java.util.*;

public class Main {
    public static class Meeting implements Comparable<Meeting> {
        int start;
        int end;
        int people;

        public Meeting(int start, int end, int people) {
            this.start = start;
            this.end = end;
            this.people = people;
        }

        @Override
        public int compareTo(Meeting meeting) {
            int comp1 = Integer.compare(start, meeting.start);
            int comp2 = Integer.compare(end, meeting.end);
            int comp3 = Integer.compare(people, meeting.people);

            if (comp1 != 0)
                return comp1;
            if (comp2 != 0)
                return comp2;
            return comp3;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Meeting[] meetings = new Meeting[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i] = new Meeting(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        Arrays.sort(meetings);
        int[][] dp = new int[n][2];
        dp[0][0] = 0; dp[0][1] = meetings[0].people;;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + meetings[i].people;
        }

        bw.write(String.valueOf(Math.max(dp[n - 1][0], dp[n - 1][1])));
        bw.flush();
    }
}