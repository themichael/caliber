package com.revature.caliber.beans;

public class Note {

    private int noteId;
    private String content;
    private String sugarCoatedContent;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSugarCoatedContent() {
        return sugarCoatedContent;
    }

    public void setSugarCoatedContent(String sugarCoatedContent) {
        this.sugarCoatedContent = sugarCoatedContent;
    }

    public Note(String content, boolean sugarCoated) {
        super();
        if (sugarCoated)
            this.sugarCoatedContent = content;
        else
            this.content = content;
    }

    public Note() {
        super();
    }


}
