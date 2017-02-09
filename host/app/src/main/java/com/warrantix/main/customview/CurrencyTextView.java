package com.warrantix.main.customview;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.warrantix.main.R;

import java.text.DecimalFormat;
import java.text.ParseException;

public class CurrencyTextView extends AppCompatEditText {

	private final String TAG = "TokenTextView";
	private Drawable leftDrawable = null;

	public CurrencyTextView(Context context) {
		super(context);
		init(context);
	}

	public CurrencyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CurrencyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	/* set listeners for item click and text change */
	public void init(Context context){
		addTextChangedListener(new NumberTextWatcher(CurrencyTextView.this));
		this.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
		this.setLongClickable(false);
	}

	/*This function has whole logic for chips generate*/
	public void createLeftDrawable(String currencyType)
	{
		final float SCALE_FACTOR = convertDpToPixels(1.0f, getContext()); 	// calculated from textview_token_item's padding and font size

		// loop will generate ImageSpan for every country name separated by comma
		// inflate chips_edittext layout
		LayoutInflater lf = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		TextView textView = (TextView) lf.inflate(R.layout.textview_currency_item, null);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				getResources().getDimension(R.dimen.normal_font_height) * SCALE_FACTOR);
		textView.setText(currencyType); // set text

		// capture bitmap of generate textview
		int spec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		textView.measure(spec, spec);
		textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());

		Bitmap b = Bitmap.createBitmap(textView.getWidth(), textView.getHeight(),Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(b);
		canvas.translate(-textView.getScrollX(), -textView.getScrollY());
		textView.draw(canvas);
		textView.setDrawingCacheEnabled(true);

		Bitmap cacheBmp = textView.getDrawingCache();
		Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
		setCompoundDrawablesWithIntrinsicBounds((Drawable)new BitmapDrawable(viewBmp), null, null, null);
	}

	public static float convertDpToPixels(float dp, Context context){
		Resources resources = context.getResources();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dp, resources.getDisplayMetrics()
		);
	}

	private class ActionModeCallbackInterceptor implements android.view.ActionMode.Callback {
		@Override
		public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
			return false;
		}

		@Override
		public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
			return false;
		}

		@Override
		public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
			return false;
		}

		@Override
		public void onDestroyActionMode(android.view.ActionMode mode) {

		}
	}

	class NumberTextWatcher implements TextWatcher {

		private DecimalFormat df;
		private DecimalFormat dfnd;
		private boolean hasFractionalPart;

		private AppCompatEditText et;

		public NumberTextWatcher(AppCompatEditText et)
		{
			df = new DecimalFormat("#,###.##");
			df.setDecimalSeparatorAlwaysShown(true);
			dfnd = new DecimalFormat("#,###");
			this.et = et;
			hasFractionalPart = false;
		}

		@SuppressWarnings("unused")
		private static final String TAG = "NumberTextWatcher";

		@Override
		public void afterTextChanged(Editable s)
		{
			et.removeTextChangedListener(this);

			try {
				int inilen, endlen;
				inilen = et.getText().length();

				String v = s.toString().replace(String.valueOf(df.getDecimalFormatSymbols().getGroupingSeparator()), "");
				Number n = df.parse(v);
				int cp = et.getSelectionStart();
				if (hasFractionalPart) {
					et.setText(df.format(n));
				} else {
					et.setText(dfnd.format(n));
				}
				endlen = et.getText().length();
				int sel = (cp + (endlen - inilen));
				if (sel > 0 && sel <= et.getText().length()) {
					et.setSelection(sel);
				} else {
					// place cursor at the end?
					et.setSelection(et.getText().length() - 1);
				}
			} catch (NumberFormatException nfe) {
				// do nothing?
			} catch (ParseException e) {
				// do nothing?
			}

			et.addTextChangedListener(this);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
			if (s.toString().contains(String.valueOf(df.getDecimalFormatSymbols().getDecimalSeparator())))
			{
				hasFractionalPart = true;
			} else {
				hasFractionalPart = false;
			}
		}

	}
}
