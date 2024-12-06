package com.example.soundscape.view

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soundscape.R
import com.example.soundscape.ui.theme.SoundScapeTheme

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SoundScapeTheme {
                //True = Login ; False = Register
                val showLoginForm = rememberSaveable { mutableStateOf(true) }

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Brush
                        .verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary
                            )
                        )
                    )
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if(showLoginForm.value)
                        {
                            Image(
                                painter = painterResource(id = R.drawable.applogo),
                                contentDescription = "Logo",
                                modifier = Modifier
                                    .size(300.dp)
                                    .clip(CircleShape)
                            )

                            Spacer(modifier = Modifier.height(50.dp))

                            UserForm(isCreateAccount = false)
                            {
                                email, password, name, age ->
                                Log.d("Login", "Login: $email, $password, $name, $age")
                            }
                        }
                        else
                        {
                            Text(
                                text  = "Cree una cuenta",
                                color = MaterialTheme.colorScheme.tertiary,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            UserForm(isCreateAccount = true)
                            {
                                email, password, name, age ->
                                Log.d("Registro", "Registro: $email, $password, $name, $age")
                            }
                        }

                        //Seccion de registro
                        Spacer(modifier = Modifier.padding(15.dp))

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            val labelLogin = if(showLoginForm.value) "¿No tienes cuenta?"
                                else "¿Ya tienes cuenta?"
                            val labelRegister = if(showLoginForm.value) "Registrate"
                                else "Inicia sesión"

                            Text(text = labelLogin)
                            Text(text = labelRegister,
                                modifier = Modifier
                                    .clickable { showLoginForm.value = !showLoginForm.value }
                                    .padding(start = 5.dp),
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }

                }

            }
        }
    }

    @Composable
    private fun UserForm(
        isCreateAccount: Boolean = false,
        onDone: (String, String, String, Int) -> Unit = {email, password, name, age ->})
    {
        val email = rememberSaveable { mutableStateOf("") }
        val name = rememberSaveable { mutableStateOf("") }
        val age = rememberSaveable { mutableStateOf("") }
        val password = rememberSaveable { mutableStateOf("") }
        val passwordVisibility = rememberSaveable { mutableStateOf(false) }

        val allDataIsCaptured = remember(email.value, password.value) {
            email.value.trim().isNotEmpty() &&
                    password.value.trim().isNotEmpty()
        }

        //Para ocultar el teclado, usaremos una funcion exerimental
        val keyboardController = LocalSoftwareKeyboardController.current

        Column( horizontalAlignment = Alignment.CenterHorizontally)
        {
            EmailInput(emailState = email)

            PasswordInput(
                passwordState = password,
                passwordVisibility = passwordVisibility,
                labelId = "Password")

            if(isCreateAccount)
            {
                NameInput(nameState = name)
                
                AgeInput(ageState = age)
            }

            SubmitButton(
                textId = if(isCreateAccount) "Crear Cuenta" else "Iniciar Sesion",
                inputValid = allDataIsCaptured
            )
            {
                //aqui mandamos la funcion del UserForm

                if(isCreateAccount)
                {
                    onDone(
                        email.value.trim(),
                        password.value.trim(),
                        name.value.trim(),
                        age.value.toInt())
                }
                else
                {
                    onDone(
                        email.value.trim(),
                        password.value.trim(),
                        "none",
                        0)
                }

                keyboardController?.hide()
            }
        }
    }

    private @Composable
    fun AgeInput(
        ageState: MutableState<String>,
        labelId: String = "Edad")
    {
        InputField(
            valueState = ageState,
            labelId = labelId,
            keyboardType = KeyboardType.Number
        )
    }

    private @Composable
    fun NameInput(
        nameState: MutableState<String>,
        labelId: String = "Nombre") 
    {
        InputField(
            valueState = nameState,
            labelId = labelId,
            keyboardType = KeyboardType.Text
        )
    }

    private @Composable
    fun SubmitButton(
        textId: String,
        inputValid: Boolean,
        onClic: ()-> Unit)
    {
        Button(
            onClick = onClic,  //Aqui ejecutamos la funcion del login/registro
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth(),
            shape = CircleShape,
            enabled = inputValid)
        {
            Text(text = textId,
                modifier = Modifier.padding(5.dp))
        }
    }

    private @Composable
    fun PasswordInput(
        passwordState: MutableState<String>,
        passwordVisibility: MutableState<Boolean>,
        labelId: String)
    {
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = {passwordState.value = it},
            label = {Text(text= labelId)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier
                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White
            )

        )
    }

    private @Composable
    fun EmailInput(
        emailState: MutableState<String>,
        labelId: String = "Email")
    {
        InputField(
            valueState = emailState,
            labelId = labelId,
            keyboardType = KeyboardType.Email
        )
    }


    //Elementos que se repiten y reutilizan

    private @Composable
    fun InputField(
        valueState: MutableState<String>,
        labelId: String,
        keyboardType: KeyboardType,
        isSingleLine: Boolean = true
    ) {
        OutlinedTextField(
            value = valueState.value,
            onValueChange = {valueState.value = it},
            label = {Text(text = labelId)},
            textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Normal),
            singleLine = isSingleLine,
            modifier = Modifier
                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White
            )
        )
    }


    @Preview (showBackground = true)
    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    fun LoginPreview(){
        SoundScapeTheme {
            //True = Login ; False = Register
            val showLoginForm = rememberSaveable { mutableStateOf(true) }

            Box(modifier = Modifier
                .fillMaxSize()
                .background(Brush
                    .verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary
                        )
                    )
                )
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    if(showLoginForm.value)
                    {
                        Image(
                            painter = painterResource(id = R.drawable.applogo),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size(300.dp)
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.height(50.dp))

                        UserForm(isCreateAccount = false)
                        {
                                email, password, name, age ->
                            Log.d("Login", "Login: $email, $password, $name, $age")
                        }
                    }
                    else
                    {
                        Text(
                            text  = "Cree una cuenta",
                            color = MaterialTheme.colorScheme.tertiary,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        UserForm(isCreateAccount = true)
                        {
                                email, password, name, age ->
                            Log.d("Registro", "Registro: $email, $password, $name, $age")
                        }
                    }

                    //Seccion de registro
                    Spacer(modifier = Modifier.padding(15.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        val labelLogin = if(showLoginForm.value) "¿No tienes cuenta?"
                        else "¿Ya tienes cuenta?"
                        val labelRegister = if(showLoginForm.value) "Registrate"
                        else "Inicia sesión"

                        Text(text = labelLogin)
                        Text(text = labelRegister,
                            modifier = Modifier
                                .clickable { showLoginForm.value = !showLoginForm.value }
                                .padding(start = 5.dp),
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }

            }

        }
    }

}

