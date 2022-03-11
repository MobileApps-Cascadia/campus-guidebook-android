package edu.cascadia.mobas.campusguidebook;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.content.Intent;

import java.util.concurrent.TimeUnit;

import edu.cascadia.mobas.campusguidebook.application.AppConfig;
import edu.cascadia.mobas.campusguidebook.application.AppInit;

@RequiresApi(api = Build.VERSION_CODES.O)
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // TODO: Consider executing behind MainActivity instead of splash per recommended practices
        // Schedule in intent to navigate to main after the given time delay
        ((CampusGuidebookApp) getApplication())
                .getAppExecutors()
                .scheduled()
                .schedule( () -> {
                    // use an intent to navigate directly to main activity
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();  // end this activity
                }, AppConfig.SPLASH_DISPLAY_TIME, TimeUnit.MILLISECONDS);

        // Run all app initialization tasks
        (new AppInit((CampusGuidebookApp)this.getApplication())).initialize();
    }
}