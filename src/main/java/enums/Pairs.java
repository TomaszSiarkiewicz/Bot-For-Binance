package enums;

public enum Pairs {
    AION_USDT("AIONUSDT"),
    BTC_USDT("BTCUSDT"),
    PSG_USDT("PSGUSDT"),
    IOST_USDT("IOSTUSDT"),
    NANO_USDT("NANOUSDT"),
    FTM_USDT("FTMUSDT");


    private final String name;



    Pairs(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
