package Print;

public interface Chain {
    public void setNextChain(Chain nextChain);
    public void print(Request request);
}
