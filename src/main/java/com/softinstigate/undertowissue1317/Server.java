/*
 * Copyright SoftInstigate srl. All Rights Reserved.
 *
 *
 * The copyright to the computer program(s) herein is the property of
 * SoftInstigate srl, Italy. The program(s) may be used and/or copied only
 * with the written permission of SoftInstigate srl or in accordance with the
 * terms and conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied. This copyright notice must not be removed.
 */
package com.softinstigate.undertowissue1317;

import static io.undertow.Handlers.path;
import io.undertow.Undertow;
import io.undertow.predicate.Predicate;
import io.undertow.predicate.PredicateParser;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathHandler;

/**
 *
 * @author Andrea Di Cesare <andrea@softinstigate.com>
 */
public class Server implements HttpHandler {
    Undertow server;

    public Server() {
        PathHandler paths = path()
                .addPrefixPath("/one", this)
                .addPrefixPath("/two", this);

        this.server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(paths).build();
        
        this.server.start();
    }

    @Override
    public void handleRequest(HttpServerExchange hse) throws Exception {
        System.out.println("************* relative path: ".concat(hse.getRelativePath()));
        System.out.println(("************* request path: ".concat(hse.getRequestPath())));

        String predicate1 = "path[/foo]";

        Predicate p1 = PredicateParser.parse(predicate1, this.getClass().getClassLoader());

        if (p1.resolve(hse)) {
            hse.setStatusCode(200);
            hse.endExchange();
        } else {
            hse.setStatusCode(403); // forbidden
            hse.endExchange();
        }
    }

    public void stop() {
        if (this.server != null) {
            this.server.stop();
        }
    }
}
