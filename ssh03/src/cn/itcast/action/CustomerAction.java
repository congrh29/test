package cn.itcast.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.entity.Customer;
import cn.itcast.entity.PageBean;
import cn.itcast.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private Customer customer = new Customer();
	public Customer getModel() {
		return customer;
	}
	
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	
	public Customer getCustomer() {
		return customer;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	private List<Customer> list;
	public List<Customer> getList() {
		return list;
	}
	
	//使用属性封装得到当前页
	private Integer currentPage;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	//分页列表
	public String listpage() {
		//调用方法返回封装之后的pageBean对象
		PageBean pageBean = customerService.findPage(currentPage);
		//pageBean对象放到域对象
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listpage";
	}
	
	//修改操作- 真正修改数据库
	public String update() {
		//使用模型驱动获取表单提交数据
		customerService.update(customer);
		return "update";
	}

	//修改操作- 根据id查询
	public String showCus() {
		//得到要修改客户的cid值
		int cid = customer.getCid();
		//根据cid查询客户对象
		Customer cus = customerService.findOne(cid);
		//传递到页面中显示
		ServletActionContext.getRequest().setAttribute("customer", cus);
		return "showCus";
	}
	
	//删除客户的方法
	public String deleteCus() {
		//1 查询
		int cid = customer.getCid();
		Customer c = customerService.findOne(cid);
		//2 根据查询对象删除
		customerService.delete(c);
		return "deleteCus";
	}
	
	//客户列表
	public String list() {
//		List<Customer> list = customerService.findAll();
		//放到域对象
//		ServletActionContext.getRequest().setAttribute("list", list);
		
		//放到值栈
		list = customerService.findAll();
		return "list";
	}
	
	//2 添加客户功能
	public String addCustomer() {
		//调用方法实现添加
		customerService.add(customer);
		return "addCustomer";
	}
	
	//1 到添加页面
	public String toAddPage() {
		return "toAddPage";
	}
	public String listCondition(){
		//使用模型驱动得到值
		List<Customer> list = customerService.listCondition(customer);
		ServletActionContext.getRequest().setAttribute("list",list);
		return "listCondition";
	}

}
