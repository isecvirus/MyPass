const UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
const LOWER = "abcdefghijklmnopqrstuvwxyz";
const NUMBER = "0123456789";
const PUNCTUATION = `~\`!@#$%^&*()_-+=}]{[\"':;?/>.<,|\\`;

const uppers_field = document.querySelector("#upper-chars");
const contain_uppers = document.querySelector("#contain-uppers");

const lowers_field = document.querySelector("#lower-chars");
const contain_lowers = document.querySelector("#contain-lowers");

const numbers_field = document.querySelector("#number-chars");
const contain_numbers = document.querySelector("#contain-numbers");

const punctuations_field = document.querySelector("#punctuation-chars");
const contain_punctuations = document.querySelector("#contain-punctuations");

const customs_field = document.querySelector("#custom-chars");
const contain_customs = document.querySelector("#contain-customs");

const length_field = document.querySelector(".spinner");
const password_field = document.querySelector("#password");



uppers_field.value = UPPER;
lowers_field.value = LOWER;
numbers_field.value = NUMBER;
punctuations_field.value = PUNCTUATION;

function get_all_chars() {
  const allChars = [
    contain_uppers.checked ? uppers_field.value : "",
    contain_lowers.checked ? lowers_field.value : "",
    contain_numbers.checked ? numbers_field.value : "",
    contain_punctuations.checked ? punctuations_field.value : ""
  ];
  
  return allChars.filter(charSet => charSet !== "").join('');
}

// Uppers restriction
uppers_field.addEventListener("keypress", function (event) {
  const char = event.key;
  var chars = event.target.value;
  if (chars.includes(char) || !UPPER.includes(char)) {
    event.preventDefault();
  }
});

// Lowers restriction
lowers_field.addEventListener("keypress", function (event) {
  const char = event.key;
  var chars = event.target.value;
  if (chars.includes(char) || !LOWER.includes(char)) {
    event.preventDefault();
  }
});

// Numbers restriction
numbers_field.addEventListener("keypress", function (event) {
  const char = event.key;
  var chars = event.target.value;
  if (chars.includes(char) || !NUMBER.includes(char)) {
    event.preventDefault();
  }
});

// Punctuations restriction
punctuations_field.addEventListener("keypress", function (event) {
  const char = event.key;
  var chars = event.target.value;
  if (chars.includes(char) || !PUNCTUATION.includes(char)) {
    event.preventDefault();
  }
});

// Customs restriction
customs_field.addEventListener("keypress", function (event) {
  const char = event.key;
  var chars = event.target.value;

  if (chars + get_all_chars().includes(char)) {
    event.preventDefault();
  }
});

document.querySelector("#show").addEventListener("click", function (event) {
  const value = event.target.checked;

  if (value) {
    document.querySelector("#password").type = "text";
  } else {
    document.querySelector("#password").type = "password";
  }
});

document
  .querySelector("#reset-upper-chars")
  .addEventListener("click", function (event) {
    uppers_field.value = LOWER;
  });

document
  .querySelector("#reset-lower-chars")
  .addEventListener("click", function (event) {
    lowers_field.value = LOWER;
  });

document
  .querySelector("#reset-number-chars")
  .addEventListener("click", function (event) {
    numbers_field.value = NUMBER;
  });

document
  .querySelector("#reset-punctuation-chars")
  .addEventListener("click", function (event) {
    punctuations_field.value = PUNCTUATION;
  });

document
  .querySelector("#clear-custom-chars")
  .addEventListener("click", function (event) {
    customs_field.value = "";
  });

document.querySelector(".generate").addEventListener("click", function () {
  const chars = get_all_chars();

  if (chars) {
    const length = length_field.value;
    let final = "";

    for (i=0;i<length;i++) {
      final += chars[Math.floor(Math.random() * chars.length)];
    }

    password_field.value = final;
  }
});
