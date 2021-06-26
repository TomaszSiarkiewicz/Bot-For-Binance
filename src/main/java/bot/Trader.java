package bot;

import enums.Pairs;

import java.time.ZonedDateTime;

public class Trader {
    private long lastUpdate = ZonedDateTime.now().getNano();
    private boolean run = false;
    private boolean panic = false;
    private final BinanceDataRepository bdr;
    private final CurrentPriceProvider cpp;

    public Trader() {
        bdr = new BinanceDataRepository();
        cpp = new CurrentPriceProvider(bdr);
    }
    RsiStrategy rsiStrategy=new RsiStrategy(Pairs.AION_USDT, bdr, )

}
