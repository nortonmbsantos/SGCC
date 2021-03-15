package br.edu.utfpr.sgcc.config;


import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import br.edu.utfpr.sgcc.config.Encryptor;

public class DBConnection {
	private static SessionFactory factory;
	static {
		factory = new Configuration().configure().buildSessionFactory();
	}

	public static Session getSession() {
		return factory.openSession();
	}

	public void doWork() {
		Session session = getSession();
		// do work.
		session.close();
	}

	// Call this during shutdown
	public static void close() {
		factory.close();
	}
}
