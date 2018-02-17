package com.okta.sdk.resource.droplet;

import com.okta.sdk.resource.Resource;

public interface DropletContainer extends Resource {

    Droplet getDroplet();
    DropletContainer setDroplet(Droplet droplet);
}
