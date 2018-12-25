package edu.sollers.javaprog.CalendarDemo;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author rutpatel
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/CalendarController")
@Controller
public class CalendarController {

	@GetMapping
	public ModelAndView doGet() {
		return new ModelAndView("home");
	}

	@PostMapping
	public void  doPost(HttpServletRequest request, HttpServletResponse response) {
		
		int year = Integer.parseInt(request.getParameter("year"));
		int refYear = 1980;
		int count = 0 ;
		int lYear = 28;
		String[] dayArr = {"Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday","Monday"};
		
		while(refYear < year){
			if(isLeap(refYear)) {
				count++;
			}
			count++;
			refYear++;
		}
		if(count > 6){
			count = count % 7;
		}
		
		if(isLeap(year)){
			lYear = 29;
		}
		
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<h1><u>Calendar of " + year + "</u></h1>");
			
			int jan = count, feb = ((jan+31)%7), mar = ((feb+lYear)%7), apr = ((mar+31)%7), may = ((apr+30)%7), jun = ((may+31)%7),
					jul = ((jun+30)%7), aug = ((jul+31)%7), sep = ((aug+31)%7), oct = ((sep+30)%7), nov = ((oct+31)%7), dec = ((nov+30)%7);
			
			out.print("<div><b>1st Jan " + dayArr[jan] + "</b></div>");
			out.print("<div><b>1st Feb " + dayArr[feb] + "</b></div>");
			out.print("<div><b>1st Mar " + dayArr[mar] + "</b></div>");
			out.print("<div><b>1st Apr " + dayArr[apr] + "</b></div>");
			out.print("<div><b>1st May " + dayArr[may] + "</b></div>");
			out.print("<div><b>1st Jun " + dayArr[jun] + "</b></div>");
			out.print("<div><b>1st Jul " + dayArr[jul] + "</b></div>");
			out.print("<div><b>1st Aug " + dayArr[aug] + "</b></div>");
			out.print("<div><b>1st Sep " + dayArr[sep] + "</b></div>");
			out.print("<div><b>1st Oct " + dayArr[oct] + "</b></div>");
			out.print("<div><b>1st Nov " + dayArr[nov] + "</b></div>");
			out.print("<div><b>1st Dec " + dayArr[dec] + "</b></div>");
			
			out.print("<br><h1><u>Federal Holiday of " + year + "</u></h1>");
			
			int ny = jan, mlk = (7-((jan+14)%7))+14, pd = (7-((feb+14)%7))+14, md = (7-((may+23)%7))+23, id = ((jul+3)%7), ld = (7-sep),
					cld = (7-oct), td = (4-((nov+23)%7))+22, cd = ((dec+24)%7);
			
			int[] fullMoon = {414,43,323,411,331,418,48,328,416,45,325,413,42,322,410,330,417,47,327};
			int remainder = ((year+1)%19);
			int fullMoonDay = fullMoon[remainder-1];
			int mR = Integer.parseInt(String.valueOf(fullMoonDay).substring(0,1));
			int dR = Integer.parseInt(String.valueOf(fullMoonDay).substring(1));
			int month;
			String mName;
			
			if(mR == 3) {
				month = mar;
				mName = "Mar";
			}
			else {
				month = apr;
				mName = "Apr";
			}
			
			dR--;	//month already pointing to 1
			int ed = (6-((month+dR)%7))+dR;
			
			if(ed > 31) {
				mName = "Apr";
				ed = (6-apr);
			}
			
			out.print("<div>New Year Day----------------<b>1 Jan " + dayArr[ny] + "</b></div>");
			out.print("<div>Martin Luther King Day----<b>" + mlk + " Jan Monday</b></div>");
			out.print("<div>President Day-----------------<b>" + pd + " Feb Monday</b></div>");
			out.print("<div>Easter Day--------------------<b>" + ed + " " + mName + " Sunday</b></div>");
			out.print("<div>Memorial Day----------------<b>" + md + " May Monday</b></div>");
			out.print("<div>Independence Day-----------<b>4 Jul " + dayArr[id] + "</b></div>");
			out.print("<div>Labor Day--------------------<b>" + ld + " Sep Monday</b></div>");
			out.print("<div>Columbus Day---------------<b>" + cld + " Oct Monday</b></div>");
			out.print("<div>ThanksGiving Day----------<b>" + td + " Nov Thursday</b></div>");
			out.print("<div>Christmas Day---------------<b>25 Dec " + dayArr[cd] + "</b></div>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	public boolean isLeap(int refYear) {
		if((refYear % 4) == 0) {
			if((refYear % 100) == 0) {
				if((refYear % 400) == 0) {
					return true;
				}
				else{
					return false;
				}
			}
			else {
				return true;
			}
		}
		return false;
	}
}