const prompt = require("prompt-sync")();
let arr= prompt("Enter array elements separated by space");
console.log(arr);
let arrNum = arr.split(",").map(value => Number(value.trim()));
console.log(arrNum);
arrNum.sort((a,b)=>a-b);
console.log(arrNum.at(-1));

