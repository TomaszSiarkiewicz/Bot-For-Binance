package enums;

public enum Pairs {
    BTC_USDT("BTCUSDT"),
    AION_USDT("AIONUSDT"),
    PSG_USDT("PSGUSDT"),
    IOST_USDT("IOSTUSDT"),
    NANO_USDT("NANOUSDT"),
    FTM_USDT("FTMUSDT"),
    BNB_USDT("BNBUSDT"),
    DOGE_USDT("DOGEUSDT"),
    ATOM_USDT("ATOMUSDT"),
    ALGO_USDT("ALGOUSDT"),
    SRM_USDT("SRMUSDT");


    private final String name;



    Pairs(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
