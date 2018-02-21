package com.digitalocean.sdk.resource.droplet;

import com.digitalocean.sdk.resource.Resource;

import java.util.List;

public interface Droplet extends Resource {

    Long getId();
    String getName();
    Long getMemory();
    Integer getVcpus();
    Integer getDisk();
    Boolean getLocked();
    String getStatus();

    String getSize();
    String getImage();

    List<String> getTags();

    DropletRegion getDropletRegion();

    Droplet setId(Long id);
    Droplet setName(String name);
    Droplet setMemory(Long memory);
    Droplet setVcpus(Integer vcpus);
    Droplet setDisk(Integer disk);
    Droplet setLocked(Boolean locked);
    Droplet setStatus(String status);

    Droplet setSize(String size);
    Droplet setImage(String image);

    Droplet setTags(List<String> tags);

    Droplet setDropletRegion(DropletRegion dropletRegion);
}
