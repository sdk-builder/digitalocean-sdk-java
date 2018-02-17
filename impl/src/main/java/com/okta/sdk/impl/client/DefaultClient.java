package com.okta.sdk.impl.client;

import com.okta.sdk.cache.CacheManager;
import com.okta.sdk.client.AuthenticationScheme;
import com.okta.sdk.client.Proxy;
import com.okta.sdk.impl.api.ClientCredentialsResolver;
import com.okta.sdk.impl.http.QueryString;
import com.okta.sdk.impl.http.authc.RequestAuthenticatorFactory;
import com.okta.sdk.impl.util.BaseUrlResolver;
import com.okta.sdk.resource.droplet.Droplet;
import com.okta.sdk.resource.droplet.DropletContainer;
import com.okta.sdk.resource.log.LogEventList;

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
    public LogEventList getLogs(String until, String since, String filter, String q, String sortOrder) {

        QueryString queryArgs = new QueryString();
        if (until != null) queryArgs.put("until", until);
        if (since != null) queryArgs.put("since", since);
        if (filter != null) queryArgs.put("filter", filter);
        if (q != null) queryArgs.put("q", q);
        if (sortOrder != null) queryArgs.put("sortOrder", sortOrder);

        String href = QueryString.buildHref("/api/v1/logs", queryArgs);
        return getDataStore().getResource(href, LogEventList.class);
    }

    @Override
    public LogEventList getLogs() {

        String href = "/api/v1/logs";
        return getDataStore().getResource(href, LogEventList.class);
    }

    @Override
    public Droplet getDroplet(String id) {
        hasText(id, "'id' is required and cannot be null or empty.");
        String href = "/v2/droplets/" + id;
        DropletContainer dropletContainer = getDataStore().getResource(href, DropletContainer.class);
        return dropletContainer.getDroplet();
    }
}
