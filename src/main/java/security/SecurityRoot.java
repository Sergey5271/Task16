package security;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "security")
public class SecurityRoot {

    private List<SecurityConstraint> constraints;

    public SecurityRoot() {
    }

    public SecurityRoot(List<SecurityConstraint> constraints) {
        this.constraints = new ArrayList<>(constraints);
    }

    public List<SecurityConstraint> getConstraints() {
        if (constraints != null) {
            return new ArrayList<>(constraints);
        }
        return null;
    }

    @XmlElement(name = "constraint")
    public void setConstraints(List<SecurityConstraint> constraints) {
        this.constraints = new ArrayList<>(constraints);
    }

    @Override
    public String toString() {
        return "SecurityRoot{" +
                "constraints=" + constraints +
                '}';
    }
}
