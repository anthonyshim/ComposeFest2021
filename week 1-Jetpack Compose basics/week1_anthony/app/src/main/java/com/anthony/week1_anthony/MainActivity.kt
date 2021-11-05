package com.anthony.week1_anthony

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anthony.week1_anthony.ui.theme.Week1_anthonyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week1_anthonyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }

    override fun onBackPressed() {

    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Welcome to the Basics Codelab!")
            Button(modifier = Modifier.padding(vertical = 24.dp), onClick = onContinueClicked) {
                Text("Continue")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Surface(color = MaterialTheme.colors.primary) {
        Row(modifier = Modifier
            .padding(all = 24.dp)
            .animateContentSize(animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMedium))) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Hello,")
                Text(text = name, style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold))
                if (expanded) {
                    Text(text = ("Composem ipsum color sit lazy, padding theme elit, sed do bouncy. ").repeat(4))
                }
            }
            IconButton(onClick = {
                expanded = !expanded
            }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore, contentDescription = if (expanded) stringResource(id = R.string.show_less) else stringResource(id = R.string.show_more)
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun DefaultPreview() {
    Week1_anthonyTheme {
        MyApp()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Preview(showBackground = true, widthDp = 320, heightDp = 480, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GreetingsPreview() {
    Week1_anthonyTheme {
        Greetings()
    }
}

@Composable
private fun MyApp() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding) {
        OnboardingScreen() { shouldShowOnboarding = false }
    } else {
        Greetings()
    }
}

@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(all = 8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun MyApp(names: List<String> = listOf("World", "Compose")) {
    Column(modifier = Modifier.padding(all = 8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        names.forEach { name ->
            Greeting(name = name)
        }
    }
}