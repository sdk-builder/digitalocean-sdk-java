package com.digitalocean.sdk.resource.droplet;

import com.digitalocean.sdk.resource.Resource;

public interface Droplet extends Resource {

    Long getId();
    String getName();
    Long getMemory();
    Integer getVcpus();
    Integer getDisk();
    Boolean getLocked();
    String getStatus();

    String getRegion();
    String getSize();
    String getImage();

    Droplet setId(Long id);
    Droplet setName(String name);
    Droplet setMemory(Long memory);
    Droplet setVcpus(Integer vcpus);
    Droplet setDisk(Integer disk);
    Droplet setLocked(Boolean locked);
    Droplet setStatus(String status);

    Droplet setRegion(String region);
    Droplet setSize(String size);
    Droplet setImage(String image);
}
