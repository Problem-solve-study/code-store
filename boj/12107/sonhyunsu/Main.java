//제출번호: 89096831
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {        
        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

        wr.write(65 + (Integer.parseInt(re.readLine()) == 1 ? 1 : 0));

        wr.flush();
        wr.close();
        re.close();
    }
}
