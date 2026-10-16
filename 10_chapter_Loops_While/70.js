//const num1 = require('fs').readFileSync(0, 'utf8');
//const num2 = require('fs').readFileSync(0, 'utf8');
//const num3 = require('fs').readFileSync(0, 'utf8');
const prompt = require("prompt-sync")();
let num1 = Number(prompt("Enter first side of triangle"));
let num2 = Number(prompt("Enter second side of triangle"));
let num3 = Number(prompt("Enter third side of triangle"));

if(num1===num2 && num2===num3){
    console.log("Equilateral Triangle");
} else if(num1===num2 || num2===num3 || num1===num3){
    console.log("Isosceles Triangle");}
else if(num1!==num2 && num2!==num3 && num1!==num3){
    console.log("Scalene Triangle");   
}