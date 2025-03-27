import java.io.*;

// 예외처리 케이스
/*
 * 1. 문자열의 첫번째 또는 마지막에 "_" 기호가 오면 error
 * 2. 문자열의 첫번째가 대문자가 되어서는 안된다
 * 3. 문자열에 "_" 기호를 제외한 나머지 기호가 들어오면 error
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        boolean flag = true; // flag = true의 뜻은 java 변수명, flag = false의 뜻은 C++ 변수명

        // 문자열의 첫번째 또는 마지막에 _ 문자가 있으면 error
        // 시작하는 문자가 대문자면 error
        if (str.charAt(0) == '_' || str.charAt(str.length() - 1) == '_' ||
                (str.charAt(0) >= 'A' && str.charAt(0) <= 'Z')) {
            System.out.println("Error!");
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '_') {
                flag = false;
                break;
            }
        }

        if (flag) {
            if (isjavaValidation(str)) {
                System.out.println(replaceJavatoC(str));
            } else {
                System.out.println("Error!");
            }
        } else {
            if (iscValidation(str)) {
                System.out.println(replaCtoJava(str));
            } else {
                System.out.println("Error!");
            }
        }
    }

    // java 변수명 유효값 처리
    public static boolean isjavaValidation(String str) {

        for (int i = 0; i < str.length(); i++) {
            // 소문자 또는 대문자로 이루어져 있지 않으면 false, 즉 특수문자가 들어오면 false
            if (!((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') ||
                    ((str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')))) {
                return false;
            }
        }
        return true;
    }

    // C++ 변수명 유효값 처리
    public static boolean iscValidation(String str) {
        for (int i = 0; i < str.length(); i++) {
            // "_" 기호 뒤에 소문자를 제외한 다른 문자가 오면 false
            if (str.charAt(i) == '_' && !(str.charAt(i + 1) >= 'a' && str.charAt(i + 1) <= 'z')) {
                return false;
            }
            // 소문자 또는 "_" 기호를 제외한 다른 문자가 들어오면 false
            else if (!(str.charAt(i) == '_' || (str.charAt(i) >= 'a' && str.charAt(i) <= 'z'))) {
                return false;
            }
        }
        return true;
    }

    public static String replaceJavatoC(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            // 대문자가 있으면 해당 자리를 (_ + 소문자로 바꿔서 저장)
            if ((str.charAt(i) >= 'a' && str.charAt(i) <= 'z')) {
                sb.append(str.charAt(i));
            } else {
                sb.append("_" + (char) (str.charAt(i) + 32));
            }
        }
        return sb.toString();
    }

    public static String replaCtoJava(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            // (_ + 소문자) 를 대문자로 치환해서 저장
            if (str.charAt(i) == '_') {
                sb.append((char) (str.charAt(i + 1) - 32));
                i++;
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }
}
