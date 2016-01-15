package com.example.edson.reminders;

/**
 * Created by edson on 13/01/2016.
 */
public class Lembrete {
    private int fid;
    private String fContent;
    private int fImportancia;

    public Lembrete(int id, String content, int importancia){
        fid = id;
        fContent = content;
        fImportancia = importancia;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getContent() {
        return fContent;
    }

    public void setContent(String content) {
        fContent = content;
    }

    public int getImportancia() {
        return fImportancia;
    }

    public void setImportancia(int importancia) {
        fImportancia = importancia;
    }
}
