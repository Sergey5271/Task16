<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<c:choose>
    <c:when test="${applicationScope.captchaStrategy.getClass().simpleName eq 'HiddenFieldCaptchaStrategy'}">
    <img src="captcha/CaptchaServlet?captchaId=${captchaId}">
        <input type="hidden" name="captchaId" value="${captchaId}">
    </c:when>
    <c:otherwise>
    <img src="captcha/CaptchaServlet">
    </c:otherwise>
</c:choose>
