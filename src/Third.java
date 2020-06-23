import javax.swing.text.html.parser.Entity;
import java.util.*;

public class Third {
    private String testString = null;
    private char[] chars;
    private int location = 0;
    Stack<Character> symbolStack = new Stack<>();
    Stack<Integer> stateStack = new Stack<>();
    private Map<String,Enity> actionMap = new HashMap<>();
    private Map<String,Integer> gotoMap = new HashMap<>();
    private List<String> grammar = new ArrayList<>();
    private boolean error = false;
    public static void main(String[] args){
        new Third().start();
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
        setGrammar();
        init();
        LR();
        String res = error?"有误":"无误";
        System.out.println("分析完成,此表达式"+res);
    }
    private void LR(){
        while (true){
            int state = stateStack.peek();
            char c = chars[location];
            Enity enity = actionMap.getOrDefault(state + String.valueOf(c),null);
            if (enity == null){
                error();
                if (location >= testString.length()){
                    break;
                }
            }else {
                char symbol = enity.getSymbol();
                if (symbol == 's'){
                    //移进
                    if(c == 'i'){
                        symbolStack.push('i');
                        System.out.println("action:移进id,进入到"+enity.getState()+"号状态");
                    }else if (c == '$'){
                    }else {
                        symbolStack.push(c);
                        System.out.println("action:移进"+String.valueOf(c)+"进入到"+enity.getState()+"号转态");
                    }
                    location++;
                    stateStack.push(enity.getState());
                }else if (symbol == 'r'){
                    //规约
                    String s = grammar.get(enity.getState());
                    System.out.print("按"+s+"规约 ");

                    String substring = s.substring(s.indexOf(">") + 1);
                    int l = substring.length();
                    for (char h:substring.toCharArray()){
                        if (h == 'i'){
                            l --;
                        }
                    }
                    while (l > 0){
                        symbolStack.pop();
                        stateStack.pop();
                        l --;
                    }
                    symbolStack.push(s.charAt(0));
                    Integer integer = gotoMap.get(stateStack.peek() + String.valueOf(symbolStack.peek()));
                    stateStack.push(integer);
                    System.out.println("规约完成，进入"+integer+"号转态");
                }else if (symbol == 'a'){
                    //acc
                    break;
                }else {
                    error();
                }
            }
        }
    }
    private void error(){
        System.out.println("出错啦，跳过一个字符");
        error = true;
        location ++;
    }
    private void setGrammar(){
        grammar.add("S'->S");
        grammar.add("S->V=E");
        grammar.add("S->E");
        grammar.add("V->*E");
        grammar.add("V->id");
        grammar.add("E->V");
    }
    private void init(){
        actionMap.put("0*",new Enity('s',4));
        actionMap.put("0i",new Enity('s',5));
        actionMap.put("1$",new Enity('a',14));
        actionMap.put("2=",new Enity('s',6));
        actionMap.put("2$",new Enity('r',5));
        actionMap.put("3$",new Enity('r',2));
        actionMap.put("4*",new Enity('s',4));
        actionMap.put("4i",new Enity('s',5));
        actionMap.put("5=",new Enity('r',4));
        actionMap.put("5$",new Enity('r',4));
        actionMap.put("6*",new Enity('s',11));
        actionMap.put("6i",new Enity('s',12));
        actionMap.put("7=",new Enity('r',3));
        actionMap.put("8=",new Enity('r',5));
        actionMap.put("8$",new Enity('r',5));
        actionMap.put("9$",new Enity('r',1));
        actionMap.put("10$",new Enity('r',5));
        actionMap.put("11=",new Enity('s',11));
        actionMap.put("11i",new Enity('s',12));
        actionMap.put("12$",new Enity('r',4));
        actionMap.put("13$",new Enity('r',3));

        gotoMap.put("0S",1);
        gotoMap.put("0V",2);
        gotoMap.put("0E",3);
        gotoMap.put("4V",8);
        gotoMap.put("4E",7);
        gotoMap.put("6V",10);
        gotoMap.put("6E",9);
        gotoMap.put("11V",10);
        gotoMap.put("11E",13);

        stateStack.push(0);
    }

}
