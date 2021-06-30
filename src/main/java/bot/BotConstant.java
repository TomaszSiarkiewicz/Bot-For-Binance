package bot;

import enums.Pairs;

import java.util.List;

public class BotConstant {

    public static final int RSI_BUY = 35;
    public static final int RSI_SELL = 61;
    public static final List<Pairs> tradeableCoins = List.of(
            Pairs.AION_USDT,
            Pairs.FTM_USDT,
            Pairs.IOST_USDT,
            Pairs.PSG_USDT,
            Pairs.NANO_USDT,
            Pairs.ALGO_USDT,
            Pairs.BNB_USDT,
            Pairs.DOGE_USDT,
            Pairs.ATOM_USDT,
            Pairs.SRM_USDT,
            Pairs.XRP_USDT,
            Pairs.SHIB_USDT,
            Pairs.ADA_USDT,
            Pairs.MATIC_USDT,
            Pairs.ICP_USDT);

}
