package android.example.jesuitas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

<<<<<<< HEAD:app/src/main/java/android/example/hellotoast/MainActivity.java

=======
    public void likeItMsg(View view) {
        Toast like = Toast.makeText(this, R.string.msgLikeIt, Toast.LENGTH_LONG );
        like.show();
    }
>>>>>>> Release:app/src/main/java/android/example/jesuitas/MainActivity.java
}