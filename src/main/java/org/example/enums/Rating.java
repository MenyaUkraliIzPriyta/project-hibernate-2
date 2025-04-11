package org.example.enums;

public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private final String dbValue;

    Rating(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getValue() {
        return dbValue;
    }

    public static Rating fromDbValue(String dbValue) {
        for (Rating rating : values()) {
            if (rating.dbValue.equals(dbValue)) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Unknown DB value: " + dbValue);
    }
}
