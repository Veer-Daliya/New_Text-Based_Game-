//Weapon Class
public class Weapon {

    //instance variables
    private String name;
    private int damage;
    private int defense;
    private int range;

    // Constructor
    public Weapon(String name, int damage, int defense, int range) {
        this.name = name;
        this.damage = damage;
        this.defense = defense;
        this.range = range;

    }

    //A toString method to return a string representation
    public String toString() {
        return "This weapon: " + this.name + " has " + this.damage + " attack power " + "and " + this.defense + " defensive capabilities and " + this.range + " range";

    }

    //Getters
    public String getName() {
        return this.name;
    }
    public int getDamage() {
        return this.damage;
    }
    public int getDefense() {
        return this.defense;
    }
    public int getRange() {
        return this.range;
    }

    //Setters
    public void setDamage(int damage) {
        this.damage = damage;
    }







}
