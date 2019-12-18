package filter;

public enum SortingMode {
    NAME(" ORDER BY title"),
    NAME_DESC(" ORDER BY title DESC"),
    PRICE(" ORDER BY price"),
    PRICE_DESC(" ORDER BY price DESC");

    private String sql;

    SortingMode(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
