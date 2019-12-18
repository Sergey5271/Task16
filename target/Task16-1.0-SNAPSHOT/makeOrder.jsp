<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="filter.SortingMode" %>

<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="style/style.css">
    <title>Cart</title>
</head>

<body>
<%-- HEADER --%>

<%@ include file="/jspf/header.jspf" %>

<%-- HEADER --%>
<c:choose>
    <c:when test="${empty sessionScope.user}">
        <div id="shoppingCart">
            <div class="alert alert-warning hidden-print" role="alert">To make order, please sign in</div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Book</th>
                    <th>Price</th>
                    <th>Count</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${sessionScope.cart.items}">
                    <tr class="item">
                        <td class="text-center"><img class="small" src="imgBooks?image=${item.key.image}" alt="${item.key.title}"><br>${item.key.title}</td>
                        <td class="price">${item.key.price}</td>
                        <td class="count"> <input type="number" value="${item.value}"></td>
                        <td class="hidden-print">
                            <c:choose>
                                <c:when test="${item.value > 1 }">
                                    <a class="btn btn-danger remove-product" data-id-product="${item.key.id }" data-count="1">Remove one</a><br><br>

                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-danger remove-product" id="${item.key.id}" data-id-product="${item.key.id}" name="deleteItem" data-count="1">Remove one</button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2" class="text-right"><strong>Total:</strong></td>
                    <td colspan="2" class="total">${sessionScope.cart.totalPrice}</td>
                </tr>
                </tbody>
            </table>
    </c:when>
    <c:otherwise>
        <div id="shoppingCart">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Book</th>
                    <th>Count</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${sessionScope.cart.items}">
                    <tr class="item">
                        <td class="text-center"><img class="small" src="imgBooks?image=${item.key.image}" alt="${item.key.title}"><br>${item.key.title}</td>
                        <td class="count"> <input type="number" id="${item.key.id}" name="editItemQuantityInput" value="${item.value}"></td>
                        <td class="price">${item.key.price}</td>
                        <td class="hidden-print">
                            <c:choose>
                                <c:when test="${item.value > 1 }">
                                    <a class="btn btn-danger remove-product" data-id-product="${item.key.id }" data-count="1">Remove one</a><br><br>
                                    <a class="btn btn-danger remove-product remove-all" name="deleteItem" data-id-product="${item.key.id }" data-count="${item.value }">Remove all</a>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-danger remove-product" id="${item.key.id}" data-id-product="${item.key.id}" name="deleteItem" data-count="1">Remove one</button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2" class="text-right"><strong>Total:</strong></td>
                    <td colspan="2" class="total">${sessionScope.cart.totalPrice}</td>
                </tr>
                </tbody>
            </table>

            <form action="makeOrder" method="POST">
                <div class="form-group">
                    <label for="creditCardNum">Credit card number</label>
                    <input type="text" id="creditCardNum" name="creditCardNum" value="${order.creditCard}">
                </div>
                <div class="form-group">
                    <label for="address">Address</label>
                    <input type="text" id="address" name="address" value="${order.address}" required>

                </div>
                <a class="btn btn-dark mr-2" href="<c:url value='/cart'/>">Back</a>
                <input type="submit" class="btn btn-success" value="Make orders">
            </form>


            <a class="btn btn-primary" href="<c:url value='makeOrder'/>" type="submit">Make order</a>
    </c:otherwise>
</c:choose>
</div>
</body>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript">
        const $cartItemsCountSpan = $("#cartItemsCountSpan");
        const $editItemQuantityInputs = $("[name='editItemQuantityInput']");
        const $totalPriceTd = $('#totalPriceTd');
        $editItemQuantityInputs.change(changeItemQuantity);
        function changeItemQuantity(event) {
            let id = $(event.target).attr('id');
            let count = $(event.target).val();
            $.post("./editCount", { id: id, count: count }).done(function(data) {
                let json = JSON.parse(data);
                $(event.target).parent().next().text(json.itemPrice.toFixed(2) + ' USD');
                $totalPriceTd.text(json.totalPrice.toFixed(2) + ' USD');
                $cartItemsCountSpan.text(json.itemsCount);
            });
        }
    </script>

    <script>
    const buyItemButtons = $("[name='deleteItem']");
    buyItemButtons.click(deleteItem);

      function deleteItem(event) {
        let bookId = $(event.target).attr('id');
        $.post('./deleteFromCart', { 'id': bookId }).done(function(data) {
            alert(data);
            window.location = './shoppingCart';
        });
    }
</script>
</html>