const prompt = require("prompt-sync")();
let side1 = Number(prompt("Enter first side of triangle"));
let side2 = Number(prompt("Enter second side of triangle"));
let side3 = Number(prompt("Enter third side of triangle"));

if ((side1 && side2 && side3) > 0) {
    if (side1 === side2 && side2 === side3) {
        console.log("Equilateral");
    } else if (side1 === side2 || side2 === side3 || side1 === side3) {
        console.log("Isosceles");
    } else if (side1 !== side2 && side2 !== side3 && side1 !== side3) {
        console.log("Scalene");
    }
}
else 
    console.log("Invalid input");
