let nums = [10,25,30,45];
let results = nums.find((temp)=> temp > 20);
console.log(results); // returns the first matching value: 25.

let index = nums.findIndex((n)=> n > 20);
console.log(index); // returns the index of the first match: 1.

console.log(nums.findLast(n => n > 20)); // returns the last matching value: 45.

console.log(nums.findLastIndex(n => n > 20)); // returns the index of the last match: 3.