
// 11500KB	68ms
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] cpp = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        if (cpp[0] < 'a') {
            sb = new StringBuilder("Error!");
        } else {
            int flag = 0;
            int i = 0;

            while (i < cpp.length) {
                if (cpp[i] == '_' && flag != 2) {
                    i++;
                    if (i >= cpp.length || cpp[i] < 'a') {
                        sb = new StringBuilder("Error!");
                        break;
                    }
                    sb.append(Character.toUpperCase(cpp[i++]));
                    if (flag == 0)
                        flag = 1;
                } else if (cpp[i] <= 'Z' && flag != 1) {
                    sb.append("_").append(Character.toLowerCase(cpp[i++]));
                    if (flag == 0)
                        flag = 2;
                } else if ((cpp[i] <= 'Z' && flag == 1) || (cpp[i] == '_' && flag == 2)) {
                    sb = new StringBuilder("Error!");
                    break;
                } else {
                    sb.append(cpp[i++]);
                }
            }
        }
        System.out.print(sb);
    }
}
