public class Enemy {
    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private Weapon myWeapon;
    private World myWorld;
    private Item myItem;

    public Enemy(String name, int maxHp, int attack, int defense, Weapon myWeapon, World myWorld) {
        this.name = name;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.myWeapon = myWeapon;
        this.myWorld = myWorld;
        this.hp = maxHp;

    }
    
    //Getters

    public String getName()
    {
        return this.name;
    }

    public int getMaxHp()
    {
        return this.maxHp;
    }

    public int getAttack()
    {
        return this.attack;
    }

    public int getDefense()
    {
        return this.defense;
    }

    public Weapon getWeapon()
    {
        return this.myWeapon;
    }

    public int getHp()
    {
        return this.hp;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public void setItem(Item item)
    {
        this.myItem = item;
    }



    //This method checks whether or not this player is alive
    public boolean isAlive() {
        return hp > 0;
    }





    public void setHP(int hp) {
        this.hp = Math.max(0, hp);
    }



    //public void getMove(){

        //if (this.hp < 20 && myItem != null){

       // }




   // }

    public boolean attack(Player player) {
       int baseDamage = this.attack + this.myWeapon.getDamage();
       boolean isCrit = Math.random() < 0.10; // 10% crit chance for enemy
       int totalDamage = isCrit ? baseDamage * 2 : baseDamage;
       player.setHp(player.getHp() - totalDamage);
       return isCrit;
   }





    public String toString() {
        return "The hero " + this.name + " has an HP of "
                + this.hp + " and is in the world of "
                + this.myWorld;
    }


}
