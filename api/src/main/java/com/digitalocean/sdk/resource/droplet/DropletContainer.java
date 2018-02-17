package com.digitalocean.sdk.resource.droplet;

import com.digitalocean.sdk.resource.Resource;

public interface DropletContainer extends Resource {

    Droplet getDroplet();
    DropletContainer setDroplet(Droplet droplet);
}
