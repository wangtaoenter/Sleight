package com.sleightdemos.util;

import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;

/**
 * 
 * @author 
 * @version [V1, 2009-11-2]
 */
public abstract class OwnInputFilter implements InputFilter
{

    private boolean mAppendInvalid; // whether to append or ignore invalid characters

    /**
     * Base constructor for LoginFilter
     * 
     * @param appendInvalid whether or not to append invalid characters.
     */
    OwnInputFilter(boolean appendInvalid)
    {
        mAppendInvalid = appendInvalid;
    }

    /**
     * Default constructor for LoginFilter doesn't append invalid characters.
     */
    OwnInputFilter()
    {
        mAppendInvalid = false;
    }

    /**
     * This method is called when the buffer is going to replace the range
     * <code>dstart &hellip; dend</code> of <code>dest</code> with the new text
     * from the range <code>start &hellip; end</code> of <code>source</code>.
     * Returns the CharSequence that we want placed there instead, including an
     * empty string if appropriate, or <code>null</code> to accept the original
     * replacement. Be careful to not to reject 0-length replacements, as this
     * is what happens when you delete text.
     */
    public CharSequence filter(CharSequence source, int start, int end,
        Spanned dest, int dstart, int dend)
    {
        char[] out = new char[end - start]; // reserve enough space for whole string
        int outidx = 0;
        boolean changed = false;

        onStart();

        // Scan through beginning characters in dest, calling onInvalidCharacter() 
        // for each invalid character.
        for (int i = 0; i < dstart; i++)
        {
            char c = dest.charAt(i);
            if (!isAllowed(c))
                onInvalidCharacter(c);
        }

        // Scan through changed characters rejecting disallowed chars
        for (int i = start; i < end; i++)
        {
            char c = source.charAt(i);
            if (isAllowed(c))
            {
                // Character allowed. Add it to the sequence.
                out[outidx++] = c;
            }
            else
            {
                if (mAppendInvalid)
                    out[outidx++] = c;
                else
                    changed = true; // we changed the original string
                onInvalidCharacter(c);
            }
        }

        // Scan through remaining characters in dest, calling onInvalidCharacter() 
        // for each invalid character.
        for (int i = dend; i < dest.length(); i++)
        {
            char c = dest.charAt(i);
            if (!isAllowed(c))
                onInvalidCharacter(c);
        }

        onStop();

        if (!changed)
        {
            return null;
        }

        String s = new String(out, 0, outidx);

        if (source instanceof Spanned)
        {
            SpannableString sp = new SpannableString(s);
            TextUtils.copySpansFrom((Spanned) source, start, end, null, sp, 0);
            return sp;
        }
        else
        {
            return s;
        }
    }

    /**
     * Called when we start processing filter.
     */
    public void onStart()
    {

    }

    /**
     * Called whenever we encounter an invalid character.
     * 
     * @param c the invalid character
     */
    public void onInvalidCharacter(char c)
    {

    }

    /**
     * Called when we're done processing filter
     */
    public void onStop()
    {

    }

    /**
     * Returns whether or not we allow character c. Subclasses must override
     * this method.
     */
    public abstract boolean isAllowed(char c);

    public static class EnterFilter extends OwnInputFilter
    {

        @Override
        public boolean isAllowed(char c)
        {
            if ('\n' == c)
            {
                return false;
            }
            return true;
        }

    }

}
