package security;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class SecurityConstraint {

    private String urlPattern;

    private List<String> role;

    public String getUrlPattern() {
        return urlPattern;
    }

    @XmlElement(name = "url-pattern")
    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public List<String> getRole() {
        if (role != null) {
            return new ArrayList<>(role);
        }
        return null;
    }

    @XmlElement(name = "role")
    public void setRole(List<String> role) {
        this.role = new ArrayList<>(role);
    }

    @Override
    public String toString() {
        return "SecurityConstraint{" +
                "urlPattern='" + urlPattern + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
