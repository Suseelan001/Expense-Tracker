package com.example.expensetracker.commonText

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.graphics.Color

class CommonText {

    val customColors = TextFieldColors(
        focusedTextColor = Color.Unspecified,
        unfocusedTextColor = Color.Unspecified,
        disabledTextColor = Color.Unspecified,
        errorTextColor = Color.Unspecified,
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        disabledContainerColor = Color.Gray,
        errorContainerColor = Color.Unspecified,
        cursorColor = Color.Black,
        errorCursorColor = Color.Unspecified,
        textSelectionColors = TextSelectionColors(handleColor = Color.Black, backgroundColor = Color.Black),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Unspecified,
        errorIndicatorColor = Color.Unspecified,
        focusedLeadingIconColor = Color.Unspecified,
        unfocusedLeadingIconColor = Color.Unspecified,
        disabledLeadingIconColor = Color.Unspecified,
        errorLeadingIconColor = Color.Unspecified,
        focusedTrailingIconColor = Color.Unspecified,
        unfocusedTrailingIconColor = Color.Unspecified,
        disabledTrailingIconColor = Color.Unspecified,
        errorTrailingIconColor = Color.Unspecified,
        focusedLabelColor = Color.Unspecified,
        unfocusedLabelColor = Color.Unspecified,
        disabledLabelColor = Color.Gray,
        errorLabelColor = Color.Unspecified,
        focusedPlaceholderColor = Color.Unspecified,
        unfocusedPlaceholderColor = Color.Unspecified,
        disabledPlaceholderColor = Color.Unspecified,
        errorPlaceholderColor = Color.Unspecified,
        focusedSupportingTextColor = Color.Unspecified,
        unfocusedSupportingTextColor = Color.Unspecified,
        disabledSupportingTextColor = Color.Unspecified,
        errorSupportingTextColor = Color.Unspecified,
        focusedPrefixColor = Color.Unspecified,
        unfocusedPrefixColor = Color.Unspecified,
        disabledPrefixColor = Color.Unspecified,
        errorPrefixColor = Color.Unspecified,
        focusedSuffixColor = Color.Unspecified,
        unfocusedSuffixColor = Color.Unspecified,
        disabledSuffixColor = Color.Unspecified,
        errorSuffixColor = Color.Unspecified
    )

    companion object {
        val customColors: TextFieldColors
            get() = CommonText().customColors
    }
}
