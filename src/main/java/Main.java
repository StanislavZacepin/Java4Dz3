import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
//        Реализовать алгоритм перевода из инфиксной записи в постфиксную для арифметического выражения.
//                Вычислить запись если это возможно


    }

    // Получаем приоритет оператора
    public static int getPriority(String s){
        Map<String,Integer> map = new HashMap<>();
        map.put("+",0);
        map.put("-",0);
        map.put("*",1);
        map.put("/",1);
        map.put("(",2);
        map.put(")",2);


        return map.get(s);
    }

    public static boolean isNumber(String c){

        Pattern pattern = Pattern.compile("^(0|[1-9][0-9]*)$");
        Matcher matcher = pattern.matcher(c);
        boolean res = matcher.find();
        return res;
    }
}