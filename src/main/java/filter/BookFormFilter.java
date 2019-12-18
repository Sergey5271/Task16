package filter;

import bean.FilterFormBean;

public class BookFormFilter {

    private final static String QUERY_BASE = "SELECT books.id,title,author, genre, edition, name, image, price FROM books INNER JOIN types ON books.types_id = types.id";
    private final static String DEFAULT_ITEM_COUNT = "3";
    private final static String DEFAULT_PAGE = "1";

    private FilterFormBean filterFormBean;

    public BookFormFilter(FilterFormBean filterFormBean) {
        this.filterFormBean = filterFormBean;
    }

    public String buildQuery() {
        StringBuilder builder = new StringBuilder(QUERY_BASE);
        boolean previousPart = addMaxPriceToQuery(filterFormBean, builder, false);
        previousPart = addMinPriceToQuery(filterFormBean, builder, previousPart);
        previousPart = addCategoriesToQuery(filterFormBean, builder, previousPart);
        previousPart = addEditionsToQuery(filterFormBean, builder, previousPart);
        addSortToQuery(filterFormBean, builder);
        addPage(filterFormBean, builder);
        addSearch(filterFormBean, builder, previousPart);

        return builder.toString();
    }

    private boolean addMaxPriceToQuery(FilterFormBean filterFormBean, StringBuilder builder, boolean previousPart) {
        if (filterFormBean.getMax() != null && !filterFormBean.getMax().isEmpty()) {
            link(builder, previousPart);
            builder.append("price <= ").append(filterFormBean.getMax());
            return true;
        }
        return false;
    }

    private boolean addMinPriceToQuery(FilterFormBean filterFormBean, StringBuilder builder, boolean previousPart) {
        if (filterFormBean.getMin() != null && !filterFormBean.getMin().isEmpty()) {
            link(builder, previousPart);
            builder.append("price >= ").append(filterFormBean.getMin());
            return true;
        }
        return false;
    }

    private void addSearch(FilterFormBean filterForm, StringBuilder builder,
                           boolean previousPart) {
        if (filterForm.getSearchName() != null && !filterForm.getSearchName().isEmpty()) {
            link(builder, previousPart);
            builder.append("title LIKE '%").append(filterForm.getSearchName()).append("%'");
        }
    }

    private boolean addCategoriesToQuery(FilterFormBean filterForm, StringBuilder builder,
                                         boolean previousTriggered) {
        if (filterForm.getGenres() != null && filterForm.getGenres().length > 0) {
            link(builder, previousTriggered);
            builder.append("genre IN (");
            for (String category : filterForm.getGenres()) {
                builder.append("'").append(category).append("'").append(",");
            }
            builder.deleteCharAt(builder.length() - 1).append(")");
            return true;
        }
        return previousTriggered;
    }

    private boolean addEditionsToQuery(FilterFormBean filterForm, StringBuilder builder,
                                       boolean previousTriggered) {
        if (filterForm.getEditions() != null && filterForm.getEditions().length > 0) {
            link(builder, previousTriggered);
            builder.append("edition IN (");
            for (String category : filterForm.getEditions()) {
                builder.append("'").append(category).append("'").append(",");
            }
            builder.deleteCharAt(builder.length() - 1).append(")");
            return true;
        }
        return previousTriggered;
    }

    private boolean addSortToQuery(FilterFormBean filterFormBean, StringBuilder builder) {
        if (filterFormBean.getSortingMode() != null && !filterFormBean.getSortingMode().isEmpty()) {
            SortingMode sortingMode = SortingMode.valueOf(filterFormBean.getSortingMode());
            builder.append(sortingMode.getSql());
            return true;
        }
        return false;
    }

    private void addPage(FilterFormBean filterForm, StringBuilder builder) {
        if (filterFormBean.getPage() == null || filterFormBean.getPage().isEmpty()) {
            filterFormBean.setPage(DEFAULT_PAGE);
        }
        if (filterFormBean.getItemCount() == null || filterFormBean.getItemCount().isEmpty()) {
            filterFormBean.setItemCount(DEFAULT_ITEM_COUNT);
        }
        int skip = (Integer.parseInt(filterForm.getPage()) - 1) * Integer.parseInt(filterForm.getItemCount());
        builder.append(" LIMIT ").append(skip).append(",").append(filterForm.getItemCount());
    }

    private void link(StringBuilder builder, boolean previousTriggered) {
        builder.append((previousTriggered) ? " AND " : " WHERE ");
    }
}
