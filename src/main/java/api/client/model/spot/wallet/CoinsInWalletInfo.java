package api.client.model.spot.wallet;

import java.math.BigDecimal;

public class CoinsInWalletInfo {
    private String coin;
    private BigDecimal free;
    private BigDecimal locked;
    private String name;

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public BigDecimal getFree() {
        return free;
    }

    public void setFree(BigDecimal free) {
        this.free = free;
    }

    public BigDecimal getLocked() {
        return locked;
    }

    public void setLocked(BigDecimal locked) {
        this.locked = locked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CoinsInWalletInfo{" +
                "coin='" + coin + '\'' +
                ", free=" + free +
                ", locked=" + locked +
                ", name='" + name + '\'' +
                '}';
    }
}
