package com.again.fitbox.fitbox_android.util;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by jeong on 2016. 11. 27..
 */

public class EmoticonHandler implements TextWatcher {

    private final EditText mEditor;
    private final ArrayList<ImageSpan> mEmoticonsToRemove = new ArrayList<ImageSpan>();

    public EmoticonHandler(EditText editor) {
        // Attach the handler to listen for text changes.
        mEditor = editor;
        mEditor.addTextChangedListener(this);
    }

    public void insert(String emoticon, int resource) {
        // Create the ImageSpan
        Drawable drawable = mEditor.getResources().getDrawable(resource);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);

        // Get the selected text.
        int start = mEditor.getSelectionStart();
        int end = mEditor.getSelectionEnd();
        Editable message = mEditor.getEditableText();

        // Insert the emoticon.
        message.replace(start, end, emoticon);
        message.setSpan(span, start, start + emoticon.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    @Override
    public void beforeTextChanged(CharSequence text, int start, int count, int after) {
        // Check if some text will be removed.
        if (count > 0) {
            int end = start + count;
            Editable message = mEditor.getEditableText();
            ImageSpan[] list = message.getSpans(start, end, ImageSpan.class);

            for (ImageSpan span : list) {
                // Get only the emoticons that are inside of the changed
                // region.
                int spanStart = message.getSpanStart(span);
                int spanEnd = message.getSpanEnd(span);
                if ((spanStart < end) && (spanEnd > start)) {
                    // Add to remove list
                    mEmoticonsToRemove.add(span);
                }
            }
        }
    }

    @Override
    public void afterTextChanged(Editable text) {
        Editable message = mEditor.getEditableText();

        // Commit the emoticons to be removed.
        for (ImageSpan span : mEmoticonsToRemove) {
            int start = message.getSpanStart(span);
            int end = message.getSpanEnd(span);

            // Remove the span
            message.removeSpan(span);

            // Remove the remaining emoticon text.
            if (start != end) {
                message.delete(start, end);
            }
        }
        mEmoticonsToRemove.clear();
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int before, int count) {
    }

}
