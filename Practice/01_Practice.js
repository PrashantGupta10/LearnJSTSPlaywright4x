const prompt = require("prompt-sync")();
let year = Number(prompt("Enter a year in YYYY"));
//let year = parseInt(data);
if (year%400===0)
    console.log("YES");
else if (year%4 ===0 && year%100!== 0)
    console.log("YES");
else
    console.log("NO");