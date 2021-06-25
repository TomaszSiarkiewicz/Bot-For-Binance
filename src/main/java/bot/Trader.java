package bot;

import java.time.ZonedDateTime;

public class Trader {
    private long lastUpdate = ZonedDateTime.now().getNano();
    private boolean run = false;
    private boolean panic = false;
    private final BinanceDataRepository binanceDataRepository;

    public Trader() {
        binanceDataRepository = new BinanceDataRepository();
    }

}
