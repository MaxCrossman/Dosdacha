<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container" style="padding-top: 10px">
    <nav class="navbar navbar-default navbar-light" style="border-radius: 20px; background-color: #5588FF;">
        <div class="container-fluid">

            <div class="navbar-header">
                <a class="navbar-brand" href="/home">
                    <img src="https://2ch.hk/mobi/src/2035681/16089792913370.png"
                         width="40" height="40" alt="" loading="lazy">
                </a>
            </div>

            <ul class="nav d-inline-flex mr-auto">
                <li class="nav-item">
                    <a class="nav-link text-light" href="/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" href="/boards">All Boards</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" href="/newboard">New Board</a>
                </li>
            </ul>

            <ul class="navbar-nav my-2 my-lg-0">
                <li class="nav-item active">
                    <a class="nav-link text-white" href="/profile">Profile</a>
                </li>
            </ul>
        </div>

    </nav>

    <div class="vw-auto vh-100 py-2">
        <div class="pg-inline mt-3 my-lg-0 p-3 bg-white border" style="border-radius:20px">
            <div class="container d-inline">
                <div class="row justify-content-between">
                    <div class="col-md-auto">
                        <h1>Profile</h1>
                    </div>
                    <div class="col-md-auto">
                        <form action="/profile" method="post">
                            <input type="submit" class="btn btn-outline-danger" value="Logout">
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-auto">
                        <p class="my-3 text-muted font-weight-bold">${firstName} ${lastName}</p>
                        <p class="my-3 text-muted font-weight-bold">${email}</p>
                        <p>Your posts and comments:</p>

                        <div class="container border">
                            <table class="table text-break">
                                <c:forEach items="${posts}" var="post">
                                    <tr>
                                        <td>
                                            <a href="${post.getLink()}" class="text-secondary">${post.getTimestamp()}</a>
                                            <p>${post.getValue()}</p>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
