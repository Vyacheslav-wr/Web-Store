function Validate()
{
    if (document.forms["myform"]["first_name"].value.replace(/[A-zА-я]+/, "OK") !== "OK")
    {
        alert("Incorrect first name!");
        return false;
    }

    else if (document.forms["myform"]["last_name"].value.replace(/[A-zА-я]+/, "OK") !== "OK")
    {
        alert("Incorrect second name!");
        return false;
    }

    else if (document.forms["myform"]["phone_Num"].value.replace(/[+]?[\d]+/, "OK") !== "OK")
    {
        alert("Incorrect contact number!")
        return false;
    }
    else if (document.forms["myform"]["login"].value.length < 8 || document.forms["myform"]["login"].value.length > 20)
    {
        alert("Login length should be 8-20 symbols!");
        return false;
    }
    else if (document.forms["myform"]["password"].value.length < 8 || document.forms["myform"]["password"].value.length > 20)
    {
        alert("Password length should be 8-20 symbols!");
        return false;
    }
    else if (document.forms["myform"]["email"].value.replace(/^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$+/, "OK") !== "OK")
    {
        alert("Incorrect Email!");
        return false;
    }
    alert("New user registered successfully!");
    return true;
}