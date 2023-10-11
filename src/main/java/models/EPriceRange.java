package models;

public enum EPriceRange {
    UNDER_100_USD(1, "UNDER 100 USD"),
    RANGE_100_299_USD(2, "100 - 299 USD"),
    RANGE_300_499_USD(3, "300 - 499 USD"),
    RANGE_500_699_USD(4, "500 - 699 USD"),
    RANGE_700_999_USD(5, "700 - 999 USD"),
    OVER_999_USD(6, "OVER 999 USD");

    private int id;
    private String title;

    private EPriceRange(int id, String title) {
        this.id = id;
        this.title = title;
    }

    // Getter methods for id and title
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
