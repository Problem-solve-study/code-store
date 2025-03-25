//제출번호: 91966179
//메모리:   26288 KB
//실행시간: 212 ms
import java.io.*;
import java.util.*;

//주어진 명령어를 그대로 구현하면 되는 문제
//명령어가 많아서 구현하기 귀찮음..
//하나의 명령어 집합에 대해서 연산해야 하는 숫자가 여러 개 들어오므로
//명령어를 미리 저장해두고 사용해야 함
//나눗셈 나머지는 프로그램에서 사용하는 연산을 그대로 사용하면 됨 (그렇게 구현되어 있기 때문)
//Error가 나오는 경우를 모두 세세하게 체크해야 하는 것에 주의할 것 (1틀함)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] cmds = new String[100001]; //명령어 리스트
        int cmdCnt = 0;
        long[] stack = new long[1001]; //스택
        int stackPos = 0;

        long limit = (long) 1e9; //에러 리미트

        String cmd;
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        //만약 입력된 명령어가 QUIT이면 프로그램 종료
        while (!(cmd = br.readLine()).equals("QUIT")) {
            cmdCnt = 0;
            //입력된 명령어가 END가 아닐 때까지 입력 받아서 명령어 리스트에 저장
            while (!cmd.equals("END")) {
                cmds[cmdCnt++] = cmd;
                cmd = br.readLine();
            }

            int t = Integer.parseInt(br.readLine());
            for (int i = 0; i < t; i++) {
                int num = Integer.parseInt(br.readLine());

                //숫자를 스택에 저장함
                stackPos = 1;
                stack[0] = num;

                boolean isError = false;
                //명령어 수행
                execute:
                for (int pc = 0; pc < cmdCnt; pc++) {
                    //스택이 비어있지 않으면서
                    if (stackPos > 0) {
                        //첫 번째 수가 에러 리미트를 넘어간다면 Error임
                        if (stack[stackPos - 1] > limit || stack[stackPos - 1] < -limit) {
                            isError = true;
                            break;
                        }
                    }

                    switch (cmds[pc]) {
                        case "POP": //스택에서 뺌
                            if (stackPos == 0) {
                                isError = true;
                                break execute;
                            }
                            
                            stackPos--;
                            break;

                        case "INV": //첫 번째 수의 부호를 바꿈
                            if (stackPos == 0) {
                                isError = true;
                                break execute;
                            }
                            
                            stack[stackPos - 1] = -stack[stackPos - 1];
                            break;

                        case "DUP": //첫 번째 수 복제
                            if (stackPos == 0) {
                                isError = true;
                                break execute;
                            }
                            
                            stack[stackPos] = stack[stackPos - 1];
                            stackPos++;
                            break;

                        case "SWP": //첫 번째, 두 번째 수 교체
                            if (stackPos < 2) {
                                isError = true;
                                break execute;
                            }
                            
                            long temp = stack[stackPos - 1];
                            stack[stackPos - 1] = stack[stackPos - 2];
                            stack[stackPos - 2] = temp;
                            break;

                        case "ADD": //두 숫자를 더해서 스택에 넣음
                            if (stackPos < 2) {
                                isError = true;
                                break execute;
                            }
                            
                            stackPos--;
                            stack[stackPos - 1] = stack[stackPos - 1] + stack[stackPos];
                            break;

                        case "SUB": //두 숫자를 빼서 스택에 넣음 (두 번째 - 첫 번째)
                            if (stackPos < 2) {
                                isError = true;
                                break execute;
                            }

                            stackPos--;
                            stack[stackPos - 1] = stack[stackPos - 1] - stack[stackPos];
                            break;

                        case "MUL": //두 숫자를 곱해서 스택에 넣음
                            if (stackPos < 2) {
                                isError = true;
                                break execute;
                            }

                            stackPos--;
                            stack[stackPos - 1] = stack[stackPos - 1] * stack[stackPos];
                            break;

                        case "DIV": //두 숫자를 나누고 그 몫을 스택에 넣음 (두 번째 / 첫 번째)
                            if (stackPos < 2) {
                                isError = true;
                                break execute;
                            }

                            stackPos--;

                            //첫 번째 수가 0인지 확인해야 함
                            if (stack[stackPos] == 0) {
                                isError = true;
                                break execute;
                            }

                            stack[stackPos - 1] = stack[stackPos - 1] / stack[stackPos];
                            break;

                        case "MOD": //두 숫자를 나누고 그 나머지를 스택에 넣음 (두 번째 / 첫 번째)
                            if (stackPos < 2) {
                                isError = true;
                                break execute;
                            }

                            stackPos--;
                            
                            if (stack[stackPos] == 0) {
                                isError = true;
                                break execute;
                            }
                            
                            stack[stackPos - 1] = stack[stackPos - 1] % stack[stackPos];
                            break;
                    
                        default: //NUM에 해당, NUM 뒤의 숫자를 스택에 넣음
                            st = new StringTokenizer(cmds[pc]);
                            st.nextToken();
                            stack[stackPos++] = Integer.parseInt(st.nextToken());
                            break;
                    }
                }

                //중간에 Error로 종료됐거나, 스택에 원소가 1개가 아니거나, 들어가 있는 원소가 에러 리미트를 넘어갔다면 에러 출력
                if (isError || stackPos != 1 || stack[0] > limit || stack[0] < -limit) {
                    sb.append('E').append('R').append('R').append('O').append('R');
                } else {
                    //그 외는 스택의 원소 출력
                    sb.append(stack[0]);
                }
                sb.append('\n');
            }
            sb.append('\n'); //출력 형식을 맞춰 줌
            br.readLine(); //입력 형식을 맞춰 줌
        }

        System.out.print(sb);
    }
}

