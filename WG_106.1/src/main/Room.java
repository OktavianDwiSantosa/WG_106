package main;

import required.enums.Direction;
import required.Thing;

import java.util.HashMap;

public class Room extends Thing {
    private final int roomID;
    private HashMap<Direction, Room> nextRoom = new HashMap<>();

    public Room(String vName, String vDescription, int vRoomID) {
        super(vName, vDescription);
        roomID = vRoomID;
    }

    // Getter
    public int getRoomID() {
        return roomID;
    }

    public HashMap<Direction, Room> getNextRoom() {
        return nextRoom;
    }

    public void setNextRoom(HashMap<Direction, Room> vNextRoom) { nextRoom = vNextRoom; }
}
