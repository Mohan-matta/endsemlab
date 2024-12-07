package HQLDemo;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

public class HQLOperations {
	 
	 
	 public static void main (String args[])
	 {
		 HQLOperations operations  = new HQLOperations();
		// operations.addProduct();
		 operations.displayallProductscompleteObject();
		// operations.displayallProductspartialobject();
		// operations.aggregatefunctions();
		// operations.updatepositionalparams();
		// operations.updatenamedparams();
		// operations.deletepositionalparams();
		// operations.deletenamedparams();
		// operations.displayallproductsbypositionparams();
		// operations.displayproductbynamedparams();
		// operations.displayproducts();
	 }
	 
	 public void addProduct()
	 {
		 Configuration configuration = new Configuration();
		 configuration.configure("hibernate.cfg.xml");
		 SessionFactory sf = configuration.buildSessionFactory();
		 Session session = sf.openSession();
		 
		 Transaction t = session.beginTransaction();
		 
		  Product product  = new Product();
		 
		  product.setId(3);
		  product.setCategory("GADGETS");
		  product.setName("TV");
		  product.setCost(50000.0);
		  product.setStock(10);
		  product.setStatus(true);
		  
		  session.persist(product);
		  t.commit();
		  System.out.println("Product added successfully");
		  
		  session.close();
		  
		 }
	 
	 public void displayallProductscompleteObject()
	 {
           Configuration configuration = new Configuration();
	       configuration.configure("hibernate.cfg.xml");
	       SessionFactory sf = configuration.buildSessionFactory();
	        Session session = sf.openSession();
		 
		    String hql = "from Product"; // select * from product_table
		    Query <Product>  qry = session.createQuery(hql,Product.class);
		    List<Product > productlist = qry.getResultList();
		    System.out.println("Total Products="+productlist.size());
		    
		    for(Product p : productlist)
		    {   
		    	System.out.println("ID:"+p.getId());
		        System.out.println("Category:"+p.getCategory());
		        System.out.println("Name:"+p.getName());
		        System.out.println("Cost:"+p.getCost());
		        System.out.println("Stock:"+p.getStock());
		        System.out.println("Status:"+p.isStatus());
			
		    }
		    System.out.println("allProductscompletelly displayed successfully");
			
		    
		    session.close();
		    
		   sf.close();
	 }
	 
	 public void  displayallProductspartialobject()
	 {   
		  
		    Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	        SessionFactory sf = configuration.buildSessionFactory();
	        Session session = sf.openSession();
		 
	        String hql = " select p.id,p.name,p.cost from Product p "; //  p is reference object or alias
		    Query <Object[]>  qry = session.createQuery(hql,Object[].class);
		    List<Object[]> productlist = qry.getResultList();
		    
		    System.out.println("Total Products="+productlist.size());
		    
		    for(Object[] obj : productlist)
		    {   
		    	System.out.println("Product ID:"+ obj[0]);
		    	System.out.println("Product Name:"+obj[1]);
		    	System.out.println("Product Cost:"+obj[2]);
		    	
		    }
	        
		 session.close();
		 sf.close();
		 
	 }
	 
	 
	  public void aggregatefunctions()
	    {
		    Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	        
	        SessionFactory sf = configuration.buildSessionFactory();
	        Session session = sf.openSession();
	        
	        String hql1 = "select count(*) from Product";
	        // you can also use count(property)
	       Query<Long> qry  = session.createQuery(hql1, Long.class);
	      long  count =   qry.getSingleResult();
	      System.out.println("Total Products="+count);
		    
	      String hql2 = "select sum(cost) from Product";
	      Query <Double>qry1 = session.createQuery(hql2, Double.class);
	      double totalcost = qry1.getSingleResult();
	      System.out.println("Total Cost="+totalcost);
	      
	      String hql3 = "select avg(cost) from Product";
	      Query <Double> qry2 =  session.createQuery(hql3, Double.class);
	       double avgcost =  qry2.getSingleResult();
	       System.out.println("Avg Cost="+avgcost);
	       
	       String hql4 = "select min(stock) from Product";
		      Query <Integer> qry3 =  session.createQuery(hql4, Integer.class);
		       int minstock =  qry3.getSingleResult();
		       System.out.println("Min Stock="+minstock);
	      
		       String hql5 = "select max(stock) from Product";
			      Query <Integer> qry4 =  session.createQuery(hql5, Integer.class);
			       int maxstock =  qry4.getSingleResult();
			       System.out.println("Max Stock="+maxstock);
		       
	      
	    }
	    
