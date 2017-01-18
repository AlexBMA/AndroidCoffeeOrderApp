package com.example.alexandru.justjava;

import android.content.Context;
import android.content.Intent;
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

        composeEmail(new String[1], "", "");

//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6, -122.3"));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            Log.i("msg","here in if");
//            startActivity(intent);
//        }


    }

    /**
     * @param addresses the email addresses to send
     * @param subject   the subject of the email
     * @param text      the text of the mail
     */

    public void composeEmail(String[] addresses, String subject, String text) {

        addresses[0] = "burghelea.alex@gmail.com";
        subject = "Coffee order ";
        text = createOrderSummary();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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
            //get the context
            Context context = getApplicationContext();
            // set the text to display
            CharSequence text = "You cannot have more than 100 coffees";
            // the length of time will stay on screen
            int duration = Toast.LENGTH_SHORT;
            // creating a toast
            Toast toast = Toast.makeText(context, text, duration);
            // making the toast display
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
            //get the context
            Context context = getApplicationContext();
            // set the text to display
            CharSequence text = "You cannot have less than 1 coffee";
            // the length of time will stay on screen
            int duration = Toast.LENGTH_SHORT;
            // creating a toast
            Toast toast = Toast.makeText(context, text, duration);
            // making the toast display
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
