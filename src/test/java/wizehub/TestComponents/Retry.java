package wizehub.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//retry executing failed test cases to check if flaky tests (false positive) are present

public class Retry implements IRetryAnalyzer{
//if test fails it also comeshere after listner to ask should i run again to check if i am flaky
	//if we want to add it to test there is keyword to mention in test method
	int count=0;
	int maxTry=1;
	
	@Override
	public boolean retry(ITestResult result) //result will contain all the method information which came for rerun
	{
		if(count<maxTry)   //if max try 2 then it will run in this loop 2 times so 2 times rerun
		{	count++;
			return true;
		}
		return false;  //the moment it comes to this line returns false the retrying will stop 
	}
	
	

}