	    public void updatepositionalparams()
	    { 
	    	Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	        
	        SessionFactory sf = configuration.buildSessionFactory();
	        Session session = sf.openSession(); 
	         
	         Transaction  t = session.beginTransaction();
	         
	         Scanner sc = new Scanner(System.in);
	         System.out.println("Enter Product ID:");
	         int pid = sc.nextInt();
	         System.out.println("Enter Product Name:"); 
	         String pname = sc.next();
	         System.out.println("Enter Product Cost:");
	         Double  pcost = sc.nextDouble();
	         
	         
	         String hql = "update Product set name = ?1,cost = ?2 where id = ?3";
	           MutationQuery qry= session.createMutationQuery(hql);
	           qry.setParameter(1, pname);
	           qry.setParameter(2, pcost);
	           qry.setParameter(3, pid);
	    
	          int n =  qry.executeUpdate();	         
	         t.commit();
	         System.out.println(n+" Product(s) Updated Successfully");
	         
	         sc.close();
             session.close();
             sf.close();
	      
	    }
	   
	    public void updatenamedparams()
	    {  
	    	Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	        
	        SessionFactory sf = configuration.buildSessionFactory();
	        Session session = sf.openSession(); 
	         
	         Transaction  t = session.beginTransaction();
	         
	         Scanner sc = new Scanner(System.in);
	         System.out.println("Enter Product ID:");
	         int pid = sc.nextInt();
	         System.out.println("Enter Product Name:"); 
	         String pname = sc.next();
	         System.out.println("Enter Product Cost:");
	         Double  pcost = sc.nextDouble();
	         
	         
	         String hql = "update Product set name = :v1,cost =:v2  where id = :v3";
	           MutationQuery qry= session.createMutationQuery(hql);
	           qry.setParameter("v1", pname);
	           qry.setParameter("v2", pcost);
	           qry.setParameter("v3", pid);
	    
	          int n =  qry.executeUpdate();	         
	         t.commit();
	         System.out.println(n+" Product(s) Updated Successfully");
	         
	         sc.close();
             session.close();
             sf.close();
	      
	    }
	    
	    
	    public void deletepositionalparams()
	    {
	    	   
	    	Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	        
	        SessionFactory sf = configuration.buildSessionFactory();
	        Session session = sf.openSession(); 
	         
	         Transaction  t = session.beginTransaction();
	         
	         Scanner sc = new Scanner(System.in);
	         System.out.println("Enter Product ID:");
	         int pid = sc.nextInt();
	      
	         String hql = "delete from  Product  where id = ?1";
	           MutationQuery qry= session.createMutationQuery(hql);
	           qry.setParameter(1, pid);
	          
	          int n =  qry.executeUpdate();	         
	         t.commit();
	         
	          if(n>0) {
	            System.out.println(" Product Deleted Successfully");
	          }
	          else {
	        	  System.out.println(" Product ID Not Found ");
	          }
	         
	         sc.close();
             session.close();
             sf.close();
	      
	    }
	    
	    
	    public void deletenamedparams() 
	    { 
	    	Configuration configuration = new Configuration();
	    	   configuration.configure("hibernate.cfg.xml");
	    	   
	    	   SessionFactory sf = configuration.buildSessionFactory();
	    	   Session session = sf.openSession();
	    	  
	    	   Transaction t = session.beginTransaction();
	    	   
	    	   Scanner sc = new Scanner(System.in);
	    	   System.out.println("Enter Product Id:");
	    	   int pid = sc.nextInt();
	    	   
	    	   String hql = "delete from Product where id=:v";
	    	   MutationQuery qry =session.createMutationQuery(hql);
	    	   qry.setParameter("v",pid);
	    	   int n = qry.executeUpdate();
	    	   
	    	   t.commit();
	         
	    	   if(n>0)
	    	   {
	    		   System.out.println("Product Deleted Successfully");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("Product ID Not Found");
	    	   }
	    	   sc.close();
	    	   session.close();
	    	   sf.close();
	    	   
	      
	    }
	    
