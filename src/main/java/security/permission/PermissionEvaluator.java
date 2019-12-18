package security.permission;

import entity.Role;

public interface PermissionEvaluator {
    boolean hasAccess(Role role, String url);
    boolean isPathSecured(String url);
}
