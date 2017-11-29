package com.gc.controller;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gc.dto.ItemsDto;
import com.gc.dto.UsersDto;

/*
 * author: Emanuel Pelean
 *
 */
@Controller
public class HomeController {
	
	//loads the home page
	@RequestMapping("/")
	//add cookie parameter HttpServletResponse
	public ModelAndView helloWorld1() {
		//this comes from the config file 
				Configuration config = new Configuration().configure("hibernate.cfg.xml");
				SessionFactory sessionFactory = config.buildSessionFactory();
				//session from hibernate 
				Session session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				//pull info from db, criteria is based on ItemsDto
				Criteria crit = session.createCriteria(ItemsDto.class);
				ArrayList<ItemsDto> list = (ArrayList<ItemsDto>)crit.list();
				tx.commit();
				session.close();
		return new ModelAndView("index", "message", list);
	}
	
	// take data from a form and just add it to a page
	@RequestMapping(value = "submitform", method = RequestMethod.POST)
	public String submitForm(Model model, @RequestParam("firstName") String firstN, @RequestParam("lastName") String lastN,
				@RequestParam("email") String userEmail, @RequestParam("userPassword") String password,
				@RequestParam("phone") String userPhone, @RequestParam("address") String userAddress,
				@RequestParam("city") String userCity, @RequestParam("state") String userState,
				@RequestParam("zip") String userZip, @RequestParam("music") String userMusic,
				@RequestParam("gender") String userGender, @RequestParam("background") String userBackground) {
			model.addAttribute("showUserInfo", "Welcome" + " " + firstN + " " + lastN);
			//this comes from the config file
			Configuration config = new Configuration().configure("hibernate.cfg.xml");
			SessionFactory sessionFactory = config.buildSessionFactory();
			//session from hibernate
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			UsersDto newUser = new UsersDto();
			int id = 0;
			newUser.setId(id);
			newUser.setFirstname(firstN);
			newUser.setLastname(lastN);
			newUser.setEmail(userEmail);
			newUser.setUserPassword(password);
			newUser.setPhone(userPhone);
			newUser.setAddress(userAddress);
			newUser.setCity(userCity);
			newUser.setState(userState);
			newUser.setZip(userZip);
			newUser.setMusic(userMusic);
			newUser.setGender(userGender);
			newUser.setBackground(userBackground);
			session.save(newUser);
			//pull info from db, criteria is based on ItemsDto
			Criteria crit = session.createCriteria(ItemsDto.class);
			ArrayList<ItemsDto> list = (ArrayList<ItemsDto>)crit.list();
			tx.commit();
			session.close();
			model.addAttribute("newUserAdded", newUser);
			model.addAttribute("message", list);
			return "index";
	}
	
	@RequestMapping("/getnewprod")
	public String prodFrom() {
		return "addprodform";
	}
	
	// this is like write in crud
	@RequestMapping(value="addnewproduct", method=RequestMethod.POST)
	public String addNewProduct(@RequestParam("productName") String name, @RequestParam("description") String desc,
			@RequestParam("quantity") int quantity, @RequestParam("price") double price, Model model ) {
				Configuration config = new Configuration().configure("hibernate.cfg.xml");
				SessionFactory sessionFactory = config.buildSessionFactory();
				//session from hibernate 
				Session session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				ItemsDto newItems = new ItemsDto();
				newItems.setProductName(name);
				newItems.setDescription(desc);
				newItems.setQuantity(quantity);
				newItems.setPrice(price);
				session.save(newItems);
				tx.commit();
				session.close();
				// add info to database
				model.addAttribute("newProductAdded", newItems);
		return "addprodsuccess";
	}
	
	// like read in crud
	@RequestMapping("/adminconsole")
	public ModelAndView checkConsole() {
		//this comes from the config file 
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		//session from hibernate 
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//pull info from db, criteria is based on ItemsDto
		Criteria crit = session.createCriteria(ItemsDto.class);
		ArrayList<ItemsDto> list = (ArrayList<ItemsDto>)crit.list();
		tx.commit();
		session.close();
		return new ModelAndView("adminconsole", "message", list);
	}
	
