/*
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String[] sounds = br.readLine().split(" ");

            HashSet<String> animalSound = new HashSet<>();
            while (true) {
                String[] in = br.readLine().split(" ");
                if (in[1].equals("does"))
                    break;
                animalSound.add(in[2]);
            }
            for (int j = 0; j < sounds.length; j++) {
                if (animalSound.contains(sounds[j]))
                    continue;
                sb.append(sounds[j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}