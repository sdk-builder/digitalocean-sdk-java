package com.digitalocean.quickstart;

import com.digitalocean.sdk.client.Client;
import com.digitalocean.sdk.client.Clients;
import com.digitalocean.sdk.resource.droplet.Droplet;
import com.digitalocean.sdk.resource.droplet.DropletBuilder;
import com.digitalocean.sdk.resource.droplet.DropletList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class QuickStart {

    private static final Logger log = LoggerFactory.getLogger(QuickStart.class);
    private static final String REGION_SLUG = "nyc1";
    private static final String DROPLET_SIZE = "s-1vcpu-1gb";
    private static final String DROPLET_IMAGE = "ubuntu-20-04-x64";

    public static void main(String[] args) {
        // demo
        // Client - using ~/.digitalocean/digitalocean.yaml
        Client client =  Clients.builder().build();

        // let's create a droplet
        Droplet droplet = DropletBuilder.instance()
            .setName("micah-standalone")
            .setRegionSlug(REGION_SLUG)
            .setSize(DROPLET_SIZE)
            .setImage(DROPLET_IMAGE)
            .addTag("micah")
            .buildAndCreate(client);

        log.info("Created droplet: {}", droplet.getName());

        // let's lookup a droplet by its id
        Droplet droplet2 = client.getDropletById(droplet.getId());

        log.info("Looked up droplet with id: {}. Found name: {}", droplet.getId(), droplet2.getName());

        // let's create a bunch of droplets all at once
        String tagName = "conference";
        Droplet dropletTemplate = DropletBuilder.instance()
            .setName(tagName + "-test")
            .setRegionSlug(REGION_SLUG)
            .setSize(DROPLET_SIZE)
            .setImage(DROPLET_IMAGE)
            .addTag(tagName)
            .build(client);

        DropletList droplets = client.createDroplets(dropletTemplate, 10);

        log.info("Created 10 droplets using name template: {}-test", tagName);
        droplets.stream().forEach(d -> {
            log.info("iterating over new droplets - {}", d.getName());
        });

        // let's create another bunch of droplets all at once
        tagName = "micah";
        dropletTemplate = DropletBuilder.instance()
            .setName(tagName + "-test")
            .setRegionSlug(REGION_SLUG)
            .setSize(DROPLET_SIZE)
            .setImage(DROPLET_IMAGE)
            .addTag(tagName)
            .build(client);

        DropletList droplets2 = client.createDroplets(dropletTemplate, 10);

        // let's get all the droplets by tag
        DropletList droplets3 = client.listDropletsByTag("micah");

        log.info("Found {} droplets with tag 'micah'", droplets3.getTotal());

        // let's use streams
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("page", 1);
        queryParams.put("per_page", 5);
        DropletList droplets4 = client.listDroplets(queryParams);

        log.info("Found {} droplets overall", droplets4.getTotal());

        droplets4.stream()
            .filter(d -> d.getTags().contains("micah"))
            .forEach(d -> {
                log.info("iterating over new droplets - {}, with tag: {}", d.getName(), d.getTags().iterator().next());
            });

        // let's delete a droplet
        droplet.delete();

        // let's delete all droplets by tag
        client.deleteByTag("conference");
        log.info("Deleted all droplets with tag: conference");

        client.deleteByTag("micah");
        log.info("Deleted all droplets with tag: micah");
    }
}
