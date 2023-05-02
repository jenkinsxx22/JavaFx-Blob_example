package application.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import application.model.Photos;
import application.util.HIbernateUtil;

public class PhotosServices {
public void Save(Photos photo) {
		
		Transaction transaction = null;
		try(Session  session = HIbernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			session.save(photo);
			transaction.commit();
			session.close();
		}catch(Exception e) {
			if(transaction==null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
	}
	public void Update(Photos photo) {
		Transaction transaction = null;
		
		try(Session  session = HIbernateUtil.getSessionFactory().openSession()){
		
			transaction = session.beginTransaction();
			session.saveOrUpdate(photo);
			transaction.commit();
			session.close();
		}catch(Exception e) {
			if(transaction==null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	public void Delete(long id) {
		Photos photo = new Photos();
		Transaction transaction = null;
		try(Session  session = HIbernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			photo = session.load(Photos.class, id);
			session.delete(photo);
			transaction.commit();
		}catch(Exception e) {
			if(transaction==null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Photos GetById(long id) {
		Photos student = new Photos();
		Transaction transaction = null;
		try(Session  session = HIbernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			student = session.load(Photos.class, id);
			transaction.commit();
		}catch(Exception e) {
			if(transaction==null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return student;
	}
	
	public List<Photos> GetAll(){
		List<Photos> listUsers = new ArrayList<Photos>();

		Transaction transaction = null;
		try(Session  session = HIbernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			listUsers = session.createQuery("from Photos").getResultList();
			transaction.commit();
		}catch(Exception e) {
			if(transaction==null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		return listUsers;
	}
	public long GetCount() {
		Transaction transaction = null;
		long cnt=0;
		try(Session  session = HIbernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			Query query = session.createSQLQuery("select count(u.Id) as count from Photos u");			
//			cnt =  (long) query.uniqueResult();
			Iterator count = query.iterate();
			transaction.commit();
		}catch(Exception e) {
			
			if(transaction==null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return cnt;
	}

}
