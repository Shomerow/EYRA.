package com.example.eyra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.eyra.ui.theme.EYRATheme
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


class HomeScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EYRATheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.statusBars)
                )
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var showAddNoteDialog by remember { mutableStateOf(false) }
    var noteTitle by remember { mutableStateOf("") }
    var noteContent by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(veryLightGrayOrAlmostWhite)
    ) {
        // --- Elementos Superiores ---
        TopDecorations(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter)
            .padding(top = 40.dp, start = 16.dp, end = 16.dp))

        // --- Botones Laterales Derechos ---
        SideActionButtons(
            modifier = Modifier.align(Alignment.CenterEnd),
            onAddNoteClick = {
                showAddNoteDialog = true
            },
            onMenuClick = {
                println("Botón de menú clickeado")
            }
        )

        // --- Matriz de Puntos Central ---
        DotMatrix(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp, bottom = 80.dp, start = 32.dp, end = 80.dp)
        )

        // --- Barra de Navegación Inferior ---
        BottomNavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        // --- Diálogo para Añadir Nota ---
        if (showAddNoteDialog) {
            AddNoteDialog(
                title = noteTitle,
                content = noteContent,
                onTitleChange = { noteTitle = it },
                onContentChange = { noteContent = it },
                onDismissRequest = {
                    showAddNoteDialog = false
                    // Resetear campos al cerrar
                    noteTitle = ""
                    noteContent = ""
                },
                onConfirm = {
                    println("Guardar Nota desde Diálogo: Título - $noteTitle, Contenido - $noteContent")
                    showAddNoteDialog = false
                    // Resetear campos después de guardar
                    noteTitle = ""
                    noteContent = ""
                }
            )
        }
    }
}

@Composable
fun TopDecorations(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ilustración Geométrica Superior Izquierda
        GeometricIllustrationTopLeft(modifier = Modifier.size(40.dp))

        // Icono Cubo 3D (Placeholder)
        Image(
            imageVector = Icons.Filled.ViewInAr,
            contentDescription = "Cubo 3D",
            modifier = Modifier.size(48.dp),
            colorFilter = ColorFilter.tint(blackColor.copy(alpha = 0.7f))
        )

        // Formas Triangulares Superior Derecha
        TriangleShapesTopRight(modifier = Modifier.size(50.dp, 40.dp))
    }
}

@Composable
fun GeometricIllustrationTopLeft(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2
        // Círculo amarillo
        drawCircle(
            color = brightYellow,
            radius = radius,
            center = Offset(radius, radius)
        )
        // Semicírculo negro (Arc)
        drawArc(
            color = blackColor,
            startAngle = -90f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(0f, 0f),
            size = Size(radius * 2, radius * 2)
        )
    }
}

@Composable
fun TriangleShapesTopRight(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        // Triángulo negro grande
        val pathBlack = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, size.height / 2)
            lineTo(0f, size.height)
            close()
        }
        drawPath(pathBlack, color = blackColor)

        // Triángulo blanco pequeño (superpuesto o al lado)
        val pathWhite = Path().apply {
            moveTo(size.width * 0.4f, size.height * 0.3f)
            lineTo(size.width * 0.8f, size.height * 0.5f)
            lineTo(size.width * 0.4f, size.height * 0.7f)
            close()
        }
        drawPath(pathWhite, color = whiteColor)
    }
}

@Composable
fun SideActionButtons(
    modifier: Modifier = Modifier,
    onAddNoteClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(end = 16.dp, top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Botón de Menú
        SideButton(
            icon = Icons.Filled.Menu,
            description = "Menú",
            onClick = onMenuClick
        )
        // Botón de Añadir Nota
        SideButton(
            icon = Icons.Filled.Add,
            description = "Añadir Nota",
            onClick = onAddNoteClick
        )
    }
}

@Composable
fun SideButton(
    icon: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(48.dp)
            .background(darkGrayAlmostBlack, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = whiteColor,
            modifier = Modifier.size(28.dp)
        )
    }
}


@Composable
fun DotMatrix(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val numRows = 10
        val numCols = 6
        val dotSize = 8.dp
        val shadowOffset = 2.dp

        repeat(numRows) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(numCols) {
                    DotWithShadow(dotSize = dotSize, shadowColor = brightYellow, shadowOffset = shadowOffset)
                }
            }
        }
    }
}

@Composable
fun DotWithShadow(dotSize: Dp, shadowColor: Color, shadowOffset: Dp) {
    Box {
        Box(
            modifier = Modifier
                .offset(x = shadowOffset, y = shadowOffset)
                .size(dotSize)
                .background(shadowColor, CircleShape)
        )
        // Punto principal
        Box(
            modifier = Modifier
                .size(dotSize)
                .background(blackColor, CircleShape)
        )
    }
}


@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(darkGrayAlmostBlack)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(icon = Icons.Filled.DateRange, description = "Calendario", onClick = { /*TODO*/ })

        // Botón Home elevado
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .offset(y = (-16).dp)
                .size(64.dp, 56.dp)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
                .background(brightYellow, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = blackColor,
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        BottomNavItem(icon = Icons.Filled.Book, description = "Libro", onClick = { /*TODO*/ })
    }
}

@Composable
fun BottomNavItem(icon: ImageVector, description: String, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.size(48.dp)) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = whiteColor.copy(alpha = 0.8f),
            modifier = Modifier.size(28.dp)
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
fun HomeScreenPreview() {
    EYRATheme {
        HomeScreen()
    }
}

@Composable
fun AddNoteDialog(
    title: String,
    content: String,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        // --- Forma y Color del Contenedor del Diálogo ---
        shape = RoundedCornerShape(16.dp),
        containerColor = veryLightGrayOrAlmostWhite,

        // --- Título ---
        title = {
            Text(
                text = "Añadir Nueva Nota",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = darkGrayAlmostBlack
            )
        },

        // --- Campos de Texto ---
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = onTitleChange,
                    label = { Text("Título de la nota", color = darkGrayAlmostBlack.copy(alpha = 0.7f)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = brightYellow,
                        unfocusedBorderColor = darkGrayAlmostBlack.copy(alpha = 0.4f),
                        focusedLabelColor = brightYellow,
                        cursorColor = brightYellow,
                        focusedTextColor = darkGrayAlmostBlack,
                        unfocusedTextColor = darkGrayAlmostBlack,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = content,
                    onValueChange = onContentChange,
                    label = { Text("Escribe algo...", color = darkGrayAlmostBlack.copy(alpha = 0.7f)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 120.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = brightYellow,
                        unfocusedBorderColor = darkGrayAlmostBlack.copy(alpha = 0.4f),
                        focusedLabelColor = brightYellow,
                        cursorColor = brightYellow,
                        focusedTextColor = darkGrayAlmostBlack,
                        unfocusedTextColor = darkGrayAlmostBlack,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        },

        // --- Botones de Acción ---
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank() && content.isNotBlank()) {
                        onConfirm()
                    }
                },
                shape = RoundedCornerShape(percent = 50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = brightYellow,
                    contentColor = blackColor
                ),
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text("GUARDAR", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = darkGrayAlmostBlack.copy(alpha = 0.7f)
                )
            ) {
                Text("CANCELAR")
            }
        }
    )
}

