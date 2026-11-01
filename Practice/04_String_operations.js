//Template Literals
const name ="Prashant";
const age = 30;
const msg = `My name is ${name} and I am ${age} years old.`;
console.log(msg); // My name is Prashant and I am 30 years old.

//Common String methods
const str = "Playwright";
console.log(str.length); // 10
console.log(str.toUpperCase());// PLAYWRIGHT
console.log(str.toLowerCase());// playwright
console.log(str.includes("Play")); // true
console.log(str.startsWith("  P")); //false
console.log(str.startsWith("P")); //true
console.log(str.endsWith("t")); //true
console.log(str.endsWith("  ")); //false

//Finding and Extracting
const str2 = "Playwright Testing";
console.log(str2.indexOf("w")); // 4
console.log(str2.lastIndexOf("t")); // 14
console.log(str2.slice(0, 5)); // Play
console.log(str2.slice(5));//
"Javascript".slice(-6); // Script, A negative index counts backward from the end. -6 starts at the sixth character from the end, returning "script".
"Javascript".substring(-6); // Javascript, Negative arguments are treated as 0, so this becomes substring(0) and returns the whole string.
"Javascript".substr(4,6); // Script
console.log(str2.substring(0, 10)); // Playwright
console.log(str2.substr(0, 10)); // Playwright

//Replacing and Splitting
const str3 = "JavaScript is great";
console.log(str3.replace("great", "awesome")); // JavaScript is awesome
console.log(str3.replaceAll("a", "@")); // J@V@Script is gre@t
console.log(str3.split(" ")); // ["JavaScript", "is", "great"]

//Other useful methods
const str4 = "Playwright";
console.log(str4.charAt(2));// a
console.log(str4.charCodeAt(2));// 97
console.log(String.fromCharCode(97)); // a
console.log(str4.repeat(3)); // PlaywrightPlaywrightPlaywright
console.log(str4.padStart(12,'*'));// **Playwright
console.log(str4.padEnd(12,'*'));// Playwright**


"a-b-a".lastIndexOf("a"); // 4

"banana".replace("a","X")// bXnana
"banana".replace(/a/g,"X")// bXnXnX
"banana".replaceAll("a","X")//


