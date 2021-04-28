package required;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Thing {
    private String name;
    private String description;
    private String trueClassType; // digunakan untuk sorting dan print khusus
    private ArrayList<Thing> arrThing = new ArrayList<>(); // container objek lain

    // Constructor
    public Thing(String vName, String vDescription) {
        name = vName;
        description = vDescription;
        trueClassType = getClass().getSimpleName(); // mengingat type Class ketika pertama kali dibuat
    }

    public void describe() { // mencetak nama dan deskripsi Thing
        System.out.println(name);
        System.out.println(description);
    }

    public void showAllClass() {
        int i = 1;

        // cara sorting Item sesuai trueClassType
        Comparator<Thing> trueClassSort = Comparator.comparing(Thing::getTrueClassType);
        getArrThing().sort(trueClassSort);

        for (Thing objThing : getArrThing()) {
            System.out.println("\n" + "Item " + i);
            objThing.describe();
            i++;
        }
    }

    public void showOneClass(String vTrueClassType) {
        int i = 1;
        for (Thing objThing : getArrThing()) {
            if (objThing.getTrueClassType().equals(vTrueClassType)) {
                System.out.println("\n" + vTrueClassType + " " + i);
                objThing.describe();
                i++;
            }
        }
    }

    public ArrayList<Thing> getOneClass(String vTrueClassType) {
        ArrayList<Thing> tempThing = new ArrayList<>();
        for (Thing objThing : getArrThing()) {
            if (objThing.getTrueClassType().equals(vTrueClassType)) {
                tempThing.add(objThing);
            }
        }
        return tempThing;
    }

    public void addThing(Thing objThing) { // menambahkan Thing ke dalam arrThing
        arrThing.add(objThing);
    }

    public void removeThing(Thing objThing) {
        arrThing.remove(objThing);
    }

    public Thing getThing(int index) {
        return arrThing.get(index);
    }

    public boolean searchThing(Thing objThing) {
        return arrThing.contains(objThing);
    }

    public int arrSize() {
        return arrThing.size();
    }

    // Getter
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTrueClassType() {
        return trueClassType;
    }

    public ArrayList<Thing> getArrThing() {
        return arrThing;
    }
}
