package v2.enums;

public enum TableName {
    AUTHORS("A"),
    TITLES("T"),
    ASS("ASS");

    private String name;

    TableName(String name) {
        this.name = name;
    }

    public static TableName parse(String str) {
        for (TableName item : values()) {
            if (item.name.equals(str)) return item;
        }
        return null;
    }
}
