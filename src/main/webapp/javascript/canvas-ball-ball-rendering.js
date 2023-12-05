import { validateSpeed, validateWeight } from './validation.js';

const canvas = document.getElementsByTagName('canvas')[0];
const ctx = canvas.getContext('2d');

function drawRectangle(x, y, width, height, color) {
    let temp = ctx.fillStyle;
    ctx.fillStyle = color;
    // Рисуем прямоугольник (x, y, width, height)
    ctx.fillRect(x, y, width, height);
    ctx.fillStyle = temp;
}
function clearCanvas() {
    ctx.clearRect(0, 0, canvas.width-100, canvas.height);
    // for (let i = 0; i <= 10; i ++){
    //     drawRectangle(100 * i, 297, 2, 3, 'white');
    // }
}


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
let ball_1 = {
    x: 50,
    y: canvas.height - 50,
    radius: 50,
    color: 'black'
};

let ball_2 = {
    x: 950,
    y: canvas.height - 50,
    radius: 50,
    color: 'black'
};


function updateBalls(function1, function2) {
    $.get((window.location.href).toString().replace("/ball-ball", "/ball-ball-cord"))
        .done(function(response) {
            console.log(response);
            ball_1.x = Number(response.split(" ")[0]);
            ball_2.x = Number(response.split(" ")[1]);
            clearCanvas();
            function1(ball_1);
            function2(ball_2);
        });
}



clearCanvas();
drawBall(ball_1);
drawBall(ball_2);
let start = document.getElementById("start");
let reset = document.getElementById("reset");
let speedInput1 = document.getElementById("speed_ball_1");
let formInput1 = document.getElementById("form_ball_1");
let speedInput2 = document.getElementById("speed_ball_2");
let formInput2 = document.getElementById("form_ball_2");


function defineFunctions(){
    let drawFunction1;
    let drawFunction2;
    switch (formInput1.value){
        case "ball":
            drawFunction1 = drawBall;
            break;
        case "hoop":
            drawFunction1 = drawHoop;
            break;
        case "donut":
            drawFunction1 = drawDonut;
            break;
    }
    switch (formInput2.value){
        case "ball":
            drawFunction2 = drawBall;
            break;
        case "hoop":
            drawFunction2 = drawHoop;
            break;
        case "donut":
            drawFunction2 = drawDonut;
            break;
    }
    return [drawFunction1, drawFunction2]
}

let onChange = ()=>{
    let tempArr = defineFunctions();
    let drawFunction1 = tempArr[0];
    let drawFunction2 = tempArr[1];
    drawFunction1(ball_1);
    drawFunction2(ball_2);
}
formInput1.addEventListener("change", onChange);
formInput2.addEventListener("change", onChange);

let intervalId = 0;

reset.onclick = () => {
    let tempArr = defineFunctions();
    let drawFunction1 = tempArr[0];
    let drawFunction2 = tempArr[1];
    $.get((window.location.href).toString().replace("/ball-ball", "/ball-ball-reset"), function(response) {
        if (intervalId !== 0){
            clearInterval(intervalId);
            intervalId = 0;
        }
        updateBalls(drawFunction1, drawFunction2);
    });
};
start.onclick = ()=>{
    reset.click();
    let tempArr = defineFunctions();
    let drawFunction1 = tempArr[0];
    let drawFunction2 = tempArr[1];
    let speedValidation1 = validateSpeed(speedInput1.value);
    let speedValidation2 = validateSpeed(speedInput2.value);
    if(!speedValidation1 || !speedValidation2){
        if (!speedValidation1){
            let parent = speedInput1.parentNode;
            parent.classList.add("red-alert");
            setTimeout(()=>{parent.classList.remove("red-alert");}, 100);
            speedInput1.value="";
        }

        if (!speedValidation2){
            let parent = speedInput2.parentNode;
            parent.classList.add("red-alert");
            setTimeout(()=>{parent.classList.remove("red-alert");}, 100);
            speedInput2.value="";
        }
        return;
    }
    setTimeout(()=>{let data = {
        speed_1: Number(speedInput1.value).toFixed(2)*100,
        speed_2: Number(speedInput2.value).toFixed(2)*100,
        form_1: formInput1.value,
        form_2: formInput2.value
    };
        intervalId = setInterval(function (){updateBalls(drawFunction1, drawFunction2)}, 8);
        $.post((window.location.href).toString().replace("/ball-ball", "/ball-ball-start"), data, function(response) {
            if (intervalId !== 0){
                clearInterval(intervalId);
                intervalId = 0;
            }
            let tempArr = defineFunctions();
            let drawFunction1 = tempArr[0];
            let drawFunction2 = tempArr[1];
            updateBalls(drawFunction1, drawFunction2);
        });}, 100);
};




