// 16536KB, 136ms

import java.io.*;

public class Main {
    static int res = Integer.MIN_VALUE;
    static String f;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        f = br.readLine();

        select(n / 3 + 1, 0, new boolean[n + 1]);
        bw.write(String.valueOf(res));
        bw.flush();
    }

    public static void select(int limit, int cnt, boolean[] bracket) {
        //연산자를 선택하여 괄호 추가
        res = Math.max(res, getValue(bracket));
        if (limit == cnt) {
            return;
        }
        
        for (int i = 1; i < f.length(); i += 2) {
            //인접한 연산자는 선택하면 안됨
            if (bracket[i]) continue;
            if (i - 2 >= 0 && bracket[i - 2]) continue;
            if (i + 2 < f.length() && bracket[i + 2]) continue;
            
            bracket[i] = true;
            select(limit, cnt + 1, bracket);
            bracket[i] = false;
        }
    }

    public static int getValue(boolean[] bracket) {
        //수식을 계산
        
        //괄호가 씌여진 연산자를 먼저 계산하여 새로운 식을 생성
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < f.length(); i++) {
            if (bracket[i]) {
                for (int j = sb.length() - 1; j >= 0; j--) {
                    if ((sb.charAt(j) == '-' && j - 1 >= 0 && !Character.isDigit(sb.charAt(j - 1))) || Character.isDigit(sb.charAt(j))) { 
                        sb.deleteCharAt(j);
                    } else {
                        break;
                    }
                }
                sb.append(calc(f.charAt(i - 1) - '0', f.charAt(i + 1) - '0', f.charAt(i)));
                i++;
            } else {
                sb.append(f.charAt(i));
            }
        }
        
        String newF = sb.toString();
        int i = 0;
        sb = new StringBuilder();
        for (; i < newF.length(); i++) {
            if ((i == 0 && newF.charAt(i) == '-') || Character.isDigit(newF.charAt(i))) { 
                sb.append(newF.charAt(i));
            } else {
                break;
            }
        }
        int value = Integer.parseInt(sb.toString());
        for (; i < newF.length(); i++) {
            if (!Character.isDigit(newF.charAt(i))) {
                int next = getNextP(i, newF);
                value = calc(value, next, newF.charAt(i));
                if (next < 0) i++;
            }
        }

        return value;
    }

    public static int getNextP(int idx, String f) {
        //해당 연산자의 위치로부터 오른쪽 피연산자 추출
        StringBuilder p = new StringBuilder();

        for (int i = idx + 1; i < f.length(); i++) {
            if ((i == idx + 1 && f.charAt(i) == '-') || Character.isDigit(f.charAt(i))) {
                p.append(f.charAt(i));
            } else {
                break;
            }
        }

        return Integer.parseInt(p.toString());
    }

    public static int calc(int p1, int p2, char y) {
        //연산자 1개, 피연산자 2개로 값을 계산
        int value = 0;

        switch (y) {
            case '+':
                value = p1 + p2;
                break;
            case '-':
                value = p1 - p2;
                break;
            case '*':
                value = p1 * p2;
        }

        return value;
    }
}