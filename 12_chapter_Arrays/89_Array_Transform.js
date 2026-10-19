let scores = [45,82,91,60,73];
let grades = scores.map(i=> i>70 ? "pass" : "fail");
    console.log(grades); // ["fail", "pass", "pass", "fail", "pass"]
    
let scores1 = [45,82,91,60,73];
let passing = scores1.filter(s=> s > 70);
console.log(passing); // [82, 91, 73]
