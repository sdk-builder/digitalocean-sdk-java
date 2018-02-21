package com.digitalocean.sdk.resource.droplet;

import com.digitalocean.sdk.client.Client;
import com.digitalocean.sdk.lang.Classes;

public interface DropletBuilder {

    static DropletBuilder instance() {
        return Classes.newInstance("com.digitalocean.sdk.impl.resource.droplet.DefaultDropletBuilder");
    }

    DropletBuilder setRegionSlug(String regionSlug);
    DropletBuilder setName(String name);
    DropletBuilder setSize(String size);
    DropletBuilder setImage(String image);

    DropletBuilder addTag(String tag);

    Droplet build(Client client);
    Droplet buildAndCreate(Client client);
}
