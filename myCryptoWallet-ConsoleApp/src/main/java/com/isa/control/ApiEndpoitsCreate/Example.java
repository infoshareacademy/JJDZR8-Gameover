package com.isa.control.ApiEndpoitsCreate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {
    @SerializedName("result")
    @Expose
    public List<Result> result = null;
    @SerializedName("cursor")
    @Expose
    Cursor CursorObject;
    @SerializedName("allowance")
    @Expose
    Allowance AllowanceObject;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public Cursor getCursorObject() {
        return CursorObject;
    }

    public void setCursorObject(Cursor cursorObject) {
        CursorObject = cursorObject;
    }

    public Allowance getAllowanceObject() {
        return AllowanceObject;
    }

    public void setAllowanceObject(Allowance allowanceObject) {
        AllowanceObject = allowanceObject;
    }
}

