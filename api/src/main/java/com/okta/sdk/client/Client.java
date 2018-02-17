package com.okta.sdk.client;

import com.okta.sdk.ds.DataStore;
import com.okta.sdk.resource.droplet.Droplet;
import com.okta.sdk.resource.log.LogEventList;

public interface Client extends DataStore {

    DataStore getDataStore();

    LogEventList getLogs(String until, String since, String filter, String q, String sortOrder);
    LogEventList getLogs();

    Droplet getDroplet(String id);
}
