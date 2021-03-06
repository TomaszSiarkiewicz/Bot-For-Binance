package api.client.model.spot.wallet;

public class SystemStatus {
    private String status;
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SystemStatus{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
