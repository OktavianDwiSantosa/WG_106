package main3;

import required.Item;
import required.Thing;

import java.util.ArrayList;

public class GameEngine {
    public static void main(String[] args) {
        Weapon coba = new Weapon("Pedang Besi", "Pedang berkarat", null, 200, "Rare",
                0.5, 0.3, 0.4);
        Hero oktav = new Hero("Oktav", "Manusia", 1, 1,
                50, 20, 15);
        coba.setOwner(oktav);
        coba.setEquip(true);
        coba.setPrice(8950);
        coba.describe();

        Hero budi = new Hero("Budi", "Manusia", 1, 1,
                35, 30, 10);

        System.out.println();
        oktav.describe();
        System.out.println();
        budi.describe();

        ArrayList<Thing> arrThing = new ArrayList<>();
        arrThing.add(coba);
        arrThing.add(oktav);
        arrThing.add(budi);

        Room objRoom = new Room("Coba", "Ruangan");
        objRoom.getArrThing().addAll(arrThing);

        Inventory objInv = new Inventory("Tes", "Mantap");
        objInv.getArrThing().addAll(objRoom.getArrThing());

        System.out.println("\n" + objInv.getArrThing() + "\n");

        objInv.getThing(0).describe();

        // lakukan down casting jika ingin memanggil atribut/method dari trueClass tertentu
        // kalau untuk mencetak saja, maka tidak perlu di down cast
        /* Weapon objWeapon = (Weapon) objRoom.getArrThing().get(0);
         System.out.println("Price : " + objWeapon.getPrice()); */

        Box kotak1 = new Box("Kotak 1", "Kotak besi yang tidak terkunci", 10);
        Weapon senjata2 = new Weapon("Pedang Kayu", "Pedang kayu biasa", null, 150, "Common",
                0.2, 0.2, 0.1);
        senjata2.setPrice(5203);
        senjata2.setOwner(budi);

        kotak1.addThing(senjata2);

        System.out.println();
        kotak1.getThing(0).describe();

        Inventory objInv2 = new Inventory("Tes Inv 2", "");
        MedKit objMedKit = new MedKit("MedKit 1", "MedKit 50%", oktav, 100, "Common");
        objMedKit.setHealthMultiplier(0.5);
        objMedKit.setHealthValue(75);

        MedKit objMedKit2 = new MedKit("MedKit 2", "MedKit 30%", budi, 60, "Common");
        objMedKit2.setHealthMultiplier(0.3);
        objMedKit2.setHealthValue(45);

        objInv2.addThing(coba);
        objInv2.addThing(objMedKit);
        objInv2.addThing(senjata2);
        objInv2.addThing(objMedKit2);

        System.out.println("\nIni tes print true class\n");
        objInv2.describe();

        System.out.println("\nIni tes print item sesuai trueClassType\n");
        objInv2.showOneClass("MedKit");

        System.out.println("\nIni tes jual beli item\n");
        Inventory objInventory1 = new Inventory("User Inventory", "");
        Merchant objMerchant1 = new Merchant("Stranger", "Hello Stranger");

        Weapon objWeapon1 = new Weapon("Blaster", "Dark Blaster", objInventory1, 250, "Rare",
                0.6, 0.2, 0.2);
        MedKit objMedKit1 = new MedKit("MedKit 3", "MedKit 20%", objInventory1, 21, "Common");
        Material objMaterial1 = new Material("Copper", "Small Copper", objInventory1, 20, "Common");

        objInventory1.addThing(objWeapon1);
        objInventory1.addThing(objMedKit1);
        objInventory1.addThing(objMaterial1);
        objInventory1.describe();
        System.out.println();

        Room objRoom1 = new Room("Underground", "Basement of soldier");
        objRoom1.addThing(objMerchant1); // ada Merchant di ruangan underground
        Hero.setActiveRoom(objRoom1); // semua Hero berada di ruangan underground

        System.out.println("Size array objRoom1 : " + objRoom1.arrSize());
        System.out.println("Size array objInventory1 : " + objInventory1.arrSize());

        Merchant tempMerchant = (Merchant) objRoom1.getThing(0); // ambil Merchant
        Item tempItem = ((Item) objInventory1.getThing(0)); // ambil Weapon
        tempItem.sold(tempMerchant);

        System.out.println("\nApakah di inventory ada tempItem ? " + objInventory1.searchThing(tempItem));
        System.out.println("Apakah di merchant ada tempItem ? " + tempMerchant.searchThing(tempItem));


        System.out.println("\nIni tes battle system\n");

        Hero hero1 = new Hero("Elon", "", 1,
                0, 50, 12, 10);

        Skill skill1 = new Skill("Skill A", "", hero1, "Rare",
                0.3, "Light", 15);

        Skill skill2 = new Skill("Skill B", "", hero1, "Rare",
                0.5, "Normal", 30);

        Skill skill3 = new Skill("Skill C", "", hero1, "Epic",
                0.8, "Heavy", 45);

        hero1.addThing(skill1);
        hero1.addThing(skill2);
        hero1.addThing(skill3);

        Hero hero2 = new Hero("Tony", "", 1,
                0, 40, 18, 15);

        Skill skill4 = new Skill("Skill D", "", hero2, "Rare",
                0.3, "Light", 15);

        Skill skill5 = new Skill("Skill E", "", hero2, "Rare",
                0.5, "Normal", 30);

        Skill skill6 = new Skill("Skill F", "", hero2, "Epic",
                0.8, "Heavy", 45);

        hero2.addThing(skill4);
        hero2.addThing(skill5);
        hero2.addThing(skill6);

        Enemy enemy1 = new Enemy("Robot A", "", 1,
                10, 50, 10, 15);

        Skill skill7 = new Skill("Skill G", "", enemy1, "Rare",
                0.3, "Light", 15);

        Skill skill8 = new Skill("Skill H", "", enemy1, "Rare",
                0.5, "Normal", 30);

        Skill skill9 = new Skill("Skill I", "", enemy1, "Epic",
                0.8, "Heavy", 45);

        enemy1.addThing(skill7);
        enemy1.addThing(skill8);
        enemy1.addThing(skill9);

        Enemy enemy2 = new Enemy("Robot B", "", 2,
                12, 40, 12, 10);

        Skill skill10 = new Skill("Skill J", "", enemy2, "Rare",
                0.3, "Light", 15);

        Skill skill11 = new Skill("Skill K", "", enemy2, "Rare",
                0.5, "Normal", 30);

        Skill skill12 = new Skill("Skill L", "", enemy2, "Epic",
                0.8, "Heavy", 45);

        enemy2.addThing(skill10);
        enemy2.addThing(skill11);
        enemy2.addThing(skill12);

        GameInfo.activeParty.add(hero1);
        GameInfo.activeParty.add(hero2);

        Room room1 = new Room("Ruangan A", "");
        room1.addThing(enemy1);
        room1.addThing(enemy2);

        ArrayList<Thing> arrEnemy = room1.getOneClass("Enemy");

        BattleSystem objBS = new BattleSystem();
        objBS.getArrHero().addAll(GameInfo.activeParty);
        objBS.getArrEnemy().addAll(arrEnemy);

        objBS.startBattle();
    }
}
