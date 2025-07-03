//Arrays
let fruits = ["apple","pineapple","banana","coconut"];
console.log(fruits[0]);
console.log(fruits[1]);
console.log(fruits[2]);
console.log(fruits[3]);

fruits.push("Gauva");
console.log(fruits[4]);
console.log("Before Pop : "+fruits.length);
fruits.pop();
console.log("After Pop :"+fruits.length);
fruits.unshift("jackfruit");
//fruits.shift();
for(let i=0;i<fruits.length;i++){
    console.log(fruits[i]);
}

for(let i=0;i<fruits.length;i++){
    console.log(fruits[i].toUpperCase());
}

for(let i=0;i<fruits.length;i++){
    console.log(fruits[i].toLowerCase());
}

console.log(fruits.length);