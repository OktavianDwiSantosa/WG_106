package main;

import required.Thing;

public class Chapter extends Thing {
    private final int chapterID;

    public Chapter(String vName, int vChapterID) {
        super(vName, "");
        chapterID = vChapterID;
    }

    // Getter
    public int getChapterID() {
        return chapterID;
    }

}
