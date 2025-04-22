package com.example.bmicalculatorplus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bmicalculatorplus.databinding.ActivityMainBinding;

/**
 * Główna aktywność aplikacji BMI Calculator Plus.
 * Odpowiada za inicjalizację splash screena, wiązanie layoutu oraz konfigurację nawigacji dolnej.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Metoda wywoływana podczas tworzenia aktywności.
     * Ustawia splash screen, ładuje layout oraz konfiguruje nawigację między fragmentami.
     *
     * @param savedInstanceState Stan zapisany aplikacji, jeśli istnieje (np. po rotacji ekranu).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);

       ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}