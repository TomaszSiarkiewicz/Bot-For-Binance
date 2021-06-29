package bot;

import enums.Pairs;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Trade {
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private float rsiBuy;
    private float rsiSell;
    private float macdSel;
    private float macdBuy;
    private BigDecimal qty;
    private Pairs pair;
    private BigDecimal profit;

    public Trade(RsiStrategy rsiStrategy) {
        int index = rsiStrategy.getIndicatorsProvider().getIndicators().size()-1;
        buyPrice = rsiStrategy.getIndicatorsProvider().getIndicators().get(index).getPrice();
        rsiBuy = rsiStrategy.getIndicatorsProvider().getIndicators().get(index).getRsi();
        macdBuy = rsiStrategy.getMacdBuy();
        pair = rsiStrategy.getPair();
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
        profit = sellPrice.divide(buyPrice, 5, RoundingMode.HALF_UP);


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

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public Pairs getPair() {
        return pair;
    }

    public void setPair(Pairs pair) {
        this.pair = pair;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", rsiBuy=" + rsiBuy +
                ", rsiSell=" + rsiSell +
                ", macdSel=" + macdSel +
                ", macdBuy=" + macdBuy +
                ", qty=" + qty +
                ", pair=" + pair +
                ", profit=" + profit +
                '}';
    }
}
