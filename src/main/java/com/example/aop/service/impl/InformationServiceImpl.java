package com.example.aop.service.impl;

import com.example.aop.annotation.LogMethodExecution;
import com.example.aop.service.InformationService;
import org.springframework.stereotype.Service;

@Service
public class InformationServiceImpl implements InformationService {

    @LogMethodExecution
    @Override
    public String displayInformation() {

        StringBuilder builder = new StringBuilder();
        builder.append("Displaying information: ");
        builder.append(System.getProperty("line.separator"));
        builder.append("Liverpool 6 times Champions league winner ");
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }

    @Override
    public String getReturnValue() {
        System.out.println("getReturnValue() is running ");
        displayInformation();
        return "Pogba is an average player";
    }

    @Override
    public void throwException() throws Exception {
        System.out.println("getThrowException() is running ");
        throw new Exception("Man utd no longer big team Error");
    }

}
