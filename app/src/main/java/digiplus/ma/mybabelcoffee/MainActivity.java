package digiplus.ma.mybabelcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        public void submitOrder(View view){
            CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
            boolean HasWhippedCream = whippedCreamCheckBox.isChecked();
            CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
            boolean HasChocolate = chocolateCheckBox.isChecked();
            int price = calculatePrice(HasWhippedCream, HasChocolate);
            String message = createOrderSummary(price, HasWhippedCream, HasChocolate);
            displayMessage(message);
    }
        public void Decrement(View view) {
            quantity = quantity - 1;
            displayQuantity(quantity);
    }
        public void Increment(View view) {
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
    /** *********************************************** */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate) {

        String priceMessage = ("Name :" + "Z");
        priceMessage += "\n Add whipped Cream ? : " +addWhippedCream;
        priceMessage += "\n Add chocolate ? : " +addChocolate;
        priceMessage += "\n Quantity : " +quantity;
        priceMessage += "\n Total : $" + price;
        priceMessage += "\n Thank you !";
        return priceMessage;
    }
    /** *********************************************** */

    private void displayQuantity(int NumberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + NumberOfCoffees);
    }
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}