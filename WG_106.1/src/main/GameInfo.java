package main;

import java.util.ArrayList;

// Class GameInfo berisi objek yang perlu dibagikan pada kelas lain (variabel global)

/*
    Variabel static itu "sedikit" melanggar prinsip OOP, namun,
    variabel yang berada di dalam GameInfo ini dibuat static,
    karena dalam cakupan game ini, hanya akan dibuat satu objek saja,
    tidak ada pembuatan objek kedua dst.

    Artinya, di dalam game ini:
    1. Hanya terdapat satu objek userInventory.
    2. Hanya terdapat satu objek theMerchant.
    3. Hanya terdapat satu objek ArrayList activeParty
    4. dst.

    Alasan lainnya, program ini masih single-thread,
    (hanya satu user yang melakukan eksekusi terhadap method public static void main).
*/

public final class GameInfo {
    public static Inventory userInventory; // inventory milik user
    public static Merchant theMerchant; // merchant selama permainan berlangsung
    public static ArrayList<Hero> activeParty; // hero yang dipakai untuk battle
    public static ArrayList<Hero> knownHeroes; // hero yang sudah dimiliki user
    public static ArrayList<Enemy> knownEnemies; // enemy yang sudah pernah dikalahkan
    public static ArrayList<Chapter> knownChapters; // chapter yang dapat dipilih user
    public static Room activeRoom; // tempat hero berada saat bermain
    public static boolean isGameOver = false;
}
