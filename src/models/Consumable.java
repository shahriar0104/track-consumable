package models;

public class Consumable {
    protected String name;
    protected String consumptionStartDate;
    protected String consumptionEndDate;
    protected int totalConsumptionTime;
    protected String rating;
    protected int totalConsumptionDays;
    protected boolean isEditable;

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
                '}';
    }
}
