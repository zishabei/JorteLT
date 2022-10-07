//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

public class SecurityExceptionEvent {
    public SecurityException exception;
    public int action;

    public SecurityExceptionEvent(SecurityException var1, Integer var2) {
        this.action = var2;
        this.exception = var1;
    }
}
