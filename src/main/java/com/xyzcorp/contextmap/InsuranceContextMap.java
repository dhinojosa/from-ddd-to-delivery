package com.xyzcorp.contextmap;

import guru.nidi.graphviz.engine.Format;
import org.contextmapper.contextmap.generator.ContextMapGenerator;
import org.contextmapper.contextmap.generator.model.*;

import java.io.IOException;

import static org.contextmapper.contextmap.generator.model.DownstreamPatterns.*;
import static org.contextmapper.contextmap.generator.model.UpstreamPatterns.*;

public class InsuranceContextMap {
    public static void main(String[] args) throws IOException {

        BoundedContext customerManagement = new BoundedContext("Customer Management Context");
        BoundedContext customerSelfService = new BoundedContext("Customer Self-Service Context");
        BoundedContext printing = new BoundedContext("Printing Context");
        BoundedContext debtCollection = new BoundedContext("Debt Collection Context");
        BoundedContext policyManagement = new BoundedContext("Policy Management Context");
        BoundedContext riskManagement = new BoundedContext("Risk Management Context");

        ContextMap contextMap = new ContextMap().addBoundedContext(customerManagement).addBoundedContext(customerSelfService).addBoundedContext(printing).addBoundedContext(debtCollection).addBoundedContext(policyManagement).addBoundedContext(riskManagement)

            // Customer/Supplier relationship example
            .addRelationship(new UpstreamDownstreamRelationship(customerManagement, customerSelfService).setCustomerSupplier(true))

            // Upstream/Downstream relationship with OHS, PL, ACL, and CF examples
            .addRelationship(new UpstreamDownstreamRelationship(printing, customerManagement).setUpstreamPatterns(OPEN_HOST_SERVICE, PUBLISHED_LANGUAGE).setDownstreamPatterns(ANTICORRUPTION_LAYER)).addRelationship(new UpstreamDownstreamRelationship(printing, policyManagement).setUpstreamPatterns(OPEN_HOST_SERVICE, PUBLISHED_LANGUAGE).setDownstreamPatterns(ANTICORRUPTION_LAYER)).addRelationship(new UpstreamDownstreamRelationship(printing, debtCollection).setUpstreamPatterns(OPEN_HOST_SERVICE, PUBLISHED_LANGUAGE).setDownstreamPatterns(ANTICORRUPTION_LAYER)).addRelationship(new UpstreamDownstreamRelationship(customerManagement, policyManagement).setUpstreamPatterns(OPEN_HOST_SERVICE, PUBLISHED_LANGUAGE).setDownstreamPatterns(CONFORMIST))

            // Shared Kernel relationship example
            .addRelationship(new SharedKernel(debtCollection, policyManagement))

            // Partnership relationship example
            .addRelationship(new Partnership(riskManagement, policyManagement));
        String homeDirectory = System.getProperty("user.home");

        // generate the Context Map
        new ContextMapGenerator().setLabelSpacingFactor(10).setWidth(3600).generateContextMapGraphic(contextMap, Format.PNG, homeDirectory + "/myContextMap.png");
    }
}
