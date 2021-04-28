package main3;

import java.util.ArrayList;

public class GameInfo { // berisi objek yang perlu dibagikan pada kelas lain (variabel global)
    public static Inventory userInventory; // inventory milik user
    public static ArrayList<Hero> activeParty = new ArrayList<>(); // hero yang dipakai untuk battle
    public static ArrayList<Hero> unlockedHero; // hero yang sudah dimiliki user
    public static boolean isGameOver;
}
