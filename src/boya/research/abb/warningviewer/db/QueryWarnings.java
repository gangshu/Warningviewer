package boya.research.abb.warningviewer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import boya.research.abb.warningviewer.ds.KlocworkWarning;

public class QueryWarnings {
    static Connection conn = null;

    public static void getConnection(String username) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@selserver.case.edu:1521:orcl", username,
                "andypodgurski");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static boolean isConnected(){
    	if (conn!=null)
    		return true;
    	else
    		return false;
    }
    
    
    public static void disConnection(){
    	try{
    		if(conn!=null)
    			conn.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    
    public static ArrayList<String> getCheckerNames() {
        String sql = "select distinct checker_name from klocwork_warnings where type = 'custom' and reviewer is not null order by checker_name";
        PreparedStatement ps = null;
        ResultSet rSet = null;
        ArrayList<String> ret = new ArrayList<String>();
        try {
            ps = conn.prepareStatement(sql);
            rSet = ps.executeQuery();
            while (rSet.next()) {
                ret.add(rSet.getString(1));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rSet != null)
                    rSet.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return ret;

    }
    
    public static ArrayList<String> getCheckerNamesByReviewer(String reviewer) {
        String sql = "select distinct checker_name from klocwork_warnings where type = 'custom' and reviewer = ? order by checker_name";
        PreparedStatement ps = null;
        ResultSet rSet = null;
        ArrayList<String> ret = new ArrayList<String>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, reviewer);
            rSet = ps.executeQuery();
            while (rSet.next()) {
                ret.add(rSet.getString(1));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rSet != null)
                    rSet.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return ret;

    }

    public static ArrayList<Integer> getWarningKeys(String checkerName) {
    	
        String sql = "select key from klocwork_warnings where checker_name = ? order by key";
        PreparedStatement ps = null;
        ResultSet rSet = null;
        ArrayList<Integer> ret = new ArrayList<Integer>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, checkerName);
            rSet = ps.executeQuery();
            while (rSet.next()) {
                ret.add(rSet.getInt(1));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rSet != null)
                    rSet.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return ret;
    }

    public static KlocworkWarning getWarning(int key) {        
        String sql = "select * from klocwork_warnings where key = ?";
        PreparedStatement ps = null;
        ResultSet rSet = null;
        KlocworkWarning warning = new KlocworkWarning();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, key);
            rSet = ps.executeQuery();
            if (!rSet.next()) {
                System.out.println("No klocwork warning with key " + key + " exists!");
                return null;
            }
            
            warning.key = rSet.getInt("key");
            warning.patternKey = rSet.getInt("pattern_key");
            warning.checkerName = rSet.getString("checker_name");
            warning.fileName = rSet.getString("filename");
            warning.type = rSet.getString("type");
            warning.functionName = rSet.getString("functionname");
            warning.line = rSet.getInt("line");
            warning.col = rSet.getInt("col");
            warning.message = rSet.getString("message");
            warning.confirm = rSet.getString("confirm");
            warning.reviewer = rSet.getString("reviewer");
            warning.comments = rSet.getString("comments");
            
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rSet != null)
                    rSet.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return warning;
    }

    public static int updateWarningFeedback(int key, String confirm, String comments) {        
        String sql = "update klocwork_warnings set confirm = ?, comments = ? where key = ?";
        PreparedStatement ps = null;
        ResultSet rSet = null;
        int ret = 0;
        try {
            ps = conn.prepareStatement(sql);            
            ps.setString(1, confirm);
            ps.setString(2, comments); 
            ps.setInt(3, key);
            ret = ps.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rSet != null)
                    rSet.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return ret;
    }
    
    public static int insertWarning(KlocworkWarning warning){
        String sql = "insert into klocwork_warnings (key, pattern_key, checker_name, filename, type, functionname, line, col, message)values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        ResultSet rSet = null;
        int ret = 0;
        try {
            ps = conn.prepareStatement(sql);            
            ps.setInt(1, warning.key);
            ps.setInt(2, warning.patternKey); 
            ps.setString(3, warning.checkerName);
            ps.setString(4, warning.fileName);
            ps.setString(5, warning.type); 
            ps.setString(6, warning.functionName);
            ps.setInt(7, warning.line);
            ps.setInt(8, warning.col); 
            ps.setString(9, warning.message); 
            ret = ps.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rSet != null)
                    rSet.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return ret;
    }
    
    public static int maxKey(){
        String sql = "select max(key) from klocwork_warnings";
        PreparedStatement ps = null;
        ResultSet rSet = null;
        int ret = 0;
        try {
            ps = conn.prepareStatement(sql);            
            rSet = ps.executeQuery();
            if(!rSet.next())
                return 0;
            ret = rSet.getInt(1);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rSet != null)
                    rSet.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return ret;
    }
    
    public static ArrayList<String> getReviewers(){
        String sql = "select distinct reviewer from klocwork_warnings where reviewer is not null order by reviewer";
        PreparedStatement ps = null;
        ResultSet rSet = null;
        ArrayList<String> ret = new ArrayList<String>();
        try {
            ps = conn.prepareStatement(sql);            
            rSet = ps.executeQuery();
            while(rSet.next()){
                ret.add(rSet.getString(1));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rSet != null)
                    rSet.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return ret;
    }
    
    public static void main(String[] args){
        QueryWarnings.getConnection("ac800m");
        ArrayList<String> checkerNames = QueryWarnings.getCheckerNames();
        
        System.out.println("Test: getCheckerNames");
        for(String checkerName : checkerNames){
            System.out.println(checkerName);
        }
        
        ArrayList<Integer> warningKeys = QueryWarnings.getWarningKeys("checker_1");
        System.out.println("Test: getWarningKeys");
        for(Integer warningKey : warningKeys){
            System.out.println(warningKey);
        }
        
        QueryWarnings.updateWarningFeedback(3, "Y", "Commenst for warning 3");
        
        KlocworkWarning warning = QueryWarnings.getWarning(6);
        System.out.println("Test: getWarning");
        System.out.println("Key: " + warning.key);
        System.out.println("patternKey: " + warning.patternKey);
        System.out.println("checkerName: " + warning.checkerName);
        System.out.println("type: " + warning.type);
        System.out.println("functionName: " + warning.functionName);
        System.out.println("line: " + warning.line);
        System.out.println("col: " + warning.col);
        System.out.println("message: " + warning.message);
        System.out.println("confirm: " + warning.confirm);
        System.out.println("reviewer: " + warning.reviewer);
        System.out.println("comments: " + warning.comments);
        
        System.out.println("Test getReviewers");
        ArrayList<String> reviewers = getReviewers();
        for(String reviewer:reviewers){
            System.out.println(reviewer);
        }
        
        System.out.println("Test getCheckerName by Reviewers");
        ArrayList<String> checkerNames2 = getCheckerNamesByReviewer("gang");
        for(String checkerName : checkerNames2){
            System.out.println(checkerName);
        }
        
        System.out.println("Test check maximum key");
        System.out.println(QueryWarnings.maxKey());
        warning.key = QueryWarnings.maxKey() + 1;
        System.out.println("Test insertion");
        System.out.println(QueryWarnings.insertWarning(warning));
    }
    

}
