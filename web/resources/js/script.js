
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function onlynumbers(event, field, allowDot) {
    var nav4 = window.Event ? true : false;
    var key = nav4 ? event.which : event.keyCode;
    var thereAreDot = (field.value.indexOf('.',0) > -1) ? true: false;
    
    return (key<=13 || (key>=48 && key<=57) || (key===46 && allowDot && !thereAreDot));
}

function withwhitespace(field) {
    if(!/\S/.test(field.value)) {
        field.value = '';
        return false;
    }
    
    field.value = field.value.replace(/^(\s|\&nbsp;)*|(\s|\&nbsp;)*$/g,"");
    
    return true;
}