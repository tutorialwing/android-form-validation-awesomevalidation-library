package com.tutorialwing.formvalidationawesomelibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.common.collect.Range;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private EditText name;

	private EditText email;
	private EditText confirmEmail;

	private EditText password;
	private EditText confirmPassword;

	private EditText phone;
	private EditText age;

	private Button submit;

	private AwesomeValidation awesomeValidation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
//// or
//		awesomeValidation = new AwesomeValidation(COLORATION);
//		awesomeValidation.setColor(Color.YELLOW);  // optional, default color is RED if not set
//// or
//		awesomeValidation = new AwesomeValidation(UNDERLABEL);
//		awesomeValidation.setContext(this);  // mandatory for UNDERLABEL style
//// or
//		AwesomeValidation mAwesomeValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);

		initView();
	}

	private void initView() {
		name = (EditText) findViewById(R.id.etName);
		email = (EditText) findViewById(R.id.etEmail);
		confirmEmail = (EditText) findViewById(R.id.etConfirmEmail);
		password = (EditText) findViewById(R.id.etPassword);
		confirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
		phone = (EditText) findViewById(R.id.etPhone);
		age = (EditText) findViewById(R.id.etAge);

		submit = (Button) findViewById(R.id.submit);
		submit.setOnClickListener(this);

		addValidationToViews();
	}

	private void addValidationToViews() {

		awesomeValidation.addValidation(this, R.id.etName, RegexTemplate.NOT_EMPTY, R.string.invalid_name);

		awesomeValidation.addValidation(this, R.id.etEmail, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
		awesomeValidation.addValidation(this, R.id.etConfirmEmail, R.id.etEmail, R.string.invalid_confirm_email);

		String regexPassword = ".{8,}";
		awesomeValidation.addValidation(this, R.id.etPassword, regexPassword, R.string.invalid_password);
		awesomeValidation.addValidation(this, R.id.etConfirmPassword, R.id.etPassword, R.string.invalid_confirm_password);

		awesomeValidation.addValidation(this, R.id.etPhone, "^[+]?[0-9]{10,13}$", R.string.invalid_phone);
		awesomeValidation.addValidation(this, R.id.etAge, Range.closed(12, 60), R.string.invalid_age);
	}

	private void submitForm() {
		// Validate the form...
		if (awesomeValidation.validate()) {
			// Here, we are sure that form is successfully validated. So, do your stuffs now...
			Toast.makeText(this, "Form Validated Successfully", Toast.LENGTH_LONG).show();
		}
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.submit:
				submitForm();
				break;
		}
	}
}
