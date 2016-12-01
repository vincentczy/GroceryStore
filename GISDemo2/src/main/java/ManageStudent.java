import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Student;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageStudent {
	private static SessionFactory factory;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory obj" + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		ManageStudent me = new ManageStudent();
		
		long stu1 = me.addStudent(1, "John Snow");
	}
	
	public long addStudent(long id, String name){
		Session session = factory.openSession();
		Transaction tx = null;
		long stuID = 0;
		try{
			tx = session.beginTransaction();
			Student stu = new Student(id, name);
			stuID = (Long) session.save(stu);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return stuID;
	}
}
