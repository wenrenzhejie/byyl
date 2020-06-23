import java.util.Scanner;

public class First {
    private static String testString = null;
    private int currentState = 12;
    private final static int DIGIT = 1;
    private final static int POINT = 2;
    private final static int OTHER = 3;
    private final static int POWER = 4;
    private final static int PLUS = 5;
    private final static int MINUS = 6;
    private final static int EndState = -1;
    public static void main(String[] args){
        System.out.println("请输入一个要判断的字符，并以$结尾：");
        Scanner scanner = new Scanner(System.in);
        testString = scanner.next();
        First first = new First();
        boolean unsignedNumber = first.isUnsignedNumber(testString);
        String res = unsignedNumber && first.currentState == 19? testString + "是无符号整数":testString+"不是无符号整数";
        System.out.println(res);
    }
    private  boolean isUnsignedNumber(String str){
        for (char c:str.toCharArray()){
            int ch = analysisCharacter(c);
            currentToNext(ch);
            if (currentState == EndState){
                return false;
            }
        }
        return true;
    }
    public  int analysisCharacter(char c) {
        switch (c) {
            case '.':
                return POINT;
            case 'E':
            case 'e':
                return POWER;
            case '+':
                return PLUS;
            case '-':
                return MINUS;
            default:
                if (Character.isDigit(c)) {
                    return DIGIT;
                }
                break;
        }
        return OTHER;
    }

    public  void currentToNext(int ch) {
        switch (currentState) {
            case 12:
                switch (ch) {
                    case DIGIT:
                        currentState = 13;
                        break;
                    default:
                        currentState = EndState;
                        break;
                }
                break;
            case 13:
                switch (ch) {
                    case DIGIT:
                        currentState = 13;
                        break;
                    case POINT:
                        currentState = 14;
                        break;
                    case POWER:
                        currentState = 16;
                        break;
                    case OTHER:
                        currentState=19;
                        break;
                    default:
                        currentState = EndState;
                        break;
                }
                break;
            case 14:
                switch (ch) {
                    case DIGIT:
                        currentState = 15;
                        break;
                    default:
                        currentState = EndState;
                        break;
                }
                break;
            case 15:
                switch (ch) {
                    case DIGIT:
                        currentState = 15;
                        break;
                    case POWER:
                        currentState = 16;
                        break;
                    case OTHER:
                        currentState = 19;
                        break;
                    default:
                        currentState = EndState;
                        break;
                }
                break;
            case 16:
                switch (ch) {
                    case DIGIT:
                        currentState = 18;
                        break;
                    case PLUS:
                    case MINUS:
                        currentState = 17;
                        break;
                    default:
                        currentState = EndState;
                        break;
                }
                break;
            case 17:
                switch (ch) {
                    case DIGIT:
                        currentState = 18;
                        break;
                    default:
                        currentState = EndState;
                        break;
                }
                break;
            case 18:
                switch (ch) {
                    case DIGIT:
                        currentState = 18;
                        break;
                    case OTHER:
                        currentState = 19;
                        break;
                    default:
                        currentState = EndState;
                        break;
                }
                break;
        }
    }

}
