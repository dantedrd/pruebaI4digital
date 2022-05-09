package com.i4digital.i4digitalTest.util;

import com.i4digital.i4digitalTest.costants.TypeRisk;
import com.i4digital.i4digitalTest.dto.PatientResultDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;


@Component
public class ValidInfoPatient {

    private final Double MAX_SUGAR=70.0;
    private final Double MAX_FAT=88.5;
    private final Double MAX_OXYGEN=60.0;

    private final String MEDIUM_SUGAR="50,70";
    private final String MEDIUM_FAT="62.2,88.5";
    private final String MEDIUM_OXYGEN="60,70";

    private final Double LOW_SUGAR=50.0;
    private final Double LOW_FAT=62.2;
    private final Double LOW_OXYGEN=70.0;

    private final String ERROR_RANGE="Los valores suministrados no son correctos para los % deben ser [0-100]";


    public TypeRisk ValidTypeRisk(Double sugar,Double fat,Double oxygen){
        TypeRisk risk=null;
        if(isHigh(sugar,fat,oxygen)){
            risk=TypeRisk.HIGH;
        }
        if(isMedium(sugar,fat,oxygen)){
            risk=TypeRisk.MEDIUM;
        }
        if(isLow(sugar,fat,oxygen)){
            risk=TypeRisk.Low;
        }
        return risk;
    }

    public HashMap<Integer,String> validInfoPercents(ArrayList<PatientResultDto> patientResults){
        return patientResults
                .stream()
                .filter(patientResultDto ->
                        (patientResultDto.getSugar()>100||patientResultDto.getSugar()<0)||
                        (patientResultDto.getFat()>100||patientResultDto.getFat()<0)||
                        (patientResultDto.getOxygen()>100||patientResultDto.getOxygen()<0))
                .collect(HashMap::new, (m,v)->m.put(v.getDni(),ERROR_RANGE), HashMap::putAll);
    }

    private boolean isHigh(Double sugar,Double fat,Double oxigen){
        return  sugar>MAX_SUGAR && fat>MAX_FAT&&oxigen<MAX_OXYGEN;
    }

    private boolean isMedium(Double sugar,Double fat,Double oxygen){
        int isSugarMediumLess=Double.compare(sugar,Double.parseDouble(MEDIUM_SUGAR.split(",")[0]));
        int isSugarMediumHigh=Double.compare(sugar,Double.parseDouble(MEDIUM_SUGAR.split(",")[1]));

        int isFatMediumLess=Double.compare(sugar,Double.parseDouble(MEDIUM_FAT.split(",")[0]));
        int isFatMediumHigh=Double.compare(sugar,Double.parseDouble(MEDIUM_FAT.split(",")[1]));

        int isOxygenMediumLess=Double.compare(sugar,Double.parseDouble(MEDIUM_OXYGEN.split(",")[0]));
        int isOxygenMediumHigh=Double.compare(sugar,Double.parseDouble(MEDIUM_OXYGEN.split(",")[1]));

       return (isSugarMediumLess>0&&isSugarMediumHigh<0)
               &&(isFatMediumLess>0&&isFatMediumHigh<0)
               &&(isOxygenMediumLess>0&&isOxygenMediumHigh<0);

    }

    private boolean isLow(Double sugar,Double fat,Double oxygen){
        return  sugar<LOW_SUGAR && fat<LOW_FAT&&oxygen>LOW_OXYGEN;
    }




}
