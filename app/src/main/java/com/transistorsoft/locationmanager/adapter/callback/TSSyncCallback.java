//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.adapter.callback;

import com.transistorsoft.locationmanager.data.LocationModel;
import java.util.List;

public interface TSSyncCallback {
    void onSuccess(List<LocationModel> var1);

    void onFailure(String var1);
}
