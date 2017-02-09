package com.warrantix.main.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.warrantix.main.R;

public class MessageDialog extends Dialog {

    private OkButtonListener okButtonListener = null;

    private String mMessage = "";
    private String mTitle = "";

    public MessageDialog(Activity a) {
        super(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_message);

        initialize();
    }

    private void initialize() {
        TextView lblTitle = (TextView) findViewById(R.id.lblTitleInMessageDialog);
        lblTitle.setText(mTitle);

        TextView lblMessage = (TextView) findViewById(R.id.lblMessageInMessageDialog);
        lblMessage.setText(mMessage);

        if (mMessage.equalsIgnoreCase("") == true)
            lblMessage.setVisibility(View.GONE);

        Button btnOk = (Button) findViewById(R.id.btnOkInMessageDialog);
        btnOk.setOnClickListener(btnOkClickListener);

        setCanceledOnTouchOutside(false);
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setOkButtonListener(OkButtonListener listener) {
        okButtonListener = listener;
    }

    private final View.OnClickListener btnOkClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (okButtonListener != null)
                okButtonListener.callback();

            dismiss();
        }
    };

    public interface OkButtonListener {
        // you can define any parameter as per your requirement
        public void callback();
    }
}