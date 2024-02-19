package com.mycompany.cajerocontrol;


/**
 * cifrado cesar
 * @author asielapodaca
 */
public class Encriptacion {
    
    private int codigo = 5;
    //método para cifrar el texto
    public String cifrado(String texto) {
        StringBuilder cifrado = new StringBuilder();
        int code = codigo % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) + code) > 'z') {
                    cifrado.append((char) (texto.charAt(i) + code - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + code));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) + code) > 'Z') {
                    cifrado.append((char) (texto.charAt(i) + code - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + code));
                }
            }
        }
        return cifrado.toString();
    }

    //método para descifrar el texto
    public String descifrado(String texto) {
        StringBuilder cifrado = new StringBuilder();
        int code = codigo % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) - code) < 'a') {
                    cifrado.append((char) (texto.charAt(i) - code + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - code));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) - code) < 'A') {
                    cifrado.append((char) (texto.charAt(i) - code + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - code));
                }
            }
        }
        return cifrado.toString();
    }
}
