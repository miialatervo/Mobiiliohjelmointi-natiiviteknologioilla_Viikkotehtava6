import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldExampleTheme {
                ScaffoldApp()
            }
        }
    }
}

@Composable
fun MyTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                DropdownMenuItem(onClick = { navController.navigate("Info") }) {
                    Text("Info")
                }
                DropdownMenuItem(onClick = { navController.navigate("Settings") }) {
                    Text("Settings")
                }
            }
        }
    )
}

@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { MyTopBar("My App", navController) },
        content = { Text(text = "Content for Home screen") }
    )
}

@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Info", navController) },
        content = { Text(text = "Content for Info screen") }
    )
}

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Settings", navController) },
        content = { Text(text = "Content for Settings screen") }
    )
}

@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable(route = "Home") {
            MainScreen(navController)
        }
        composable(route = "Info") {
            InfoScreen(navController)
        }
        composable(route = "Settings") {
            SettingsScreen(navController)
        }
    }
}
