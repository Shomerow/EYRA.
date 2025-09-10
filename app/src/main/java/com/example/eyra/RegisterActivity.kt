package com.example.eyra

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eyra.ui.theme.EYRATheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EYRATheme {
                RegisterScreen()
            }
        }
    }
}

@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(darkGrayAlmostBlack) // Usando el color definido globalmente o localmente
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Para permitir scroll si el contenido es muy alto
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 1. Título "Crear una cuenta"
            Text(
                text = "Crear una cuenta",
                color = whiteText,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 32.dp, bottom = 24.dp)
            )

            // 2. Ilustración Secuencial
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IllustrativeStep(drawableId = R.drawable.star_on, description = "Paso 1")
                IllustrativeStep(drawableId = R.drawable.star_on, description = "Paso 2")
                IllustrativeStep(drawableId = R.drawable.star_on, description = "Paso 3")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Campos de entrada
            CustomTextField( // Reutilizamos el CustomTextField de LoginActivity
                value = name,
                onValueChange = { name = it },
                placeholder = "Nombre",
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = "Nombre de usuario",
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Correo electrónico",
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
            CustomTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Confirmar contraseña",
                keyboardType = KeyboardType.Password,
                isPassword = true,
                passwordVisible = confirmPasswordVisible,
                onPasswordVisibilityChange = { confirmPasswordVisible = !confirmPasswordVisible }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de Crear Cuenta (Añadido basado en la lógica de un formulario de registro)
            Button(
                onClick = { /* TODO: Lógica de creación de cuenta */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightGrayBackground,
                    contentColor = darkGrayAlmostBlack
                )
            ) {
                Text("Crear cuenta", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Opción: "¿Tienes una cuenta?"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center, // Centrado
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("¿Tienes una cuenta? ", style = TextStyle(color = whiteText, fontSize = 12.sp))
                ClickableText(
                    text = AnnotatedString("Inicia sesión"),
                    onClick = { /* TODO: Lógica para ir a LoginActivity */ },
                    style = TextStyle(color = whiteText, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                )
            }

            Spacer(modifier = Modifier.weight(1f, fill = false))

            // 5. Texto "iniciar sesión con:"
            Text(
                text = "o regístrate con:",
                color = whiteText,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
            )

            // 6. Íconos de redes sociales
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Reutilizamos SocialLoginIcon de LoginActivity
                SocialLoginIcon(drawableId = R.drawable.star_on, contentDescription = "Facebook")
                SocialLoginIcon(drawableId = R.drawable.star_on, contentDescription = "Google")
                SocialLoginIcon(drawableId = R.drawable.star_on, contentDescription = "Más opciones")
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun IllustrativeStep(drawableId: Int, description: String, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = drawableId),
        contentDescription = description,
        modifier = modifier
            .size(80.dp)
            .padding(4.dp),
        contentScale = ContentScale.Fit
    )
}


@Preview(showBackground = true, backgroundColor = 0xFF333333) // Fondo de preview oscuro
@Composable
fun RegisterScreenPreview() {
    EYRATheme {
        RegisterScreen()
    }
}

