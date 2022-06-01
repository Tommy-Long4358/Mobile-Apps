package com.example.tipcalculatorv1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private TipCalculator tipCalc;
    public NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // TipCalculator object
        tipCalc = new TipCalculator( 0.17f, 100.0f );

        // Sets the layout of a screen based on the XML of activity_main.XML
        setContentView(R.layout.activity_main);

        // Finds the ID of a view
        billEditText = (EditText) findViewById(R.id.amount_bill);
        tipEditText = (EditText) findViewById((R.id.amount_tip_percent));

        TextChangeHandler tch = new TextChangeHandler();

        // Registers object on textView
        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);

    }
    public void calculate()
    {
        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();

        // Gets the ID of a view
        TextView tipTextView = (TextView) findViewById(R.id.amount_tip);
        TextView totalTextView = (TextView) findViewById((R.id.amount_total));

        try
        {
            // convert billString and tipString to floats
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);

            // update the Model
            tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);

            // ask Model to calculate tip and total amounts
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();

            // update the View with formatted tip and total amounts
            // Puts a $ sign
            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));
        }
        catch (NumberFormatException nfe)
        {
            // pop up an alert view here (optional)
        }
    }

    private class TextChangeHandler implements TextWatcher
    {
        // Somewhere within e, the text has changed
        public void afterTextChanged(Editable e)
        {
            calculate();
        }

        // Within charsequence, the count characters beginning at start
        // are about to replaced with after characters
        public void beforeTextChanged( CharSequence s, int start,
                                       int count, int after ) {
        }

        // Within charsequence, the count characters beginning at start
        // have just replaced before characters
        public void onTextChanged( CharSequence s, int start,
                                   int before, int after ) {
        }
    }
}