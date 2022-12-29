package br.com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.bean.Pessoa;

public class PessoaDAO {

	Pessoa p;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
	EntityManager em = emf.createEntityManager();
	ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	Scanner sc1 = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);

	public void Create() {

		System.out.println("Digite a quantia de cadastros: ");
		int n = sc1.nextInt();

		try {
			em.getTransaction().begin();
			for (int i = 0; i < n; i++) {

				System.out.println("Digite o nome: ");
				String nome = sc2.nextLine();
				System.out.println("Digite o email: ");
				String email = sc2.nextLine();
				em.persist(new Pessoa(null, nome, email));
			}
			em.getTransaction().commit();
			System.out.println("Cadastrados");
		} catch (Exception e) {
			em.getTransaction().rollback();
		}finally {
			em.close();
		}

	}

	public void Read() {
		try {
			System.out.println("Digite o id: ");
			Integer id = sc2.nextInt();

			Pessoa p = em.find(Pessoa.class, id);
			System.out.println(p);
		} catch (Exception e) {
			em.getTransaction().rollback();
		}

	}
	
	public void ReadFull() {
		
		List<Pessoa> pessoas;
		
		
		try {
			
		pessoas = em.createQuery("FROM Pessoa p").getResultList();
			
		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa);
		}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void Update(Integer id) {
		try {
			em.getTransaction().begin();

				System.out.println("Digite o nome: ");
				String nome = sc2.nextLine();
				System.out.println("Digite o email: ");
				String email = sc2.nextLine();
				em.merge(new Pessoa(id, nome, email));

			em.getTransaction().commit();
			System.out.println("Atualizados!");
		} catch (Exception e) {
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
	}
	
	public void Delete(Integer id) {
		try {
			
			Pessoa p = em.find(Pessoa.class, id);
			
			em.getTransaction().begin();

				
				em.remove(p);

			em.getTransaction().commit();
			System.out.println("Removido!");
		} catch (Exception e) {
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
	}

}
