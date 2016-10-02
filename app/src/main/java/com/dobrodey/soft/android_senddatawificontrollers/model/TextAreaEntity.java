package com.dobrodey.soft.android_senddatawificontrollers.model;

import com.dobrodey.soft.android_senddatawificontrollers.display.DisplayDualColorText;
import com.dobrodey.soft.android_senddatawificontrollers.display.DisplayFullColorText;
import com.dobrodey.soft.android_senddatawificontrollers.display.DisplaySingleColorText;
import com.dobrodey.soft.android_senddatawificontrollers.display.DisplayText;

import java.io.Serializable;

/**
 * Created by User on 04.08.2016.
 */
public class TextAreaEntity extends AreaEntity implements Serializable{
    private static final long serialVersionUID = 777L;
    private String mValue;
    private String mFont;
    private String mColorFont;

    private int mCharSpacing;
    private int mAlignSpacing;
    private int mStartLineSpacing;

    private DisplayText displayText;

    private TextAreaEntity() {
        super(0, 0, "horizontal",null);
        mValue = "";
    }

    public TextAreaEntity(int width, int height, String font, String colorFont, String orientation, int charSpacing, int alignSpacing, int startLineSpacing, String colorType) {
        super(width, height, orientation, null);
        this.mFont = font;
        this.mColorFont = colorFont;

        this.mCharSpacing = charSpacing;
        this.mAlignSpacing = alignSpacing;
        this.mStartLineSpacing = startLineSpacing;
        mValue = "";
        setColorType(colorType);
    }

    private void setColorType(String colorType) {
        switch (colorType) {
            case "single": {
                displayText = new DisplaySingleColorText();
                break;
            }
            case "dual": {
                displayText = new DisplayDualColorText();
                break;
            }
            case "full": {
                displayText = new DisplayFullColorText();
                break;
            }
        }
    }

    @Override
    public String display(String hexStr) {
        return displayText.display(this, mValue, hexStr);
    }

    public String display(String hexStr, String value) {
        return displayText.display(this, value, hexStr);
    }

    public String getEncryptionDate() {
        return displayText.getEncryptionArea(this, mValue);
    }


    public String getValue() {
        return mValue;
    }

    public String getFont() {
        return mFont;
    }

    public void setFont(String mFont) {
        this.mFont = mFont;
    }

    public String getColorFont() {
        return mColorFont;
    }

    public void setColorFont(String mColorFont) {
        this.mColorFont = mColorFont;
    }

    public int getCharSpacing() {
        return mCharSpacing;
    }

    public void setCharSpacing(int mCharSpacing) {
        this.mCharSpacing = mCharSpacing;
    }

    public int getAlignSpacing() {
        return mAlignSpacing;
    }

    public void setAlignSpacing(int alignSpacing) {
        this.mAlignSpacing = alignSpacing;
    }

    public int getStartLineSpacing() {
        return mStartLineSpacing;
    }

    public void setStartLineSpacing(int startLineSpacing) {
        this.mStartLineSpacing = startLineSpacing;
    }

    @Override
    public String toString() {
        return super.toString() + " font=" + mFont + ", color=" + mColorFont +
                ", charSpacing=" + mCharSpacing + ", alignSpacing=" + mAlignSpacing + ", startLineSpacing=" + mStartLineSpacing;
    }

    public void setValue(String value) {
        this.mValue = value;
    }
}
