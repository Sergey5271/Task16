// Constants './constants.js'
const EMAIL_REG_EXP = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

// Utils './utils.js'

function addValidationClasses(field, isValid) {
  $(field).toggleClass('is-valid', isValid);
  $(field).toggleClass('is-invalid', !isValid);
}

const validateAfter3Chars = handler => value => value.length >= 3 && handler();

// Validation utils './validation-utils.js'

const checkers = {
  validateStringLength: string => Boolean(string.length),
  validateByRegExp: (regExp, value) => regExp.test(value)
};

// Registration Form Validation './registration-form-validation.js'

function validateUserName(userNameInput) {
  addValidationClasses(userNameInput, checkers.validateStringLength($(userNameInput).val()));
}

function validateUserEmail(userEmailInput) {
  addValidationClasses(userEmailInput, checkers.validateByRegExp(EMAIL_REG_EXP, $(userEmailInput).val()));
}

function validateUserSurname(userSurnameInput) {
  addValidationClasses(userSurnameInput, checkers.validateStringLength($(userSurnameInput).val()));
}

function validateUserPassword(userPasswordInput) {
  addValidationClasses(userPasswordInput, $(userPasswordInput).val().length > 10);
}

function validateRegistrationForm(registrationForm) {
  const registrationFormInputs = $(registrationForm).find('input');
  const userNameInput = $(registrationForm).find('[name="userName"]');
  const userSurnameInput = $(registrationForm).find('[name="userSurname"]');
  const userEmailInput = $(registrationForm).find('[name="userEmail"]');
  const userPasswordInput = $(registrationForm).find('[name="userPassword"]');
  

  validateUserName(userNameInput);
  validateUserEmail(userEmailInput);
  validateUserSurname(userSurnameInput);
  validateUserPassword(userPasswordInput);


  return !registrationFormInputs.hasClass('is-invalid');
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
  validateUserEmailAfter3Chars($(event.target).val());
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

const registrationForm = $('#registration-form');

function initializeRegistrationFormValidation(registrationForm) {
  const userNameInput = $(registrationForm).find('[name="userName"]');
  const userSurnameInput = $(registrationForm).find('[name="userSurname"]');
  const userEmailInput = $(registrationForm).find('[name="userEmail"]');
  const userPasswordInput = $(registrationForm).find('[name="userPassword"]');

  $(registrationForm).submit(handleRegistrationFormSubmit);
  $(userEmailInput).on('input', handleUserEmailInput);
  $(userNameInput).on('input', handleUserNameBlur);
  $(userSurnameInput).on('input', handleUserSurnameBlur);
  $(userPasswordInput).on('input', handleUserPasswordBlur);
}

initializeRegistrationFormValidation(registrationForm);