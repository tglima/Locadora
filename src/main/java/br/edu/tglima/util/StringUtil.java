package br.edu.tglima.util;

/**
 * Classe com diversos métodos utilitários para manipulação de <b>Strings</b>
 * @author tglima
 *
 */
public final class StringUtil {
	
	/**
	 * Converte todas as primeiras letras de uma <code>String</code> para maiúsculas.
	 * <br/>
	 * Exemplo: 
	 * <p><b>input</b> = isabel cristina leopoldina;</p>
	 * <p><b>output</b> = Isabel Cristina Leopoldina;</p>
	 *  	
	 * @param str String formada com uma ou mais palavras.
	 * @return Uma String com todas as primeiras letras em maiúsculas.
	 */
	public final static String capitalizeFirstLetters(String str){
        char[] array = str.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (Character.isWhitespace(array[i - 1])) {
                array[i] = Character.toUpperCase(array[i]);
            }
        }
        return new String(array);
	}

}
