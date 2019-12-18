$(document).ready(function (){
    var language = localStorage.getItem("language");

    if (language === null) {
        language = "en";
    }

    if (language === "en") {
        $('.set_en').addClass('active');
        $('.set_es').removeClass('active');
    } else {
        $('.set_es').addClass('active');
        $('.set_en').removeClass('active');

        jQuery.extend(jQuery.validator.messages, {
            required: "Este campo es requerido.",
            remote: "Please fix this field.",
            email: "Por favor introduce un correo válido.",
            url: "Por favor introduce una URL válida.",
            date: "Por favor introduce una fecha válida.",
            dateISO: "Por favor introduce una fecha (ISO) válida.",
            number: "Por favor introduce un número válido.",
            digits: "Por favor introduce sólo dígitos.",
            creditcard: "Por favor introduce un número de tarjeta de crédito válido.",
            equalTo: "Por favor introduce el mismo valor otra vez.",
            accept: "Por favor introduce un valor con una extensión válida.",
            maxlength: jQuery.validator.format("Por favor no introducas más de {0} caracteres."),
            minlength: jQuery.validator.format("Por favor introduce al menos {0} caracteres."),
            rangelength: jQuery.validator.format("Por favor introduce un valor entre {0} y {1} caracteres de largo."),
            range: jQuery.validator.format("Por favor introduce un valor entre {0} y {1}."),
            max: jQuery.validator.format("Por favor introduce un valor menor o igual que {0}."),
            min: jQuery.validator.format("Por favor introduce un valor mayor o igual que {0}.")
        });
    }

    $.i18n.init({
        resGetPath: '/Lotar/resources/locales/__lng__.json',
        load: 'unspecific',
        fallbackLng: false,
        lng: language
    }, function (t){
        $('#side-menu').i18n();
        $('#dashboard').i18n();
        $('.nav').i18n();
        $('.footer').i18n();

        $('#login').i18n();
        $('#signup').i18n();
        $('#footer').i18n();
        $('#home').i18n();
        $('#investment').i18n();
        $('#transactionsinvest').i18n();

    });

    $('.set_en').on('click', function (){
        var language = "en";
        localStorage.setItem("language", language);

        i18n.setLng('en', function(){
            $('#side-menu').i18n();
            $('#dashboard').i18n();
            $('.nav').i18n();
            $('.footer').i18n();

            $('#login').i18n();
            $('#signup').i18n();
            $('#footer').i18n();
            $('#home').i18n();
            $('#investment').i18n();
            $('#transactionsinvest').i18n();


            $('.set_en').addClass('active');
            $('.set_es').removeClass('active');
        });

        jQuery.extend(jQuery.validator.messages, {
            required: "This field is required.",
            remote: "Please fix this field.",
            email: "Please enter a valid email address.",
            url: "Please enter a valid URL.",
            date: "Please enter a valid date.",
            dateISO: "Please enter a valid date (ISO).",
            number: "Please enter a valid number.",
            digits: "Please enter only digits.",
            creditcard: "Please enter a valid credit card number.",
            equalTo: "Please enter the same value again.",
            accept: "Please enter a value with a valid extension.",
            maxlength: jQuery.validator.format("Please enter no more than {0} characters."),
            minlength: jQuery.validator.format("Please enter at least {0} characters."),
            rangelength: jQuery.validator.format("Please enter a value between {0} and {1} characters long."),
            range: jQuery.validator.format("Please enter a value between {0} and {1}."),
            max: jQuery.validator.format("Please enter a value less than or equal to {0}."),
            min: jQuery.validator.format("Please enter a value greater than or equal to {0}.")
        });
    });

    $('.set_es').on('click', function (){
        var language = "es";
        localStorage.setItem("language", language);

        i18n.setLng('es', function(){
            $('#side-menu').i18n();
            $('#dashboard').i18n();
            $('.nav').i18n();
            $('.footer').i18n();

            $('#login').i18n();
            $('#signup').i18n();
            $('#footer').i18n();
            $('#home').i18n();
            $('#investment').i18n();
            $('#transactionsinvest').i18n();

            $('.set_es').addClass('active');
            $('.set_en').removeClass('active');
        });

        jQuery.extend(jQuery.validator.messages, {
            required: "Este campo es requerido.",
            remote: "Please fix this field.",
            email: "Por favor introduce un correo válido.",
            url: "Por favor introduce una URL válida.",
            date: "Por favor introduce una fecha válida.",
            dateISO: "Por favor introduce una fecha (ISO) válida.",
            number: "Por favor introduce un número válido.",
            digits: "Por favor introduce sólo dígitos.",
            creditcard: "Por favor introduce un número de tarjeta de crédito válido.",
            equalTo: "Por favor introduce el mismo valor otra vez.",
            accept: "Por favor introduce un valor con una extensión válida.",
            maxlength: jQuery.validator.format("Por favor no introducas más de {0} caracteres."),
            minlength: jQuery.validator.format("Por favor introduce al menos {0} caracteres."),
            rangelength: jQuery.validator.format("Por favor introduce un valor entre {0} y {1} caracteres de largo."),
            range: jQuery.validator.format("Por favor introduce un valor entre {0} y {1}."),
            max: jQuery.validator.format("Por favor introduce un valor menor o igual que {0}."),
            min: jQuery.validator.format("Por favor introduce un valor mayor o igual que {0}.")
        });
    })
});