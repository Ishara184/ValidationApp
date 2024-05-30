package com.example.validation.models

import android.view.ActionProvider.VisibilityListener
import com.example.validation.models.validation.ValidationResults

class FormData(private val studentId:String,
               private val year:String,
               private val semester:String,
               private val agree:Boolean) {

    //validate student ID
    fun validationStudentId():ValidationResults{
        //check the field is empty
        return if (studentId.isEmpty()){
            ValidationResults.Empty("Please enter the student ID")
        }
        //check the id has been start with "IT"
        else if (!studentId.startsWith("IT")){
            ValidationResults.Invalid("Student ID should start with IT")
        }
        //check entered id contain 10 characters
        else if (studentId.length<10){
            ValidationResults.Invalid("Student ID must contain 10 characters")
        }
        else if (studentId.length>10){
            ValidationResults.Invalid("Student ID can only contain 10 characters")
        }
        else{
            ValidationResults.Valid
        }
    }

    //validate year field
    fun validateYear():ValidationResults{
        return if (year.isEmpty()){
            ValidationResults.Empty("Select the academic year")
        }
        else{
            ValidationResults.Valid
        }
    }

    //validate semester field
    fun validateSemester():ValidationResults{
        return if (semester.isEmpty()){
            ValidationResults.Empty("Select the academic semester")
        }
        else{
            ValidationResults.Valid
        }
    }

    //validate agreement check box
    fun validateAgreement():ValidationResults{
        return if (!agree){
            ValidationResults.Invalid("You must agree for the terms and conditions")
        }
        else{
            ValidationResults.Valid
        }
    }

}