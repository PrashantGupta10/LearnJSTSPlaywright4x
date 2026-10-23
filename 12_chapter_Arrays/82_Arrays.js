let browsers = ["chrome", "firefox", "safari"];

let scores = new Array(3); // creates empty array of length 3
scores[0] = "1";
scores[1] = "2";
console.log(scores); // ["1", undefined, undefined]

let scores2 = new Array(1,2,3);
console.log(scores2); // [1,2,3]

let numbers = new Array(100,200,300,400);
console.log(numbers);

let test = Array.of(10,20,30,40,50);
console.log(test.length); // 5
console.log(test); // [10,20,3,40,50]

let chars = Array.from ("hello"); // only for string to char conversion
//["h", "e", "l", "l", "o"]
console.log(chars.length); // 5
console.log(chars); // ["h", "e", "l", "l", "o"]

