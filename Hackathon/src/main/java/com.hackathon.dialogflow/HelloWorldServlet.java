package com.hackathon.dialogflow;


import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2.QueryResult;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.google.api.gax.rpc.ApiException;
//import com.google.cloud.dialogflow.v2.QueryResult;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	static String jsonPath="\"C:\\Users\\hanlu.feng\\git\\dialogflowfile\\PlantesBot-df051857c11c.json\"";
	static String jsonPath="C:/Users/hanlu.feng/git/dialogflowfile/PlantesBot-df051857c11c.json";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");  
        PrintWriter out = resp.getWriter();  
        out.println("hello world,servlet2");
        System.out.println("This is a log");
        out.close();  
        sendData2DailogFlow();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public void sendData2DailogFlow(){


		String text="what is the color of earth ";
		String countrycode ="en-US";
		String projectId="plantesbot-pcnpcj";
		String sessionID="123456789";
		List<String>texts = new ArrayList<>();
		texts.add(text);
		try {
//			DetectIntentTexts.authExplicit(jsonPath);
			Map<String, QueryResult> map = DetectIntentTexts.detectIntentTexts(projectId, texts, sessionID, countrycode);
		} catch (ApiException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
