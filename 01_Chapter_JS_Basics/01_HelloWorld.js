console.log("Hello, World!");

function logStep(step) {
  console.log("step: " + step);
}
const result = logStep("login");
console.log(result);

console.log(add(2, 3));
function add(a, b) {
  return a + b;
}

//console.log(multiply(2, 3));
//const multiply = (a, b) => a * b;

function retry(times = 3) {
  return "retrying " + times + " times";
}
console.log(retry());
console.log(retry(1));

(function () {
  var secret = "token";
  console.log("ran");
})();
console.log(typeof secret);

function counter() {
  let count = 0;
  return () => ++count;
}
const next = counter();
next();
console.log(next());