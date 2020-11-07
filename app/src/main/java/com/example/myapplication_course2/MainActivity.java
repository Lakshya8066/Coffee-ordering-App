
/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.myapplication_course2;



import java.text.NumberFormat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int numberOfCoffee = 0;
    public void increment(View view)
    {
        if(numberOfCoffee<=100)
            numberOfCoffee++;
        display(numberOfCoffee);
    }
    public void decrement(View view)
    {
        if(numberOfCoffee>0)
            numberOfCoffee--;
        display(numberOfCoffee);
    }
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolatecheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        EditText namefield = (EditText) findViewById(R.id.name_edittext);
        String name = namefield.getText().toString();
        //Log.v("MainActivity", "name is "+name);
        //you can also use name.getText().toString to convert it to string.
        String priceMessage = createOrderSummary(5, numberOfCoffee,whippedCreamCheckBox.isChecked(),chocolatecheckbox.isChecked(),name);
        //displayMessage(priceMessage);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));//only for email apps
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage );
        if(intent.resolveActivity((getPackageManager()))!=null)
            startActivity(intent);

        //This is a segment to practice intents by calling google maps
        // displayPrice(numberOfCoffee*5);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6, -122.3"));
//        if(intent.resolveActivity(getPackageManager())!=null)
//            startActivity(intent);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
     */
    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
    private String createOrderSummary( int price, int quantity, boolean wcreamcheckbox, boolean chocolatecheckvalue, String name)
    {
        if(wcreamcheckbox)
            price++;
        if(chocolatecheckvalue)
            price+=2;
        String printmessage = "Hi "+name+"!";
        printmessage+="\n" +(wcreamcheckbox?"Whipped Cream added!":"No whipped cream");
        printmessage+="\n"+ (chocolatecheckvalue?"Chocolate added!":"No chocolate");
        printmessage+="\nQuantity: "+quantity;
        printmessage+="\nTotal: "+(quantity*price);
        printmessage+="\nThank You.";
        return printmessage;
    }
}