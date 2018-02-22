package com.digitalocean.sdk.client;

import com.digitalocean.sdk.ds.DataStore;
import com.digitalocean.sdk.resource.droplet.Droplet;
import com.digitalocean.sdk.resource.droplet.DropletList;

import java.util.Map;

public interface Client extends DataStore {

    DataStore getDataStore();
    Droplet getDropletById(Long id);
    DropletList listDroplets();
    DropletList listDroplets(Map<String, Object> queryParams);
    DropletList listDropletsByTag(String tag);
    Droplet createDroplet(Droplet droplet);
    DropletList createDroplets(Droplet droplet, int numDroplets);
    void deleteByTag(String tag);
}
