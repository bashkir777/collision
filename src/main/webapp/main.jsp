<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Проект по физике. Суплецов Д., Матвеев Р.</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main-page-styles.css">
    <script>
        document.addEventListener("contextmenu", function(event) {
            event.preventDefault();
        });
    </script>
</head>
<body>
    <div class="header header1-position">
        Ball-wall collision
    </div>
    <div class="header header2-position">
        Ball-ball collision
    </div>
    <div class="container ">
        <a class="image-link" href="${pageContext.request.contextPath}/ball-wall">
            <img src="${pageContext.request.contextPath}/png/ball-wall.png" alt="ball-wall image">
        </a>
        <a class="image-link" href="${pageContext.request.contextPath}/ball-ball">
            <img src="${pageContext.request.contextPath}/png/ball-ball.png" alt="ball-ball image">
        </a>
    </div>
</body>
</html>
