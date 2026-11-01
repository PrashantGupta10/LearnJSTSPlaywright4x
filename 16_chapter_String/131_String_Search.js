// Searching & Checking

let url = "https://staging.vwo.com/api/login?retry=true";
// includes()
console.log(url.includes("staging")); // true
console.log(url.includes("production"));// false

// startsWith / endsWith
console.log(url.startsWith("https"));// true
console.log(url.startsWith("http://")); // false
console.log(url.endsWith("true")); // true

// indexOf / lastIndexOf
console.log(url.indexOf("a"));// 12
console.log(url.lastIndexOf("a"));// 30
console.log(url.indexOf("nothere"));// -1

// ASCII -> A -> 65
