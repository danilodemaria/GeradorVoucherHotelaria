/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voucherentrada;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import javax.swing.JTextField;
public class ValorMasc implements KeyListener {
	JTextField caixaTexto;
	DecimalFormat formato;
	int inteiro, decimal;
	public ValorMasc(JTextField jt, int tam, int dec){
		caixaTexto = jt;
		inteiro = tam-dec;
		decimal = dec;
		String mascara = "";
		if((tam-dec)>0){
			for(int a=0; a<dec; a++){mascara += "0";}
			if(mascara.length()>0){mascara = "."+mascara;}
			for(int a=0; a<tam-dec; a++){
				mascara = "#"+mascara;
				if(((a+1)%3)==0 && (a+1)<(tam-dec)){mascara = ","+mascara;}
			}
		}
		formato = new DecimalFormat(mascara);
	} 
	public void keyTyped(KeyEvent e) {   
        char c = e.getKeyChar();   
        String textNum = caixaTexto.getText().replace(".","").replace(",","");
          if(!Character.isDigit(c) || textNum.length()>=(inteiro+decimal) ){   
            	 e.consume(); 
          }      
     }   
     public void keyPressed(KeyEvent e) {  
     }   
     public void keyReleased(KeyEvent e) {
       	 String textoAntes = caixaTexto.getText();
        	if(textoAntes.replace(".","").replace(",","").length()<=decimal){
        		if(inteiro>=decimal){
        			caixaTexto.setText(textoAntes.replace(".","").replace(",",""));
        		}else{
        		}
            }
            else if(textoAntes.replace(".","").replace(",","").length()>decimal){
            	Double num = 0.0;
               	String textNum = textoAntes.replace(".","").replace(",","");
               	if(decimal>0){
               		textNum = textNum.substring(0,textNum.length()-decimal)+"."+textNum.substring(textNum.length()-decimal,textNum.length());
               	}else{
               		textNum = textNum+".0";
               	}
               	try{
            		num = Double.parseDouble(textNum);
            	}catch (Exception exp) { }
            	String fim = formato.format(num);
            	if(fim.substring(0,1).equals(",")){fim = "0"+fim;}
            	caixaTexto.setText(fim);
            }
     }
}