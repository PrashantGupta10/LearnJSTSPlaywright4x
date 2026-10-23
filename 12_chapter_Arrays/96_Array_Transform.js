// Map and Filters


// every vs some

// every — ALL must pass
console.log([80, 90, 85].every(i => i >= 70));// true
console.log([80, 60, 85].every(i => i >= 70));// false


// some — AT LEAST ONE must pass
console.log([80, 60, 85].some(s => s < 70));// true
console.log([80, 90, 85].some(s => s < 70)); // false