package jsfbeadando.bo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jsfbeadando.model.Company;

public class CompaniesBO {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	static final String DB_URL = "jdbc:derby:E:\\Eclipse\\derby\\derbyDB\\jsfbeadand;create=true";

	// Database credentials
	static final String USER = "derbyuser";
	static final String PASS = "derbypwd";

	public String createCompaniesTable() {
		Connection conn = null;
		Statement stmt = null;
		String sql;
		String result;

		// STEP 2: Register JDBC driver
		try {
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("createCompaniesTable(): Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);// getConnection(DB_URL,USER,PASS);

			// STEP 4: Create a statement
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			DatabaseMetaData dbm = conn.getMetaData();
			// check if "company2" table is there
			ResultSet tables = dbm.getTables(null, null, "COMPANIES", null);

			if (tables.next()) { // Table exists
				System.out.println("COMPANIES table already exists!!");
				result = "Existing";
			} else { // Table does not exist

				System.out.println("Creating table ...");

				sql = "CREATE TABLE APP.COMPANIES " + "(ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
						+ "NAME VARCHAR(100))";
				stmt.executeUpdate(sql);

				System.out.println("COMPANIES table created!!");
				result = "Created";
			}

			stmt.close();
			conn.close();
			return result;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error creating COMPANIES table!!");
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

	public long insertCompany(Company company) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String sql;

		// STEP 2: Register JDBC driver
		try {
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("insertCompany(): Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);// getConnection(DB_URL,USER,PASS);

			sql = "INSERT INTO APP.COMPANIES" + "(name) VALUES" + "(?)";

			// STEP 4: Create a prepared statement
			System.out.println("Creating prepared statement...");
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, company.getName());

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
			System.out.println("Error creating row in COMPANIES table!!");
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

	public List<Company> findAllCompanies() {
		List<Company> companiesList = new ArrayList<Company>();
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

			sql = "SELECT * FROM APP.COMPANIES";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 6: Extract data from result set

			while (rs.next()) {
				Company currentCompany = new Company();
				// Retrieve by column name

				currentCompany.setId(rs.getLong("ID"));
				currentCompany.setName(rs.getString("NAME"));

				// Display values
				// System.out.println(currentCompany.getName());
				companiesList.add(currentCompany);
			}

			// STEP 7: Clean-up environment
			rs.close();

			stmt.close();
			conn.close();
			return companiesList;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error reading COMPANIES table!!");
			return null;
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
}
