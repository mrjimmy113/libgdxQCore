package com.quang.core.element;

import com.quang.core.assets.QI18NAsset;
import com.quang.core.event.E_I18NChange;

public class QLabelI18N extends QLabel {

    String key;
    boolean isUpperCase = false;
    boolean isDisable = false;

    String value1 = "";
    String value2 = "";

    public QLabelI18N(final String key) {
        super("I18N");
        this.key = key;
        setText();
        setup();
    }

    public QLabelI18N(final String key, final boolean isUpperCase) {
        super("I18N");
        this.isUpperCase = isUpperCase;
        this.key = key;
        setup();
    }

    public void setKey(String key) {
        this.key = key;
        setText();
    }

    public void setDisable(boolean disable) {
        isDisable = disable;
    }

    private void setup() {
        setText();
        E_I18NChange.e.addListener(new E_I18NChange.II18NChange() {
            @Override
            public void invoke() {
                setText();
            }
        });
    }

    private void setText() {
        if(isDisable) return;
        if(key == null) return;
        if(key.isEmpty()) return;

        String message = QI18NAsset.get().format(key,value1,value2);
        if(isUpperCase) message = message.toUpperCase();
        setText(message);
    }

    public String getCurI18NValue() {
        if(key == null) return "";
        if(key.isEmpty()) return "";
        return QI18NAsset.get().get(key);
    }

    public void setValue1(String value1) {
        this.value1 = value1;
        setText();
    }

    public void setValue2(String value2) {
        this.value2 = value2;
        setText();
    }
}
