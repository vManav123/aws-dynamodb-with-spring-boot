package com.aws.dynamodb.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.aws.dynamodb.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public String save(Employee employee)
    {
        dynamoDBMapper.save(employee);
        return "Employee Detail Saved in the Database successfully + "+employee.getEmployeeId();
    }

    public Employee getEmploy(String employeeId)
    {
        return dynamoDBMapper.load(Employee.class,employeeId);
    }

    public String deleteEmployee(String employeeId)
    {
        dynamoDBMapper.delete(dynamoDBMapper.load(Employee.class,employeeId));
        return "Employee details deleted Successfully";
    }

    public String updateEmployee(Employee employee)
    {
        if(dynamoDBMapper.load(Employee.class,employee.getEmployeeId())!=null)
            dynamoDBMapper
                    .save(Employee.class,new DynamoDBSaveExpression()
                    .withExpectedEntry("employeeId",new ExpectedAttributeValue(new AttributeValue().withS(employee.getEmployeeId()))
                    ));
        return "Employee Data Updated Successfully";
    }
}
