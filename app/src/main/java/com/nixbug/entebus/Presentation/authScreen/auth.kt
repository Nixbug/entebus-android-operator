package com.nixbug.entebus.presentation.authScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nixbug.entebus.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Auth() {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    var selectedCompany by remember { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }
    val companies = listOf("Company A", "Company B", "Company C","Company D", "Company E", "Company F","Company G", "Company H", "Company I","Company J", "Company K", "Company L")

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2033B1),
                        Color(0xFF47C7FF),
                        Color(0xFF10C555)
                    )
                )
            ).pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .wrapContentHeight()
                .verticalScroll(scrollState)
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(
                    id = R.drawable.entebus_logo
                ),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(2.dp, Color.White, CircleShape)
                    .shadow(6.dp, CircleShape),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Entebus Conductor",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.White
            )

            Text(
                text = "Sign in to your account",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            //Company Drop Down
            CompanyDropdown(
                selectedCompany = selectedCompany,
                expanded = expanded,
                onExpandChange = { expanded = it },
                companies = companies,
                onSelect = { company ->
                    selectedCompany = company
                    expanded = false
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Username Field
            UsernameField(
                value = userName,
                onChange = { userName = it },
                focusManager = focusManager
            )

            Spacer(modifier = Modifier.height(16.dp))

            //password Field
            PasswordField(
                value = password,
                onChange = { password = it },
                visible = passwordVisible,
                toggle = { passwordVisible = !passwordVisible }
            )

            Spacer(modifier = Modifier.height(20.dp))


            //Sign in Button
            SignInButton(onClick = { /* To Handle sign-in*/  })

            Spacer(modifier = Modifier.height(15.dp))

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyDropdown(
    selectedCompany: String?,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    companies: List<String>,
    onSelect: (String) -> Unit
) {
    Text(
        text = "Company",
        fontSize = 16.sp,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    )

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandChange
    ) {
        OutlinedTextField(
            value = selectedCompany ?: "",
            onValueChange = {},
            readOnly = true,
            placeholder = { Text("Select your company") },
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable)
                .fillMaxWidth(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color(0xFF47C7FF),
                unfocusedBorderColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandChange(false) },
            modifier = Modifier.background(Color.White)
        ) {
            companies.forEach { company ->
                DropdownMenuItem(
                    text = { Text(company, color = Color.Black) },
                    onClick = { onSelect(company) }
                )
            }
        }
    }
}

@Composable
fun UsernameField(value: String, onChange: (String) -> Unit, focusManager: androidx.compose.ui.focus.FocusManager) {
    Text(
        text = "Username",
        fontSize = 16.sp,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    )

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        placeholder = { Text("Enter your username") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedBorderColor = Color(0xFF47C7FF),
            unfocusedBorderColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
        )
    )
}

@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
    visible: Boolean,
    toggle: () -> Unit
) {
    Text(
        text = "Password",
        fontSize = 16.sp,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    )

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        placeholder = { Text("Enter your password") },
        singleLine = true,
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = toggle) {
                Icon(
                    imageVector = if (visible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (visible) "Hide password" else "Show password"
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedBorderColor = Color(0xFF47C7FF),
            unfocusedBorderColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
        )
    )
}

@Composable
fun SignInButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8BC34A)),
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 10.dp,
            focusedElevation = 8.dp
        )
    ) {
        Text("Sign in", color = Color.White, fontSize = 16.sp)
    }
}
