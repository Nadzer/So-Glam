package it.moondroid.coverflowdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import it.moondroid.coverflowdemo.R;


public class ContactActivity extends AppCompatActivity {
    private EditText name;
    private EditText subject;
    private Button button;
    private TextView intro;
    private EditText email;
    private EditText phone;
    private EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getUI();
        setUI();
    }

    private void setUI() {
        intro.setText(Html.fromHtml(getString(R.string.contact_intro)));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkForm()) {
                    sendMessage();
                }
            }
        });
    }

    private void sendMessage() {
        String obj = subject.getText().toString();
        String mess = message.getText().toString();
        String cell = phone.getText().toString();
        String contact = name.getText().toString();

        Intent iEmail = new Intent(Intent.ACTION_SEND);
        iEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{ "new-ssk@hotmail.fr"});
        iEmail.putExtra(Intent.EXTRA_SUBJECT, "So Glam Creation - " + obj);
        iEmail.putExtra(Intent.EXTRA_TEXT, mess);
        iEmail.putExtra(Intent.EXTRA_PHONE_NUMBER, cell);

        //need this to prompts email client only
        iEmail.setType("message/utf-8");

        startActivity(Intent.createChooser(iEmail, "Choisissez une messagerie :"));
    }

    private boolean checkForm() {
        if (name.getText().toString().length() <= 0) {
            name.setError("Veuillez indiquer votre nom");

            return false;
        } if (email.getText().toString().length() <= 0
                && phone.getText().toString().length() <= 0) {
            email.setError("Veuillez indiquer une adresse mail ou un numéro de téléphone");

            return false;
        } if (message.getText().toString().trim().length() <= 0) {
            message.setError("Veuillez indiquer votre nom");

            return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void getUI() {
        intro = (TextView) findViewById(R.id.contact_intro);
        name = (EditText) findViewById(R.id.contact_name);
        subject = (EditText) findViewById(R.id.contact_subject);
        email = (EditText) findViewById(R.id.contact_email);
        phone = (EditText) findViewById(R.id.contact_phone);
        message = (EditText) findViewById(R.id.contact_message);
        button = (Button) findViewById(R.id.contact_button);
    }
}
