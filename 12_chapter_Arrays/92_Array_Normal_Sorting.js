let nums = [10,1,21,2];
nums.sort((a,b) => a-b);
console.log(nums);// [ 1, 2, 10, 21 ]  //  ascending order by default
nums.sort((a,b) => b-a);
console.log(nums);// [ 21, 10, 2, 1 ]  //  descending order by default

let fruits = ['apple','Banana',"cherry"];
fruits.sort();
console.log(fruits);// [ 'Banana', 'apple', 'cherry' ]  //  lexicographic order by default
fruits.sort((a, b) => a.localeCompare(b))//  ascending order by default
console.log(fruits);// [ 'apple', 'Banana', 'cherry' ]  //  ascending order by default

// arrow Functions