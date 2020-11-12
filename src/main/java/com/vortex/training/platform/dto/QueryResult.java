package com.vortex.training.platform.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author : light
 * @date: 2020/10/30 11:16
 */
public class QueryResult<T> implements Serializable {

    private long rowCount;
    private List<T> items;

    public QueryResult() {

    }

    public QueryResult(List<T> items, long rowCount) {
        this.items = items;
        this.rowCount = rowCount;
    }

    public long getRowCount() {
        return rowCount;
    }

    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}
