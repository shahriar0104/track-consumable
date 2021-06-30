package models;

public class Consumable {
    private String name;
    private String consumptionStartDate;
    private String consumptionEndDate;
    private int totalConsumptionTime;
    private String rating;
    private int totalConsumptionDays;
    private boolean isEditable;
    private int consumableType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConsumptionStartDate() {
        return consumptionStartDate;
    }

    public void setConsumptionStartDate(String consumptionStartDate) {
        this.consumptionStartDate = consumptionStartDate;
    }

    public String getConsumptionEndDate() {
        return consumptionEndDate;
    }

    public void setConsumptionEndDate(String consumptionEndDate) {
        this.consumptionEndDate = consumptionEndDate;
    }

    public int getTotalConsumptionTime() {
        return totalConsumptionTime;
    }

    public void setTotalConsumptionTime(int totalConsumptionTime) {
        this.totalConsumptionTime = totalConsumptionTime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getTotalConsumptionDays() {
        return totalConsumptionDays;
    }

    public void setTotalConsumptionDays(int totalConsumptionDays) {
        this.totalConsumptionDays = totalConsumptionDays;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public int getConsumableType() {
        return consumableType;
    }

    public void setConsumableType(int consumableType) {
        this.consumableType = consumableType;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                ", consumptionStartDate='" + consumptionStartDate + '\'' +
                ", consumptionEndDate='" + consumptionEndDate + '\'' +
                ", totalConsumptionTime=" + totalConsumptionTime +
                ", rating='" + rating + '\'' +
                ", totalConsumptionDays=" + totalConsumptionDays +
                ", isEditable=" + isEditable +
                ", consumableType=" + consumableType +
                '}';
    }
}
