package br.java.a01_cpfauth.mascara;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class Mascara {
    public static String CPF_MASC = "###.###.###.##";
    public static String CELULAR_MASC = "(##) #### #####";
    public static String CEP_MASC = "#####-###";

    public static String semMasc(String s) {

        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "").replaceAll(" ", "")
                .replaceAll(",", "");
    }

    public static boolean eUmSinal(char c) {
        if (c == '.' || c == '-' || c == '/' || c == '(' || c == ')' || c == ',' || c == ' ') {
            return true;
        } else {
            return false;
        }
    }

    public static String masc(String masc, String text) {
        int i = 0;
        String mascara = "";

        for(char m : masc.toCharArray()) {
            if (m != '#') {
                mascara += m;

                continue;
            }

            try {
                mascara += text.charAt(i);
            } catch (Exception e) {
                break;
            }
            i++;
        }
        return mascara;
    }

    public static TextWatcher insert(final String masc, final EditText ediTxt) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = Mascara.semMasc(s.toString());
                String mascara = "";

                if (isUpdating) {
                    old = str;
                    isUpdating = true;

                    return;
                }
                int index = 0;

                for(int i = 0; i < masc.length(); i++) {
                    char m = masc.charAt(i);

                    if (m != '#') {
                        if (index == str.length() && str.length() < old.length()) {
                            continue;
                        }
                        mascara += m;

                        continue;
                    }

                    try {
                        mascara += str.charAt(index);
                    } catch (Exception e) {
                        break;
                    }
                    index++;
                }
                if (mascara.length() > 0) {
                    char last_char = mascara.charAt(mascara.length() - 1);
                    boolean hadSign = false;

                    while(eUmSinal(last_char) && str.length() == old.length()) {
                        mascara = mascara.substring(0, mascara.length() - 1);
                        last_char = mascara.charAt(mascara.length() - 1);

                        hadSign = true;
                    }
                    if (mascara.length() > 0 & hadSign) {
                        mascara = mascara.substring(0, mascara.length() - 1);
                    }
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }
}
