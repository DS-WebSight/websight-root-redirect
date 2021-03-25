package pl.ds.websight.root.redirect;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Root redirect servlet configuration")
public @interface RootRedirectServletConfig {

    @AttributeDefinition(name = "redirectTarget", description = "Target URL where root of application should point to")
    String redirectTarget() default "/apps/websight/index.html";

    @AttributeDefinition(name = "permanent", description = "If set to true redirect status is 301 (moved permanently) otherwise 302 (moved temporarily)")
    boolean isPermanent() default false;
}

