package com.digitalocean.sdk.client;

import com.digitalocean.sdk.ds.DataStore;
import com.digitalocean.sdk.resource.droplet.Droplet;
import com.digitalocean.sdk.resource.droplet.DropletList;

public interface Client extends DataStore {

    DataStore getDataStore();
    Droplet getDroplet(String id);
    DropletList listDroplets();
    DropletList listDroplets(Integer page, Integer perPage);
    Droplet createDroplet(Droplet droplet);
    DropletList createDroplets(Droplet droplet, int numDroplets);
}
