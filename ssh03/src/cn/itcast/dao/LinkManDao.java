package cn.itcast.dao;

import cn.itcast.entity.LinkMan;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by crh on 2017/2/26.
 */
public class LinkManDao extends HibernateDaoSupport{
//实现添加联系人
    public void add(LinkMan linkMan) {
        this.getHibernateTemplate().save(linkMan);
    }

    public List<LinkMan> findAll() {
       return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
    }
}
