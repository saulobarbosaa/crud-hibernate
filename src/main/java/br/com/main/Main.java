package br.com.main;

import br.com.dao.PessoaDAO;

public class Main {

	public static void main(String[] args) {
		
		PessoaDAO pdao = new PessoaDAO();
		
//	pdao.Create();
//		pdao.Update(7);
//		pdao.Read();
//		pdao.Delete(2);
		pdao.ReadFull();
	}

}