	    public void displayallproductsbypositionparams()
 	   {    
	    	
	    	 Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	           
	         SessionFactory sf = configuration.buildSessionFactory();
	          Session session = sf.openSession(); 
	          
	          Scanner sc  = new Scanner(System.in);
	          System.out.println("Enter Product ID");
	          int pid = sc.nextInt();
	          String hql = "from Product where id = ?1";
	          Query <Product> qry = session.createQuery(hql, Product.class);
	          qry.setParameter(1, pid);
	          Product p =  qry.getSingleResultOrNull();
	      
	          if(p!=null)
	          {
	        	  System.out.println(p.toString());
	          }
	          else {
	        	  System.out.println("Product Id not found");
	          }
	         
	          sc.close();
	    	   session.close();
	    	   sf.close();
 		   
 	   }
	     
	    
	    // display product based on category and stock
		   public void displayproducts()
		   {  
			   Configuration configuration = new Configuration();
	   	   configuration.configure("hibernate.cfg.xml");
	   	   
	   	   SessionFactory sf = configuration.buildSessionFactory();
	   	   Session session = sf.openSession();
	       Scanner sc  = new Scanner(System.in);
	       System.out.println("Enter Product Category");
	       String pcategory = sc.next();
	       System.out.println("Enter Product Stock");
	       int pstock = sc.nextInt();
	   	   String hql = "from Product where category=?1 and stock>=?2"; 
	   	   
	   	   Query<Product> qry = session.createQuery(hql, Product.class);
	   	   qry.setParameter(1, pcategory);
	   	   qry.setParameter(2, pstock);
	   	   List<Product> productlist = qry.getResultList();
	   	   System.out.println("Total Products="+productlist.size());
	   	   
	   	   for(Product p : productlist)
	   	   {
	   		  System.out.println("ID:"+p.getId());
	   		  System.out.println("Category:"+p.getCategory());
	   		  System.out.println("Name:"+p.getName());
	   		  System.out.println("Cost:"+p.getCost());
	   		  System.out.println("Quantity:"+p.getStock());
	   		  System.out.println("Stock Status:"+p.isStatus());
	   	   }
	   	   session.close();
	   	   sf.close();
	    }
			
		 
		    
		   
		   
		  public void displayproductbynamedparams()
		    {
		    	Configuration configuration = new Configuration();
			        configuration.configure("hibernate.cfg.xml");
			           
			         SessionFactory sf = configuration.buildSessionFactory();
			          Session session = sf.openSession(); 
			          
			          Scanner sc  = new Scanner(System.in);
			          System.out.println("Enter Product ID");
			          int pid = sc.nextInt();
			          String hql = "from Product where id =:v";
			          Query <Product> qry = session.createQuery(hql, Product.class);
			          qry.setParameter("v", pid);
			          Product p =  qry.getSingleResultOrNull();
			      
			          if(p!=null)
			          {
			        	  //we can use getter methods to print every property of Product object
			        	  System.out.println(p.toString());
			          }
			          else {
			        	  System.out.println("Product Id not found");
			          }
			         
			          sc.close();
			    	   session.close();
			    	   sf.close();
		    }
	 
	 
}
