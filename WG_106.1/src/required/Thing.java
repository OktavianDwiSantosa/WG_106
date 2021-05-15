package required;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Thing {
    // atribut diatur final agar hanya me-referensi ke satu objek setelah di construct
    private final String name;
    private final String description;

    // https://www.javatpoint.com/java-string-to-object
    private final ArrayList<Thing> arrThing = new ArrayList<>(); // container objek lain

    // Constructor
    public Thing(String vName, String vDescription) {
        name = vName;
        description = vDescription;
    }

    public void describe() {
        // mencetak nama dan deskripsi Thing
        System.out.println(name);
        System.out.println(description);
    }

    public void showAllClass() {
        int i = 1;

        // sorting Thing sesuai "Class Asli" ketika object itu pertama kali dibuat
        getArrThing().sort(Comparator.comparing(Thing::getTrueClassName));

        for (Thing objThing : getArrThing()) {
            System.out.println("\n" + "Object " + i);
            objThing.describe();
            i++;
        }
    }

    public void showOneClass(String vTrueClassType) {
        int i = 1;
        for (Thing objThing : getArrThing())
            if (objThing.getTrueClassName().equals(vTrueClassType)) {
                System.out.println("\n" + vTrueClassType + " " + i);
                objThing.describe();
                i++;
            }
    }

    public ArrayList<Thing> getOneClass(String vTrueClassType) {
        ArrayList<Thing> tempThing = new ArrayList<>();

        for (Thing objThing : getArrThing())
            if (objThing.getTrueClassName().equals(vTrueClassType))
                tempThing.add(objThing);

        tempThing.sort(Comparator.comparing(Thing::getName));
        return tempThing;
    }

    public void addThing(Thing objThing) { arrThing.add(objThing); }

    public void removeThing(Thing objThing) { arrThing.remove(objThing); }

    public Thing getThing(int index) { return arrThing.get(index); }

    public boolean searchThing(Thing objThing) { return arrThing.contains(objThing); }

    public int arrSize() { return arrThing.size(); }

    // Getter
    public String getName() { return name; }

    public String getTrueClassName() { return getClass().getSimpleName(); }

    public ArrayList<Thing> getArrThing() { return arrThing; }
}
