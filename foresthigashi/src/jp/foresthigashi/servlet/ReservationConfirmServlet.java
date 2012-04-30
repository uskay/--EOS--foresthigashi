package jp.foresthigashi.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.foresthigashi.model.ReservationValidater;
import jp.foresthigashi.util.ObjectConverter;
import jp.foresthigashi.util.TxtWriter;

/**
 * ReservationServlet
 */
public class ReservationConfirmServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;   
	private static final String CLASS_NAME = "ReservationConfirmServlet";
	
    /**
     * Constructor
     */
    public ReservationConfirmServlet() {
        super();
    }
    
	/**
	 * initialize
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	/**
	 * doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String METHOD_NAME = "doPost";
		
		TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, "start..", TxtWriter.LOG_INFO);
		
		request.setCharacterEncoding("utf-8");
		
		Properties prop = new ReservationValidater().validate(ObjectConverter.convert2Prop4HttpRequest(request.getParameterMap()));
		
		TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, prop.toString(), TxtWriter.LOG_INFO);
		
	    response.setCharacterEncoding("UTF-8");	    
	    response.getWriter().write(ObjectConverter.convert2JSON(prop));
	    
	    TxtWriter.writeLog(CLASS_NAME, METHOD_NAME, "end..", TxtWriter.LOG_INFO);

	}
}
