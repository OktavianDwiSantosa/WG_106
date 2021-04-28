package main3;

import required.Thing;

public class Chapter extends Thing {
    private int chapterID;

    public Chapter(String vName) {
        super(vName, "");
    }

    @Override
    public void describe() {
        super.describe();
    }

    public int getChapterID() {
        return chapterID;
    }

    public void setChapterID(int vChapterID) {
        chapterID = vChapterID;
    }
}
