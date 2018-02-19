package com.digitalocean.quickstart;

import com.digitalocean.sdk.client.Client;
import com.digitalocean.sdk.client.Clients;
import com.digitalocean.sdk.resource.droplet.DropletList;

public class QuickStart {

    public static void main(String[] args) {

        Client client =  Clients.builder().build();

        DropletList droplets = client.listDroplets(null, 1);
        System.out.println("total droplets: " + droplets.getTotal());
        droplets.stream()
            .filter(d -> d.getName().startsWith("ma"))
            .forEach(d -> System.out.println(d.getName()));
    }
}
