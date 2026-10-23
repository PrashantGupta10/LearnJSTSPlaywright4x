console.log(1+2);
console.log(2*2);

if ("hello") console.log("String is truthy"); // "hello" = truthy

if (42) console.log("Number is truthy");

if ({}) console.log("Empty object is truthy!");

if ([]) console.log("Empty array is truthy!");

// false results

if ("") console.log("Won't print");  // "" -> falsy result

if (null) console.log("Won't print");

if (undefined) console.log("Won't print");

if (NaN) console.log("Won't print");

if (0) console.log("Won't print"); 


const user = { name: "Asha", role: "tester" };
user.role = "lead";
console.log(user.role);

if (true) {
  var retries = 3;
  let timeout = 5000;
}
console.log(retries);
console.log(timeout);

console.log(apiUrl);
let apiUrl = "/login";

const baseUrl = "https://staging.example.test";
baseUrl = "https://prod.example.test";
console.log(baseUrl);

