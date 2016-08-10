package ua.savelichev.electronic.ui.filters;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter("/add-notebook")
public class AddNotebookFilter implements Filter {

    private static final Logger log = Logger.getLogger(AddNotebookFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        if (!((HttpServletRequest) servletRequest).getMethod().equals("POST")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        ((HttpServletRequest) servletRequest).getSession().setAttribute("badData", false);

        String producer, model, price, description, displayDiagonal, processor, ram, hdd, imageRef;
        producer = servletRequest.getParameter("producer");
        model = servletRequest.getParameter("model");
        price = servletRequest.getParameter("price");
        description = servletRequest.getParameter("description");
        displayDiagonal = servletRequest.getParameter("displayDiagonal");
        processor = servletRequest.getParameter("processor");
        ram = servletRequest.getParameter("ram");
        hdd = servletRequest.getParameter("hdd");
        imageRef = servletRequest.getParameter("imageRef");

        if (producer.equals("") || model.equals("") || price.equals("") || description.equals("") ||
                displayDiagonal.equals("") || processor.equals("") || ram.equals("") || hdd.equals("")
                || imageRef.equals("")) {
            ((HttpServletRequest) servletRequest).getSession().setAttribute("badData", true);
            ((HttpServletResponse) servletResponse).sendRedirect("add-notebook");
            log.debug("Wrong input data");
            return;
        }
        String integerRegExp = "\\d";
        String imageRefRegExp = "$.jpg";

        if (!price.matches(integerRegExp) ||
                !ram.matches(integerRegExp) ||
                !hdd.matches(integerRegExp) ||
                !imageRef.matches(imageRefRegExp)) {
            ((HttpServletRequest) servletRequest).getSession().setAttribute("badData", true);
            ((HttpServletResponse) servletResponse).sendRedirect("add-notebook");
            log.debug("Wrong input data");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
