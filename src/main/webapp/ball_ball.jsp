<%@ page import="pojos.Ball" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Упругое столкновение двух шаров</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/other.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="module" src="${pageContext.request.contextPath}/javascript/canvas-ball-ball-rendering.js"
            defer></script>
    <script type="module" src="${pageContext.request.contextPath}/javascript/validation.js" defer></script>

</head>
<body>
<div class="canvas-wrapper">
    <canvas id="myCanvas" width="1100" height="300"></canvas>
</div>
<div class="axis">
    <div>0</div>
    <div style="margin-left: 92px">1</div>
    <div style="margin-left: 92px">2</div>
    <div style="margin-left: 92px">3</div>
    <div style="margin-left: 92px">4</div>
    <div style="margin-left: 92px">5</div>
    <div style="margin-left: 92px">6</div>
    <div style="margin-left: 92px">7</div>
    <div style="margin-left: 92px">8</div>
    <div style="margin-left: 92px">9</div>
    <div style="margin-left: 80px">10 (m)</div>
</div>
<a style="top: 580px!important; left: 25px!important;" href="${pageContext.request.contextPath}/main">
    Back
</a>
<div class="panel" style="padding: 40px; margin-left: 150px">
    <div class="ball-info">
        <div class="above-form-pole">
            <label for="speed_ball_1">Speed(m/s)</label>
            <input placeholder="float in [1; 10]" id="speed_ball_1" type="text" name="speed">
        </div>
        <div class="above-form-pole">
            <label for="form_ball_1">Form</label>
            <select name="form_ball_1" id = "form_ball_1">
                <option  value="ball">Ball</option>
                <option  value="hoop">Hoop</option>
                <option value="donut">Donut</option>
            </select>
        </div>
    </div>

    <div class="ball-info">
        <div class="above-form-pole">
            <label for="speed_ball_2">Speed(m/s)</label>
            <input placeholder="float in [1; 10]" id="speed_ball_2" type="text" name="speed">
        </div>
        <div class="above-form-pole">
            <label for="form_ball_2">Form</label>
            <select name="form_ball_2" id="form_ball_2">
                <option  value="ball">Ball</option>
                <option value="hoop">Hoop</option>
                <option value="donut">Donut</option>
            </select>
        </div>
    </div>
    <button id="start" style="margin-left: 20px">Start</button>

    <button id="reset">Reset</button>


</div>
</body>
</html>