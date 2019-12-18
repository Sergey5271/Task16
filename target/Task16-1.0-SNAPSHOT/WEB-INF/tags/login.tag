<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

     <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <!-- LOGIN -->
               <li class="nav-item">
                   <a class="nav-link" href="signUp">
                   <fmt:message key="label.signUp"/>
                   </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="signIn">
                     <fmt:message key="label.signIn"/>
                    </a>
                </li>
                </ul>
            </c:when>
            <c:otherwise>
                <!-- LOGOUT -->
                <img src="img?image=${sessionScope.user.image}">
                    <form action="logout" method="POST"class="form-inline my-2 my-lg-0" >
                <input type="submit" class="btn btn-outline-light my-2 my-sm-0" value="<fmt:message key='label.logout'/>"/>
            </form>
            </c:otherwise>
        </c:choose>
    </div>