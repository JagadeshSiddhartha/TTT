package com.ttt;

import java.io.IOException;
import java.io.PrintWriter;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.*;
import java.util.*;
import java.io.*;

@WebServlet("/scrapeIt")
public class scrapeIt extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public scrapeIt() {
        super();
    }
    
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        
        Collections.reverse(list);

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");  
		    PrintWriter out = response.getWriter();  
		    out.print("<head>\n" + 
		    		"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" + 
		    		"</head>");
		    out.print("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
		    out.print("<div class=\"container\">\n" +
		    		"   <h2>Enter how many top used words you want to see</h2>" +
		    		"	<form action=\"scrapeIt\" method=\"post\">  \n" + 
		    		"		Count: <input type=\"text\" name=\"count\"/><br/><br/>  \n" + 
		    		"		<input type=\"submit\" value=\"register\"/>\n" + 
		    		"	</form>  \n" + 
		    		"</div>");
		    String countString=request.getParameter("count");
		    if (!countString.matches("[0-9]+")) {
		    	out.print("This is an invalid input, Please give another one");
		    }
		    else {
			    
			    int count = Integer.parseInt(countString);
			    if(count<=0) {
			    	out.print("This is an invalid input, Please give another one");
			    }
			    else if(count >16) {
			    	out.print("Total ranks are only 16, so enter in range of 1 to 16");
			    }
				else {
				    String url = "http://terriblytinytales.com/test.txt";
				    URL oracle = new URL(url);
			        URLConnection yc = oracle.openConnection();
			        BufferedReader in = new BufferedReader(new InputStreamReader(
			                                yc.getInputStream()));
			        String inputLine,dataString="";
			        while ((inputLine = in.readLine()) != null)
			        	dataString += inputLine;
			        String[] data = dataString.split(" ");
			        
			        Set<Integer> count_set = new HashSet<Integer>();
			        Map<String,Integer> word_map=new HashMap<String,Integer>();
			        
			        
			        for(int i=0;i<data.length;i++) {
			        	int val =1;
			        	if(word_map.containsKey(data[i])) {
			        		val = word_map.get(data[i])+1;
			        	}
			        	word_map.put(data[i], val);
			        }
			        
			        word_map = sortByValue(word_map);
			        
			        out.println("<br>");
			        out.print("<div class=\"container\">" + 
	        				"<table class=\"table\">" +
	        					"<thead>\n" + 
	        					"      <tr>\n" + 
	        					"        <th>Word</th>\n" + 
	        					"        <th>Count</th>\n" + 
	        					"      </tr>\n" + 
	        					"    </thead>");
			        out.print("<tbody>\n" + 
			        		"      <tr>");
			        for(Map.Entry m:word_map.entrySet()){  
			            count_set.add((Integer)m.getValue());
			            if(count_set.size()>count) break;
			            out.println("<tr>\n" + 
			            		"        <td>" + m.getKey() + "</td>\n" + 
			            		"        <td>" + m.getValue()+ "</td>\n" + 
			            		"      </tr>");
			        }
			        out.print("</tbody>\n" + 
			        	"  </table>"+
			        "</div>");
			        in.close();
				 }
		    }
	}
}
