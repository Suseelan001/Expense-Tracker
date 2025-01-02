package com.example.expensetracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.expensetracker.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)


var HonchokomonoWithHexe0e0e018sp: TextStyle = TextStyle(
    fontFamily = FontFamily(Font(R.font.honchokomono_italic_ight)),
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp,
    letterSpacing = TextUnit.Unspecified,
    color = Hexe0e0e0
)

var HonchokomonoWithHexe0e0e020sp: TextStyle = TextStyle(
    fontFamily = FontFamily(Font(R.font.honchokomono_italic_ight)),
    fontWeight = FontWeight.Normal,
    fontSize = 20.sp,
    letterSpacing = TextUnit.Unspecified,
    color = Hexe0e0e0
)
var HonchokomonoWithHex674b3f18sp: TextStyle = TextStyle(
    fontFamily = FontFamily(Font(R.font.honchokomono_italic_ight)),
    fontWeight = FontWeight.Normal,
    fontSize = 20.sp,
    letterSpacing = TextUnit.Unspecified,
    color = Hex674b3f
)