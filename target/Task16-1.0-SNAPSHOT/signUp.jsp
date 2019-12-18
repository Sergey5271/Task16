<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir ="/WEB-INF/tags" prefix="t"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>SignUp</title>
</head>
<body>

<%-- HEADER --%>

<%@ include file="/jspf/header.jspf" %>

<%-- HEADER --%>

<div class="container">
    <h1 class="text-center display-4">SIGN UP</h1>
    <form action="signUp" method="POST" id="registration-form" enctype="multipart/form-data" novalidate>
        <div class="form-group">
            <label for="user-name">First Name</label>
            <input type="text" class="form-control" name="firstName" value="${bean.firstName}" placeholder="First Name">
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Looks bad!
            </div>
        </div>
        <div class="form-group">
            <label for="user-surname">Second Name</label>
            <input type="text" class="form-control" name="secondName" value="${bean.secondName}"
                   placeholder="Second Name">
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Looks bad!
            </div>
        </div>
        <div class="form-group">
            <label for="user-email">Email address</label>
            <input type="email" class="form-control" name="emailAddress" value="${bean.emailAddress}"
                   placeholder="Please, enter your email">
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Looks bad!
            </div>
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="user-pass">Password</label>
            <input type="password" class="form-control" name="password"
                   placeholder="Please, enter your password">
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Looks bad!
            </div>
        </div>

        <div class="form-group">
            <label for="avatar">Choose avatar</label>
            <input type="file" class="form-control-file" id="image" name="image"
                   accept="image/x-png,image/gif,image/jpeg">
        </div>


        <div class="form-group">
            <label for="user-captcha">Captcha</label>
            <input type="text" class="form-control" name="code" placeholder="Please, enter captcha">
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Looks bad!
            </div>
        </div>
        <button type="submit" class="btn btn-primary" value="submit">Submit</button>
        <br>
        <br>
        <t:captcha/>

    </form>
</div>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="resources/js/script.js"></script>
<!--<script src="webapp/resources/js/jqueryScript.js"></script>-->

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script>
        $(function () {
  $('[data-toggle="tooltip"]').tooltip()
})

</script>
</body>
</html>