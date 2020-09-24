package com.hackathon.dialogflow;


import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	Gson mGson = new Gson();

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
		Map<String, QueryResult> map = sendData2DailogFlow();
		out.println("Conneting....");
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		if(map.size()>0){
			for(String key: map.keySet()){
				sb.append(key+" : "+map.get(key).getIntent().getDisplayName());
				sb.append("\n");
			}
		}
		out.println(sb.toString());
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestData = request.getReader().lines().collect(Collectors.joining());
		DialogFlowRequest clientRequest = mGson.fromJson(requestData,DialogFlowRequest.class);
		String command = clientRequest.getKey();

		System.out.println("Receive data is "+"\n"+command);
		PrintWriter out = response.getWriter();
		Map<String, QueryResult> map = sendData2DailogFlow(command);
//		out.println("Conneting....");
		StringBuilder sb = new StringBuilder();
//		sb.append("\n");
		if(map.size()>0){
			for(String key: map.keySet()){
				sb.append(key+" : "+map.get(key).getIntent().getDisplayName());
//				sb.append("\n");
			}
		}
		System.out.println("send to client: "+sb.toString());
		out.println(sb.toString());
		out.close();
	}

	public Map<String, QueryResult> sendData2DailogFlow(){


		String text="Please go back ";
		String countrycode ="en-US";
//		String projectId="plantesbot-pcnpcj";
		String projectId="hackathon-otpj";
		String sessionID="123456789";
		List<String>texts = new ArrayList<>();
		texts.add(text);
		Map<String, QueryResult> map =null;
		try {
//			DetectIntentTexts.authExplicit(jsonPath);
			map = DetectIntentTexts.detectIntentTexts(projectId, texts, sessionID, countrycode);
		} catch (ApiException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return map;
	}


	public Map<String, QueryResult> sendData2DailogFlow(String text){


//		String text="Please go back ";
		String countrycode ="en-US";
//		String projectId="plantesbot-pcnpcj";
		String projectId="hackathon-otpj";
		String sessionID="123456789";
		List<String>texts = new ArrayList<>();
		texts.add(text);
		Map<String, QueryResult> map =null;
		try {
//			DetectIntentTexts.authExplicit(jsonPath);
			map = DetectIntentTexts.detectIntentTexts(projectId, texts, sessionID, countrycode);
		} catch (ApiException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return map;
	}
}
