package cn.itcast.dao;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDao extends HibernateDaoSupport {

	//添加客户
	public void add(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	//查询所有客户
	@SuppressWarnings("all")
	public List<Customer> findAll() {
		// 调用hibernate模板find方法实现
		// find方法有两个参数：第一个参数是hql语句，第二个参数值（可以省略）
		List<Customer> list = 
				(List<Customer>) this.getHibernateTemplate().find("from Customer");
		return list;
	}

	//根据id查询
	public Customer findOne(int cid) {
		// 调用hibernate模板get方法
		Customer customer = this.getHibernateTemplate().get(Customer.class, cid);
		return customer;
	}

	//删除客户
	public void delete(Customer c) {
		// 调用hibernate模板delete方法
		this.getHibernateTemplate().delete(c);
	}

	//修改客户
	public void update(Customer customer) {
		// 调用hibernate模板update方法
		this.getHibernateTemplate().update(customer);
	}

	//查询记录数
	@SuppressWarnings("all")
	public int findCount() {
		// 调用hibernate模板find方法实现
		List<Object> list =
				(List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
		// 从list中得到记录数
		if(list != null && list.size()!=0) {
			Object obj = list.get(0);
			//变成int类型
			Long lobj = (Long) obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
	}

	//分页查询
	public List<Customer> findAllPage(int begin, int pageSize) {
		// 第一种方式
		//1 得到sessionFactory对象
//		SessionFactory sessionFactory = this.getSessionFactory();
//		//2 得到session
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("from Customer");
//		query.setFirstResult(begin);
//		query.setMaxResults(pageSize);
//		List<Customer> list = query.list();
		
		//第二种 使用离线对象，使用hibernate模板的方法
		//1 使用离线对象，指定对哪个实体类进行操作
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		//2 调用hibernate模板的方法实现分页		
		//第一个参数离线对象，第二个参数开始位置，第三个参数每页记录数
		List<Customer> list = 
				(List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
		return list;
	}

    @SuppressWarnings("unchecked")
	public List<Customer> listCondition(Customer customer) {
		String hql = " from Customer where 2=2";
		List<Object> param = new ArrayList<Object>();
		if (customer.getCustName() != null && !"".equals(customer.getCustName())){

			hql += " and custName=?";
			param.add(customer.getCustName());
		}
		return (List<Customer>) this.getHibernateTemplate().find(hql, param.toArray());

    }
}
