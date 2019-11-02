package Seleniumpracticeprogs;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener extends base implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
  //As we need only failed test cases,we are writing methods here.
//testNg stores all the testcase results in "result" reference and from that we get method,and method name	
	public void onTestFailure(ITestResult result) {
		// As it is extending base class,we are writing failed() here..
	System.out.println("Failed Test");	
//By writing like this,TestNg listner will read that particular method if it is failed,
//it will print this statement and calls the failed(),it will go to base class-->failed(),and
//takes screenshot. getmethod(),getmethodname() gives screenshot of failed testmethods with
//	method name
	failed(result.getMethod().getMethodName());
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
