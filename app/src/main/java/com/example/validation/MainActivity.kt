package com.example.validation

import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.validation.models.FormData
import com.example.validation.models.validation.ValidationResults
import java.time.Year

class MainActivity : AppCompatActivity() {

    //create global variables
    lateinit var editStudentId:EditText
    lateinit var spnYear: Spinner
    lateinit var spnSemester:Spinner
    lateinit var cbAgreement:CheckBox
    lateinit var tvYear:TextView
    lateinit var tvSemester:TextView

    private var count=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialisation
        editStudentId = findViewById(R.id.edtStudentID)
        spnYear = findViewById(R.id.spnYear)
        spnSemester = findViewById(R.id.spnSemester)
        cbAgreement = findViewById(R.id.cbAgreementcheckBox)
        tvYear = findViewById(R.id.tvYear)
        tvSemester = findViewById(R.id.tvSemester)
    }

    //display alert function
    fun displayAlert(title: String, message: String){
        var builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok"){dialog, which ->

        }

        //create the alert box when need
        val dialog = builder.create()
        //display the alert box
        dialog.show()
    }

    //submit button click event
    fun submit(v: View){
        val form = FormData(editStudentId.text.toString(),
            spnYear.selectedItem.toString(),
            spnSemester.selectedItem.toString(),
            cbAgreement.isChecked)
        val studentIdValidation = form.validationStudentId()
        val spnYearValidation = form.validateYear()
        val spnSemesterValidation = form.validateSemester()
        val cbAgreementValidation = form.validateAgreement()

        //check student Id and validate it
        when (studentIdValidation){
            is ValidationResults.Valid ->{
                count ++
            }
            is ValidationResults.Invalid ->{
                editStudentId.error = studentIdValidation.errorMessage
            }
            is ValidationResults.Empty ->{
                editStudentId.error = studentIdValidation.errorMessage
            }
        }

        //check year selection
        when (spnYearValidation){
            is ValidationResults.Valid ->{
                count ++
            }
            is ValidationResults.Invalid ->{

            }
            is ValidationResults.Empty ->{
                val tv:TextView = spnYear.selectedView as TextView
                tv.error = ""
                tv.text = spnYearValidation.errorMessage
            }
        }

        //checking semester selection
        when (spnSemesterValidation){
            is ValidationResults.Empty -> {
                val tv:TextView = spnSemester.selectedView as TextView
                tv.error = ""
                tv.text = spnSemesterValidation.errorMessage
            }
            is ValidationResults.Invalid -> {

            }
            is ValidationResults.Valid -> {
                count ++
            }
        }

        //checking agreement check box
        when (cbAgreementValidation){
            is ValidationResults.Empty -> {

            }
            is ValidationResults.Invalid -> {
                displayAlert("Error",cbAgreementValidation.errorMessage)
            }
            is ValidationResults.Valid -> {
                count ++
            }
        }

        if (count == 4){
            displayAlert("Success","You have successfully registered")
        }
    }
}