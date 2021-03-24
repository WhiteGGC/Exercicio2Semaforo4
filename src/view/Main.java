package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBanco;

public class Main {

	public static void main(String[] args) {
		
		int saldo;
		int valor;
		int op;
		Semaphore saque = new Semaphore(1);
		Semaphore deposito = new Semaphore(1);
		
		for(int i=0;i<20;i++){
			saldo = (int)(Math.random() * 1001 + 1000);
			valor = (int)(Math.random() * 401 + 100);
			op = (int)(Math.random() * 101);
			Thread tConta = new ThreadBanco(i, saldo, valor, op, saque, deposito);
			tConta.start();
		}
	}

}
