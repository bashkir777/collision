import {validateSpeed, validateWeight} from './validation.js';

const canvas = document.getElementsByTagName('canvas')[0];
const ctx = canvas.getContext('2d');


function clearCanvas() {
    ctx.clearRect(0, 0, canvas.width - 100, canvas.height);
    // for (let i = 0; i <= 9; i ++){
    //     drawRectangle(100 * i, 297, 2, 3, 'white');
    // }
}

function drawRectangle(x, y, width, height, color) {
    let temp = ctx.fillStyle;
    ctx.fillStyle = color;
    // Рисуем прямоугольник (x, y, width, height)
    ctx.fillRect(x, y, width, height);
    ctx.fillStyle = temp;
}

drawRectangle(1000, 100, 80, 200, 'black');

function drawBall(ball) {
    ctx.beginPath();
    ctx.arc(ball.x, ball.y, ball.radius, 0, Math.PI * 2);
    ctx.fillStyle = ball.color;
    ctx.fill();
    ctx.closePath();
}
function drawDonut(ball) {
    drawBall(ball);
    ball.radius = 25;
    ball.color = "#171717";
    drawBall(ball);
    ball.radius = 50;
    ball.color = "black";
}

function drawHoop(ball) {
    drawBall(ball);
    ball.radius = 40;
    ball.color = "#171717";
    drawBall(ball);
    ball.radius = 50;
    ball.color = "black";
}
let ball = {
    x: 50,
    y: canvas.height - 50,
    dx: 2,
    dy: -2,
    radius: 50,
    color: 'black'
};

function updateBall(_function) {
    $.get((window.location.href).toString().replace("/ball-wall", "/ball-wall-cord"))
        .done(function (response) {
            console.log(response);
            ball.x = Number(response);
            clearCanvas();
            _function(ball);
        });
}


clearCanvas();
drawBall(ball);

let start = document.getElementById("start");
let reset = document.getElementById("reset");
let speedInput = document.getElementById("speed");
let formInput = document.getElementById("form_ball");
let frictionCheckBox = document.getElementById("wall_friction");
let intervalId = 0;

function defineFunction(){
    let drawFunction;
    switch (formInput.value){
        case "ball":
            drawFunction = drawBall;
            break;
        case "hoop":
            drawFunction = drawHoop;
            break;
        case "donut":
            drawFunction = drawDonut;
            break;
    }
    return drawFunction;
}

let onChange = ()=>{
    let drawFunction = defineFunction();
    drawFunction(ball);
}
formInput.addEventListener("change", onChange);

reset.onclick = () => {
    let drawFunction = defineFunction();
    $.get((window.location.href).toString().replace("/ball-wall", "//ball-wall-reset"), function (response) {
        if (intervalId !== 0) {
            clearInterval(intervalId);
            intervalId = 0;
        }
        updateBall(drawFunction);
    });
};
start.onclick = () => {
    reset.click();
    let drawFunction = defineFunction();
    let speedValidation = validateSpeed(speedInput.value);
    if (!speedValidation) {
        let parent = speedInput.parentNode;
        parent.classList.add("red-alert");
        setTimeout(() => {
            parent.classList.remove("red-alert");
        }, 100);
        speedInput.value = "";
        return;
    }
    setTimeout(() => {
        let data = {
            speed: Number(speedInput.value).toFixed(2)*100,
            form: formInput.value,
            wall_friction: frictionCheckBox.checked
        };
        intervalId = setInterval(()=>{updateBall(drawFunction)}, 8);
        $.post((window.location.href).toString().replace("/ball-wall", "/ball-wall-start"), data, function (response) {
            if (intervalId !== 0) {
                clearInterval(intervalId);
                intervalId = 0;
            }
            let drawFunction = defineFunction();
            updateBall(drawFunction);
        });
    }, 100);
};




