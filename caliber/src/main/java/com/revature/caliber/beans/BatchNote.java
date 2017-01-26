package com.revature.caliber.beans;

public class BatchNote extends Note {

    private Week week;
    private Batch batch;

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public BatchNote(Week week, Batch batch) {
        super();
        this.week = week;
        this.batch = batch;
    }

    public BatchNote() {
        super();
    }

    public BatchNote(String content, boolean sugarCoated) {
        super(content, sugarCoated);
    }

}
