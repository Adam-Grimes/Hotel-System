/**
 * Class: B.Sc. in Computing
 * Instructor: Maria Boyle
 * Description: Models an Employee
 * Date: dd/mm/yyyy
 * @author Adam Grimes
 * @version 1.0
**/
package ie.atu.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ie.atu.hotel.Employee;

public class EmployeeSerializer {
	private ArrayList<Employee> employees;
	
	private final String FILENAME = "employees.bin";	
	private File employeesFile;	
	
	
	// Default Constructor
	public EmployeeSerializer(){
		// Construct EmployeeList ArrayList
		employees = new ArrayList<Employee>();

		// TODO Construct employeesFile and physically create the File
		employeesFile = new File(FILENAME);
		
		try {
	            
	          if (!employeesFile.exists()) {
	                boolean fileCreated = employeesFile.createNewFile();
	                if (fileCreated) {
	                    System.out.println("File created: " + FILENAME);
	                } else {
	                    System.out.println("File already exists: " + FILENAME);
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("An error occurred while creating the file.");
	            e.printStackTrace();
	        }
		
	}	

	/////////////////////////////////////////////////////////////
	// Method Name : add()								              //
	// Return Type : void								              //
	// Parameters : None								                 //
	// Purpose : Reads one Employee record from the user       //
	//           and adds it to the ArrayList called employees //
	/////////////////////////////////////////////////////////////
	public void add(){ 
	    // Create an Employee object
		 Employee employee = new Employee();


		 // if an employee is read it will add them to the Array List
		 if(employee.read()==true)
			 employees.add(employee);
		 else
			 Employee.setNextNumber(Employee.getNextNumber() - 1);
	}

	///////////////////////////////////////////////////////
	// Method Name : list()						              //
	// Return Type : void					   	           //
	// Parameters : None						                 //
	// Purpose : Lists all Employee records in employees //
	///////////////////////////////////////////////////////		
	public void list(){
		String employeesToList="";

		if(!employees.isEmpty()) {
			// for every Employee object in employees
			for(Employee tmpEmployee : employees) {
				// add it to employeesToList and
				employeesToList+=tmpEmployee;
				// add a newline
				employeesToList+="\n";
			}
			
			// Display employeesToList in a messageDialog
		   JOptionPane.showMessageDialog(null, employeesToList, "EMPLOYEE LIST", JOptionPane.INFORMATION_MESSAGE);	
		}
		else
			// Display "No Employees stored..." in messageDialog
		   JOptionPane.showMessageDialog(null, "No Employees to list.", "EMPLOYEE LIST", JOptionPane.INFORMATION_MESSAGE);	
	}	

	////////////////////////////////////////////////////////////////
	// Method Name : view()									              //
	// Return Type : Employee								              //
	// Parameters : None								                    //
	// Purpose : Displays the required Employee record on screen  //
	//         : And returns it, or null if not found             //   
	////////////////////////////////////////////////////////////////	

	
	public Employee view() {
	    // Create a JTextField for employee number input
	    JTextField txtEmployeeNumber = new JTextField();
	    
	    Object[] message = {
	        "Enter Employee Number:", txtEmployeeNumber
	    };
	    
	    JDialog dialog = new JDialog();
	    dialog.setAlwaysOnTop(true);  // Keep the dialog on top
	    
	    // Show the dialog and capture the user's choice (OK or Cancel)
	    int option = JOptionPane.showConfirmDialog(dialog, message, "Input", JOptionPane.OK_CANCEL_OPTION);
	    
	    // If the user clicks OK, try to find the employee
	    if (option == JOptionPane.OK_OPTION) {
	        try {
	            // Get the employee number entered by the user
	            int employeeNumber = Integer.parseInt(txtEmployeeNumber.getText());

	            // Search for the employee in the list
	            for (Employee employee : employees) {
	                if (employee.getNumber() == employeeNumber) {
	                    // If found, display the employee details
	                    JOptionPane.showMessageDialog(dialog, employee, "Employee Found", JOptionPane.INFORMATION_MESSAGE);
	                    return employee;  // Return the found employee
	                }
	            }

	            // If employee not found, show a message
	            JOptionPane.showMessageDialog(dialog, "Employee not found.", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (NumberFormatException e) {
	            // If the input is not a valid number, show an error
	            JOptionPane.showMessageDialog(dialog, "Invalid employee number. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } 
	    // If user clicks Cancel or closes the dialog, do nothing and return null
	    return null;
	}



	
	
	///////////////////////////////////////////////////////////////////
	// Method Name : delete()							        	           //
	// Return Type : void								        	           //
	// Parameters : None									                    //
	// Purpose : Deletes the required Employee record from employees //
	///////////////////////////////////////////////////////////////////	
    public void delete() {
    	Employee employeeToDelete=view();
    	if(employeeToDelete != null)
    		employees.remove(employeeToDelete);
    }

	///////////////////////////////////////////////////////////////
	// Method Name : edit()			  					                //
	// Return Type : void								    	          //
	// Parameters : None								     	             //
	// Purpose : Edits the required Employee record in employees //
	///////////////////////////////////////////////////////////////	

	
    public void edit() {
    	Employee employeeToEdit=view();
    	if(employeeToEdit != null)	{
    		if (employeeToEdit.read() ==true) {
    			int index=employees.indexOf(employeeToEdit);
    			employees.set(index, employeeToEdit);	
    		}
    	}	
    }
	
	// This method will serialize the employees ArrayList when called, 
	// i.e. it will write employees to a file called employees.bin
	public void serializeEmployees(){

	
		try {
			// Serialize the ArrayList...
			FileOutputStream fileStream = new FileOutputStream(employeesFile);
		
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
				
			os.writeObject(employees);
			System.out.println("Employees serialized successfully.");
			os.close();
			
		}
		catch(FileNotFoundException fNFE){
			System.out.println("Cannot create file for employee.");
		}
		catch(IOException ioE){
			 System.out.println("Cannot write to " + FILENAME + ".");
		}	
		
	}

	// This method will deserialize the Employees ArrayList when called, 
	// i.e. it will restore the employees ArrayList from the file employees.bin
	public void deserializeEmployees() {
		 ObjectInputStream is=null;
		
		 try {
			 // Deserialize the ArrayList...
			 FileInputStream fileStream = new FileInputStream(employeesFile);
				
			 is = new ObjectInputStream(fileStream);
						
			 
			 employees = (ArrayList<Employee>)is.readObject();
	
			 if (employees.isEmpty()) {

				    Employee.setNextNumber(10000);
			 } 
			 else {
				
				    Employee lastEmployee = employees.get(employees.size() -1);
				    
				    // We then get their number and add 1 to it
				    int nextNumber = lastEmployee.getNumber() + 1;
				    
				    // Set the next number to the calculated value
				    Employee.setNextNumber(nextNumber);
				}


			 is.close();
		}
		catch(FileNotFoundException fNFE){
			 System.out.println("Cannot open file " + employeesFile.getName() + ".");
		}
		catch(IOException ioE){
			 System.out.println("Cannot read from " + employeesFile.getName() + ".");
		}
		catch(Exception e){
			 System.out.println("Cannot read from " + employeesFile.getName() + ".");
		}
	}

 
}