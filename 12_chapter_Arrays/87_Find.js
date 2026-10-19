let nums = [10,25,30,45];
let results = nums.find((temp)=> temp > 20);
console.log(results); // 25


let index = nums.findIndex((n)=> n > 20);
console.log(index); // 1

nums.findLast(n => n > 20); // 45
nums.findLastIndex(n => n > 20); // 3