package br.com.mendes.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext-test.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public abstract class DAOImplTest {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	protected void save(Object... entidades) {
		for (Object entidade : entidades) {
			this.getSession().saveOrUpdate(entidade);
		}
		this.getSession().flush();
		this.getSession().clear();
	}

}