/* String을 최대한 안 쓰고 br도 쓰지 않은 버전 - 재미로만 보세요
//제출번호: 91969976
//메모리:   14316 KB
//실행시간: 104 ms
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        byte[] cmds = new byte[100001];
        int[] nums = new int[100001];
        int cmdCnt = 0;
        
        long[] stack = new long[1001];
        int stackPos = 0;

        long limit = (long) 1e9;

        int[] cmd = new int[5];
        StringBuilder sb = new StringBuilder();
        while (readString(cmd)[0] != 'Q') {
            cmdCnt = 0;
            while (cmd[0] != 'E') {
                if (cmd[0] == 'N') {
                    nums[cmdCnt] = readInt();
                }

                switch(cmd[0]) {
                    case 'P': cmds[cmdCnt++] = 0; break;
                    case 'I': cmds[cmdCnt++] = 1; break;
                    case 'D': cmds[cmdCnt++] = (byte) (cmd[1] == 'U' ? 2 : 7); break;
                    case 'S': cmds[cmdCnt++] = (byte) (cmd[1] == 'W' ? 3 : 5); break;
                    case 'A': cmds[cmdCnt++] = 4; break;
                    case 'M': cmds[cmdCnt++] = (byte) (cmd[1] == 'U' ? 6 : 8); break;
                    default: cmds[cmdCnt++] = 9; break;
                }
                
                readString(cmd);
            }

            int t = readInt();
            for (int i = 0; i < t; i++) {
                int num = readInt();

                stackPos = 0;
                stack[0] = num;

                boolean isError = false;
                execute:
                for (int pc = 0; pc < cmdCnt; pc++) {
                    if (stackPos > -1) {
                        if (stack[stackPos] > limit || stack[stackPos] < -limit) {
                            isError = true;
                            break;
                        }
                    }

                    switch (cmds[pc]) {
                        case 0: //POP
                            if (stackPos < 0) {
                                isError = true;
                                break execute;
                            }
                            
                            stackPos--;
                            break;

                        case 1: //INV
                            if (stackPos < 0) {
                                isError = true;
                                break execute;
                            }
                            
                            stack[stackPos] = -stack[stackPos];
                            break;

                        case 2: //DUP
                            if (stackPos < 0) {
                                isError = true;
                                break execute;
                            }
                            
                            stack[stackPos + 1] = stack[stackPos++];
                            break;

                        case 3: //SWP   
                            if (stackPos < 1) {
                                isError = true;
                                break execute;
                            }
                            
                            long temp = stack[stackPos];
                            stack[stackPos] = stack[stackPos - 1];
                            stack[stackPos - 1] = temp;
                            break;

                        case 4: //ADD
                            if (stackPos < 1) {
                                isError = true;
                                break execute;
                            }
                            
                            stack[--stackPos] = stack[stackPos] + stack[stackPos + 1];
                            break;

                        case 5: //SUB
                            if (stackPos < 1) {
                                isError = true;
                                break execute;
                            }

                            stack[--stackPos] = stack[stackPos] - stack[stackPos + 1];
                            break;

                        case 6: //MUL
                            if (stackPos < 1) {
                                isError = true;
                                break execute;
                            }

                            stack[--stackPos] = stack[stackPos] * stack[stackPos + 1];
                            break;

                        case 7: //DIV
                            if (stackPos < 1) {
                                isError = true;
                                break execute;
                            }

                            if (stack[stackPos] == 0) {
                                isError = true;
                                break execute;
                            }

                            stack[--stackPos] = stack[stackPos] / stack[stackPos + 1];
                            break;

                        case 8: //MOD
                            if (stackPos < 1) {
                                isError = true;
                                break execute;
                            }
                            
                            if (stack[stackPos] == 0) {
                                isError = true;
                                break execute;
                            }
                            
                            stack[--stackPos] = stack[stackPos] % stack[stackPos + 1];
                            break;
                    
                        default: //NUM
                            stack[++stackPos] = nums[pc];
                            break;
                    }
                }

                if (isError || stackPos != 0 || stack[0] > limit || stack[0] < -limit) {
                    sb.append('E').append('R').append('R').append('O').append('R');
                } else {
                    sb.append(stack[0]);
                }
                sb.append('\n');
            }
            sb.append('\n');
            System.in.read();
        }

        System.out.print(sb);
    }

    static int[] readString(int[] str) throws IOException {
        int pos = 0;
        while ((str[pos++] = System.in.read()) > 47);

        return str;
    }

    static int readInt() throws IOException {
        int c = System.in.read();
        boolean isMinus = c == 45;
        int n = 0;

        if (!isMinus) {
            n = c & 15;
        }
        c = System.in.read();

        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }

        return n;
    }
}
 */