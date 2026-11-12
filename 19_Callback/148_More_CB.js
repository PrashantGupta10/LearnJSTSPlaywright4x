function test(item, callMeWhenDone) {
    console.log("Hi start the Testcase", item);
    callMeWhenDone();
}

test("Verify the login page is working", async (page) =>{
});

function callMeWhenDone() {
    console.log("Testcase completed");
}