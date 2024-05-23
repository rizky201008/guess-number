package com.vixiloc.guessnumber.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.sp
import com.vixiloc.guessnumber.R

val regularFontFamily = FontFamily(
    Font(R.font.pixel_ae_regular)
)

val boldFontFamily = FontFamily(
    Font(R.font.pixel_ae_bold)
)

private val defaultTypography = Typography()
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = regularFontFamily,
        fontSize = defaultTypography.bodyLarge.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = defaultTypography.bodyLarge.letterSpacing
    ),
    bodyMedium = TextStyle(
        fontFamily = regularFontFamily,
        fontSize = defaultTypography.bodyMedium.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = defaultTypography.bodyMedium.letterSpacing
    ),
    bodySmall = TextStyle(
        fontFamily = regularFontFamily,
        fontSize = defaultTypography.bodySmall.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = defaultTypography.bodySmall.letterSpacing
    ),
    headlineLarge = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = defaultTypography.headlineLarge.fontSize,
        fontWeight = FontWeight.Bold,
        letterSpacing = defaultTypography.headlineLarge.letterSpacing
    ),
    headlineMedium = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = defaultTypography.headlineMedium.fontSize,
        fontWeight = FontWeight.Bold,
        letterSpacing = defaultTypography.headlineMedium.letterSpacing
    ),
    headlineSmall = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = defaultTypography.headlineSmall.fontSize,
        fontWeight = FontWeight.Bold,
        letterSpacing = defaultTypography.headlineSmall.letterSpacing
    ),
    titleLarge = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = defaultTypography.titleLarge.fontSize,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = defaultTypography.titleLarge.letterSpacing
    ),
    titleMedium = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = defaultTypography.titleMedium.fontSize,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = defaultTypography.titleMedium.letterSpacing
    ),
    titleSmall = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = defaultTypography.titleSmall.fontSize,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = defaultTypography.titleSmall.letterSpacing
    ),
    labelLarge = TextStyle(
        fontFamily = regularFontFamily,
        fontSize = defaultTypography.labelLarge.fontSize,
        fontWeight = FontWeight.Medium,
        letterSpacing = defaultTypography.labelLarge.letterSpacing
    ),
    labelMedium = TextStyle(
        fontFamily = regularFontFamily,
        fontSize = defaultTypography.labelMedium.fontSize,
        fontWeight = FontWeight.Medium,
        letterSpacing = defaultTypography.labelMedium.letterSpacing
    ),
    labelSmall = TextStyle(
        fontFamily = regularFontFamily,
        fontSize = defaultTypography.labelSmall.fontSize,
        fontWeight = FontWeight.Medium,
        letterSpacing = defaultTypography.labelSmall.letterSpacing
    ),
    displayLarge = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = defaultTypography.displayLarge.fontSize,
        fontWeight = FontWeight.Bold,
        letterSpacing = defaultTypography.displayLarge.letterSpacing
    ),
    displayMedium = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = defaultTypography.displayMedium.fontSize,
        fontWeight = FontWeight.Bold,
        letterSpacing = defaultTypography.displayMedium.letterSpacing
    ),
    displaySmall = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = defaultTypography.displaySmall.fontSize,
        fontWeight = FontWeight.Bold,
        letterSpacing = defaultTypography.displaySmall.letterSpacing
    )
)