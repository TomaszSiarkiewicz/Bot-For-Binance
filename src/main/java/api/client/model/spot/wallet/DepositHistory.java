package api.client.model.spot.wallet;

import java.math.BigDecimal;

public class DepositHistory {
    BigDecimal amount;
    String coin;
    String network;
    String status;
    String address;
    String addressTag;
    String txId;
    Long insertTime;
    String transferType;
    String confirmTimes;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressTag() {
        return addressTag;
    }

    public void setAddressTag(String addressTag) {
        this.addressTag = addressTag;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public Long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Long insertTime) {
        this.insertTime = insertTime;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getConfirmTimes() {
        return confirmTimes;
    }

    public void setConfirmTimes(String confirmTimes) {
        this.confirmTimes = confirmTimes;
    }

    @Override
    public String toString() {
        return "DepositHistory{" +
                "amount=" + amount +
                ", coin='" + coin + '\'' +
                ", network='" + network + '\'' +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", addressTag='" + addressTag + '\'' +
                ", txId='" + txId + '\'' +
                ", insertTime=" + insertTime +
                ", transferType='" + transferType + '\'' +
                ", confirmTimes='" + confirmTimes + '\'' +
                '}';
    }
}
