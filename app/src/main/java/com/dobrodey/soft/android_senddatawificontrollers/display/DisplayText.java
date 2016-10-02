package com.dobrodey.soft.android_senddatawificontrollers.display;

import android.util.Log;
import android.widget.Toast;

import com.dobrodey.soft.android_senddatawificontrollers.model.Symbol;
import com.dobrodey.soft.android_senddatawificontrollers.model.TextAreaEntity;
import com.dobrodey.soft.android_senddatawificontrollers.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12.08.2016.
 */
public abstract  class DisplayText implements Serializable{
    private static final long serialVersionUID = 777L;
    public abstract String encryption(String input, String fontColor);

    public  String display(TextAreaEntity txtArea, String text, String hexString) {
        if(text != null) {
            String resultHexStr = getEncryptionArea(txtArea, text);
            hexString = Utils.writeDataToPacket(txtArea.getPositionInitBytes(), resultHexStr, hexString);
        }
        return hexString;
    }

    public String getEncryptionArea(TextAreaEntity txtArea, String text) {
        text = text.replaceAll(" ", "");
        List<Symbol> symbolArray = new ArrayList<Symbol>();

        for (int i = 0; i < text.length(); i++) {
            symbolArray.add(new Symbol(text.charAt(i), txtArea.getFont(), txtArea.getCharSpacing(), txtArea.getStartLineSpacing()));
        }

        String binaryCode = toBinaryString(symbolArray, txtArea.getWidth(), txtArea.getHeight());

        if (txtArea.isVerticalOrientation()) {
            binaryCode = toVerticalOrientation(binaryCode, txtArea.getHeight());
        }
        return encryption(binaryCode, txtArea.getColorFont());
    }


    private static String toVerticalOrientation(String hexString, int height) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < hexString.length() / height; i++) {
            for(int j = height - 1; j >= 0; j--) {
                int index = j * (hexString.length() / height) + i;
                result.append(hexString.substring(index, index + 1));
            }
        }
        return result.toString();
    }

    public  static String toBinaryString(List<Symbol> symbolMassive, Integer segmentWidth, Integer segmentHeight) {
		//sorry
    }
}
