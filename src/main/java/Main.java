import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
//        Реализовать алгоритм перевода из инфиксной записи в постфиксную для арифметического выражения.
//                Вычислить запись если это возможно

        // "9 3 1 - 3 * + 10 2 / +"
        System.out.println(Calculation("9 + ( 3 - 1 ) * 3 + 10 / 2"));

    }

    //Преобразование инфиксного выражения в постфиксное выражение
    //param string 9 + ( 3 - 1 ) * 3 + 10 / 2
    public static String Calculation(String string) {
        Stack<String> stack = new Stack();
        String[] chars = string.split(" ");
        String res = "";
        for (int i = 0; i < chars.length; i++) {
            String s = String.valueOf(chars[i]);
            if (isNumber(s)) {
                if (res.length() == 0)
                    res += s;
                else
                    res += " " + s;
            } else {
                if (s.equals("(")) {
                    stack.push(s);
                } else {
                    if (s.equals(")")) {
                        String t = "";
                        String s1 = "";
                        while (!(t = stack.pop()).equals("(")) {
                            s1 += " " + t;
                        }
                        res += s1;
                    } else {
                        int priority = getPriority(s);
                        String s1 = "";
                        boolean flag = false;
                        while (!stack.empty()) {
                            flag = false;
                            s1 = stack.pop();
                            if (s1.equals("(")) {
//                                stack.push("(");
                                break;
                            }

                            if (getPriority(s1) >= priority) {
                                res += " " + s1;
                                flag = true;
                            }
                        }
                        if (!s1.equals("") && !flag)
                            stack.push(s1);
                        stack.push(s);
                    }
                }
            }
        }

        while (!stack.empty()) {
            res += " " + stack.pop();
        }

        return res;
    }

    // Получаем приоритет оператора
    public static int getPriority(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("+", 0);
        map.put("-", 0);
        map.put("*", 1);
        map.put("/", 1);
        map.put("(", 2);
        map.put(")", 2);


        return map.get(s);
    }

    public static boolean isNumber(String c) {

        Pattern pattern = Pattern.compile("^(0|[1-9][0-9]*)$");
        Matcher matcher = pattern.matcher(c);
        boolean res = matcher.find();
        return res;
    }
}