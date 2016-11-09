package com.example.rohit.tolist.Models;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rohit on 01/11/2016.
 */

public class Work {
    private String title;
    private String detail;
    private String category;
    private DatabaseReference reference;

    public Work(){}
    public Work(String title, String detail, String category) {
        this.title = title;
        this.detail = detail;
        this.category = category;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setReference(DatabaseReference reference) {
        this.reference = reference;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getCategory() {
        return category;
    }

    public DatabaseReference getReference() {
        return reference;
    }

    public Map<String,Object> toMap() {
        HashMap<String , Object> result = new HashMap<>();
        result.put("title",title);
        result.put("detail",detail);
        result.put("category",category);
        return  result;
    }
}
