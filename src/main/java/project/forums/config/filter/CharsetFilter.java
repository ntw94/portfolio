package project.forums.config.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CharsetFilter implements Filter {
    private String charset = "utf-8";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(charset);
        chain.doFilter(request, response);
    }

}
