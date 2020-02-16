package model.cliente;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.data.structure.SeparateChainingHashST;

public class Main {
	
	private static String M;
	private static Integer n;
	private static Integer e;
	
	
	
	static BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(System.out));
	static Scanner lector = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception 
	{
		int opcion = -1;

		
		while (opcion != 0) {
			try {
				escritor.write("---------------Cifrado RSA---------------\n");
				escritor.write("Ingrese un numeral\n");
				escritor.write("Opciones:\n");
				escritor.write("1: Ingresar n. \n");
				escritor.write("2: Ingresar e. \n");
				escritor.write("3: Ingresar M (mensaje). \n");
				escritor.write("4: Cifrar \n");
				escritor.write("0: Salir\n");
				escritor.write("------------------------------------------------\n");
				escritor.flush();
				opcion = lector.nextInt();

				switch(opcion) 
				{
				case 1: ingresarn(); break;
				case 2: ingresare(); break;
				case 3: ingresarM(); break;
				case 4: cifrar(); break;

				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (InputMismatchException ime) {
				try {
					escritor.write("No ingreso un numeral\n");
					escritor.write("Ingrese cualquier letra y enter para continuar\n");
					escritor.flush();
					lector.nextLine();
					lector.nextLine();
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}

		try {
			escritor.write("Adios!");
			escritor.flush();
			escritor.close();
			lector.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}


	


	// ingresar n
	private static void ingresarn() throws IOException {
		escritor.write("Ingrese un numero producto de primos (p*q) menor a 10 digitos\n");
		escritor.flush();
		
		String capturan = lector.next();
		
		if(isNumeric(capturan) == false || capturan.length() > 10) {
			if(isNumeric(capturan) == false)	escritor.write("El valor '"+capturan+"' no es un numero, ingrese un numero valido\n");		
			else if(capturan.length() > 10) escritor.write("El valor '"+capturan+"'es mayor a 10 digitos, ingrese un numero valido\n");
		}
		else {		
			n = Integer.parseInt(capturan);
			escritor.write("n = "+n+"\n");	
		}
			
		//volver al main
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();		
	}

	
	
	
	// ingresar e
	private static void ingresare() throws IOException {	
		escritor.write("Ingrese entero positivo primo relativo con (p-1)(q-1)\n");
		escritor.flush();
		
		String captura = lector.next();
				
		if(isNumeric(captura) == true) {
			e = Integer.parseInt(captura);
			escritor.write("e = "+e+"\n");	
		}
		else {
			escritor.write("El valor '"+captura+"' no es un numero, ingrese un numero valido\n");	
		}
		//volver al main
				escritor.write("Ingrese cualquier letra y Enter para continuar\n");
				escritor.flush();
				lector.next();		
	}
	
	
	
	//Valido que sean numeros
	private static boolean isNumeric(String cadena){
		try {
			Long.parseLong(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	
	
	
	
	private static void ingresarM() throws IOException {
		escritor.write("Ingrese el mensaje a codificar - *Recuerde sin Ñ ni numeros\n");
		escritor.flush();
		
		String captura = lector.next();
		
		//compruebo que solo contenga letras
        Pattern patron = Pattern.compile("[^A-Za-z ]");
        Matcher encaja = patron.matcher(captura);
	
		if(!encaja.find() && !captura.equals("")) {
			M = captura.toUpperCase();
			escritor.write("M = "+M+"\n");
		}
		else escritor.write("El mensaje '"+captura+"' contiene numeros o la letra ñ.\n");
		
		//volver al main
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();		
	}
	
	
	
	
	
	//cifrar el mensaje con los parametro dados
	@SuppressWarnings("unused")
	private static void cifrar() throws Exception, IOException {
		
		if(n == null || e == null || M == null) {
			throw new Exception("ingrese valores antes de inciar la codificación");	
		}
				
		SeparateChainingHashST<String, String> valorLetras = createValorLetras(); //valor de las letras en numero
		
		String valorCifrar ="";

		for (int i = 0; i < M.length(); i++) {
			//extrae cada letra de la cadena
			char actualChar = (char) M.charAt(i);			
			//dar valor a las letras
			valorCifrar += valorLetras.darValor(String.valueOf(actualChar));						
		}
		
//		System.out.println(valorCifrar); //valor a cifrar
					
		String cifradoFinal = ""; //CIFRADO FINAL a devolver
		
		
		//Divir mensaje en bloques
		if(n < 25) // bloques de 2 digitos
		{
			int longitudBl = valorCifrar.length(); //longitud del mensaje a cifrar
			int numeroDeBloques = longitudBl/2; //numero de bloques a cifrar
			
			int iniSep = 0;
			int finalSep = 2;
			
			for (int i = 0; i < numeroDeBloques; i++) {
				
				String bloqueActual = valorCifrar.substring(iniSep,finalSep); //extraigo el bloque actual del mensaje
				
				cifradoFinal += exp(n,e,bloqueActual); //codifico y agrego a la cadena final del cofrado.
				
				iniSep += 2;
				finalSep += 2;
			}				
		}
		else if( n > 2525) // bloques de 4 digitos
		{
			int longitudBl = valorCifrar.length(); //longitud del mensaje a cifrar
			int numeroDeBloques = longitudBl/4; //numero de bloques a cifrar
			
			int iniSep = 0;
			int finalSep = 4;
			
			for (int i = 0; i < numeroDeBloques; i++) {
				
				String bloqueActual = valorCifrar.substring(iniSep,finalSep); //extraigo el bloque actual del mensaje
				
				cifradoFinal += exp(n,e,bloqueActual); //codifico y agrego a la cadena final del cofrado.
				
				iniSep += 4;
				finalSep += 4;
			}	
		}
		else if(n > 252525) // bloques de 6 digitos
		{
			int longitudBl = valorCifrar.length(); //longitud del mensaje a cifrar
			int numeroDeBloques = longitudBl/6; //numero de bloques a cifrar
			
			int iniSep = 0;
			int finalSep = 6;
			
			for (int i = 0; i < numeroDeBloques; i++) {
				
				String bloqueActual = valorCifrar.substring(iniSep,finalSep); //extraigo el bloque actual del mensaje
				
				cifradoFinal += exp(n,e,bloqueActual); //codifico y agrego a la cadena final del cofrado.
				
				iniSep += 6;
				finalSep += 6;
			}				
		}
		else if(n > 25252525)// bloques de 8 digitos
		{
			int longitudBl = valorCifrar.length(); //longitud del mensaje a cifrar
			int numeroDeBloques = longitudBl/8; //numero de bloques a cifrar
			
			int iniSep = 0;
			int finalSep = 8;
			
			for (int i = 0; i < numeroDeBloques; i++) {
				
				String bloqueActual = valorCifrar.substring(iniSep,finalSep); //extraigo el bloque actual del mensaje
				
				cifradoFinal += exp(n,e,bloqueActual); //codifico y agrego a la cadena final del cofrado.
				
				iniSep += 8;
				finalSep += 8;
			}	
		}


		escritor.write("n = "+n+"\n");
		escritor.write("e = "+e+"\n");
		escritor.write("M = "+M+"\n");
		escritor.write("///////////////////////////////////////////////////\n");
		escritor.write("Cifrado = "+cifradoFinal+"\n");
		escritor.write("///////////////////////////////////////////////////\n");

		
		//volver al main
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();	
	}
	
	

	
	//convierte el abc en un diccionario asignando valores a cada letra
	private static SeparateChainingHashST<String, String> createValorLetras()
	{
		SeparateChainingHashST<String, String> valor = new SeparateChainingHashST<>();
		
		char[] s;
		s = new char[26];
		for (int i = 0; i < 26; i++) {
			s[i] = (char) ('A' + i);
			
			if(i>=10) valor.insertar(String.valueOf(s[i]), String.valueOf(i));
			else valor.insertar(String.valueOf(s[i]), String.valueOf("0"+i));		
//			System.out.println("letra: "+s[i]+" "+valor.darValor(String.valueOf(s[i])));
		}
		return valor;
	}
	
	
	
	
	
	
	//metodo que devulve: bloque^e % n
	private static String exp(Integer pN, Integer pE, String bloque) {
		BigInteger bloqueExp = new BigInteger(bloque).pow(pE);
		BigInteger modFinal = bloqueExp.mod(new BigInteger(String.valueOf(pN)));		
		String respuesta = String.valueOf(modFinal);
		return respuesta;	
	}
	
	
	
}
