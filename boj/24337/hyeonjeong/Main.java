import java.io.*;


// 어렵다!
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int a = nextInt();
        int b = nextInt();

        int[] buildings = new int[n];
        for (int i = 0; i < n; i++) {
            buildings[i] = 1;
        }

        // a + b - 1이 n보다 크면 건물을 세울 수 없음
        if (a + b - 1 > n) {
            System.out.println(-1);
            return;
        }

        // b가 보는 건물부터 내림차순으로 세우기
        for (int i = 0; i < b; i++) {
            buildings[n - i - 1] = (i + 1);
        }

        // a가 1이면 b가 보는 가장 먼 건물이 가장 앞에 있어야 함
        if (a == 1) {
            if (n - b != 0) {
                buildings[0] = buildings[n - b];
                buildings[n - b] = 1;
            }
        }

        // b 앞에 a가 볼 수 있는 건물 세우기
        else {
            // a가 b보다 크면 a개만큼 건물 세우기
            if (a > b) {
                    for (int i = 0; i < a; i++) {
                    buildings[n - b - i] = a - i;
                }
            }
            
            // b가 1이 아니면 a-1개 만큼 건물 세우기
            else {
                for (int i = 0; i < a - 1; i++) {
                    buildings[n - b - 1 - i] = a - i - 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(buildings[i]);
            System.out.print(" ");
        }
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
