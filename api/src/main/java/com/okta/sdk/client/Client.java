package com.okta.sdk.client;

import com.okta.sdk.ds.DataStore;
import com.okta.sdk.resource.droplet.Droplet;

public interface Client extends DataStore {

    DataStore getDataStore();

    Droplet getDroplet(String id);
}
