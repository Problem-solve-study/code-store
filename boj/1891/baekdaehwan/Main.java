/**
 * 11560KB	64ms
 * 분할정복...? 인거 같긴 한데, 다른 방법이 보여서 비트연산으로 풀었습니다.
 * 
 * [사고 흐름]
 * 사분면 관련 문제가 으레 그렇듯 분할 정복이 정해인것 같다.
 * 근데 x, y를 비트로 나타낸 길이가 조각 번호의 길이와 일치하는 것으로 보아, i번째 문자를 i번째 비트로 움직이면 될것 같다.
 * 재밌겠다. 당장 구현하자
 * 
 * 
 * [알고리즘 설명]
 * 예시 없이는 설명이 힘듦으로, 예시를 들겠습니다.
 * 
 * 최초 사분면 : 341
 * x : 3
 * y : 0
 * 이라고 가정하겠습니다.
 * x를 비트로 나타내면, 011이라고 할 수 있습니다. 즉, len('011') == len('341')이기 때문에 각 비트를 각 사분면과 짝지을 수 있습니다.
 * 
 * 이제 한 비트와 어떻게 연관되는지 알아보겠습니다. 현재 확인 중인 부분을 괄호로 표현하겠습니다.
 * [1]
 *  3  4 (1)
 *  0  1 (1)
 * 사분면이 1, 비트가 1입니다. 비트가 1이기에 해당 인덱스의 사분면은 1 => 2로 바뀌어야 합니다.
 * 하지만 1 => 2인 경우 상위의 사분면에도 영향을 주게 됩니다. 그렇기 때문에 바로 다음 비트에 1을 더해줍니다.
 *  3  4 (1)
 *  1  0 (1)
 * 그 결과 위와 같아집니다.
 * 
 * [2]
 *  3 (4) 2
 *  1 (0) 1
 * 사분면이 4, 비트가 0입니다. 비트가 0이기에 넘어갑니다.
 * 
 * [3]
 * (3) 4  2
 * (1) 0  1
 * 사분면이 3, 비트가 1입니다. 비트가 1이기에 해당 인덱스의 사분면은 3 => 4로 바뀌어야 합니다.
 * 3 => 4의 경우 상위의 사분면에는 영향을 주지 않기 때문에 다음 비트에 1을 더하지 않습니다.
 *  4  4  2
 * 그 결과 위와 같아집니다. 따라서 답은 442입니다. 
 * 
 * 
 * 이 개념을 y축과 음수에도 적용하면 아래와 같이 구현됩니다.
 */

import java.io.*;
import java.util.*;

public class Main {
	static final long LSB = 1l;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(st.nextToken());
		char[] cur = st.nextToken().toCharArray();
		
		st = new StringTokenizer(br.readLine());
		long x = Long.parseLong(st.nextToken());
		long y = Long.parseLong(st.nextToken());
		boolean sx = x>=0;
		boolean sy = y>=0;
		x = Math.abs(x);
		y = Math.abs(y);
		
		for (int i=0; i<d; ++i) {
            // 현재 비트가 1이라면 사분면 변경을 수행합니다.
			if ((x&LSB) == 1) {
				int idx = d-1-i;
				if (sx) {
                    // 만약 현재 확인중인 사분면이 2나 3이었다면 1이나 4로 변경합니다.
					if (cur[idx] == '2') cur[idx] = '1';
					else if (cur[idx] == '3') cur[idx] = '4';

                    // 만약 현재 확인중인 사분면이 1이나 4라면 사분면 변경과 함깨, 다음 비트에 1을 더해야 합니다.
					else {
						if (cur[idx] == '1') cur[idx] = '2';
						else if (cur[idx] == '4') cur[idx] = '3';
                        // 다음 비트에 1을 더하는 부분입니다.
						x += 2;
					}
				}
				else {
					if (cur[idx] == '1') cur[idx] = '2';
					else if (cur[idx] == '4') cur[idx] = '3';
					else {
						if (cur[idx] == '2') cur[idx] = '1';
						else if (cur[idx] == '3') cur[idx] = '4';
						x += 2;
					}
				}
			}
            // 다음 비트로 넘어갑니다.
			x>>=1;
		}

		for (int i=0; i<d; ++i) {
			if ((y&LSB) == 1) {
				int idx = d-1-i;
				if (sy) {
					if (cur[idx] == '4') cur[idx] = '1';
					else if (cur[idx] == '3') cur[idx] = '2';
					else {
						if (cur[idx] == '1') cur[idx] = '4';
						else if (cur[idx] == '2') cur[idx] = '3';
						y += 2;
					}
				}
				else {
					if (cur[idx] == '1') cur[idx] = '4';
					else if (cur[idx] == '2') cur[idx] = '3';
					else {
						if (cur[idx] == '4') cur[idx] = '1';
						else if (cur[idx] == '3') cur[idx] = '2';
						y += 2;
					}
				}
			}
			y>>=1;
		}
		
        // 비트 시프팅으로 인한 x와 y의 값 감소가 이루어졌지만, x나 y가 양수라면 더 상위의 사분면에서 이동해야 한다는 뜻입니다.
        // 그것은 불가능하니 -1을 출력합니다.
		if (0<x || 0<y) {
			System.out.println(-1);
		}
		else {
			StringBuilder res = new StringBuilder();
			for (char c: cur) res.append(c);
			System.out.println(res);
		}
    }
}
