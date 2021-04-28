package main3;

import required.Thing;

public class Room extends Thing {
    private int roomID;

    public Room(String vName, String vDescription) {
        super(vName, vDescription);
    }

    @Override
    public void describe() {
        super.describe();
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int vRoomID) {
        roomID = vRoomID;
    }
}
