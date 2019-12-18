// Constants './constants.js'
const EMAIL_REG_EXP = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

// Utils './utils.js'

function addValidationClasses(field, isValid) {
  field.classList.toggle('is-valid', isValid);
  field.classList.toggle('is-invalid', !isValid);
}

const validateAfter3Chars = handler => value => value.length >= 3 && handler();

// Validation utils './validation-utils.js'

const checkers = {
  validateStringLength: string => Boolean(string.length),
  validateByRegExp: (regExp, value) => regExp.test(value)
};

// Registration Form Validation './registration-form-validation.js'

function validateUserName(userNameInput) {
  addValidationClasses(userNameInput, checkers.validateStringLength(userNameInput.value));
}

function validateUserEmail(userEmailInput) {
  addValidationClasses(userEmailInput, checkers.validateByRegExp(EMAIL_REG_EXP, userEmailInput.value));
}

function validateUserSurname(userSurnameInput) {
  addValidationClasses(userSurnameInput, checkers.validateStringLength(userSurnameInput.value));
}

function validateUserPassword(userPasswordInput) {
  addValidationClasses(userPasswordInput, userPasswordInput.value.length > 10);
}

function validateRegistrationForm(registrationForm) {
  const registrationFormInputs = registrationForm.querySelectorAll(':scope input');
  const registrationFormInputsArray = Array.prototype.slice.call(registrationFormInputs);
 const userNameInput = registrationForm.elements.firstName;
  const userSurnameInput = registrationForm.elements.secondName;
  const userEmailInput = registrationForm.elements.emailAddress;
  const userPasswordInput = registrationForm.elements.password;

  

  validateUserName(userNameInput);
  validateUserEmail(userEmailInput);
  validateUserSurname(userSurnameInput);
  validateUserPassword(userPasswordInput);


  return !registrationFormInputsArray.some(input => input.classList.contains('is-invalid'));
}

// Registration Form Handlers './registration-form-handlers.js'

function handleUserNameBlur(event) {
  validateUserName(event.target);
  console.log(event.target.name + ':' + event.target.value);
}

function handleUserSurnameBlur(event) {
  validateUserSurname(event.target);
  console.log(event.target.surname + ':' + event.target.value);
}


function handleUserEmailInput(event) {
  const validateUserEmailAfter3Chars = validateAfter3Chars(() => {
    validateUserEmail(event.target);
  });
  validateUserEmailAfter3Chars(event.target.value);
}

function handleUserPasswordBlur(event) {
  validateUserPassword(event.target);
  console.log(event.target.password + ':' + event.target.value);
}


function handleRegistrationFormSubmit(event) {
  const isValid = validateRegistrationForm(event.target);
  if(!isValid) {
    event.preventDefault();
  }
}

// Registration Form './registration-form.js'

const registrationForm = document.getElementById('registration-form');

function initializeRegistrationFormValidation(registrationForm) {
  const userNameInput = registrationForm.elements.firstName;
  const userSurnameInput = registrationForm.elements.secondName;
  const userEmailInput = registrationForm.elements.emailAddress;
  const userPasswordInput = registrationForm.elements.password;

  registrationForm.addEventListener('submit', handleRegistrationFormSubmit);
  userNameInput.addEventListener('input', handleUserNameBlur);
  userSurnameInput.addEventListener('input', handleUserSurnameBlur);
    userEmailInput.addEventListener('input', handleUserEmailInput);
  userPasswordInput.addEventListener('input', handleUserPasswordBlur);
}

initializeRegistrationFormValidation(registrationForm);