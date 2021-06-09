package macdMtmBot;

import java.math.BigDecimal;

public class BotResult {
    BigDecimal wallet;
    int trades;
    int successTrades;
    int RsiBuy;
    int RsiSel;
    int IOSTtrades;
    int AIONtrades;
    int PSGtrades;
    int FTMtrades;
    int NANOtrades;


    public BigDecimal getWallet() {
        return wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

    public int getTrades() {
        return trades;
    }

    public void setTrades(int trades) {
        this.trades = trades;
    }

    public int getSuccessTrades() {
        return successTrades;
    }

    public void setSuccessTrades(int successTrades) {
        this.successTrades = successTrades;
    }

    public int getRsiBuy() {
        return RsiBuy;
    }

    public void setRsiBuy(int rsiBuy) {
        RsiBuy = rsiBuy;
    }

    public int getRsiSel() {
        return RsiSel;
    }

    public void setRsiSel(int rsiSel) {
        RsiSel = rsiSel;
    }

    public int getIOSTtrades() {
        return IOSTtrades;
    }

    public void setIOSTtrades(int IOSTtrades) {
        this.IOSTtrades = IOSTtrades;
    }

    public int getAIONtrades() {
        return AIONtrades;
    }

    public void setAIONtrades(int AIONtrades) {
        this.AIONtrades = AIONtrades;
    }

    public int getPSGtrades() {
        return PSGtrades;
    }

    public void setPSGtrades(int PSGtrades) {
        this.PSGtrades = PSGtrades;
    }

    public int getFTMtrades() {
        return FTMtrades;
    }

    public void setFTMtrades(int FTMtrades) {
        this.FTMtrades = FTMtrades;
    }

    public int getNANOtrades() {
        return NANOtrades;
    }

    public void setNANOtrades(int NANOtrades) {
        this.NANOtrades = NANOtrades;
    }

    @Override
    public String toString() {
        return "BotResult{" +
                "wallet=" + wallet +
                ", trades=" + trades +
                ", successTrades=" + successTrades +
                ", successTradesPercent=" + ((float) successTrades / (float) trades) * 100 +
                ", RsiBuy=" + RsiBuy +
                ", RsiSel=" + RsiSel +
                ", IOSTtrades=" + IOSTtrades +
                ", AIONtrades=" + AIONtrades +
                ", PSGtrades=" + PSGtrades +
                ", NANOtrades=" + NANOtrades +
                ", FTMtrades=" + FTMtrades +
                '}';
    }
}
