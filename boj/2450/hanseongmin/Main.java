import java.io.*;
import java.util.*;

/*
 * 34712KB, 356ms
 * 
 * N이 십만이니 NlogN 혹은 N에 풀어야할 것 같았는데 DP는 아닌거 같아 보여서 그리디일것 같다는 생각을 했다.
 * 모양이 3개밖에 없으니 같은 모양들끼리 연속하게 배치를 할 경우 나올 수 있는 경우의 수는
 * 1 2 3, 1 3 2, 2 1 3, 2 3 1, 3 1 2, 3 2 1 이렇게 6개까지의 경우의 수밖에 없으니
 * 해당 경우의 수에 맞게 일단 배치를 해보고 그 중에서 최솟값을 가져오면 된다.
 * 
 * 값을 스왑할 때 그리디하게 스왑해야했었는데 이걸 고려하지 못해서 1틀
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int N;
	static int[] arr, cnt;
	
	public static void main(String[] args) throws Exception {
		N = nextInt();
		arr = new int[N];
		cnt = new int[4];
		for (int i = 0; i < N; i++) {
			arr[i] = nextInt();
			if (arr[i] == 1) cnt[1]++;
			else if (arr[i] == 2) cnt[2]++;
			else cnt[3]++;
		}
		
		int res = Integer.MAX_VALUE;
		//배치 가능한 모든 조합을 보고 그 중 최솟값을 가져옴
		res = Math.min(res, simul(1, 2));
		res = Math.min(res, simul(1, 3));
		res = Math.min(res, simul(2, 1));
		res = Math.min(res, simul(2, 3));
		res = Math.min(res, simul(3, 1));
		res = Math.min(res, simul(3, 2));
		System.out.print(res);
	}
	
	static int simul(int a, int b) {
		//배열의 깊은 복사
		int[] temp = new int[N];
		for (int i = 0; i < N; i++) {
			temp[i] = arr[i];
		}
		
		//먼저 배열의 맨 앞쪽부터 a로 채운다.
		//입력을 받으면서 1, 2, 3의 개수를 카운팅했기 때문에 1의 개수, 2의 개수, 3의 개수는 알고 있는 상태다.
		//배열의 앞단을 a로 채운다면 맨 처음부터 a의 개수만큼의 원소들이 a로 채워져야 하며
		//이때 스왑 대상은 원소들은 b를 우선적으로 옮겨줘야한다.
		//스왑의 순서는 자신이 마음대로 결정할 수 있으므로 b를 우선적으로 옮겨줘야 추후 배열의 중간 부분을 b로 채울 때
		//스왑이 적게 일어난다.
		int res = 0;
		//채울 배열의 인덱스는 0
		int arrIdx = 0;
		//cnt[a] ~ N까지 중 a와 동일한 인덱스를 찾는다. 이 원소들이 0 ~ cnt[a] 원소들과 스왑될 것이다.
		ArrayList<Integer> list = find(cnt[a], a, temp);
		//b를 먼저 옮기기 위해 0 ~ cnt[a]까지의 원소 중 b와 c를 나눠서 큐에 넣는다.
		Queue<Integer> bQ = new ArrayDeque<>();
		Queue<Integer> cQ = new ArrayDeque<>();
		for (int i = 0; i < cnt[a]; i++) {
			if (temp[i] == b) {
				bQ.add(i);
			} else {
				cQ.add(i);
			}
		}
		
		for (int i = 0; i < list.size(); i++, arrIdx++) {
			//범위 내에 있는 원소가 이미 a와 동일하다면 이 원소는 옮길 필요가 없다.
			if (temp[arrIdx] == a) {
				i--;
				continue;
			}
			res++;
			
			//옮길 때 b를 우선적으로 옮긴다.
			int cur = bQ.isEmpty() ? cQ.remove() : bQ.remove();
			temp[cur] ^= temp[list.get(i)];
			temp[list.get(i)] ^= temp[cur];
			temp[cur] ^= temp[list.get(i)];
		}
		
		//그 다음 배열의 중간 부분을 b로 채운다.
		arrIdx = cnt[a];
		//그 다음 스왑 대상이 될 b들은 cnt[a] + cnt[b] ~ N의 범위에 있을 것이니 그 범위에서 탐색한다.
		list = find(cnt[a] + cnt[b], b, temp);
		for (int i = 0; i < list.size(); i++, arrIdx++) {
			if (temp[arrIdx] == b) {
				i--;
				continue;
			}
			res++;
			temp[arrIdx] ^= temp[list.get(i)];
			temp[list.get(i)] ^= temp[arrIdx];
			temp[arrIdx] ^= temp[list.get(i)];
		}
		
		//여기까지 끝났다면 배열의 맨 앞단을 a, 중간 부분을 b로 채웠으니
		//자연스레 배열의 맨 마지막 부분은 c로 채워져있을 것이고 조건에 맞게 스왑한 것임 
		return res;
	}
	
	static ArrayList<Integer> find(int startIdx, int v, int[] arr) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = startIdx; i < N; i++) {
			if (arr[i] == v) {
				list.add(i);
			}
		}
		
		return list;
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}