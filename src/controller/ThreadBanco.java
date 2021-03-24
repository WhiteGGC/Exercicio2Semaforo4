package controller;

import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {
	
	private int cod;
	private int saldo;
	private int valor;
	private int op;
	private Semaphore saque;
	private Semaphore deposito;
	
	public ThreadBanco(int cod, int saldo, int valor, int op, Semaphore saque, Semaphore deposito){
		this.cod = cod;
		this.saldo = saldo;
		this.valor = valor;
		this.op = op;
		this.saque = saque;
		this.deposito = deposito;
	}
	
	@Override
	public void run() {
		if(op%2==0){
			try {
				saque.acquire();
				Operacao();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				saque.release();
			}
		}else if(op%2!=0){
			try {
				deposito.acquire();
				Operacao();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				deposito.release();
			}
		}
	}
	
	private void Operacao(){
		if(op%2==0){
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("A conta "+cod+" tinha "+saldo+" reais, mas com um saque de "+valor+" ficou com "+(saldo-valor)+" reais");
			saldo -= valor;
		}else if(op%2!=0){
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("A conta "+cod+" tinha "+saldo+" reais, mas com um deposito de "+valor+" ficou com "+(saldo+valor)+" reais");
			saldo += valor;
		}
	}
}
