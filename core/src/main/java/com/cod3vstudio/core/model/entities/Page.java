package com.cod3vstudio.core.model.entities;

/**
 * Created by Administrator on 10/10/2016.
 */
public class Page {
    private final int pageNumber;
    private final String name;
    private final String message;
    private final String action;

    public Page(int pageNumber, String name, String message, String action) {
        this.pageNumber = pageNumber;
        this.name = name;
        this.message = message;
        this.action = action;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getAction() {
        return action;
    }
}
