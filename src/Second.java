import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Second {
    private String testString = null;
    private char[] chars;
    private int location = 0;
    private char lookHead;
    private boolean error = false;
    private List<Integer> list = new ArrayList<>();
    public static void main(String[] args){
        new Second().start();
    }
    private void start(){
        System.out.println("请输入一个表达式：");
        Scanner scanner = new Scanner(System.in);
        testString = scanner.next();
        testString = testString.replaceAll("x","i");
        testString = testString.replaceAll("y","i");
        testString = testString.replaceAll("z","i");
        chars = testString.toCharArray();
        System.out.println(Arrays.toString(chars));
        lookHead = chars[location];
        E();
        String res = error?"不合法":"合法";
        System.out.println("分析完成,此表达式"+res);
        if(error){
            System.out.println("出错位置为"+list);
        }
    }
    private void E(){
        if(lookHead == '(' || lookHead == 'i'){
            System.out.println("E->TA");
            T();
            A();
        }else {
            error();
        }

    }
    private void T(){
        if(lookHead=='(' || lookHead == 'i'){
            System.out.println("T->FB");
            F();
            B();
        }else {
            error();
        }

    }
    private void A(){
        if (lookHead == '+'){
            System.out.println("A->+TA");
            nextToken();
            T();
            A();
        }else if (lookHead == '$' || lookHead == ')'){
            System.out.println("A->£");
        }else {
            error();
        }
    }
    private void B(){
        if (lookHead == '*'){
            System.out.println("B->*FB");
            nextToken();
            F();
            B();
        }else if (lookHead == '+' || lookHead == '$' || lookHead == ')'){
            System.out.println("B->£");
        }else {
            error();
        }
    }
    private void F(){
        if (lookHead == '('){
            System.out.println("F->(E)");
            nextToken();
            E();
            if (lookHead != ')'){
                error();
            }
        }else if (lookHead == 'i'){
            System.out.println("F->id");
            nextToken();
        }else {
            error();
        }
    }
    private void error(){
        System.out.println("出错啦，跳过一个字符");
        list.add(location + 1);
        error = true;
        nextToken();
    }
    private void nextToken(){
        location++;
        if(location==chars.length){
            String res = error?"不合法":"合法";
            System.out.println("分析完成,此表达式"+res);
            System.exit(0);
        }else {
            lookHead = chars[location];
        }
    }
}