	@RequestMapping(value="searchbyproductname", method=RequestMethod.GET)
	public ModelAndView searchProduct(@RequestParam("productName") String prod) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		//session from hibernate 
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(ItemsDto.class);
		//use like or similar to where clause
		crit.add(Restrictions.like("productName", "%" + prod + "%"));
		ArrayList<ItemsDto> list = (ArrayList<ItemsDto>)crit.list();
		tx.commit();
		session.close();
		return new ModelAndView("adminconsole", "message", list);
		
	}
	
	@RequestMapping(value="searchbyproductnameindex", method=RequestMethod.GET)
	public ModelAndView searchProductIndex(@RequestParam("productName") String prod) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		//session from hibernate 
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(ItemsDto.class);
		//use like or similar to where clause
		crit.add(Restrictions.like("productName", "%" + prod + "%"));
		ArrayList<ItemsDto> list = (ArrayList<ItemsDto>)crit.list();
		tx.commit();
		session.close();
		return new ModelAndView("index", "message", list);
		
	}
	
	@RequestMapping("/userregistration")
	public String registerUser() {
		return "userregistration";
	}
	
	// this mapping is needed to pass the parameter as a hidden field to the update
		// form, and for future use when needed
		@RequestMapping("/update")
		public ModelAndView updateForm(@RequestParam("id") String id) {
			return new ModelAndView("updateprodform", "productName", id);
		}

		@RequestMapping("/updateproduct")
		public ModelAndView updateProduct(@RequestParam("id") String id, @RequestParam("description") String desc,
				@RequestParam("quantity") int quantity, @RequestParam("price") double price) {
			// temp Object will store info for the object we want to update
			ItemsDto temp = new ItemsDto();
			//by passing in the product id from a hidden field we can determine what row to edit
			temp.setProductName(id);
			temp.setPrice(price);
			temp.setDescription(desc);
			temp.setQuantity(quantity);
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			SessionFactory sessionFact = cfg.buildSessionFactory();
			Session codes = sessionFact.openSession();
			codes.beginTransaction();
			codes.update(temp); // update the object from the list
			codes.getTransaction().commit(); // update the row from the database table
			ArrayList<ItemsDto> prodList = getAllItems();
			return new ModelAndView("adminconsole", "message", prodList);
		}
		
		//use this method so you don't have to repeat the following code
				private ArrayList<ItemsDto> getAllItems() {
					Configuration config = new Configuration().configure("hibernate.cfg.xml");
					/*
					 * The SessionFactory is a factory of session and client of Connection Provider.
					 * It holds second level cache (optional) of data
					 */
					SessionFactory sessionFactory = config.buildSessionFactory();
					/*
					 * A Session is used to get a physical connection with a database. The Session
					 * object is lightweight and designed to be instantiated each time an
					 * interaction is needed with the database. Persistent objects are saved and
					 * retrieved through a Session object.
					 * 
					 * The session objects should not be kept open for a long time because they are
					 * not usually thread safe and they should be created and destroyed them as
					 * needed. The main function of the Session is to offer, create, read, and
					 * delete operations for instances of mapped entity classes.
					 */
					Session session = sessionFactory.openSession();
					Transaction tx = session.beginTransaction();
					Criteria crit = session.createCriteria(ItemsDto.class);
					ArrayList<ItemsDto> list = (ArrayList<ItemsDto>) crit.list();
					tx.commit();
					session.close();
					return list;
				}
		
		@RequestMapping("/delete")
		public ModelAndView deleteCustomer(@RequestParam("id") String id) {
			// temp Object will store info for the object we want to delete
			ItemsDto temp = new ItemsDto();
			temp.setProductName(id);
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			SessionFactory sessionFact = cfg.buildSessionFactory();
			Session codes = sessionFact.openSession();
			codes.beginTransaction();
			codes.delete(temp); // delete the object from the list
			codes.getTransaction().commit(); // delete the row from the database table
			ArrayList<ItemsDto> prodList = getAllItems();
			return new ModelAndView("adminconsole", "message", prodList);
		}
	
}