package com.digitalocean.sdk.impl.client;

import com.digitalocean.sdk.cache.CacheManager;
import com.digitalocean.sdk.client.AuthenticationScheme;
import com.digitalocean.sdk.client.Proxy;
import com.digitalocean.sdk.impl.api.ClientCredentialsResolver;
import com.digitalocean.sdk.impl.http.QueryString;
import com.digitalocean.sdk.impl.http.authc.RequestAuthenticatorFactory;
import com.digitalocean.sdk.impl.resource.droplet.InternalDroplet;
import com.digitalocean.sdk.impl.util.BaseUrlResolver;
import com.digitalocean.sdk.resource.droplet.Droplet;
import com.digitalocean.sdk.resource.droplet.DropletContainer;
import com.digitalocean.sdk.resource.droplet.DropletList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.digitalocean.sdk.lang.Assert.hasText;
import static com.digitalocean.sdk.lang.Assert.notNull;

public class DefaultClient extends AbstractClient {

    public DefaultClient(ClientCredentialsResolver clientCredentialsResolver,
                         BaseUrlResolver baseUrlResolver,
                         Proxy proxy,
                         CacheManager cacheManager,
                         AuthenticationScheme authenticationScheme,
                         RequestAuthenticatorFactory requestAuthenticatorFactory,
                         int connectionTimeout) {
        super(clientCredentialsResolver, baseUrlResolver, proxy, cacheManager, authenticationScheme, requestAuthenticatorFactory, connectionTimeout);
    }

    @Override
    public Droplet getDroplet(String id) {
        hasText(id, "'id' is required and cannot be null or empty.");
        String href = "/v2/droplets/" + id;
        DropletContainer dropletContainer = getDataStore().getResource(href, DropletContainer.class);
        return dropletContainer.getDroplet();
    }

    @Override
    public DropletList listDroplets() {
        String href = "/v2/droplets";
        return getDataStore().getResource(href, DropletList.class);
    }

    @Override
    public DropletList listDroplets(Integer page, Integer perPage) {
        QueryString queryString = new QueryString();

        if (page != null) {
            queryString.put("page", page);
        }

        if (perPage != null) {
            queryString.put("per_page", perPage);
        }
        String href = QueryString.buildHref("/v2/droplets", queryString);
        return getDataStore().getResource(href, DropletList.class);
    }

    @Override
    public Droplet createDroplet(Droplet droplet) {
        notNull(droplet, "A droplet parameter is required");

        // need to setup InternalDroplet here
        InternalDroplet internalDroplet = copyDroplet(droplet);
        internalDroplet.setName(droplet.getName());

        String href = "/v2/droplets";
        DropletContainer dropletContainer =  getDataStore().create(
            href,
            internalDroplet,
            null,
            DropletContainer.class
        );
        return dropletContainer.getDroplet();
    }

    @Override
    public DropletList createDroplets(Droplet droplet, int numDroplets) {
        List<String> names = IntStream.rangeClosed(1, numDroplets).boxed()
            .map(i -> droplet.getName() + "-" + i)
            .collect(Collectors.toList());

        // need to setup InternalDroplet here
        InternalDroplet internalDroplet = copyDroplet(droplet);
        internalDroplet.setNames(names);

        String href = "/v2/droplets";
        return getDataStore().create(
            href,
            internalDroplet,
            null,
            DropletList.class
        );
    }

    @Override
    public void deleteByTag(String tag) {
        String href = "/v2/droplets?tag_name=" + tag;
        getDataStore().delete(href);
    }

    private InternalDroplet copyDroplet(Droplet droplet) {
        // copies everything by name and names
        InternalDroplet internalDroplet = new InternalDroplet(getDataStore());

        internalDroplet.setRegion(droplet.getDropletRegion().getSlug());
        internalDroplet.setSize(droplet.getSize());
        internalDroplet.setImage(droplet.getImage());
        internalDroplet.setTags(droplet.getTags());

        return internalDroplet;
    }
}
