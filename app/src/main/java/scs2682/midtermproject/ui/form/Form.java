package scs2682.midtermproject.ui.form;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import scs2682.midtermproject.AppActivity;
import scs2682.midtermproject.R;
import scs2682.midtermproject.data.Contact;

public class Form extends LinearLayout{
    private TextView firstName;
    private TextView lastName;
    private Spinner gender;

    private AppActivity.Adapter adapter;
    private int positionInContacts = -1;

    public Form(Context context) {
        this(context, null, 0);
    }

    public Form(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Form(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(AppActivity.Adapter adapter) {
        this.adapter = adapter;
    }

    public void updateContact(Contact contact, int positionInContacts) {
        this.positionInContacts = positionInContacts;

        if (contact != null) {
            // updating a contact
            firstName.setText(contact.firstName);
            lastName.setText(contact.lastName);
            gender.setSelection(contact.gender);
        }

        firstName.requestFocus();

        // open the keyboard
        InputMethodManager inputMethodManager
                = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(firstName, 0);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        firstName = (TextView) findViewById(R.id.firstName);
        lastName = (TextView) findViewById(R.id.lastName);
        gender = (Spinner) findViewById(R.id.gender);

        //Cancel Click
        findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.onContactUpdated(null, -1);
                firstName.setText("");
                lastName.setText("");
                gender.setSelection(0);
            }
        });

        //Update Click
        findViewById(R.id.update).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstNameValue = firstName.getText().toString();
                String lastNameValue = lastName.getText().toString();
                Integer genderValue = gender.getSelectedItemPosition();

                //Validation
                if (TextUtils.isEmpty(firstNameValue) || TextUtils.isEmpty(lastNameValue)) {
                    Toast.makeText(getContext(), "First and Last Name is mandatory", Toast.LENGTH_SHORT).show();
                }
                else {
                    Contact contact = new Contact(firstNameValue, lastNameValue, genderValue);
                    adapter.onContactUpdated(contact, positionInContacts);
                    firstName.setText("");
                    lastName.setText("");
                    gender.setSelection(0);
                }
            }
        });

        //Spinner Touch
        /*gender.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //hide keyboard
                InputMethodManager inputMethodManager
                        = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(gender.getWindowToken(), 0);
                return false;
            }
        });*/
    }
}
