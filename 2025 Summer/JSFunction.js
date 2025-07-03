function happyBirthday(){
    console.log("Happy Birthday to you");
}
//With Parameter
function happyBirthday1(name,age){
    console.log(`Happy Birthday ${name} ; Your Age ${age}`);
}
//Function Return
//Add
function add(x,y){
    return x+y
}
function sub(x,y){
    let res = x-y;
    return res;
}
function mult(x,y){
    return x*y;
}
function div(x,y){
    return x/y;
}
//Even-Odd
function isEven(x){
    if(x%2===0){
        return true;
    }
    return false;
}

happyBirthday();
happyBirthday1("Mia",18);
happyBirthday1("Sia",19);
console.log("Add:", add(10, 20));
console.log("Sub:", sub(10, 20));
console.log("Div 10/20:", div(10, 20));
console.log("Div 1/-3:", div(1, -3));
console.log("Mult 1*-3:", mult(1, -3));

console.log("isEven(10):", isEven(10));
console.log("isEven(11):", isEven(11));
console.log("isEven(0):", isEven(0));
console.log("isEven(-6):", isEven(-6));

username = window.prompt("What's your name ?");
console.log(`UserName : ${username}`);

document.getElementById("mySubmit").onclick = function(){
    let username1 = document.getElementById("myName").value;
    console.log(`Username : ${username1}`);
    document.getElementById("myH1").innerText = `Welcome ${username1}`;
}
