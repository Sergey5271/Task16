package util;

import bean.RegistrationFormBean;

import java.util.HashMap;
import java.util.Map;

public class ValidationRegistration implements BeanValidator<RegistrationFormBean> {

    public Map<String, String> validate(RegistrationFormBean bean) {
        Map<String, String> map = new HashMap<>();
        isValidName(bean, map);
        isValidSurname(bean, map);
        isValidEmail(bean, map);
        isValidPassword(bean, map);
        return map;
    }


    private void isValidName(RegistrationFormBean bean, Map<String, String> map) {
        if (!bean.getFirstName().matches("^[a-zA-Z]+")) {
            map.put("name", "Name isn't valid");
        }
    }

    private void isValidSurname(RegistrationFormBean bean, Map<String, String> map) {
        if (!bean.getSecondName().matches("^[a-zA-Z]+")) {
            map.put("surname", "Surname isn't valid");
        }
    }

    private void isValidEmail(RegistrationFormBean bean, Map<String, String> map) {
        if (!bean.getEmailAddress().matches("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
            map.put("email", "Email isn't valid");
        }
    }

    private void isValidPassword(RegistrationFormBean bean, Map<String, String> map) {
        if (!bean.getPassword().matches("(?=.*[0-9]).(?=.*[A-Z]).{10,}")) {
            map.put("password", "Password isn't valid");
        }
    }

}

