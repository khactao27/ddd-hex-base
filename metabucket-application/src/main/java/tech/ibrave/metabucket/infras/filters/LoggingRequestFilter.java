package tech.ibrave.metabucket.infras.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import tech.ibrave.metabucket.shared.utils.RequestUtils;

import java.io.IOException;

@Component
@ConditionalOnProperty(value = "security.request-log",
        havingValue = "true"
)
public class LoggingRequestFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var url = RequestUtils.getRequestUrl(request);

        var referer = request.getHeader("Referer");
        var realIp = RequestUtils.getRealIp(request);
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
}
