//count vowels and consonants in a string
/*
const prompt = require("prompt-sync")();
let str= prompt("Enter your sentence");
console.log(str);
let strVowel = ["a", "e", "i", "o", "u"];
let vowelCount=0;
let ConsonentCount=0;
for (let i=0; i<str.length; i++)
    {  
        if (str[i] === "a" || str[i] === "e" || str[i] === "i" || str[i] === "o" || str[i] === "u")
            vowelCount++;
        else if(str[i]!==" ")
            ConsonentCount++;
    }
console.log("Vowel Count:", vowelCount);
console.log("Consonant Count:", ConsonentCount);
*/

//check if the string is palindrome or not
/*
const strRev = [];
let count = 0;
for (let i = str.length-1; i >=0; i--)
{
    strRev.push(str[i]);
}
console.log(strRev.join(""));

if (str.localeCompare(strRev.join(""))===0)
    console.log("YES")
else
    console.log("NO");

*/
//remove duplicate characters from a string
/*
const prompt = require("prompt-sync")();
let str= prompt("Enter your string");
let strUnique = "";
for (x of str)
{
    if (strUnique.indexOf(x) === -1)        
        strUnique += x;
}
console.log(strUnique);
*/

//anagram checking

const prompt = require("prompt-sync")();
let str1 = prompt("Enter the first string: ");
let str2 = prompt("Enter the second string: ");

if (str1.length !== str2.length) {
    console.log("NO");
}   
else {
    const sorted1 = str1.toLowerCase().split('').sort().join('');
    const sorted2 = str2.toLowerCase().split('').sort().join('');
    if (sorted1 === sorted2) {
        console.log("YES");
    }   
    else {
        console.log("NO");
    }
}
