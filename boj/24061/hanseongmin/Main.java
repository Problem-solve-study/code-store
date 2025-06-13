import java.io.*;

/*
72828KB, 544ms

문제에서 주어진 의사코드대로 구현하여 카운트만 관리하고 출력하기
매우 교육적인 문제인듯 함
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N, K, k;
    static boolean findAns;

    public static void main(String[] args) throws Exception {
        N = nextInt(); K = nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = nextInt();
        }

        //정렬해보고
        mergeSort(arr, 0, N - 1);
        //답을 못찾았으면 -1
        if (!findAns) {
            System.out.print(-1);
        } else {
            //답을 찾았으면 출력
            StringBuilder sb = new StringBuilder();
            for (int n : arr) {
                sb.append(n).append(' ');
            }
            System.out.print(sb);
        }
    }

    //문제에서 주어진대로 구현. 답을 찾았으면 바로 종료
    static void mergeSort(int[] A, int p, int r) {
        if (findAns) return;

        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(A, p, q);
            mergeSort(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    //문제에서 주어진대로 구현. 답을 찾았으면 바로 종료
    static void merge(int[] A, int p, int q, int r) {
        if (findAns) return;
        int[] tmp = new int[r - p + 1];
        int i = p; int j = q + 1; int t = 0;
        while (i <= q && j <= r) {
            if (A[i] <= A[j]) {
                tmp[t++] = A[i++];
            } else {
                tmp[t++] = A[j++];
            }
        }

        while (i <= q) {
            tmp[t++] = A[i++];
        }

        while (j <= r) {
            tmp[t++] = A[j++];
        }

        i = p; t = 0;
        while (i <= r) {
            A[i++] = tmp[t++];
            if (++k == K) { //기존 배열에 변경을 한 횟수가 K에 도달하면 답을 찾았다고 체크하고 바로 종료
                findAns = true;
                return;
            }
        }
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
