package com.hyalurion.exampleproject

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hyalurion.exampleproject.data.LanguageManager
import com.hyalurion.exampleproject.ui.ExampleApp
import com.hyalurion.exampleproject.ui.theme.ExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Load saved language setting (null means follow system language)
        val languageManager = LanguageManager(this)
        val savedLanguage = languageManager.getSavedLanguage()
        languageManager.applyLanguage(savedLanguage)
        
        setContent {
            ExampleApp()
        }
    }
    
    // Handle configuration changes to ensure language setting is applied
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        
        // Reapply language setting
        val languageManager = LanguageManager(this)
        val savedLanguage = languageManager.getSavedLanguage()
        languageManager.applyLanguage(savedLanguage)
    }
}

@Preview(showBackground = true)
@Composable
fun ExampleAppPreview() {
    ExampleTheme {
        ExampleApp()
    }
}