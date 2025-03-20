//문제: https://www.acmicpc.net/problem/17420
//메모리: 25152 KB
//시간: 448 ms

import java.io.*;
import java.util.*;

class Gifticon {
	int remain;
	int useDate;

	public Gifticon(int remain) {
		this.remain = remain;
	}
}

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		Gifticon[] gifticons = new Gifticon[n];
		for (int i = 0; i < n; i++)
			gifticons[i] = new Gifticon(nextInt());
		for (int i = 0; i < n; i++)
			gifticons[i].useDate = nextInt();
		Arrays.sort(gifticons, (o1, o2) -> o1.useDate - o2.useDate);

		long result = 0;
		while (gifticons[0].useDate > gifticons[0].remain) {
			gifticons[0].remain += 30;
			result++;
		}
		int maxRemain = gifticons[0].remain;
		int prevMaxRemain = 0;
		for (int i = 1; i < n; i++) {
			Gifticon now = gifticons[i];
			prevMaxRemain = gifticons[i - 1].useDate != now.useDate ? maxRemain : prevMaxRemain;
			int s = prevMaxRemain > now.useDate ? prevMaxRemain : now.useDate;
			if (now.remain < s) {
				int count = (s - now.remain - 1) / 30 + 1;
				result += count;
				now.remain += 30 * count;
			}
			maxRemain = now.remain > maxRemain ? now.remain : maxRemain;
		}
		System.out.println(result);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
