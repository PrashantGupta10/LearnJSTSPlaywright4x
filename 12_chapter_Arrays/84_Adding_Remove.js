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

arr.splice(2,1); //remove 1 element from index 2
console.log(arr);

arr.splice(2,0,99) //replace element at position 0
console.log(arr);

arr.splice(2,1,99) //replace element at position 1
console.log(arr);

arr.splice(2,2,99) //replace element at position 1
console.log(arr);

arr.splice(1,2,10,20) //replace element at position 1
console.log(arr);
