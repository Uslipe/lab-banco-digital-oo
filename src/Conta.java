
public abstract class Conta implements IConta {
	
	private static final String AGENCIA_PADRAO = "001";
	private static int SEQUENCIAL = 1;

	protected String agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {

		if(valor <= saldo){
			saldo -= valor;
		}
		else{
			throw new IllegalArgumentException("Valor a ser sacado é maior que o saldo disponível.");
		}
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		if(valor <= saldo){
			this.sacar(valor);
			contaDestino.depositar(valor);
		}
		else{
			throw new IllegalArgumentException("Valor a ser transferido é maior que o saldo disponível.");
		}
	}

	public String getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %s", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
}
