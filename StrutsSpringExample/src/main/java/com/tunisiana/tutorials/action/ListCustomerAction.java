package com.tunisiana.tutorials.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tunisiana.tutorials.bo.CustomerBo;
import com.tunisiana.tutorials.model.Customer;

@Component("/ListCustomer")
public class ListCustomerAction extends DispatchAction {
	
	@Autowired
	CustomerBo customerBo;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		DynaActionForm dynaCustomerListForm = (DynaActionForm) form;

		List<Customer> list = customerBo.findAllCustomer();

		dynaCustomerListForm.set("customerList", list);

		return mapping.findForward("success");

	}

}