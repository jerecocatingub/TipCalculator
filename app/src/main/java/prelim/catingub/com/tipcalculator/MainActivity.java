package prelim.catingub.com.tipcalculator;
 import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText mBill;
    private EditText mPeople;
    private SeekBar mTip;
    private TextView mTipPerPerson;
    private TextView mTotalPerPerson;
    private TextView mTPercentage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBill = (EditText) findViewById(R.id.txtBill);
        mPeople = (EditText) findViewById(R.id.txtPeople);
        mTipPerPerson = (TextView) findViewById(R.id.txtTipPerPerson);
        mTotalPerPerson = (TextView) findViewById(R.id.txtTotalPerPerson);
        mTip = (SeekBar) findViewById(R.id.seekTip);
        mTPercentage = (TextView) findViewById(R.id.txtTipPercentage);
        getTip();

    }

    public void getTip(){
        try {
            mTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int seekVal = mTip.getProgress();
                    String val = Integer.toString(seekVal);
                    mTPercentage.setText(val);
                    getPayment();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });

            mBill.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    getPayment();
                }
            });

            mPeople.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    getPayment();
                }
            });

        }catch(Exception e){
            mTipPerPerson.setText("");
            mTotalPerPerson.setText("");
        }
    }

    public void getPayment(){

        try {
            float tip = Float.parseFloat(mTPercentage.getText().toString());
            float bill = Float.parseFloat(mBill.getText().toString());
            int people = Integer.parseInt(mPeople.getText().toString());
            float tipPerPerson = (bill/people)*(tip/100);
            float totalPerPerson = (bill/people)+tipPerPerson;

            mTipPerPerson.setText("Php"+String.format("%.2f",tipPerPerson));
            mTotalPerPerson.setText("Php"+String.format("%.2f",totalPerPerson));
        }catch(Exception e){
            mTipPerPerson.setText("");
            mTotalPerPerson.setText("");
        }
    }
}

