package servlet;


import bean.FilterFormBean;
import bean.ViewBookBean;
import filter.BookFormFilter;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/main")
public class ProductListServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProductListServlet.class.getName());


    private static final String BOOKS_PARAM = "books";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilterFormBean bean = getBean(request);
        BookService bookService = (BookService) getServletContext().getAttribute("bookService");

        try {
            BookFormFilter bookFormFilter = new BookFormFilter(bean);
            request.setAttribute("bean", bean);
            LOGGER.log(Level.INFO, bookFormFilter.buildQuery());
            List<ViewBookBean> list = bookService.findAllBeansFilter(bookFormFilter);
            request.setAttribute(BOOKS_PARAM, list);
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }catch (IOException | ServletException e){
            LOGGER.log(Level.SEVERE, "Error ", e);
        }
    }


    private FilterFormBean getBean(HttpServletRequest request) {
        FilterFormBean filterFormBean = new FilterFormBean();
        filterFormBean.setGenres(request.getParameterValues("genres"));
        filterFormBean.setEditions(request.getParameterValues("edition"));
        filterFormBean.setMin(request.getParameter("minPrice"));
        filterFormBean.setMax(request.getParameter("maxPrice"));
        filterFormBean.setSearchName(request.getParameter("searchByName"));
        filterFormBean.setItemCount(request.getParameter("itemCount"));
        filterFormBean.setPage(request.getParameter("page"));
        filterFormBean.setSortingMode(request.getParameter("sortingMode"));
        return filterFormBean;
    }

}
