package customlistner;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyTestNGListner implements ITestListener {
	
	public void onStart(ITestContext arg0) {					
        			
    	System.out.println("Suite onStart"+arg0.getName());	
    }		
	
	public void onFinish(ITestContext arg0) {					
        System.out.println("Suite onFinish"+arg0.getName());			
        		
    }		
	 public void onTestStart(ITestResult arg0) {					
	    System.out.println("Result onTestStart"+" "+arg0.getName());			
	        		
	}	

	 public void onTestFailure(ITestResult arg0) {					
		System.out.println("Result onTestFailure"+" "+arg0.getName());				
	        		
	}
	 public void onTestSkipped(ITestResult arg0) {					
		 System.out.println("Result onTestSkipped"+" "+arg0.getName());  
    }
	 
	 public void onTestSuccess(ITestResult arg0) {					
		 System.out.println("Result onTestSuccess"+" "+arg0.getName());
	        		
	 }	
	 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		 System.out.println("Result onTestSuccess"+" "+result.getName());   

	 }

}
