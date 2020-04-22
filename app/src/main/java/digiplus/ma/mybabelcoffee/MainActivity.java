package digiplus.ma.mybabelcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        EditText NameField = (EditText) findViewById(R.id.NameField);
        String NameValue = NameField.getText().toString();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean HasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean HasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice(HasWhippedCream, HasChocolate);
        String message = createOrderSummary(NameValue, price, HasWhippedCream, HasChocolate);

        /** *********************************************** */
              Intent intent = new Intent(Intent.ACTION_SENDTO);
              intent.setData(Uri.parse("mailto:")); // only email apps should handle this
              intent.putExtra(Intent.EXTRA_SUBJECT, "Commande de : " +NameValue);
              intent.putExtra(Intent.EXTRA_TEXT, message);
              if (intent.resolveActivity(getPackageManager()) != null) {
              startActivity(intent);
               }

        /** *********************************************** */
        /**
         *         String url = "https://api.whatsapp.com/send?phone="+2126;
         *         Intent i = new Intent(Intent.ACTION_VIEW);
         *         i.putExtra("sms_body", message);
         *         i.setData(Uri.parse(url));
         *         startActivity(i);
         */

        displayMessage(message);
    }
    public void Decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }
    public void Increment(View view) {
        if (quantity == 4) {
            Toast.makeText(this, "You cannot have more than 4 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }
        if (addChocolate) {
            basePrice = basePrice + 2;
        }
        return quantity * basePrice;
    }


    private void displayQuantity(int NumberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + NumberOfCoffees);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * **********************************************
     */
    private String createOrderSummary(String NameValue, int price, boolean addWhippedCream, boolean addChocolate) {

        String priceMessage = getString(R.string.Order_summary_name, NameValue);
        priceMessage += "\n" + getString(R.string.Toppings);
        priceMessage += "\n" + getString(R.string.Order_summary_whipped_cream, addWhippedCream);
        priceMessage += "\n" + getString(R.string.Order_summary_chocolate, addChocolate);
        priceMessage += "\n" + getString(R.string.Order_summary_quantity, quantity);
        priceMessage += "\n "+ getString(R.string.Order_summary_price, NumberFormat.getCurrencyInstance().format(price));
        priceMessage += "\n" + getString(R.string.Thank_you);
        return priceMessage;
    }
    /** *********************************************** */
}