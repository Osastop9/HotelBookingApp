package com.decagon.hbapplicationgroupa.utils

fun fullNameIsNotEmpty(fullName: String): Boolean{
    if (fullName.isNotEmpty()){
        return true
    }
    return false
}

fun fullNameMatchesPattern(fullName: String): Boolean{
    val namePattern = "(^[A-Za-z]+\\s[A-Za-z]+$)".toRegex()

    if (fullName.matches(namePattern)) {
        return true
    }
    return false
}

fun emailIsNotEmpty(userEmail: String): Boolean{
    if (userEmail.isNotEmpty()){
        return true
    }
    return false
}

fun emailMatchesPattern(userEmail: String): Boolean{
    val emailPattern = "[a-zA-Z0-9._-]+@([a-z])+\\.[a-z]+".toRegex()

    if (userEmail.matches(emailPattern)) {
        return true
    }
    return false
}

fun phoneNumberIsNotEmpty(userPhoneNumber: String): Boolean{
    if (userPhoneNumber.isNotEmpty()){
        return true
    }
    return false
}

fun phoneNumberEqualsLength(userPhoneNumber: String): Boolean{
    if (userPhoneNumber.length == 11){
        return true
    }
    return false
}

fun isAValidNigerianNumber(userPhoneNumber: String): Boolean{
    val nigerianPhoneNumberPattern = "(09)[01][0-9]{8}|(08)[01][0-9]{8}|(07)[01][0-9]{8}".toRegex()
    if (userPhoneNumber.matches(nigerianPhoneNumberPattern)){
        return true
    }
    return false
}

fun genderIsNotEmpty(gender: String): Boolean{
    if (gender.isNotEmpty()){
        return true
    }
    return false
}

fun genderIsValid(gender: String): Boolean{
    if (gender == "Male" || gender == "Female"){
        return true
    }
    return false
}

fun passwordIsNotEmpty(password: String): Boolean{
    if (password.isNotEmpty()){
        return true
    }
    return false
}

fun passwordHasNoWhiteSpace(password: String): Boolean{
    val noWhiteSpace = "(?=\\S+$)".toRegex()

    if (password.contains(noWhiteSpace)) {
        return true
    }
    return false
}

fun passwordHasSpecialCharacters(password: String): Boolean{
    val specialCharacter = "(?=.*[@#$%^&+=])".toRegex()

    if (password.contains(specialCharacter)) {
        return true
    }
    return false
}

fun passwordHasAtLeastOneDigit(password: String): Boolean{
    val atLeastOneDigit = "(?=.*[0-9])".toRegex()

    if (password.contains(atLeastOneDigit)) {
        return true
    }
    return false
}

fun passwordHasAtLeastOneAlphabet(password: String): Boolean{
    val atLeastOneAlphabet = "(?=.*[a-zA-Z])".toRegex()

    if (password.contains(atLeastOneAlphabet)) {
        return true
    }
    return false
}

fun passwordLengthIsGreaterThanFive(password: String): Boolean{
    if (password.length > 5){
        return true
    }
    return false
}

fun checkInIsNotEmpty(checkInDate: String): Boolean{
    if (checkInDate.isNotEmpty()){
        return true
    }
    return false
}

fun checkOutIsNotEmpty(checkOutDate: String): Boolean{
    if (checkOutDate.isNotEmpty()){
        return true
    }
    return false
}

fun numberOfPeopleIsNotEmpty(numberOfPeople: String): Boolean{
    if (numberOfPeople.isNotEmpty()){
        return true
    }
    return false
}

fun roomTypeIsNotEmpty(roomType: String): Boolean{
    if (roomType.isNotEmpty()){
        return true
    }
    return false
}


fun findNumberOfDays(chekOut:String, checkIn:String):Int{
    val arrayOfMonths= arrayListOf("January","February","March","April","May","June","July",
        "August","September","October","November","December")

    val splitCheckIn = checkIn.split(","," ")
    val splitCheckOut = chekOut.split(","," ")
    val checkOutYear = splitCheckOut[3].toInt()
    val checkInMonth = splitCheckIn[1]
    val checkOutMonth = splitCheckOut[1]

    var checkInMonthPosition = 0
    for (i in arrayOfMonths.indices){
        if (checkInMonth == arrayOfMonths[i]){
            checkInMonthPosition = i
        }
    }
    val febSpecial = if (checkOutYear % 4 == 0) 29 else 28
    val arrayOfDaysInMonth= arrayListOf(31,febSpecial,31,30,31,30,31,31,30,31,30,31)

    val checkInDay = splitCheckIn[0].toInt()
    val checkOutDay = splitCheckOut[0].toInt()

    val diffInMonthToDay = if(checkInMonth != checkOutMonth){
        checkOutDay + (arrayOfDaysInMonth[checkInMonthPosition] - checkInDay)
    }else{
        checkOutDay - checkInDay
    }
    return diffInMonthToDay
}