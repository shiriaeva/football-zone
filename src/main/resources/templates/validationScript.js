function validationScript() {
    const name =
        document.AddPlayerForm.name.value;
    const surname =
        document.AddPlayerForm.surname.value;
    const birthdate =
        document.AddPlayerForm.birthdate.value;
    const genderSelected = document.getElementById('male').checked
        || document.getElementById('female').checked;
    const team =
        document.AddPlayerForm.team.value;
    const country =
        document.AddPlayerForm.country.value;

    const regName = /^([А-Яа-яA-Za-z]{1,})+([А-Яа-яA-Za-z\s\-])+$/;

    if (name === "" || !regName.test(name)) {
        document.getElementById("nameMessages").innerHTML="⚡ Имя должно начинаться с буквы и содержать только буквы, пробелы и тире. Пожалуйста, введите ваше имя правильно"
        // window.alert("Please enter your name properly.");
        // name.focus();
        return false;
    }
    else{
        document.getElementById("nameMessages").innerHTML="🥰"
    }

    if (surname === "" || !regName.test(surname)) {
        document.getElementById("surnameMessages").innerHTML="⚡ Фамилия должна начинаться с буквы и содержать только буквы, пробелы и тире. Пожалуйста, введите вашу фамилию правильно"
        // window.alert("Please enter your surname properly.");
        // surname.focus();
        return false;
    }
    else{
        document.getElementById("surnameMessages").innerHTML="🥰"
    }

    if (genderSelected !== true) {
        document.getElementById("messages").innerHTML="⚡ Пожалуйста, выберите пол"
        // window.alert("Please choose your gender.");
        // genderSelected.focus();
        return false;
    }
    else{
        document.getElementById("messages").innerHTML="🥰"
    }

    if (birthdate === "") {
        document.getElementById("birthdateMessages").innerHTML="⚡ Пожалуйста, введите дату рождения"
        // window.alert("Please enter a valid birthdate.");
        // birthdate.focus();
        return false;
    }
    else{
        document.getElementById("birthdateMessages").innerHTML="🥰"
    }

    if (team === "" || !regName.test(team))  {
        document.getElementById("teamMessages").innerHTML="⚡ Название команды должно начинаться с буквы и содержать только буквы, пробелы и тире. Пожалуйста, введите название вашей команды правильно"
        // alert("Please enter your team.");
        // team.focus();
        return false;
    }
    else{
        document.getElementById("teamMessages").innerHTML="🥰"
    }

    if (country === "" || !regName.test(country)
        || !(country === "Russia" || country === "USA" || country === "Italy"))  {

        document.getElementById("countryMessages").innerHTML="⚡ Пожалуйста, выберите из существующих стран в списке"
        // alert("Please choose from the existing countries in the list.");
        // country.focus();
        return false;
    }
    else{
        document.getElementById("countryMessages").innerHTML="🥰"
    }

    return true;
}