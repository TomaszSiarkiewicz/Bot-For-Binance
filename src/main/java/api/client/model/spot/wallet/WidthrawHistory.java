package api.client.model.spot.wallet;

import java.math.BigDecimal;

public class WidthrawHistory {
    String address;
    BigDecimal amount;
    String applyTime;
    String coin;
    String id;
    String network;
    String transferType;
    String status;
    String txId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    @Override
    public String toString() {
        return "WidthrawHistory{" +
                "address='" + address + '\'' +
                ", amount=" + amount +
                ", applyTime='" + applyTime + '\'' +
                ", coin='" + coin + '\'' +
                ", id='" + id + '\'' +
                ", network='" + network + '\'' +
                ", transferType='" + transferType + '\'' +
                ", status='" + status + '\'' +
                ", txId='" + txId + '\'' +
                '}';
    }
}
