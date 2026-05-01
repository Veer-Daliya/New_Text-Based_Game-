//Class World
public class World {
    private Player player;
    private Enemy enemy;
    private String description;



    public World(String description) {
        this.description = description;

    }

    //Getters
    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy(){
        return enemy;
    }

    public String getDescription() {
        return description;
    }


    //Setters
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }



    public String toString() {
        return "Player: " + player.getName() + "\n" +
                "HP: " + player.getHp() + "/" + player.getMaxHp() + "\n" +
                "ATK: " + (player.getAttack() + player.getWeapon().getDamage()) + "\n" +
                "Weapon: " + player.getWeapon().getName() + "\n\n" +
                "Enemy: " + enemy.getName() + "\n" +
                "HP: " + enemy.getHp() + "/" + enemy.getMaxHp() + "\n" +
                "ATK: " + (enemy.getAttack() + enemy.getWeapon().getDamage()) + "\n" +
                "Weapon: " + enemy.getWeapon().getName();
    }













}

