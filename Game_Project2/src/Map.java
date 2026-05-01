public class Map {
    private Room[][] map;

    public Map() {
        map = new Room[3][3];

        // Row 0
        map[0][0] = new Room(
                "Boss Room",
                "The final boss awaits.",
                true, false // locked, not maze
        );

        // Row 1
        map[1][0] = new Room(
                "Room 2",
                "Enemies guard each exit.",
                false, false
        );

        map[1][1] = new Room(
                "Mini Boss Room",
                "A stronger enemy stands here. South leads to the treasure room.",
                false, false
        );

        map[1][2] = new Room(
                "Unused Room",
                "This room is no longer used.",
                false, false
        );

        // Row 2
        map[2][0] = new Room(
                "Start Room",
                "The beginning of your journey.",
                false, false
        );

        map[2][1] = new Room(
                "Treasure Room",
                "A chest sits in the center. The key is inside.",
                false, false
        );

        map[2][2] = null;

        // Empty spaces
        map[0][1] = null;
        map[0][2] = null;

        // Loot setup
        map[2][1].setWeapon(new Weapon("Axe", 8, 0, 0));
        map[2][1].setItem(new Item("Potion", "Heals player", 20));
    }

    public Room[][] getMap() {
        return map;
    }

    public Room getRoom(int row, int col) {
        if (row < 0 || row >= map.length || col < 0 || col >= map[row].length) {
            return null;
        }
        return map[row][col];
    }

    public void unlockAllDoors() {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] != null && map[row][col].isLocked()) {
                    map[row][col].setLocked(false);
                }
            }
        }
    }
}