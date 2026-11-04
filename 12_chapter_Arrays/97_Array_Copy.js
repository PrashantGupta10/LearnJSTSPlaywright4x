let original = [1, 2, 3];
let copy1 = [...original];
console.log(original);// [ 1, 2, 3 ]
console.log(copy1);// [ 1, 2, 3 ]

let copy2 = original.slice();
copy2.push(92);
copy1.push(93);
console.log(original);// [ 1, 2, 3 ]
console.log(copy1);// [ 1, 2, 3, 93 ]
console.log(copy2);// [ 1, 2, 3, 92 ]



// Deep cOPY
let deep_copy_array = original;
// Deep copy

deep_copy_array.push(91);
console.log(original);// [ 1, 2, 3 ]
console.log(deep_copy_array);// [ 1, 2, 3, 91 ]