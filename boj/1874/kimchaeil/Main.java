//문제: BOJ 1874 스택 수열
//메모리: 16696 KB
//시간: 140 ms

/*
 * 스택이 비어있거나 stack에 마지막에 넣은 수가 수열에서 나와야 하는 수보다 작으면 push
 * stack에 마지막에 넣은 수가 수열에서 나와야 하는 수라면 pop
 * stack에 마지막에 넣은 수가 수열에서 나와야 하는 수보다 크다면 불가능한 수열
 */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int n = nextInt(), top = -1, num = 1, now;
		int[] stack = new int[n];
		while (n-- > 0) {
			now = nextInt();
			while (top < 0 || stack[top] < now) {
				stack[++top] = num++;
				sb.append('+').append('\n');
			}
			if (stack[top] == now) {
				top--;
				sb.append('-').append('\n');
			} else
				break;
		}
		System.out.println(top < 0 ? sb : "NO");
	}

	static int nextInt() throws IOException {
		int n, c;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
