import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // World / map setup
        World world1 = new World("The Castle Dungeons");
        Map gameMap = new Map();
        Room[][] map = gameMap.getMap();

        // Weapons / items
        Weapon sword = new Weapon("Sword", 5, 0, 0);
        Item potion = new Item("Potion", "Heals player", 20);

        // Player
        Player player1 = new Player("Hero", 70, 2, 20, sword, world1, potion);

        // Enemies in rooms
        map[2][0].setEnemy(new Enemy("Dungeon Guard", 25, 2, 10, new Weapon("Club", 3, 0, 0), world1)); // Start room
        map[1][0].setEnemy(new Enemy("Gate Guard", 35, 2, 15, new Weapon("Spear", 4, 0, 0), world1));   // Room 2
        map[1][1].setEnemy(new Enemy("Mini Boss", 50, 3, 20, new Weapon("Battle Axe", 6, 0, 0), world1)); // Mini boss
        map[0][0].setEnemy(new Enemy("Final Boss", 80, 4, 25, new Weapon("Dark Blade", 8, 0, 0), world1)); // Boss room

        // Starting player position
        int currentRow = 2;
        int currentCol = 0;

        // Game flags
        boolean hasKey = false;
        boolean treasureTaken = false;
        boolean keyTaken = false;
        boolean battleReady = false;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to " + world1.getDescription() + "!");
        System.out.println("Type: look, stats, inventory, north, south, east, west, attack, heal, open, quit");

        boolean running = true;

        while (running && player1.isAlive()) {
            Room currentRoom = map[currentRow][currentCol];

            if (currentRoom.getEnemy() != null && currentRoom.getEnemy().isAlive() && !battleReady) {
                player1.healToFull();
                System.out.println("Your HP is restored to full for this battle.");
                battleReady = true;
            }

            System.out.println("\n--------------------");
            System.out.println("You are in: " + currentRoom.getName());
            System.out.println(currentRoom.getDescription());

            if (currentRoom.getEnemy() != null && currentRoom.getEnemy().isAlive()) {
                System.out.println("Enemy here: " + currentRoom.getEnemy().getName());
            }

            if (currentRoom.getWeapon() != null || currentRoom.getItem() != null) {
                System.out.println("There may be something useful here.");
            }

            System.out.print("\n> ");
            String input = scanner.nextLine().toLowerCase();

            // LOOK
            if (input.equals("look")) {
                System.out.println(currentRoom);
            }

            // STATS
            else if (input.equals("stats")) {
                System.out.println("Player: " + player1.getName());
                System.out.println("HP: " + player1.getHp() + "/" + player1.getMaxHp());
                System.out.println("Weapon: " + player1.getWeapon().getName());
                System.out.println("Has key: " + hasKey);
                System.out.println("Items in inventory: " + player1.getInventory().size());
            }

            // INVENTORY
            else if (input.equals("inventory")) {
                player1.showInventory();
            }

            // ATTACK
            else if (input.equals("attack")) {
                if (currentRoom.getEnemy() == null || !currentRoom.getEnemy().isAlive()) {
                    System.out.println("There is no enemy here.");
                } else {
                    Enemy enemy = currentRoom.getEnemy();

                    boolean playerCrit = player1.attack(enemy);
                    int playerDamage = player1.getAttack() + player1.getWeapon().getDamage();
                    if (playerCrit) {
                        playerDamage *= 2;
                        System.out.println("*** CRITICAL HIT! ***");
                    }
                    System.out.println(player1.getName() + " attacked " + enemy.getName()
                            + " and dealt " + playerDamage + " damage!");

                    if (!enemy.isAlive()) {
                        battleReady = false;
                        System.out.println(enemy.getName() + " has been defeated!");
                        currentRoom.setEnemy(null);

                        if (currentRoom.getName().equals("Boss Room")) {
                            System.out.println("You defeated the final boss!");
                            System.out.println("You win!");
                            break;
                        }
                    } else {
                        boolean enemyCrit = enemy.attack(player1);
                        int enemyDamage = enemy.getAttack() + enemy.getWeapon().getDamage();
                        if (enemyCrit) {
                            enemyDamage *= 2;
                            System.out.println("*** ENEMY CRITICAL HIT! ***");
                        }
                        System.out.println(enemy.getName() + " attacked " + player1.getName()
                                + " and dealt " + enemyDamage + " damage!");
                    }
                }
            }

            // HEAL
            else if (input.equals("heal")) {
                player1.useItem();
            }

            // OPEN / TAKE TREASURE / TAKE KEY
            else if (input.equals("open")) {
                if (currentRoom.getName().equals("Treasure Room") && !treasureTaken) {
                    System.out.println("You open the chest.");
                    System.out.println("You found the key!");
                    hasKey = true;
                    keyTaken = true;
                    gameMap.unlockAllDoors();
                    System.out.println("All locked doors are now unlocked.");
                    if (currentRoom.getWeapon() != null) {
                        System.out.println("You found an " + currentRoom.getWeapon().getName() + "!");
                        player1.setWeapon(currentRoom.getWeapon());
                        currentRoom.setWeapon(null);
                    }
                    if (currentRoom.getItem() != null) {
                        System.out.println("You found a " + currentRoom.getItem().getName() + "!");
                        player1.addItem(currentRoom.getItem());
                        currentRoom.setItem(null);
                    }
                    treasureTaken = true;
                } else {
                    System.out.println("There is nothing to open here.");
                }
            }

            // MOVEMENT
            else if (input.equals("north") || input.equals("south") || input.equals("east") || input.equals("west")) {

                if (currentRoom.getEnemy() != null && currentRoom.getEnemy().isAlive()) {
                    System.out.println("You must defeat the enemy before leaving the room!");
                    continue;
                }

                // Mini Boss Room: west to Room 2, south to Treasure Room
                if (currentRoom.getName().equals("Mini Boss Room")) {
                    if (input.equals("west")) {
                        currentRow = 1;
                        currentCol = 0;
                        battleReady = false;
                        System.out.println("You move to " + map[currentRow][currentCol].getName() + ".");
                    } else if (input.equals("south")) {
                        currentRow = 2;
                        currentCol = 1;
                        battleReady = false;
                        System.out.println("You move to " + map[currentRow][currentCol].getName() + ".");
                    } else {
                        System.out.println("There is no door that way.");
                    }
                    continue;
                }

                // Start Room: north only
                if (currentRoom.getName().equals("Start Room")) {
                    if (input.equals("north")) {
                        currentRow = 1;
                        currentCol = 0;
                        battleReady = false;
                        System.out.println("You move to " + map[currentRow][currentCol].getName() + ".");
                    } else {
                        System.out.println("There is no door that way.");
                    }
                    continue;
                }

                // Room 2: north to boss, south to start, east to mini boss
                if (currentRoom.getName().equals("Room 2")) {
                    if (input.equals("north")) {
                        if (map[0][0].isLocked()) {
                            if (hasKey) {
                                map[0][0].setLocked(false);
                                System.out.println("You unlock the door.");
                                currentRow = 0;
                                currentCol = 0;
                                battleReady = false;
                                System.out.println("You move to " + map[currentRow][currentCol].getName() + ".");
                            } else {
                                System.out.println("The door is locked.");
                            }
                        } else {
                            currentRow = 0;
                            currentCol = 0;
                            battleReady = false;
                            System.out.println("You move to " + map[currentRow][currentCol].getName() + ".");
                        }
                    } else if (input.equals("south")) {
                        currentRow = 2;
                        currentCol = 0;
                        battleReady = false;
                        System.out.println("You move to " + map[currentRow][currentCol].getName() + ".");
                    } else if (input.equals("east")) {
                        currentRow = 1;
                        currentCol = 1;
                        battleReady = false;
                        System.out.println("You move to " + map[currentRow][currentCol].getName() + ".");
                    } else {
                        System.out.println("There is no door that way.");
                    }
                    continue;
                }

                // Treasure Room: north to Mini Boss Room only
                if (currentRoom.getName().equals("Treasure Room")) {
                    if (input.equals("north")) {
                        currentRow = 1;
                        currentCol = 1;
                        battleReady = false;
                        System.out.println("You move to " + map[currentRow][currentCol].getName() + ".");
                    } else {
                        System.out.println("There is no door that way.");
                    }
                    continue;
                }

                // Boss Room: south back to Room 2
                if (currentRoom.getName().equals("Boss Room")) {
                    if (input.equals("south")) {
                        currentRow = 1;
                        currentCol = 0;
                        battleReady = false;
                        System.out.println("You move to " + map[currentRow][currentCol].getName() + ".");
                    } else {
                        System.out.println("There is no door that way.");
                    }
                    continue;
                }

                System.out.println("You can't go that way.");
            }

            // QUIT
            else if (input.equals("quit")) {
                System.out.println("Goodbye!");
                running = false;
            }

            else {
                System.out.println("Invalid command.");
            }
        }

        scanner.close();
    }
}