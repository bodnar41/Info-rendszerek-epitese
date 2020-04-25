package jsfbeadando.bo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jsfbeadando.model.Customer;

public class CustomersBO {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	static final String DB_URL = "jdbc:derby:E:\\Eclipse\\derby\\derbyDB\\jsfbeadand;create=true";

	// Database credentials
	static final String USER = "derbyuser";
	static final String PASS = "derbypwd";

	public String createCustomerTable() {
		Connection conn = null;
		Statement stmt = null;
		String sql;
		String result;

		// STEP 2: Register JDBC driver
		try {
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);// getConnection(DB_URL,USER,PASS);

			// STEP 4: Create a statement
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			DatabaseMetaData dbm = conn.getMetaData();
			// check if "company2" table is there
			ResultSet tables = dbm.getTables(null, null, "CUSTOMERS", null);

			if (tables.next()) { // Table exists
				System.out.println("CUSTOMERS table already exists!!");
				result = "Existing";
			} else { // Table does not exist

				System.out.println("Creating table ...");

				sql = "CREATE TABLE APP.CUSTOMERS " + "(ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
						+ "FIRST_NAME VARCHAR(100)," + "CIM VARCHAR(100)," + "COMPANY VARCHAR(100),"
						+ "EMPL_NUMBER VARCHAR(100)," + "SALARY DOUBLE)";
				stmt.executeUpdate(sql);

				System.out.println("CUSTOMERS table created!!");
				result = "Created";
			}

			stmt.close();
			conn.close();
			return result;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error creating CUSTOMERS table!!");
			return "Error";
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}

	public List<Customer> findAllCustomers() {
		List<Customer> customerList = new ArrayList<Customer>();
		Connection conn = null;
		Statement stmt = null;
		String sql;
		String result;

		// STEP 2: Register JDBC driver
		try {
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);// getConnection(DB_URL,USER,PASS);

			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			sql = "SELECT * FROM APP.CUSTOMERS";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 6: Extract data from result set
			while (rs.next()) {
				Customer currentCustomer = new Customer();
				// Retrieve by column name
				currentCustomer.setId(rs.getInt("ID"));
				currentCustomer.setFirstName(rs.getString("FIRST_NAME"));
				currentCustomer.setCim(rs.getString("CIM"));
				currentCustomer.setCompany(rs.getString("COMPANY"));
				currentCustomer.setEmplNumber(rs.getString("EMPL_NUMBER"));
				currentCustomer.setSalary(rs.getDouble("SALARY"));

				// Display values
				// System.out.println(currentEmployee);
				customerList.add(currentCustomer);
			}
			// STEP 7: Clean-up environment
			rs.close();

			stmt.close();
			conn.close();
			return customerList;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error reading CUSTOMERS table!!");
			return customerList;
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

	}

	public long insertCustomer(Customer customer) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String sql;

		// STEP 2: Register JDBC driver
		try {
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("insertCustomer(): Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);// getConnection(DB_URL,USER,PASS);

			sql = "INSERT INTO APP.CUSTOMERS" + "(first_name, cim, company, empl_number, salary) VALUES"
					+ "(?,?,?,?,?)";

			// STEP 4: Create a prepared statement
			System.out.println("Creating prepared statement...");
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getCim());
			preparedStatement.setString(3, customer.getCompany());
			preparedStatement.setString(4, customer.getEmplNumber());
			preparedStatement.setDouble(5, customer.getSalary());

			// execute insert SQL statement
			Integer affectedRows = preparedStatement.executeUpdate();

			Long idNewRow;
			if (affectedRows == 0) {
				throw new SQLException("Creating row failed, no rows affected.");
			}

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					idNewRow = generatedKeys.getLong(1);
					System.out.println("Id of new object: " + idNewRow);
				} else {
					throw new SQLException("Creating row failed, no ID obtained.");
				}
			}

			preparedStatement.close();
			conn.close();
			return idNewRow;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error creating row in CUSTOMERS table!!");
			return 0;
		} finally {
			// finally block used to close resources
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

	}

	public long deleteCustomer(Customer customer) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String sql;

		// STEP 2: Register JDBC driver
		try {
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("deleteCustomer(): Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);// getConnection(DB_URL,USER,PASS);

			sql = "DELETE FROM APP.CUSTOMERS " + "WHERE ID = ?";

			// STEP 4: Create a prepared statement
			System.out.println("Creating prepared statement...");
			preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setLong(1, customer.getId());

			// execute insert SQL statement
			Integer affectedRows = preparedStatement.executeUpdate();

			Long idNewRow;
			if (affectedRows == 0) {
				throw new SQLException("Deleting row failed, no rows affected.");
			} else {
				System.out.println("Object deleted!!");
			}

			preparedStatement.close();
			conn.close();
			return affectedRows;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error creating row in CUSTOMERS table!!");
			return 0;
		} finally {
			// finally block used to close resources
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

	}
	public long updateCustomer(Customer customer){
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String sql;
		
	     //STEP 2: Register JDBC driver
	      try {
			Class.forName(JDBC_DRIVER);

			//STEP 3: Open a connection
			System.out.println("updateCustomer(): Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);//getConnection(DB_URL,USER,PASS);
			
			sql = "UPDATE APP.CUSTOMERS SET "
					+ "first_name=?, cim=?, company=?, empl_number=?, salary=?"
					+ "WHERE id=?";
			
			
			
			 //STEP 4: Create a prepared statement
			System.out.println("Creating prepared statement...");
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getCim());
			preparedStatement.setString(3, customer.getCompany());
			preparedStatement.setString(4, customer.getEmplNumber());
			preparedStatement.setDouble(5, customer.getSalary());
			preparedStatement.setLong(6, customer.getId());

			// execute insert SQL statement
			Integer affectedRows = preparedStatement.executeUpdate();		
		
			Long idNewRow;
	        if (affectedRows == 0) {
	            throw new SQLException("Updating row failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	idNewRow = generatedKeys.getLong(1);
	            	System.out.println("Id of new object: " + idNewRow);
	            }
	            else {
	                throw new SQLException("Updating row failed, no ID obtained.");
	            }
	        }			
				
			preparedStatement.close();
			conn.close();
			return idNewRow;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error updating row in CUSTOMERS table!!");
			return 0;
		} finally{
	      //finally block used to close resources
	      try{
	         if(preparedStatement!=null)
	        	 preparedStatement.close();
	      } catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try	      
		
	}
}
