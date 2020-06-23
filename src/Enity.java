public class Enity {
    private char symbol;
    private int state;

    public Enity(char symbol, int state) {
        this.symbol = symbol;
        this.state = state;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
