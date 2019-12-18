<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="filter.SortingMode" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value = '/style/style.css'/>">
    <title>Main</title>
</head>
<body>

<%-- HEADER --%>

<%@ include file="/jspf/header.jspf" %>

<%-- HEADER --%>

<%-- SIDEBAR --%>
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-3 ">
            <form class="sticky-top p-2" action="main" method="GET">
                <h3>Genre</h3>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" name="genres" value="Novel" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Роман</label>
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" name="genres" value="Detective" id="exampleCheck2">
                    <label class="form-check-label" for="exampleCheck2">Детектив</label>
                </div>
                <h3>Edition</h3>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" name="edition" value="Azbuka" id="exampleCheck3">
                    <label class="form-check-label" for="exampleCheck3">Азбука</label>
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" name="edition" value="Alfavit" id="exampleCheck4">
                    <label class="form-check-label" for="exampleCheck4">Алфавит</label>
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" name="edition" value="LondonGraf"
                           id="exampleCheck5">
                    <label class="form-check-label" for="exampleCheck5">ЛондонГраф</label>
                </div>

                <div class="form-group">
                    <label for="exampleInputEmail1">Min price</label>
                    <input type="number" class="form-control" name="minPrice" id="exampleInputEmail1">
                </div>

                <div class="form-group">
                    <label for="exampleInputPassword1">Max price</label>
                    <input type="number" class="form-control" name="maxPrice" id="exampleInputPassword1">
                </div>

                <div class="form-group">
                    <label for="exampleInputPassword1">Search for name</label>
                    <input type="text" class="form-control" name="searchByName" id="exampleInputPassword12">
                </div>

                <div class="form-group">
                    <label>Select</label>
                    <select class="form-control" name="itemCount">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                    </select>
                </div>

                <div class="form-group">
                    <label >Select sort</label>
                    <select class="form-control" name="sortingMode">
                        <c:forEach var="sort" items="<%=SortingMode.values()%>">
                        <option value="${sort}">${sort}</option>
                        </c:forEach>
                    </select>
                </div>


                <input type="hidden" name="page" value="${bean.page}">

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>

        <div class="col-sm-12 col-md-9">
            <div class="row">
                <c:forEach var="book" items="${books}">
                    <div class="col-sm-4">
                        <div class="card p-3 h-100" style="width: 18rem;">
                            <img src="imgBooks?image=${book.image}" class="card-img-top product-img" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">${book.title}</h5>
                                <p class="card-text">${book.author}</p>
                            </div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">${book.genre}</li>
                                <li class="list-group-item">${book.edition}</li>
                                <li class="list-group-item">${book.type}</li>
                                <li class="list-group-item">${book.price}</li>
                            </ul>
                            <div class="card-body">
                                <button name="buyItem" class="btn btn-primary" id="${book.id}">Buy</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                </li>
                <c:forEach begin="1" end="3" var="page">
                    <c:url var="urlBase" value="/main">
                        <c:forEach var="genre" items="${bean.genres}">
                            <c:param name="genres" value="${genre}"/>
                        </c:forEach>
                        <c:forEach var="edition" items="${bean.editions}">
                            <c:param name="editions" value="${edition}"/>
                        </c:forEach>
                        <c:param name="minPrice" value="${bean.min}"/>
                        <c:param name="maxPrice" value="${bean.max}"/>
                        <c:param name="searchByName" value="${bean.searchName}"/>
                        <c:param name="page" value="${page}"/>
                    </c:url>
                    <li class="page-item"><a class="page-link" href="${urlBase}">${page}</a></li>
                </c:forEach>
                <li class="page-item">
                    <a class="page-link" href="#">Next</a>
                </li>
            </ul>
        </nav>

</div>
<%-- CONTENT --%>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script>
        $(function () {
  $('[data-toggle="tooltip"]').tooltip()
});

    const buyItemButtons = $("[name='buyItem']");
    buyItemButtons.click(buyItem);

    function buyItem(event) {
        let bookId = $(event.target).attr('id');
        $.post('./addToCart', { 'id': bookId }).done(function(data) {
            alert(data);
        });
    }

</script>
</body>

</html>