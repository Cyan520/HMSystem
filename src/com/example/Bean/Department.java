package com.example.Bean;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
public class Department {
    private int id;
    private String name;
    private int mangerId;
    private String manger;
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMangerId() {
        return mangerId;
    }

    public void setMangerId(int mangerId) {
        this.mangerId = mangerId;
    }
    public String getManger(){
        return manger;
    }

    public void setManger(String manger) {
        this.manger = manger;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}

