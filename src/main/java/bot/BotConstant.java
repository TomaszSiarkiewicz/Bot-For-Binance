package bot;

import enums.Pairs;
import javassist.compiler.ast.Pair;

import java.util.LinkedList;
import java.util.List;

public class BotConstant {

    public static final int RSI_BUY = 39;
    public static final int RSI_SELL = 49;
    public static final List<Pairs> tradeableCoins = List.of(Pairs.AION_USDT, Pairs.FTM_USDT, Pairs.IOST_USDT, Pairs.PSG_USDT);

}
