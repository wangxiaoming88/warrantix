package com.warrantix.main.manager;


import android.graphics.Bitmap;
import android.graphics.Rect;

public class RegistrationManager {

    public static final String INVOICE_BITMAP = "invoice.png";
    private static RegistrationManager instance = null;

    private Bitmap invoiceBitmap = null;
    private Rect invoiceRect = null;
    private Rect dealerRect = null;
    private Rect priceRect = null;

    public static RegistrationManager getInstance() {
        if (instance == null)
            instance = new RegistrationManager();

        return instance;
    }

    public void setInvoiceBitmap(Bitmap bm) {
        if (bm == null) {
            invoiceBitmap.recycle();
            invoiceBitmap = null;
        }
        else
            invoiceBitmap = bm;
    }

    public Bitmap getInvoiceBitmap() {
        return invoiceBitmap;
    }

    public void setInvoiceRect(int startX, int startY, int width, int height) {
        invoiceRect = new Rect();
        invoiceRect.set(startX, startY, startX + width, startY + height);
    }

    public Rect getInvoiceRect() {
        if (invoiceRect == null)
            return new Rect(0, 0, 0, 0);
        return invoiceRect;
    }

    public void setDealerRect(int startX, int startY, int width, int height) {
        dealerRect = new Rect();
        dealerRect.set(startX, startY, startX + width, startY + height);
    }

    public Rect getDealerRect() {
        if (dealerRect == null)
            return new Rect(0, 0, 0, 0);
        return dealerRect;
    }

    public void setPriceRect(int startX, int startY, int width, int height) {
        priceRect = new Rect();
        priceRect.set(startX, startY, startX + width, startY + height);
    }

    public Rect getPriceRect() {
        if (priceRect == null)
            return new Rect(0, 0, 0, 0);
        return priceRect;
    }

}

