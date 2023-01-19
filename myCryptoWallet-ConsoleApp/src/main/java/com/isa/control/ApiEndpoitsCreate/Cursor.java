package com.isa.control.ApiEndpoitsCreate;

public class Cursor {
    private String last;
    private boolean hasMore;


    // Getter Methods

    public String getLast() {
        return last;
    }

    public boolean getHasMore() {
        return hasMore;
    }

    // Setter Methods

    public void setLast(String last) {
        this.last = last;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
