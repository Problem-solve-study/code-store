import java.io.*;
import java.util.*;

/*
96416KB, 680ms

다른 알고리즘 하나 없이 순수 구현만 존재하는 문제라서 천천히.. 도를 닦는 느낌으로 구현하면 된다.
혹시 실수할까봐 기능 하나 구현할 때마다 테스트하면서 넘어갔다.
다행히 한 번에 AC
 */

public class Main {
    static class Matrix {
        int[][] arr;
        static final int PART_ARR_UPSIDE_DOWN = 1;
        static final int PART_ARR_LEFT_RIGHT_INVERSION = 2;
        static final int PART_ARR_RIGHT_ROTATE = 3;
        static final int PART_ARR_LEFT_ROTATE = 4;
        static final int ARR_UPSIDE_DOWN = 5;
        static final int ARR_LEFT_RIGHT_INVERSION = 6;
        static final int ARR_RIGHT_ROTATE = 7;
        static final int ARR_LEFT_ROTATE = 8;

        Matrix(int[][] arr) {
            this.arr = arr;
        }

        public void operation(int k, int l) {
            int partArrSize = (int)Math.pow(2, l);

            if (k < 5) {
                if (partArrSize == 1) return;
                for (int i = 0; i < arr.length; i += partArrSize) {
                    for (int j = 0; j < arr.length; j += partArrSize) {
                        if (k == PART_ARR_UPSIDE_DOWN) {
                            partArrUpsideDown(i, j, partArrSize);
                        } else if (k == PART_ARR_LEFT_RIGHT_INVERSION) {
                            partArrLeftRightInversion(i, j, partArrSize);
                        } else if (k == PART_ARR_RIGHT_ROTATE) {
                            partArrRightRotate(i, j, partArrSize);
                        } else {
                            partArrLeftRotate(i, j, partArrSize);
                        } 
                    }
                }
            } else {
                if (k == ARR_UPSIDE_DOWN) {
                    arrUpsideDown(partArrSize);
                } else if (k == ARR_LEFT_RIGHT_INVERSION) {
                    arrLeftRightInversion(partArrSize);
                } else if (k == ARR_RIGHT_ROTATE) {
                    arrRightRotate(partArrSize);
                } else {
                    arrLeftRotate(partArrSize);
                }
            }
        }

        private void partArrUpsideDown(int r, int c, int size) {
            for (int j = 0; j < size; j++) {
                for (int i = 0; i < size / 2; i++) {
                    swap(r + i, c + j, r + size - i - 1, c + j);
                }
            }
        }

        private void arrUpsideDown(int size) {
            for (int j = 0; j < arr.length; j += size) {
                for (int i = 0; i < arr.length / 2; i += size) {
                    arrSwap(i, j, arr.length - size - i, j, size);
                }
            }
        }

        private void partArrLeftRightInversion(int r, int c, int size) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size / 2; j++) {
                    swap(r + i, c + j, r + i, c + size - j - 1);
                }
            }
        }

        private void arrLeftRightInversion(int size) {
            for (int i = 0; i < arr.length; i += size) {
                for (int j = 0; j < arr.length / 2; j += size) {
                    arrSwap(i, j, i, arr.length - size - j, size);
                }
            }
        }

        private void partArrRightRotate(int r, int c, int size) {
            int[][] temp = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    temp[j][size - i - 1] = arr[r + i][c + j];
                }
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    arr[r + i][c + j] = temp[i][j];
                }
            }
        }

        private void arrRightRotate(int size) {
            int[][] temp = new int[arr.length][arr.length];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    temp[i][j] = arr[i][j];
                }
            }

            for (int i = 0; i < arr.length; i += size) {
                for (int j = 0; j < arr.length; j += size) {
                    for (int r = 0; r < size; r++) {
                        for (int c = 0; c < size; c++) {
                            arr[j + r][arr.length - size - i + c] = temp[i + r][j + c];
                        }
                    }
                }
            }
        }

        private void partArrLeftRotate(int r, int c, int size) {
            int[][] temp = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    temp[size - j - 1][i] = arr[r + i][c + j];
                }
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    arr[r + i][c + j] = temp[i][j];
                }
            }
        }

        private void arrLeftRotate(int size) {
            int[][] temp = new int[arr.length][arr.length];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    temp[i][j] = arr[i][j];
                }
            }

            for (int i = 0; i < arr.length; i += size) {
                for (int j = 0; j < arr.length; j += size) {
                    for (int r = 0; r < size; r++) {
                        for (int c = 0; c < size; c++) {
                            arr[arr.length - size - j + r][i + c] = temp[i + r][j + c];
                        }
                    }
                }
            }
        }

        private void swap(int r1, int c1, int r2, int c2) {
            if (r1 == r2 && c1 == c2) return;
            arr[r1][c1] ^= arr[r2][c2];
            arr[r2][c2] ^= arr[r1][c1];
            arr[r1][c1] ^= arr[r2][c2];
        }

        private void arrSwap(int r1, int c1, int r2, int c2, int size) {
            int[][] temp = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    temp[i][j] = arr[r1 + i][c1 + j];
                    arr[r1 + i][c1 + j] = arr[r2 + i][c2 + j];
                }
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    arr[r2 + i][c2 + j] = temp[i][j];
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    sb.append(arr[i][j]).append(" ");
                }
                sb.append('\n');
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int size = (int)Math.pow(2, n);
        int[][] arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Matrix matrix = new Matrix(arr);
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            matrix.operation(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        bw.write(matrix.toString());
        bw.flush();
    }
}