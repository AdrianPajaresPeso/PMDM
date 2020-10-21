package android.example.jesuitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int numberOfProducts = 0;
    private TextView mShowCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.numeroQuantity);
    }


    public void makeOrder(View view) {

        Intent intent = new Intent(this, Activity2.class);
        CheckBox nata  = findViewById(R.id.whippedCream);
        CheckBox chocolate  = findViewById(R.id.chocolate);
        TextView numeroCafes = (TextView) findViewById(R.id.numeroQuantity);
        ;
        double price = (double) Integer.parseInt(String.valueOf(numeroCafes.getText()))*1.50;

        if(nata.isChecked()){
            price = price + 0.5;
        }
        if(chocolate.isChecked()){
            price = price + 0.5;
        }
        

        //intent.putExtra("")
        startActivity(intent);
    }

    public void countUp(View view) {
        ++numberOfProducts;
        if ( mShowCount!= null) {
            mShowCount.setText(Integer.toString(numberOfProducts));
        }
    }
    public void countDown(View view) {
        ++numberOfProducts;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(numberOfProducts));
        }
    }
}