package com.tunisiana.tutorials.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tunisiana.tutorials.bo.CustomerBo;
import com.tunisiana.tutorials.model.Customer;

/**
 * 
 * @description:
 * @author: oussama.zoghlami.ext
 * @date: 09/01/2011
 * 
 */
@Component("/ListCustomerPDF")
@SuppressWarnings("unchecked")
public class JasperListCustomerAction extends DispatchAction {

	@Autowired
	CustomerBo customerBo;

	/**
	 * This method will get the customer list and format them to a pdf file
	 * (through jasper report)
	 * 
	 * @return
	 * @throws Exception
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			// parameter map
			Map params = new HashMap();

			// get the customer list
			List<Customer> customers = customerBo.findAllCustomer();

			// prepare the customer list datasource
			JRBeanCollectionDataSource customersDS = new JRBeanCollectionDataSource(customers);

			// load the compiled jasper design file
			File reportFile = new File(getServlet().getServletContext().getRealPath(
					"/jasper/customerListTemplate.jasper"));
			byte[] byteStream = JasperRunManager.runReportToPdf(reportFile.getPath(), params,
					customersDS);

			// prepare the http response
			response.setContentType("application/pdf");
			response.setContentLength(byteStream.length);
			ServletOutputStream servletOutputStream = response.getOutputStream();
			servletOutputStream.write(byteStream, 0, byteStream.length);
			servletOutputStream.flush();
			servletOutputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return mapping.findForward("error");
		}

		return null;
	}

}
