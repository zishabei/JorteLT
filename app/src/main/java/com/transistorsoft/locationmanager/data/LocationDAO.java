//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.data;

import com.transistorsoft.locationmanager.location.TSLocation;
import java.util.List;
import org.json.JSONObject;

public interface LocationDAO {
    List<LocationModel> all();

    List<LocationModel> allWithLocking(Integer var1);

    LocationModel first();

    boolean unlock(LocationModel var1);

    boolean unlock();

    boolean unlock(List<LocationModel> var1);

    String persist(JSONObject var1);

    boolean persist(TSLocation var1);

    boolean destroy(LocationModel var1);

    void destroyAll(List<LocationModel> var1);

    void prune(int var1);

    boolean clear();

    int count();

    void close();
}
