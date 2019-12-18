package util;

import bean.FilterFormBean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FilterFormBeanValidator implements BeanValidator<FilterFormBean> {

    @Override
    public Map<String, String> validate(FilterFormBean bean) {
        Map<String, String> errors = new HashMap<>();
        isValidGenre(bean, errors);
        isValidEdition(bean, errors);
        isValidMin(bean.getMin(), errors);
        isValidMax(bean.getMax(), errors);
        return errors;
    }

    private void isValidGenre(FilterFormBean bean, Map<String, String> map) {
        if (bean.getGenres() != null && Arrays.stream(bean.getGenres()).allMatch(e -> e.matches("^\\d+$"))) {
            map.put("genres", "Invalid data");
        }
    }

    private void isValidEdition(FilterFormBean bean, Map<String, String> map) {
        if (bean.getEditions() != null && Arrays.stream(bean.getEditions()).allMatch(e -> e.matches("^\\d+$"))) {
            map.put("edition", "Invalid data");
        }
    }

    private void isValidMin(String string, Map<String, String> map) {
        if (string != null && string.matches("^\\d+$")) {
            map.put("minPrice", "Invalid data");
        }
    }

    private void isValidMax(String string, Map<String, String> map) {
        if (string != null && string.matches("^\\d+$")) {
            map.put("maxPrice", "Invalid data");
        }
    }


}
