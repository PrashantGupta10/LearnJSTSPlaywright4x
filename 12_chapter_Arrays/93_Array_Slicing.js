// Slice & Combining

let arr = [1, 2, 3, 4, 5];
// slice(start, end) — returns new array, 
// does NOT mutate actual -> ( start, end-1) = index = 0
// Don't give the end, it will automatically 
// take from start to end.

console.log(arr.slice(1, 3));// returns [2, 3]
console.log(arr);

console.log(arr.slice(2));// returns [3, 4, 5]

console.log(arr.slice(-2));// returns [4, 5]
console.log(arr.slice(-3));// returns [3, 4, 5]

console.log(arr.slice(0));// returns [1, 2, 3, 4, 5]

console.log(arr.slice(-5));// returns [1, 2, 3, 4, 5]

console.log(arr.slice(-3,-5));// returns []
