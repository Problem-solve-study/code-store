//제출번호: 93228856 | 입출력 최적화: 93229000
//메모리:   26244 KB | 입출력 최적화: 23520 KB 
//실행시간: 260 ms   | 입출력 최적화: 228 ms    
import java.io.*;

//구간 내 값이 변화하면서, 특정 구간의 합을 구하는 문제니까
//이건 세그먼트 트리를 써서 푸는 문제구나 바로 알았음
//하지만 여기서는 구간의 합만 구하는 문제니까
//펜윅트리를 이용해서 구현해보기로 함  
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static long[] fwtree;
    static int n;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        int q = nextInt();

		//펜윅 트리의 공간을 할당, 0번째 인덱스는 사용하지 않고, 1~n번째 인덱스만 사용
        fwtree = new long[n + 1];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int type = nextInt();
            
			//타입이 1이라면 업데이트
            if (type == 1) {
                int p = nextInt();
                int x = nextInt();

                update(p, x);
            } else {
				//타입이 2라면 계산하기
                int a = nextInt();
                int b = nextInt();
				
				//query는 1~i까지 구하는 함수이기 때문에
				//[a, b] = [1, b] - [1, a-1]를 이용해서 결과를 계산함
                sb.append(query(b) - query(a - 1)).append('\n');
            }
        }

        System.out.print(sb);
    }

	//펜윅트리의 업데이트
    static void update(int p, long val) {
		//펜윅트리의 범위를 벗어나지 않는 동안에
        while (p <= n) {
            fwtree[p] += val; //p 위치에 val을 업데이트
            p += p & -p; //p의 가장 끝 bit를 구해서 p에 더함
        }
    }

	//펜윅트리의 쿼리, [1, p] 구간의 값을 구함
    static long query(int p) {
        long res = 0;

		//펜윅트리의 범위를 벗어나지 않는 동안에
        while (p != 0) {
            res += fwtree[p]; //펜윅 트리의 값을 결과에 더함
            p -= p & -p; //p의 가장 끝 bit를 구해서 p에서 뺌
        }

        return res;
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}

/* 비재귀 세그트리를 이용한 방법
//제출번호: 93229728 | 입출력 최적화: 93229764
//메모리:   34956 KB | 입출력 최적화: 32352 KB 
//실행시간: 264 ms   | 입출력 최적화: 212 ms 
import java.io.*;

public class Main {
    
    static long[] segtree;
    static int n, p;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        p = 1;
        while (p < n) {
            p <<= 1;
        }

        segtree = new long[p << 1];

        int q = nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int type = nextInt();
            
            if (type == 1) {
                int a = nextInt() - 1;
                int x = nextInt();

                update(a, x);
            } else {
                int a = nextInt() - 1;
                int b = nextInt() - 1;

                sb.append(query(a, b)).append('\n');
            }
        }

        System.out.print(sb);
    }

    static void update(int pos, long val) {
        pos += p;

        while (pos != 0) {
            segtree[pos] += val;
            pos >>= 1;
        }
    }

    static long query(int l, int r) {
        long res = 0;

        l += p;
        r += p;
        while (l <= r) {
            if ((l & 1) == 1) {
                res += segtree[l++];
            }
            if ((r & 1) == 0) {
                res += segtree[r--];
            }

            l >>= 1;
            r >>= 1;
        } 

        return res;
    }

    static int nextInt() throws IOException {
        int c = System.in.read();
        
        boolean isMinus = c == '-';
        int n = (isMinus ? c = System.in.read() : c) & 15;
        c = System.in.read();
        
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        
        return isMinus ? -n : n;
    }
}
 */