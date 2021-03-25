package pl.ds.websight.root.redirect;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.servlets.OptingServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(service = {Servlet.class})
@SlingServletResourceTypes(resourceTypes = {"rep:root", "sling:OrderedFolder"})
@Designate(ocd = RootRedirectServletConfig.class)
public class RootRedirectServlet extends SlingSafeMethodsServlet implements OptingServlet {

    private static final String JCR_ROOT = "/";
    private static final String CONTENT_ROOT = "/content";

    private String redirectTarget;

    private boolean isPermanent;

    @Activate
    private void init(final RootRedirectServletConfig config) {
        redirectTarget = config.redirectTarget();
        isPermanent = config.isPermanent();
    }

    @Override
    public void doGet(@NotNull SlingHttpServletRequest request, @NotNull SlingHttpServletResponse response) throws IOException {
        if (isPermanent) {
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        } else {
            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        }
        response.sendRedirect(redirectTarget);
    }

    @Override
    public boolean accepts(@NotNull SlingHttpServletRequest request) {
        RequestPathInfo requestPathInfo = request.getRequestPathInfo();
        String requestedPath = requestPathInfo.getResourcePath();

        return ((requestedPath.equals(JCR_ROOT) || requestedPath.equals(CONTENT_ROOT))
                && requestPathInfo.getExtension() == null && requestPathInfo.getSelectors().length == 0);
    }
}
