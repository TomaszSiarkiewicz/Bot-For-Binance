package bot;

public class Bot {
    private boolean running;
    Trader trader;
    Thread tradingThread;

    public Bot() {
        trader = new Trader();
        tradingThread = new Thread(trader);
    }

    public void run() {
        tradingThread.start();
    }

    public void stop() {
    }

    public void panic() {
    }

    public Trader getTrader() {
        return trader;
    }
}
