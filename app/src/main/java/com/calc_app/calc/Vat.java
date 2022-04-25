package com.calc_app.calc;

import androidx.annotation.NonNull;

public class Vat {
    private String id;
    private String display_text;

    public Vat(String id, String display_text) {
        this.id = id;
        this.display_text = display_text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayText() {
        return display_text;
    }

    public void setDisplayText(String display_text) {
        this.display_text = display_text;
    }

    //to display object as a string in spinner
    @NonNull
    @Override
    public String toString() {
        return display_text;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vat){
            Vat c = (Vat )obj;
            return c.getDisplayText().equals(display_text) && c.getId() == id;
        }

        return false;
    }

}