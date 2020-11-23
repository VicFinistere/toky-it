package com.zoe.commerce;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private boolean checked;

    protected Command(){}

    public Command(Date date){
        this.date = date;
        this.checked = false;
    }

    public Command(Date date, Boolean checked){
        this.date = date;
        this.checked = checked;
    }

    @Override
    public String toString() {
        return String.format(
                "Command[id=%d, date='%s', checked='%s']",
                id, date, checked);
    }

    public Long getId(){
        return id;
    }

    public Date getDate(){
        return date;
    }

    public boolean isChecked(){
        return checked;
    }
}
