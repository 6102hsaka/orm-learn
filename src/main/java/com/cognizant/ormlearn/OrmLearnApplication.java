package com.cognizant.ormlearn;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Attempt;
import com.cognizant.ormlearn.model.AttemptQuestion;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Options;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.AttemptService;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	private static CountryService countryService;
	private static StockRepository stockRepository;
	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static SkillService skillService;
	private static AttemptService attemptService;

	@Autowired
	public void setStockRepository(StockRepository sRepo) {
		stockRepository = sRepo;
	}

	public static void main(String[] args) throws CountryNotFoundException {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		LOGGER.info("Inside main");

		// initializing service beans
		countryService = context.getBean(CountryService.class);
		employeeService = context.getBean(EmployeeService.class);
		departmentService = context.getBean(DepartmentService.class);
		skillService = context.getBean(SkillService.class);
		attemptService = context.getBean(AttemptService.class);

		/*
		 * testGetAllCountries(); 
		 * testFindCountryByCode(); 
		 * testAddCountry();
		 * testUpadateCountry(); 
		 * testDeleteCountry(); 
		 * testFindByNameLike();
		 * testStartsWith(); 
		 * testFindAllTillSep(); 
		 * testFindTop3ByOrderByVolumeDesc();
		 * testFindGoogle1250(); 
		 * testFindTop3ByCodeOrderByCloseAsc(); 
		 * testGetEmployee();
		 * testAddEmployee(); 
		 * testUpdateEmployee(); 
		 * testGetDepartment();
		 * testAddSkillToEmployee(); 
		 * testGetAllPermanentEmployees();
		 * testAttemptServiceGetAttempt();
		 * testGetAverageSalary(); 
		 * testGetAllEmployeesNative();
		 */
		
	}

	public static void testGetAllCountries() {
		LOGGER.info("Start");
		List<Country> countries = countryService.getAllCountries();
		LOGGER.debug("countries={}", countries);
		LOGGER.info("End");
	}

	public static void testFindCountryByCode() throws CountryNotFoundException {
		LOGGER.info("Start");
		Country country = countryService.findCountryByCode("IN");
		LOGGER.debug("Country:{}", country);
		LOGGER.info("End");
	}

	public static void testAddCountry() throws CountryNotFoundException {
		LOGGER.info("Start");
		Country country1 = new Country();
		country1.setCode("NC");
		country1.setName("New Country");
		countryService.addCountry(country1);
		Country country2 = countryService.findCountryByCode("NC");
		LOGGER.debug("Country:{}", country2);
		LOGGER.info("End");
	}

	public static void testUpadateCountry() throws CountryNotFoundException {
		LOGGER.info("Start");
		countryService.updateCountry("NC", "Newly Created Country");
		LOGGER.info("NC updated");
		Country country = countryService.findCountryByCode("NC");
		LOGGER.debug("Country:{}", country);
		LOGGER.info("End");
	}

	public static void testDeleteCountry() {
		LOGGER.info("Start");
		countryService.deleteCountry("NC");
		LOGGER.info("NC deleted");
		testGetAllCountries();
		LOGGER.info("End");
	}

	public static void testFindByNameLike() {
		LOGGER.info("Start");
		List<Country> countries = countryService.findByNameLike("%ou%");
		LOGGER.debug("Country:{}", countries);
		LOGGER.info("End");
	}

	public static void testStartsWith() {
		LOGGER.info("Start");
		List<Country> countries = countryService.startsWith('Z');
		LOGGER.debug("Country:{}", countries);
		LOGGER.info("End");
	}

	public static void testFindAllTillSep() {
		LOGGER.info("Start");
		List<Stock> stocks = stockRepository.findAllTillSep();
		LOGGER.debug("countries={}", stocks);
		LOGGER.info("End");
	}

	private static void testFindGoogle1250() {
		LOGGER.info("Start");
		List<Stock> findGoogle1250 = stockRepository.findGoogle1250();
		LOGGER.debug("stocks={}", findGoogle1250);
		LOGGER.info("End");
	}

	public static void testFindTop3ByOrderByVolumeDesc() {
		LOGGER.info("Start");
		List<Stock> stocks = stockRepository.findTop3ByOrderByVolumeDesc();
		LOGGER.debug("countries={}", stocks);
		LOGGER.info("End");
	}

	public static void testFindTop3ByCodeOrderByCloseAsc() {
		LOGGER.info("Start");
		String code = "NFLX";
		List<Stock> stocks = stockRepository.findTop3ByCodeOrderByCloseAsc(code);
		LOGGER.debug("countries={}", stocks);
		LOGGER.info("End");
	}

	private static void testGetEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Department:{}", employee.getDepartment());
		LOGGER.debug("Skills:{}", employee.getSkillList());
		LOGGER.info("End");
	}

	private static void testAddEmployee() {
		LOGGER.info("Start");
		Employee employee = new Employee();
		employee.setId(5);
		employee.setName("Akash");
		employee.setSalary(12000);
		employee.setPermanent(true);
		employee.setDateOfBirth(new Date());
		employee.setDepartment(departmentService.get(1));
		employeeService.save(employee);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.info("End");
	}

	private static void testUpdateEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(5);
		Department department = departmentService.get(2);
		employee.setDepartment(department);
		employeeService.save(employee);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.info("End");
	}

	public static void testGetDepartment() {
		LOGGER.info("Start");
		Department department = departmentService.get(3);
		LOGGER.debug("Department:{}", department);
		LOGGER.debug("Employees:{}", department.getEmployeeList());
		LOGGER.info("End");
	}

	public static void testAddSkillToEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(5);
		Skill skill = skillService.get(1);
		Set<Skill> skills = employee.getSkillList();
		skills.add(skill);
		employee.setSkillList(skills);
		employeeService.save(employee);
		LOGGER.info("End");
	}

	public static void testGetAllPermanentEmployees() {
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllPermanentEmployees();
		LOGGER.debug("Permanent Employees:{}", employees);
		employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
		LOGGER.info("End");
	}

	public static void testAttemptServiceGetAttempt() {
		LOGGER.info("Start");
		Attempt attempt = attemptService.getAttempt(1, 1);
		LOGGER.debug("Attempt:{}", attempt);
		Set<AttemptQuestion> attemptQuestionList = attempt.getAttemptQuestionList();
		LOGGER.debug("AttemptQuestions:{}", attemptQuestionList);
		attemptQuestionList.forEach((x) -> LOGGER.debug("AttemptQuestions:{}", x.getAttemptOptionList()));
		LOGGER.info("End");
	}

	public static void testGetAverageSalary() {
		LOGGER.info("Start");
		double sal = employeeService.getAverageSalary(1);
		LOGGER.debug("Average Salary:{}", sal);
		LOGGER.info("End");
	}

	public static void testGetAllEmployeesNative() {
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllEmployeesNative();
		LOGGER.debug("Employees:{}", employees);
		LOGGER.info("End");
	}
}