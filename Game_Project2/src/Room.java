public class Room {
    private String name;
    private String description;
    private boolean locked;
    private Enemy enemy;
    private Item item;
    private Weapon weapon;
    private boolean isMaze;

    public Room(String name, String description, boolean locked, boolean isMaze) {
        this.name = name;
        this.description = description;
        this.locked = locked;
        this.isMaze = isMaze;
        this.enemy = null;
        this.item = null;
        this.weapon = null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean isMaze() {
        return isMaze;
    }

    public void setMaze(boolean maze) {
        isMaze = maze;
    }

    public String toString() {
        return name + "\n" + description;
    }
}