package com.person.budget.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.budget.entity.Expenditure;
import com.person.budget.services.RecordExpenditure;
import com.person.budget.utils.JedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Objects;

@RestController
@RequestMapping("/budget/api")
public class RecordController {

    private static RecordExpenditure recordExpenditure;
    private static JedisUtils        jedisUtils;
    @Autowired
    private void setRecordExpenditure(RecordExpenditure recordExpenditure){
        RecordController.recordExpenditure = recordExpenditure;
    }
    @Autowired
    private void setJedisUtils(JedisUtils jedisUtils){
        RecordController.jedisUtils = jedisUtils;
    }

    @PostMapping("/recording")
    @ResponseBody
    public int returnTest(@RequestBody String seriazingString) throws SQLException, JsonProcessingException, ParseException {
        System.out.println("Input string: ======> " + "\"" + seriazingString + "\"");
        ObjectMapper mapper = new ObjectMapper();
        String trans = seriazingString.replace("\\","");
        Expenditure expenditure = mapper.readValue(trans, Expenditure.class);
        recordExpenditure.insertBudgetTable(expenditure);
        jedisUtils.insert2Redis("getRecords",recordExpenditure.getBudgetList(null));
        jedisUtils.insert2Redis("getbiset",recordExpenditure.getBIDataSet());
        return 1;
    }

    @PostMapping("/getRecords")
    @ResponseBody
    public String getAllRecords() throws SQLException, JsonProcessingException{
        String result = null;
        if (Objects.isNull(jedisUtils.get2Redis("getRecords"))){
            result = recordExpenditure.getBudgetList(null);
            jedisUtils.insert2Redis("getRecords",result);
        }else{
            result = jedisUtils.get2Redis("getRecords");
        }
        return  result;
    }

    @PostMapping("/getdicts")
    @ResponseBody
    public String getAllDicts() throws SQLException, JsonProcessingException{
        String result = null;
        if(Objects.isNull(jedisUtils.get2Redis("getdicts"))){
            result = recordExpenditure.getDictionaryList();
            jedisUtils.insert2Redis("getdicts",result);
        }else{
            result = jedisUtils.get2Redis("getdicts");
        }
//        return recordExpenditure.getDictionaryList();
        return result;
    }

    @PostMapping("/getbiset")
    @ResponseBody
    public String getBiDataSet() throws SQLException, JsonProcessingException{
        String result = null;
        if(Objects.isNull(jedisUtils.get2Redis("getbiset"))){
            result = recordExpenditure.getBIDataSet();
            jedisUtils.insert2Redis("getbiset",result);
        }else{
            result = jedisUtils.get2Redis("getbiset");
        }
//        return recordExpenditure.getBIDataSet();
        return result;
    }
}
