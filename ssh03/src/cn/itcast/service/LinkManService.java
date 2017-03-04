package cn.itcast.service;

import cn.itcast.dao.LinkManDao;
import cn.itcast.entity.LinkMan;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by crh on 2017/2/26.
 */
@Transactional
public class LinkManService {
    private LinkManDao linkManDao;

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }

    public void add(LinkMan linkMan) {
        linkManDao.add(linkMan);
    }

    public List<LinkMan> findAll() {
        return linkManDao.findAll();
    }
}
