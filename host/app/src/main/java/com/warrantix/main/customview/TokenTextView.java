package com.warrantix.main.customview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.adapter.TokenListAdapter;

public class TokenTextView extends AppCompatEditText implements TokenListAdapter.OnTokenItemSelectListener, View.OnTouchListener {

	private final String TAG = "TokenTextView";
	private ListView relatedListView = null;
	private TokenListAdapter relatedListAdapter = null;

	public TokenTextView(Context context) {
		super(context);
		init(context);
	}

	public TokenTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public TokenTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	/*TextWatcher, If user type any country name and press comma then following code will regenerate chips */
//	private TextWatcher textWatcher = new TextWatcher() {
//
//		@Override
//		public void onTextChanged(CharSequence s, int start, int before, int count) {
//			if(count >=1){
//				if(s.charAt(start) == ',')
//					setChips(); // generate chips
//			}
//		}
//
//		@Override
//		public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
//
//		@Override
//		public void afterTextChanged(Editable s) {}
//	};


	/* set listeners for item click and text change */
	public void init(Context context){
//		setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
//		addTextChangedListener(textWatcher);
		this.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
		this.setLongClickable(false);
		setOnTouchListener(this);
	}

	public void setTokenListView(ListView listView, TokenListAdapter adapter) {
		this.relatedListView = listView;
		this.relatedListAdapter = adapter;

		if ((listView != null) && (adapter != null)) {
			relatedListView.setAdapter(relatedListAdapter);
			relatedListView.setOnItemClickListener(relatedListAdapter);
			relatedListView.setDividerHeight(0);
			relatedListView.setDivider(null);

			relatedListAdapter.setTokenItemSelectListener(this);
		}
	}

	/*This function has whole logic for chips generate*/
	public void setChips()
	{
		final float SCALE_FACTOR = 1.0f; 	// calculated from textview_token_item's padding and font size
		if(getText().toString().contains(",")) // check comman in string
		{
			final SpannableStringBuilder ssb = new SpannableStringBuilder(getText());
			// split string wich comma
			String chips[] = getText().toString().trim().split(",");
			int x =0;
			// loop will generate ImageSpan for every country name separated by comma
			for(final String c : chips){
				// inflate chips_edittext layout 
				LayoutInflater lf = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
				TextView textView = (TextView) lf.inflate(R.layout.textview_token_item, null);
				textView.setText(c); // set text

				// capture bitmapt of genreated textview
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
				textView.destroyDrawingCache();  // destory drawable

				// create bitmap drawable for imagespan
				BitmapDrawable bmpDrawable = new BitmapDrawable(viewBmp);
				int lineHeight = getLineHeight();

//				bmpDrawable.setBounds(0, 0,bmpDrawable.getIntrinsicWidth(), bmpDrawable.getIntrinsicHeight());
				bmpDrawable.setBounds(0, 0, (int) (bmpDrawable.getIntrinsicWidth() * lineHeight / bmpDrawable.getIntrinsicHeight() * SCALE_FACTOR),
						(int)(lineHeight * SCALE_FACTOR));

				// create and set imagespan 
				ssb.setSpan(new ImageSpan(bmpDrawable),x ,x + c.length() , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				ssb.setSpan(new ClickableSpan() {
					@Override
					public void onClick(View widget) {
						int i = getText().getSpanStart(this);
						int j = getText().getSpanEnd(this);

						if (relatedListAdapter != null) {
							relatedListAdapter.addToken(getText().toString().substring(i, j));
							relatedListAdapter.notifyDataSetChanged();
						}

						ssb.delete(i, j+1);
						setText(ssb);
						setChips();
					}
				}, x ,x + c.length() , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				x = x+ c.length() +1;
			}

			// set chips span 
			setText(ssb);
			setMovementMethod(LinkMovementMethod.getInstance());
		}
	}


	@Override
	public void onTokenItemSelected(String token) {
//		String currentText = getText().toString();
//		if (currentText.equalsIgnoreCase("") == true)
//			currentText = token + ",";
//		else
//			currentText = currentText + token + ",";
//
//		setText(currentText);

		setText(token);
		setChips();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
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

}
