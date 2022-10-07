//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

public class TemplateErrorEvent {
    private final String mTemplateName;
    private final Exception mError;

    public TemplateErrorEvent(String var1, Exception var2) {
        this.mTemplateName = var1;
        this.mError = var2;
    }

    public String getTemplateName() {
        return this.mTemplateName;
    }

    public Exception getError() {
        return this.mError;
    }
}
