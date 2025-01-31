//14200KB, 104ms

import java.io.*;
import java.util.*;

public class Main {
    static HashMap<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        map.put('H', 1); map.put('C', 12); map.put('O', 16);

        String chemicalFormula = br.readLine();
        LinkedList<Character> list = new LinkedList<>();
        int size = chemicalFormula.length();
        int res = 0;
        for (int i = 0; i < size; i++) {
            list.addLast(chemicalFormula.charAt(i));
        }

        res += getFormulaWeight(list, 1);
        bw.write(String.valueOf(res));
        bw.flush();
    }

    public static LinkedList<Character> getBracketFormula(LinkedList<Character> original) {
        LinkedList<Character> list = new LinkedList<>();
        list.addFirst(')');

        int closeBracketCnt = 1;
        int openBracketCnt = 0;
        while (closeBracketCnt != openBracketCnt) {
            list.addFirst(original.removeLast());
            if (list.peekFirst() == ')') {
                closeBracketCnt++;
            } else if (list.peekFirst() == '(') {
                openBracketCnt++;
            }
        }

        list.removeFirst();
        list.removeLast();
        return list;
    }

    public static int getFormulaWeight(LinkedList<Character> list, int multiplier) {
        int res = 0;
        while (!list.isEmpty()) {
            char top = list.removeLast();
            int curMultiplier = 1;
            if (Character.isDigit(top)) {
                curMultiplier = top - '0';
                top = list.removeLast();
            }

            LinkedList<Character> temp = new LinkedList<>();
            if (list.isEmpty()) {
                res += (map.get(top) * curMultiplier);
            } else if (Character.isAlphabetic(top)) {
                temp.push(top);
                res += getFormulaWeight(temp, curMultiplier);
            } else {
                temp = getBracketFormula(list);
                res += getFormulaWeight(temp, curMultiplier);
            }
        }

        return res * multiplier;
    }
}