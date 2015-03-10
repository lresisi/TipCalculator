/**
 * A tip calculator
 */

package il.ac.huji.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculatorActivity extends ActionBarActivity {

    /* =================================================================================== */

    /**
     * The default tip rate
     */
    private static final double TIP_RATE = 0.12;

    /* =================================================================================== */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        // Set the listener of the button
        Button btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(clickListener);
    }

    /* =================================================================================== */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tip_calculator, menu);
        return true;
    }

    /* =================================================================================== */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* =================================================================================== */

    /**
     * Returns the bill amount or 0.0 if edtBillAmount is not a valid number
     * @param edtBillAmount the bill amount
     * @return the bill amount, or 0.0 if edtBillAmount is not a valid number
     */
    private double getBillAmount(EditText edtBillAmount) {
        try {
            return Double.parseDouble(edtBillAmount.getText().toString());
        }
        catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /* =================================================================================== */

    /**
     * Calculates the tip according to the bill
     * @param billAmount the bill
     * @return the tip that corresponds to the bill
     */
    private double calculateTip(Double billAmount) {
        CheckBox chkRound = (CheckBox) findViewById(R.id.chkRound);
        double tip = billAmount * TIP_RATE;

        // Check if the tip should be rounded
        if (chkRound.isChecked()) {
            tip = Math.round(tip);
        }

        return tip;
    }

    /* =================================================================================== */

    /**
     * Writes the calculated tip on the screen
     * @param tip the calculated tip
     */
    private void updateTipText(double tip) {
        TextView txtTipResult  = (TextView) findViewById(R.id.txtTipResult);
        String tipAsString = "Tip: $" + String.format((tip == Math.round(tip))? "%.0f" : "%.2f", tip);
        txtTipResult.setText(tipAsString);
    }

    /* =================================================================================== */

    /**
     * Calculates the tip according to the bill and writes it on the screen
     */
    private void calculateAndUpdateTip() {
        EditText edtBillAmount = (EditText) findViewById(R.id.edtBillAmount);
        Double billAmount = getBillAmount(edtBillAmount);
        Double tip = calculateTip(billAmount);
        updateTipText(tip);
    }

    /* =================================================================================== */

    /**
     * Listener for the button
     */
    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            calculateAndUpdateTip();
        }
    };
}