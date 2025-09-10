package com.example.eyra

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility // Para el icono de mostrar/ocultar contraseña
import androidx.compose.material.icons.filled.VisibilityOff // Para el icono de mostrar/ocultar contraseña
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eyra.ui.theme.EYRATheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EYRATheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var emailOrUser by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box( // Contenedor principal para el fondo oscuro
        modifier = modifier
            .fillMaxSize()
            .background(darkGrayAlmostBlack)
            .padding(16.dp), // Padding general para la pantalla
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() // La columna ocupa el espacio del Box
                .padding(horizontal = 24.dp), // Padding horizontal para el contenido interno
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround // Para distribuir el espacio
        ) {

            // 1. Título "Iniciar sesión"
            Text(
                text = "Iniciar sesión",
                color = whiteText,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 32.dp, bottom = 24.dp)
            )

            // 2. Ilustración Central
            Image(
                painter = painterResource(id =R.drawable.star_on ),
                contentDescription = "Ilustración de mano sosteniendo un teléfono",
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(1f),
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Campos de entrada
            CustomTextField(
                value = emailOrUser,
                onValueChange = { emailOrUser = it },
                placeholder = "Usuario o correo electrónico",
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Contraseña",
                keyboardType = KeyboardType.Password,
                isPassword = true,
                passwordVisible = passwordVisible,
                onPasswordVisibilityChange = { passwordVisible = !passwordVisible }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Opciones: "¿Olvidaste la contraseña?" y "¿Crear una cuenta?"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ClickableText(
                    text = AnnotatedString("¿Olvidaste la contraseña?"),
                    onClick = { /* TODO: Lógica olvido contraseña */ },
                    style = TextStyle(color = whiteText, fontSize = 12.sp)
                )
                ClickableText(
                    text = AnnotatedString("¿Crear una cuenta?"),
                    onClick = { /* TODO: Lógica crear cuenta */ },
                    style = TextStyle(color = whiteText, fontSize = 12.sp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // 5. Botón "iniciar sesion"
            Button(
                onClick = { /* TODO: Lógica de inicio de sesión */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightGrayBackground, // Fondo gris claro
                    contentColor = darkGrayAlmostBlack // Texto oscuro
                )
            ) {
                Text("iniciar sesion", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 6. Íconos de redes sociales
            Text(
                text = "O inicia sesión con",
                color = whiteText,
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SocialLoginIcon(drawableId = R.drawable.star_on, contentDescription = "Facebook")
                SocialLoginIcon(drawableId = R.drawable.star_on, contentDescription = "Google")
                SocialLoginIcon(drawableId = R.drawable.star_on, contentDescription = "Más opciones")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF1E1E1E) // Fondo de preview oscuro
@Composable
fun LoginScreenPreview() {
    EYRATheme {
        LoginScreen()
    }
}