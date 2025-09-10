package com.example.eyra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eyra.ui.theme.EYRATheme

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EYRATheme {
                WelcomeScreen()
            }
        }
    }
}

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(darkGrayAlmostBlack)
            .padding(0.dp)
    ) {
        // 1. Ilustración Parcial Superior Izquierda
        PartialIllustrationWA(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 85.dp)
        )

        // Contenido Principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // 2. Texto Principal
            Text(
                text = "¡Te damos la bienvenida!",
                color = whiteText,
                fontSize = 51.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 45.sp,
                modifier = Modifier.padding(start = 60.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        // 3. Ícono de Flecha (Esquina Inferior Izquierda)
        IconButton(
            onClick = { /* TODO: Lógica para regresar */ },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Regresar",
                tint = whiteText,
                modifier = Modifier.size(36.dp)
            )
        }

        // 4. Botón "Siguiente" (Esquina Inferior Derecha)
        Button(
            onClick = { /* TODO: Lógica del botón OK */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .height(50.dp)
                .widthIn(min = 100.dp),
            shape = CircleShape, // Forma ovalada
            colors = ButtonDefaults.buttonColors(
                containerColor = brightYellow,
                contentColor = Color.Black
            )
        ) {
            Text(
                "Siguiente",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun PartialIllustrationWA(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(width = 60.dp, height = 90.dp)) {
        val cornerRadiusPx = 50.dp.toPx()

        // Rectángulo amarillo (el de arriba)
        drawRoundRect(
            color = brightYellow,
            topLeft = Offset(size.width * -0.5f, size.height * 0f),
            size = Size(size.width * 1f, size.height * 1f),
            cornerRadius = CornerRadius(cornerRadiusPx)
        )

        // Rectángulo amarillo 2 (el de arriba sobrepuesto)
        drawRoundRect(
            color = brightYellow,
            topLeft = Offset(size.width * -0.5f, size.height * 0f),
            size = Size(size.width * 1.3f, size.height * 0.4f),
            cornerRadius = CornerRadius(cornerRadiusPx)
        )

        // Rectángulo amarillo más claro (el del medio)
        drawRoundRect(
            color = lightYellow,
            topLeft = Offset(size.width * -0.5f, size.height * 0.15f),
            size = Size(size.width * 1f, size.height * 0.85f),
            cornerRadius = CornerRadius(cornerRadiusPx)
        )

        // Rectángulo blanco (el de más abajo)
        drawRoundRect(
            color = white,
            topLeft = Offset(size.width * -0.5f, size.height * 0.4f),
            size = Size(size.width * 1f, size.height * 0.7f),
            cornerRadius = CornerRadius(cornerRadiusPx)
        )


    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    EYRATheme {

        Box(Modifier.background(darkGrayAlmostBlackPreview)) {
            WelcomeScreen()
        }
    }
}