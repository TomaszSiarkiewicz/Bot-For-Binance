package bot;

import enums.Pairs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;

public class Trade {
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private float rsiBuy;
    private float rsiSell;
    private float macdSel;
    private float macdBuy;
    private BigDecimal profit;
    private Pairs pair;
    private BigDecimal amountInUsdt;
    private ZonedDateTime openTime;
    private ZonedDateTime closeTime;

    public Trade(RsiStrategy rsiStrategy) {
        int index = rsiStrategy.getIndicatorsProvider().getIndicators().size()-1;
        buyPrice = rsiStrategy.getIndicatorsProvider().getIndicators().get(index).getPrice();
        rsiBuy = rsiStrategy.getIndicatorsProvider().getIndicators().get(index).getRsi();
        macdBuy = rsiStrategy.getMacdBuy();
        pair = rsiStrategy.getPair();
        openTime = ZonedDateTime.now();
    }
    public Trade(RsiStrategy rsiStrategy, Trade trade){
        int index = rsiStrategy.getIndicatorsProvider().getIndicators().size()-1;
        buyPrice = trade.getBuyPrice();
        sellPrice = rsiStrategy.getIndicatorsProvider().getIndicators().get(index).getPrice();
        rsiBuy = trade.getRsiBuy();
        rsiSell = rsiStrategy.getIndicatorsProvider().getIndicators().get(index).getRsi();
        macdBuy = trade.getMacdBuy();
        macdSel = rsiStrategy.getIndicatorsProvider().getIndicators().get(index).getMacd();
        pair = trade.getPair();
        amountInUsdt = trade.amountInUsdt;
        closeTime = ZonedDateTime.now();
        profit = sellPrice.divide(buyPrice, 10, RoundingMode.HALF_UP);

    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public float getRsiBuy() {
        return rsiBuy;
    }

    public void setRsiBuy(float rsiBuy) {
        this.rsiBuy = rsiBuy;
    }

    public float getRsiSell() {
        return rsiSell;
    }

    public void setRsiSell(float rsiSell) {
        this.rsiSell = rsiSell;
    }

    public float getMacdSel() {
        return macdSel;
    }

    public void setMacdSel(float macdSel) {
        this.macdSel = macdSel;
    }

    public float getMacdBuy() {
        return macdBuy;
    }

    public void setMacdBuy(float macdBuy) {
        this.macdBuy = macdBuy;
    }

    public Pairs getPair() {
        return pair;
    }

    public void setPair(Pairs pair) {
        this.pair = pair;
    }

    public BigDecimal getAmountInUsdt() {
        return amountInUsdt;
    }

    public void setAmountInUsdt(BigDecimal amountInUsdt) {
        this.amountInUsdt = amountInUsdt;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public ZonedDateTime getOpenTime() {
        return openTime;
    }

    public ZonedDateTime getCloseTime() {
        return closeTime;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", profit=" + profit +
                ", rsiBuy=" + rsiBuy +
                ", rsiSell=" + rsiSell +
                ", macdSel=" + macdSel +
                ", macdBuy=" + macdBuy +
                ", pair=" + pair +
                ", amountInUsdt=" + amountInUsdt +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                '}';
    }
}
