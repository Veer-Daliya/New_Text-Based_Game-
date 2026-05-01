//Item Class
public class Item {
    private String name;
    private String description;
    private int healAmount = 0;

    public Item(String name, String description, int healAmount) {
        this.name = name;
        this.description = description;
        this.healAmount = healAmount;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getHealAmount() {
        return healAmount;
    }


    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }



    public String toString() {
        return this.name + ": " + this.description + " " + this.healAmount;
    }






}
