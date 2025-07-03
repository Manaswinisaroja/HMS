var heading = document.getElementById('main-heading');
heading.style.color = 'blue';

var list = document.querySelector('.list-items');
//for(i=0;i<list.length;i++){
//list[i].style.fontSize = '24px';
//}

//Creating Elements
const ul = document.querySelector('ul');
const li = document.querySelector('li');

//Adding Elements
ul.append(li);

// console.log(list.innerText);
// console.log(list.textContent);
// console.log(list.innerHTML);
li.innerText = 'X-men';

//Modify Attributes and Classes
li.setAttribute('id','main-heading');
li.removeAttribute('id');

console.log(heading.getAttribute('id'));


//Class Attribute

li.classList.add('list-items');

li.classList.remove('list-items');

console.log(li.classList.contains('list-items'));