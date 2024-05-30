package com.example.validation.models.validation

import android.view.textclassifier.ConversationActions.Message

sealed class ValidationResults {
    data class Empty(val errorMessage: String):ValidationResults()
    data class Invalid(val errorMessage: String):ValidationResults()
    object Valid:ValidationResults()
}