import java.util.ArrayList;

//Class Player
public class Player {
    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private Weapon myWeapon;
    private World myWorld;
    private ArrayList<Item> inventory;

    public Player(String name, int maxHp, int attack, int defense, Weapon myWeapon, World myWorld, Item starterItem) {
        this.name = name;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.myWeapon = myWeapon;
        this.myWorld = myWorld;
        this.hp = maxHp;
        this.inventory = new ArrayList<Item>();

        if (starterItem != null) {
            inventory.add(starterItem);
        }
    }

    //Getters
    public String getName() {
        return this.name;
    }

    public int getMaxHp() {
        return this.maxHp;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public Weapon getWeapon() {
        return this.myWeapon;
    }

    public World getWorld() {
        return this.myWorld;
    }

    public int getHp() {
        return this.hp;
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, hp);
    }

    public void addItem(Item item) {
        if (item != null) {
            inventory.add(item);
            System.out.println(item.getName() + " was added to your inventory.");
        }
    }

    public boolean hasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void showInventory() {
        if (inventory.size() == 0) {
            System.out.println("Your inventory is empty.");
            return;
        }

        System.out.println("Inventory:");
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            System.out.println("- " + item.getName() + ": " + item.getDescription());
        }
    }

    // Uses the first healing item in the inventory and removes it after use
    public void useItem() {
        if (inventory.size() == 0) {
            System.out.println("You have no item to use.");
            return;
        }

        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item.getHealAmount() > 0) {
                this.hp += item.getHealAmount();
                if (this.hp > this.maxHp) {
                    this.hp = this.maxHp;
                }
                System.out.println(this.name + " has healed " + item.getHealAmount() + " HP and now has " + this.hp + "/" + this.maxHp + " HP");
                inventory.remove(i);
                return;
            }
        }

        System.out.println("You do not have a healing item.");
    }

    //This method checks whether or not this player is alive
    public boolean isAlive() {
        return hp > 0;
    }

    public boolean attack(Enemy enemy) {
        int baseDamage = this.attack + this.myWeapon.getDamage();
        boolean isCrit = Math.random() < 0.20; // 20% crit chance for player
        int totalDamage = isCrit ? baseDamage * 2 : baseDamage;
        enemy.setHp(enemy.getHp() - totalDamage);
        return isCrit;
    }

    public void healToFull() {
        hp = maxHp;
    }

    public void setWeapon(Weapon weapon) {
        this.myWeapon = weapon;
    }

    public String toString() {
        return this.name + "HP: " + this.hp + "/" + this.maxHp;
    }
}