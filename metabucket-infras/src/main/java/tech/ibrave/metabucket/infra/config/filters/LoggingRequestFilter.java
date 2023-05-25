package tech.ibrave.metabucket.infra.config.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import tech.ibrave.metabucket.shared.utils.RequestUtils;

;
import java.io.IOException;

@Component
public class LoggingRequestFilter extends GenericFilterBean {

    private static final String[] IGNORE_PATHS = {
            "/resources/", "favicon.ico"
    };

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = RequestUtils.getRequestUrl(request);

        if (isIgnorePath(url)) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        String referer = request.getHeader("Referer");
        String realIp = RequestUtils.getRealIp(request);
        logger.info("Real: " + realIp +
                " - Remote User: " + RequestUtils.getRequestUser(request) +
                " - Remote: " + request.getRemoteAddr() +
                " - " + (StringUtils.isEmpty(referer) ? "[No Refer]" : referer) +
                " - " + url);
        long start = System.currentTimeMillis();
        try {
            chain.doFilter(servletRequest, servletResponse);
        } finally {
            var response = (HttpServletResponse) servletResponse;
            logger.info("Real: " + realIp +
                    " - Remote User: " + RequestUtils.getRequestUser(request) +
                    " - Remote: " + request.getRemoteAddr() +
                    " - " + url +
                    " - " + response.getStatus() +
                    " - " + (System.currentTimeMillis() - start));
        }

    }

    private boolean isIgnorePath(String url) {
        for (String item : IGNORE_PATHS) {
            if (url.contains(item)) return true;
        }
        return false;
    }
}
