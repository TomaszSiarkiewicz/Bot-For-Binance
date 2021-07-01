package bot;

public class Bot {
    Trader trader;
    Thread tradingThread;
    private boolean panic;
    private boolean running;

    public Bot() {
        panic = false;
        trader = new Trader();
        tradingThread = new Thread(trader);
    }

    public void run() {
        System.out.println("--------------------------------------------RUN---------------------------------");
        tradingThread.start();
        running = true;
    }

    public void stop() {
        System.out.println("-----------------STOP----------------------");
        trader.setRun(false);
        running = false;
    }

    public void panic() {
        System.out.println("----------------------------------------------PANICKING------------------------------");
        stop();
        setPanic(true);
        trader.performPanic();
        setPanic(false);
    }

    public Trader getTrader() {
        return trader;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPanic() {
        return panic;
    }

    public void setPanic(boolean panic) {
        this.panic = panic;
    }
}
