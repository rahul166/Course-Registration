package com.example.erp.utils;

import java.util.HashMap;
import java.util.List;

public class dt2util {
    HashMap<String, List<String>> preq;
    HashMap<String,dtutil> fac;
    List<String> done;
//    int ret;

    public List<String> getDone() {
        return done;
    }

    public void setDone(List<String> done) {
        this.done = done;
    }

//    public int getRet() {
//        return ret;
//    }
//
//    public void setRet(int ret) {
//        this.ret = ret;
//    }

    public HashMap<String, List<String>> getPreq() {
        return preq;
    }

    public void setPreq(HashMap<String, List<String>> preq) {
        this.preq = preq;
    }

    public HashMap<String, dtutil> getFac() {
        return fac;
    }

    public void setFac(HashMap<String, dtutil> fac) {
        this.fac = fac;
    }
}
