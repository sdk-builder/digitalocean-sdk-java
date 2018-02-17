package com.okta.sdk.impl.client;

import com.okta.sdk.cache.CacheManager;
import com.okta.sdk.client.AuthenticationScheme;
import com.okta.sdk.client.Proxy;
import com.okta.sdk.impl.api.ClientCredentialsResolver;
import com.okta.sdk.impl.http.authc.RequestAuthenticatorFactory;
import com.okta.sdk.impl.util.BaseUrlResolver;
import com.okta.sdk.resource.droplet.Droplet;
import com.okta.sdk.resource.droplet.DropletContainer;

import static com.okta.sdk.lang.Assert.hasText;

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
}
