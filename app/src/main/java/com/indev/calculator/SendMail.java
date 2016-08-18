package com.indev.calculator;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.indev.calculator.activity.LoginActivity;
import com.indev.calculator.config.AccountConfig;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Class is extending AsyncTask because it is going to perform a networking operation
 */
public class SendMail extends AsyncTask<Void, Void, Void> {

    /* Declaring Variables*/
    private Context mContext;
    private Session session;

    /* Information to send email */
    private String email;
    private String subject;
    private String message;

    /* Progress dialog to show while sending message */
    private ProgressDialog progressDialog;

    private final String DIALOG_TITLE = "Sending message";
    private final String DIALOG_MESSAGE = "Please wait...";

    public SendMail(Context context, String email, String subject, String message) {
        this.mContext = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(mContext, DIALOG_TITLE, DIALOG_MESSAGE);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        Toast.makeText(mContext, R.string.sent_message, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);
    }

    @Override
    protected Void doInBackground(Void... params) {
        Properties properties = new Properties();
        // Configuring properties for gmail
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(AccountConfig.EMAIL, AccountConfig.PASSWORD);
                    }
                });

        try {
            MimeMessage mm = new MimeMessage(session);
            mm.setFrom(new InternetAddress(AccountConfig.EMAIL));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mm.setSubject(subject);
            mm.setText(message);
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
