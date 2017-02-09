package com.warrantix.main.activities;

import android.os.Bundle;
import com.joanzapata.pdfview.PDFView;
import com.warrantix.main.R;

public class PDFViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        initialize();
    }

    private void initialize() {
        String pdfPath = getIntent().getStringExtra("path");

        PDFView pdfView = (PDFView) findViewById(R.id.pdfview);
        pdfView.fromAsset("temp.pdf")
                .defaultPage(1)
                .swipeVertical(true)
//                .showMinimap(false)
//                .enableSwipe(true)
//                .onDraw(onDrawListener)
//                .onLoad(onLoadCompleteListener)
//                .onPageChange(onPageChangeListener)
                .load();
    }

}