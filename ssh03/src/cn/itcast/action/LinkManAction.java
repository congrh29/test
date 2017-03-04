package cn.itcast.action;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkManService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by crh on 2017/2/26.
 */
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
   private LinkMan linkMan = new LinkMan();
    public LinkMan getModel() {
        return linkMan;
    }
    private LinkManService linkManService;
    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }
    //把customerService对象注入到action里面
    private CustomerService customerService;
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
//定义两个变量,一个代表上传文件,一个代表上床文件名称
    //上传文件,变量命名:表单文件上传想,name属性值

    private File upload;
    //上传文件名,文件名称命名:文件上传项name属性+fileName
    private String uploadFileName;
    public File getUpload() {
        return upload;
    }
    public void setUpload(File upload) {
        this.upload = upload;
    }
    public String getUploadFileName() {
        return uploadFileName;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    //添加联系人的方法
    public String addLinkMan() throws IOException {
        //上传过程
        //判断是否需要上传
        if (upload != null){
            //在上传到服务器的文件夹里创建文件
            File serverFile = new File("F:\\" + uploadFileName);
            //把上传本地文件复制到服务器的文件中
            //fileutils是import org.apache.commons.io.FileUtils;
            //第一个参数:上传文件
            //第二个参数:服务器文件
            FileUtils.copyFile(upload,serverFile);
        }
        //使用模型驱动得到联系人信息,但是CID值不能得到
      //String cid = ServletActionContext.getRequest().getParameter("cid");
      //在联系人实体类有客户对象,把cid放到联系人客户对象里面
       // Customer c = new Customer();
       // c.setCid(Integer.parseInt(cid));
        //把对象放到linkman里面
      //  linkMan.setCustomers(c);
        linkManService.add(linkMan);
        return "addLinkMan";
    }
//3,联系人列表
    public String list(){
        List<LinkMan> list = linkManService.findAll();
        ServletActionContext.getRequest().setAttribute("list",list);
        return "list";
    }
    public String toAddPage(){
       // 查询所有客户,返回list集合
        //把所有客户的list集合传递到页面下拉列表中
        //因为之前在客户的service里面的方法就可以了
        //把customerService对象注入到action里面
        List<Customer> customerList = customerService.findAll();
        ServletActionContext.getRequest().setAttribute("customerList",customerList);
        return "toAddPage";
    }
}
