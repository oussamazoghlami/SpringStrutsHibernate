package com.tunisiana.tutorials.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tunisiana.tutorials.bo.CustomerBo;
import com.tunisiana.tutorials.form.CustomerForm;
import com.tunisiana.tutorials.model.Customer;

@Component("/AddCustomer")
public class AddCustomerAction extends DispatchAction {
	
	@Autowired
	CustomerBo customerBo;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CustomerForm customerForm = (CustomerForm) form;
		Customer customer = new Customer();

		// copy customerform to model
		BeanUtils.copyProperties(customer, customerForm);

		// save the customer
		customerBo.addCustomer(customer);
		return mapping.findForward("success");

	}

}