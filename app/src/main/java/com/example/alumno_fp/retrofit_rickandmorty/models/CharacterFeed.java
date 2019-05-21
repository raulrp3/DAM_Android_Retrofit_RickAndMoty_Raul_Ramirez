package com.example.alumno_fp.retrofit_rickandmorty.models;

import java.util.ArrayList;
import java.util.List;

public class CharacterFeed {

    private Integer count;
    private Integer pages;
    private String next;
    private Object previus;
    private List<Character> results = new ArrayList<>();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Object getPrevius() {
        return previus;
    }

    public void setPrevius(Object previus) {
        this.previus = previus;
    }

    public List<Character> getResults() {
        return results;
    }

    public void setResults(List<Character> results) {
        this.results = results;
    }
}
