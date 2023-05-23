package MODEl;

public class RestaurantReport {
    private long totalEarnings;
    private long completedOrdersCount;
    private long canceledOrdersCount;

    public RestaurantReport(long totalEarnings, long completedOrdersCount, long canceledOrdersCount) {
        this.totalEarnings = totalEarnings;
        this.completedOrdersCount = completedOrdersCount;
        this.canceledOrdersCount = canceledOrdersCount;
    }

    public long getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(long totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public long getCompletedOrdersCount() {
        return completedOrdersCount;
    }

    public void setCompletedOrdersCount(long completedOrdersCount) {
        this.completedOrdersCount = completedOrdersCount;
    }

    public long getCanceledOrdersCount() {
        return canceledOrdersCount;
    }

    public void setCanceledOrdersCount(long canceledOrdersCount) {
        this.canceledOrdersCount = canceledOrdersCount;
    }
}

