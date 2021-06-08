package api.client.model.spot.wallet;

public class AccountStatus {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AccountStatus{" +
                "data='" + data + '\'' +
                '}';
    }
}
