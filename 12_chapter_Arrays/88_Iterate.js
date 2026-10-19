let tests = ["login", "checkout", "search"];

for (let index = 0; index < tests.length; index++) {
    const element = tests[index];
    console.log(element);
}
console.log("-----");

for(let i=0; i<tests.length; i++){
    console.log(i, tests[i]);
}
console.log("-----");

for (let x of tests) {
    console.log(x);
}
console.log("-----");

tests.forEach((test, index) => {
    console.log(`${index}: ${test}`);
});
console.log("-----");

for(let [i, test] of tests.entries()){
    console.log(i, test);
}
let students = ["John", "Jane", "Jack", "Jill"];
for(let student in students){
    console.log(student," -> ", students[student]);
}