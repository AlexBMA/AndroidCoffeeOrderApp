package com.example.alexandru.justjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int price = 5;
    boolean hasWhippedCream;
    boolean hasChocolate;
    String name;
    int priceWhippedCream = 1;
    int priceChocolate = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_whipped_cream);
        hasWhippedCream = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.checkbox_chocolate);
        hasChocolate = checkBox.isChecked();
        EditText editText = (EditText) findViewById(R.id.edit_text_name);
        name = editText.getText().toString();

        String message = createOrderSummary();
        displayMessage(message);

    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice() {

        if (hasChocolate && hasWhippedCream)
            return quantity * (price + priceChocolate + priceWhippedCream);
        if (hasChocolate) return quantity * (price + priceChocolate);
        if (hasWhippedCream) return quantity * (price + priceWhippedCream);

        return quantity * price;
    }

    /**
     * Creates a summary for the order
     *
     * @return text summary
     */

    private String createOrderSummary() {


        String message = "Name: " + name +
                "\nAdd whipped cream? " + hasWhippedCream +
                "\nAdd whipped cream? " + hasChocolate +
                "\nQuantity: " + quantity +
                "\nTotal: " + calculatePrice() +
                "$" + "\nThank you";

        return message;
    }

    /**
     * This is the increment method
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity++;
        } else {
            Context context = getApplicationContext();
            CharSequence text = "You cannot have more than 100 coffees";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        display(quantity);

    }

    /**
     * This is the decrement method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity--;
        } else {
            Context context = getApplicationContext();
            CharSequence text = "You cannot have less than 1 coffee";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        display(quantity);

    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(number + "");
    }


    /**
     * This method displays a message on the screen
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}
