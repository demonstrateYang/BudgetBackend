package com.person.budget.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.budget.entity.PersonalDictionary;
import com.person.budget.entity.Expenditure;
import com.person.budget.stability.PgConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
public class RecordExpenditure {

    private static PgConnect pgConnect;
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private void setPgConnect(PgConnect pgConnect){
        RecordExpenditure.pgConnect = pgConnect;
    }
    public void insertBudgetTable(Expenditure expenditure) throws ParseException, SQLException {

        Connection connection;
        connection = pgConnect.getConnection();
        PreparedStatement st = connection.prepareStatement("INSERT INTO expenditure_record (id,cost,name,categoryid,cost_time) VALUES (?,?,?,?,?)");
        st.setObject(1,expenditure.getId());
        st.setObject(2,expenditure.getCost());
        st.setObject(3,expenditure.getName());
        st.setObject(4,expenditure.getCategoryid());
//        st.setNull(4,-5);
        st.setTimestamp(5, new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(expenditure.getCost_time()).getTime()));
        st.executeUpdate();
        st.close();
    }

    public String getBudgetList(String date) throws SQLException, JsonProcessingException {
        Connection connection;

        connection = pgConnect.getConnection();

        String query_Sql = "select er.id,er.cost,er.name,er.categoryid,er.cost_time,pd.imagename from expenditure_record er " +
                "left join personal_dictionary pd on pd.dictionaryid = er.categoryid" +
                " where 1=1 and to_char(cost_time,'YYYY-MM-DD') = ? order by er.cost_time desc";
        PreparedStatement st = connection.prepareStatement(query_Sql);
        st.setString(1, String.valueOf(LocalDate.now()));
//        st.setString(1, "2023-09-24");
//        st.setString(1,);
        ResultSet rs = st.executeQuery();
        List<Expenditure> list = new ArrayList<>();
        while (rs.next()){
            Expenditure expenditure = new Expenditure();
            expenditure.setId(rs.getLong(1));
            expenditure.setCost(rs.getFloat(2));
            expenditure.setName(rs.getString(3));
            expenditure.setCost_time(rs.getString(5));
            expenditure.setImagename(rs.getString(6));
            list.add(expenditure);
        }
        rs.close();
        st.close();
        System.out.println(mapper.writeValueAsString(list));
        return mapper.writeValueAsString(list);
    }

    public String getDictionaryList()throws SQLException, JsonProcessingException{
        Connection connection;

        connection = pgConnect.getConnection();

        String query_Sql = "select dictionaryid,dictionaryname,dictionarycode,imagename,id from personal_dictionary where 1=1";
        PreparedStatement st = connection.prepareStatement(query_Sql);
        ResultSet rs = st.executeQuery();
        List<PersonalDictionary> list = new ArrayList<>();
        while (rs.next()){
            PersonalDictionary dictionary = new PersonalDictionary();
            dictionary.setDictionaryid(rs.getLong(1));
            dictionary.setDictionaryname(rs.getString(2));
            dictionary.setDictionarycode(0L);
            dictionary.setImagename(rs.getString(4));
            dictionary.setId(rs.getLong(5));
            list.add(dictionary);
        }
        rs.close();
        st.close();
        System.out.println(mapper.writeValueAsString(list));
        return mapper.writeValueAsString(list);
    }

    public String getBIDataSet()throws SQLException, JsonProcessingException{
        Connection connection;

        connection = pgConnect.getConnection();

        //获取当前月份消费Chart DataSet
        String query_Sql = "select a.*,pd.dictionaryname,pd.color from (\n" +
                "select SUM(round(er.cost::numeric,2)) AS cost,er.categoryid from expenditure_record er \n" +
                "where 1=1 and  er.cost_time BETWEEN  date_trunc('MONTH', CURRENT_DATE) AND  date_trunc('MONTH', CURRENT_DATE + INTERVAL '1 month') - INTERVAL '1 S' GROUP BY er.categoryid \n" +
                ") a left join personal_dictionary pd \n" +
                "on pd.dictionaryid = a.categoryid ORDER BY A.cost DESC";
        PreparedStatement st = connection.prepareStatement(query_Sql);
        ResultSet rs = st.executeQuery();
        List<Expenditure> list = new ArrayList<>();
        while (rs.next()){
            Expenditure expenditure = new Expenditure();
            expenditure.setId(1L);
            expenditure.setCost(rs.getFloat(1));
            expenditure.setName(rs.getString(3));
            expenditure.setCost_time("");
            expenditure.setImagename("");
            list.add(expenditure);
        }
        rs.close();
        st.close();
        System.out.println(mapper.writeValueAsString(list));
        return mapper.writeValueAsString(list);
    }
}
