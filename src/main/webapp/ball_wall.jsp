<%@ page import="pojos.Ball" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Упругое столкновение шара со стеной</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/other.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="module" src="${pageContext.request.contextPath}/javascript/canvas-ball-wall-rendering.js" defer></script>
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
        <a href="${pageContext.request.contextPath}/main">
            Back
        </a>
        <div class="panel">
            <div class="above-form-pole">
                <label for="speed">Speed(m/s)</label>
                <input placeholder="float in [1; 10]" id="speed" type="text" name="speed">
            </div>
            <div class="above-form-pole">
                <label for="form_ball">Form</label>
                <select name="form_ball" id="form_ball">
                    <option value="ball">Ball</option>
                    <option value="hoop">Hoop</option>
                    <option value="donut">Donut</option>
                </select>
            </div>
            <div class="above-form-pole">
                <label for="form_ball">Wall friction</label>
                <input type="checkbox" id="wall_friction" name="wall_friction">
            </div>

            <button id="start" style="margin-left: 20px">Start</button>
            <button id="reset">Reset</button>
        </div>
    </body>
</html>