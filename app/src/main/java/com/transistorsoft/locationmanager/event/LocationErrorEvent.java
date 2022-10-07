//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

public class LocationErrorEvent {
    public int errorCode;
    public String message;

    public LocationErrorEvent(Integer var1, String var2) {
        this.errorCode = var1;
        this.message = var2;
    }

    public LocationErrorEvent(Integer var1) {
        this.errorCode = var1;
        this.message = "";
    }
}
