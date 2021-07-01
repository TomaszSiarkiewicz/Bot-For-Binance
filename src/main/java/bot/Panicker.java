package bot;

public class Panicker implements  Runnable{
    private Bot bot;

    public Panicker(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void run() {
        bot.panic();
    }
}
