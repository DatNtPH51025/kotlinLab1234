package com.poly.lab1234_ph51025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.VisualTransformation


@Composable
fun LoginScreen1() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf("") }
    var showAlert by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Logo")
        Spacer(modifier = Modifier.height(20.dp))

        InputField(value = username, label = "Username") { username = it }
        Spacer(modifier = Modifier.height(8.dp))
        InputField(value = password, label = "Password", isPassword = true) { password = it }
        Spacer(modifier = Modifier.height(8.dp))

        SavePasswordSwitch(isChecked) { isChecked = it; showAlert = it; alertMessage = "Lưu mật khẩu thành công" }
        Spacer(modifier = Modifier.height(16.dp))

        LoginButton(onClick = {
            alertMessage = if (username.isNotBlank() && password.isNotBlank()) "Login successful" else "Please enter username and password"
            showAlert = true
        })
    }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            title = { Text("Thông báo") },
            text = { Text(alertMessage) },
            confirmButton = { TextButton(onClick = { showAlert = false }) { Text("OK") } }
        )
    }
}

@Composable
fun InputField(value: String, label: String, isPassword: Boolean = false, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun SavePasswordSwitch(isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green,
                uncheckedThumbColor = Color.Gray,
                checkedTrackColor = Color.Green.copy(alpha = 0.5f),
                uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f)
            ),
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = "Lưu mật khẩu?", fontSize = 20.sp)
    }
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) { Text("Login") }
}

@Composable
fun LightSwitch() {
    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val isChecked = remember { mutableStateOf(false) }
        if (isChecked.value) {
            Image(
                painter = painterResource(id = R.drawable.den_2),
                contentDescription = "Light is On",
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.den_1),
                contentDescription = "Light is Off",
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
            )
        }
        Spacer(modifier = androidx.compose.ui.Modifier.height(24.dp))
        Switch(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green,
                uncheckedThumbColor = Color.Gray,
                checkedTrackColor = Color.Green.copy(alpha = 0.5f),
                uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f),
                checkedBorderColor = Color.Green.copy(alpha = 0.75f),
            )
        )
    }
}
@Composable
fun CategoryApp() {
    val categories = listOf(
        "Fiction", "Mystery", "Science Fiction", "Fantasy",
        "Adventure", "Historical", "Horror", "Romance"
    )
    val suggestions = listOf(
        "Biography", "Cookbook", "Poetry", "Self-help", "Thriller"
    )
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Choose a category:",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        AssistChip(
            onClick = { /* Do something */ },
            label = { Text("Need help?") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        CategoryChips(categories, selectedCategories) { category ->
            selectedCategories = if (selectedCategories.contains(category))
                selectedCategories - category
            else
                selectedCategories + category
        }
        Spacer(modifier = Modifier.height(16.dp))
        SuggestionChips(suggestions, selectedCategories) { suggestion ->
            selectedCategories = selectedCategories + suggestion
        }
        if (selectedCategories.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            SelectedCategoriesChips(selectedCategories) { category ->
                selectedCategories = selectedCategories - category
            }
            Spacer(modifier = Modifier.height(4.dp))
            AssistChip(
                onClick = { selectedCategories = setOf() },
                label = {
                    Text(
                        "Clear selections",
                        style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
                    )
                },
                colors = AssistChipDefaults.assistChipColors(containerColor = Color.DarkGray),
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChips(
    categories: List<String>,
    selectedCategories: Set<String>,
    onCategorySelected: (String) -> Unit
) {
    Text("Choose categories:", style = MaterialTheme.typography.titleMedium)
    Spacer(modifier = Modifier.height(8.dp))
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        categories.forEach { category ->
            FilterChip(
                selected = selectedCategories.contains(category),
                onClick = { onCategorySelected(category) },
                label = { Text(category) },
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@Composable
fun SuggestionChips(
    suggestions: List<String>,
    selectedCategories: Set<String>,
    onSuggestionSelected: (String) -> Unit
) {
    Text("Suggestions:", style = MaterialTheme.typography.titleMedium)
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        suggestions.forEach { suggestion ->
            val isSelected = selectedCategories.contains(suggestion)
            val chipColors = if (isSelected) {
                SuggestionChipDefaults.suggestionChipColors(containerColor = Color.LightGray)
            } else {
                SuggestionChipDefaults.suggestionChipColors()
            }
            SuggestionChip(
                onClick = { onSuggestionSelected(suggestion) },
                label = { Text(suggestion) },
                colors = chipColors,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedCategoriesChips(
    selectedCategories: Set<String>,
    onCategoryRemoved: (String) -> Unit
) {
    Text("Selected categories:", style = MaterialTheme.typography.titleMedium)
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        selectedCategories.forEach { selectedCategory ->
            InputChip(
                selected = true,
                onClick = { },
                label = { Text(selectedCategory) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Deselect",
                        modifier = Modifier
                            .clickable { onCategoryRemoved(selectedCategory) }
                            .size(18.dp)
                    )
                },
                modifier = Modifier.padding(end = 8.dp),
            )
        }
    }
}

