let arr=[1,2,3];
console.log(arr);

arr.push(4);//add element at last position
console.log(arr)

arr.pop();//remove last element
console.log(arr);                                                           

arr.push(5,6);
console.log(arr);

arr.unshift(0);//add element at first position
console.log(arr);

arr.shift(); //remove first element
console.log(arr);

arr.splice(2,1); //removes 3 at index 2 → [1, 2, 5, 6]
console.log(arr);

arr.splice(2,0,99) //inserts 99 at index 2 → [1, 2, 99, 5, 6]
console.log(arr);

arr.splice(2,1,99) //replaces the value at index 2 with 99 → [1, 2, 99, 5, 6] (no visible change)
console.log(arr);

arr.splice(2,2,99) //removes 99 and 5, then inserts 99 → [1, 2, 99, 6]
console.log(arr);

arr.splice(1,2,10,20) //removes 2 and 99, then inserts 10 and 20 → [1, 10, 20, 6]
console.log(arr);
