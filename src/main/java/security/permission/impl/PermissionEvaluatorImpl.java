package security.permission.impl;

import entity.Role;
import security.SecurityConstraint;
import security.SecurityProperties;
import security.permission.PermissionEvaluator;

public class PermissionEvaluatorImpl implements PermissionEvaluator {

    private SecurityProperties securityProperties;

    public PermissionEvaluatorImpl(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public boolean hasAccess(Role role, String url) {
        if (!isPathSecured(url)) {
            return true;
        } else {
            for (SecurityConstraint c : securityProperties.getSecurityConstraints()) {
                if (url.matches(c.getUrlPattern())) {
                    return c.getRole().contains(role.name());
                }
            }
            return false;
        }
    }

    @Override
    public boolean isPathSecured(String url) {
        for (SecurityConstraint c : securityProperties.getSecurityConstraints()) {
            if (url.matches(c.getUrlPattern())) {
                return true;
            }
        }
        return false;
    }
}
