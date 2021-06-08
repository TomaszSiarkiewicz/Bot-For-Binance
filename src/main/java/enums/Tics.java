package enums;

public enum Tics {

    MIN_1("1m"),
    MIN_3("3m"),
    MIN_5("5m"),
    MIN_15("15m"),
    MIN_30("30m"),
    H_1("1h");


    private final String name;


    Tics(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}