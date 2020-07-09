package ultils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class FontAwesome extends TextView {
    public FontAwesome(Context context, AttributeSet attributeSet, int defStyle){
        super(context,attributeSet,defStyle);
        init();
    }
    public FontAwesome(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        init();
    }
    public FontAwesome(Context context){
        super(context);
        init();
    }
    private void init(){
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"fonts/FontAwesome.ttf");
        setTypeface(tf);
    }
}
