package com.example.android.mrasyidridhotobing_1202150064_studycase5;

/**
 * Created by rasyidtobing on 25/03/2018.
 */

public class AddData {
    //deklarasi variable
    String todo, desc, prior;

    public AddData(String todo, String desc, String prior){
        this.todo=todo;
        this.desc=desc;
        this.prior=prior;
    }

    //method setter dan getter untuk to do name, description, dan priority
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}
