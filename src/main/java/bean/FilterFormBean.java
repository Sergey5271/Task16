package bean;

public class FilterFormBean {

    private String[] genres;
    private String[] editions;
    private String min;
    private String max;
    private String searchName;
    private String itemCount;
    private String page;

    private String sortingMode;

    public String[] getGenres() {
        if (genres != null) {
            return genres.clone();
        }
        return null;
    }

    public void setGenres(String[] genres) {
        if (genres != null) {
            this.genres = genres.clone();
        }
    }

    public String[] getEditions() {
        if (editions != null) {
            return editions.clone();
        }
        return null;
    }

    public void setEditions(String[] editions) {
        if (editions != null) {
            this.editions = editions.clone();
        }
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSortingMode() {
        return sortingMode;
    }

    public void setSortingMode(String sortingMode) {
        this.sortingMode = sortingMode;
    }
}
